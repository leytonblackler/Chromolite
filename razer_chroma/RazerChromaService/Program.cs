using Corale.Colore.Core;
using ColoreColor = Corale.Colore.Core.Color;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Drawing;
using System.Threading;
using Corale.Colore.Razer.Keyboard;
using Corale.Colore.Razer.Mouse;
using Corale.Colore.Razer.Mousepad;
using System.Collections;

namespace RazerChromaService
{
    static class Program
    {

        static int port = 8888;

        static bool initialised = false;

        static NotifyIcon trayIcon;
        
        [STAThread]
        static void Main()
        {
            Thread service = new Thread(RunService);
            service.Start();
            trayIcon = InitialiseTrayIcon();
        }

        private static void RunService()
        {
            //Create the buffer for the received data to be stored in.
            byte[] data = new byte[1024];
            //
            IPEndPoint endpoint = new IPEndPoint(IPAddress.Any, port);
            //Create the socket that the data will be received on.
            Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
            //Bind the socket to the endpoint.
            socket.Bind(endpoint);
            //
            EndPoint remote = (EndPoint)endpoint;

            //Loop indefinitely so that data can be received at any given time.
            while (true)
            {
                //Receive data and store it in the data byte array.
                int receive = socket.ReceiveFrom(data, ref remote);
                //Convert the data from a byte array into a string.
                string command = Encoding.ASCII.GetString(data, 0, receive);
                //Parse the command.
                ParseCommand(command);
            }
        }

        private static NotifyIcon InitialiseTrayIcon()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            NotifyIcon trayIcon = new NotifyIcon();
            ContextMenu menu = new ContextMenu();
            MenuItem title = new MenuItem();
            MenuItem exitOption = new MenuItem();
            menu.MenuItems.AddRange(new MenuItem[] { title, exitOption });
            string titleText = "Chromolite Razer Chroma SDK Service";
            title.Index = 0;
            title.Text = titleText;
            title.Enabled = false;
            exitOption.Index = 1;
            exitOption.Text = "Exit";
            exitOption.Click += new EventHandler(Exit);
            trayIcon.Icon = Properties.Resources.icon;
            trayIcon.Text = titleText;
            trayIcon.ContextMenu = menu;
            trayIcon.Visible = true;
            Application.Run();
            return trayIcon;
        }

        private static void Exit(object Sender, EventArgs e)
        {
            trayIcon.Visible = false;
            Uninitialise();
            Environment.Exit(0);
        }

        static void ParseCommand(string command)
        {
            //Console.WriteLine("Received command: [" + command + "]");

            string token = GetNextToken(command);
            command = RemoveToken(command);

            if (token.Equals("setsingledevices"))
            {
                //Split the string into an array of strings, for each of the rgb values.
                string[] values = command.Split(null);
                //Convert the strings for each rgb value into integers.
                int r = Convert.ToInt32(values[0]);
                int g = Convert.ToInt32(values[1]);
                int b = Convert.ToInt32(values[2]);
                //Set the color defined by the rgb values.
                SetSingleDevices(r, g, b);
            }

            else if (token.Equals("setkeyboard"))
            {
                SetKeyboardLayout(ParseLayout(command, Corale.Colore.Razer.Keyboard.Constants.MaxColumns));
            }

            else if (token.Equals("setmouse"))
            {
                SetMouseLayout(ParseLayout(command, Corale.Colore.Razer.Mouse.Constants.MaxRows));
            }

            else if (token.Equals("setmousepad"))
            {
                SetMousepadLayout(ParseLayout(command, Corale.Colore.Razer.Mousepad.Constants.MaxLeds));
            }

            else if (token.Equals("init"))
            {
                Initialise();
            }

            else if (token.Equals("uninit"))
            {
                Uninitialise();
            }

            else if (token.Equals("off"))
            {
                Chroma.Instance.SetAll(CreateColour(0, 0, 0));
            }

            else if (token.Equals("stop"))
            {
                Exit(null, null);
            }

            else
            {
                PrintError("Command '" + token + "' not recognised.");
            }
        }

        static int[,] ParseLayout(string command, int columns)
        {
            //Create a 2D array to represent the layout.
            int[,] layout = new int[columns, 3];
            //Split the string into an array of strings, for each of the rgb values.
            string[] values = command.Split(null);
            //Parse the RGB values for each columns.
            for (int column = 0; column < columns; column++)
            {
                //Convert the strings for each rgb value into integers.
                layout[column, 0] = Convert.ToInt32(values[(column * 3) + 0]);
                layout[column, 1] = Convert.ToInt32(values[(column * 3) + 1]);
                layout[column, 2] = Convert.ToInt32(values[(column * 3) + 2]);
            }
            return layout;
        }

        static void SetKeyboardLayout(int[,] layout)
        {
            for (int column = 0; column < Corale.Colore.Razer.Keyboard.Constants.MaxColumns; column++)
            {
                for (int row = 0; row < Corale.Colore.Razer.Keyboard.Constants.MaxRows; row++)
                {
                    Chroma.Instance.Keyboard[row, column] = CreateColour(layout[column, 0], layout[column, 1], layout[column, 2]);
                }
            }
        }

        static void SetMouseLayout(int[,] layout)
        {
            for (int row = 0; row < Corale.Colore.Razer.Mouse.Constants.MaxRows; row++)
            {
                for (int column = 0; column < Corale.Colore.Razer.Mouse.Constants.MaxColumns; column++)
                {
                    Chroma.Instance.Mouse[row, column] = CreateColour(layout[row, 0], layout[row, 1], layout[row, 2]);
                }
            }
        }

        static void SetMousepadLayout(int[,] layout)
        {
            List<ColoreColor> colours = new List<ColoreColor>();
            for (int i = 0; i < Corale.Colore.Razer.Mousepad.Constants.MaxLeds; i++)
            {
                colours.Add(CreateColour(layout[i, 0], layout[i, 1], layout[i, 2]));
            }
            colours.Reverse();
            Chroma.Instance.Mousepad.SetCustom(new Corale.Colore.Razer.Mousepad.Effects.Custom(colours));
        }

        static void PrintError(string message)
        {
            Console.WriteLine("Error parsing command: " + message);
        }

        static string GetNextToken(string command)
        {
            if (command.IndexOf(" ") == -1) return command;
            else return command.Substring(0, command.IndexOf(" "));
        }

        static string RemoveToken(string command)
        {
            if (command.IndexOf(" ") == -1) return "";
            else return command.Substring(command.IndexOf(" ") + 1, command.Length - command.IndexOf(" ") - 1);
        }

        static void SetSingleDevices(int r, int g, int b)
        {
            //Initialise the SDK if it has not yet been initialised.
            if (!initialised) Initialise();
            //Set all connected Razer Chroma devices with only a single colour capability to the given color.
            Chroma.Instance.Headset.SetAll(CreateColour(r, g, b));
            Chroma.Instance.Keypad.SetAll(CreateColour(r, g, b));
            Chroma.Instance.ChromaLink.SetAll(CreateColour(r, g, b));
        }

        static ColoreColor CreateColour(int r, int g, int b)
        {
            return  new ColoreColor((byte) r, (byte) g, (byte) b);
        }

        static void Initialise()
        {
            Chroma.Instance.Initialize();
            initialised = true;
        }

        static void Uninitialise()
        {
            Chroma.Instance.Uninitialize();
            initialised = false;
        }
    }
}
