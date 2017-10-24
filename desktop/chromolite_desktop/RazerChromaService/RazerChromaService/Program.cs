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

namespace RazerChromaService
{
    static class Program
    {

        static int port = 8888;

        static bool initialised = false;

        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Thread service = new Thread(RunService);
            service.Start();

            InitialiseTrayIcon();
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

        private static void InitialiseTrayIcon()
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
            exitOption.Click += new EventHandler(exitOption_Click);
            trayIcon.Icon = Properties.Resources.icon;
            trayIcon.Text = titleText;
            trayIcon.ContextMenu = menu;
            trayIcon.Visible = true;
            Application.Run();
        }

        private static void exitOption_Click(object Sender, EventArgs e)
        {
            Uninitialise();
            Environment.Exit(0);
        }

        static void ParseCommand(string command)
        {
            Console.WriteLine("Received command: [" + command + "]");

            string token = GetNextToken(command);
            command = RemoveToken(command);

            if (token.Equals("setall"))
            {
                //Split the string into an array of strings, for each rgb value.
                string[] values = command.Split(null);
                //Convert the strings for each rgb value into integers.
                int r = Convert.ToInt32(values[0]);
                int g = Convert.ToInt32(values[1]);
                int b = Convert.ToInt32(values[2]);
                //Set the color defined by the rgb values.
                SetColor(r, g, b);
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
                SetColor(0, 0, 0);
            }

            else
            {
                PrintError("Command '" + token + "' not recognised.");
            }
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

        static void SetColor(int r, int g, int b)
        {
            //Initialise the SDK if it has not yet been initialised.
            if (!initialised) Initialise();

            //Create a color based on the rgb values.
            var color = new ColoreColor((byte)r, (byte)g, (byte)b);
            //Set all connected Razer Chroma devices to the given color.
            Chroma.Instance.SetAll(color);
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
