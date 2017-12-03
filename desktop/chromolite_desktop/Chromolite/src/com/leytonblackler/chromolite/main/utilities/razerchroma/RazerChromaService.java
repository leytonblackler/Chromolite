package com.leytonblackler.chromolite.main.utilities.razerchroma;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.*;

public class RazerChromaService {

    /**
     * The port used to communicate with the C# Chromolite Razer Chroma SDK service via UDP.
     */
    private static final int PORT = 8888;

    public RazerChromaService() {
        //Get the current operating system as a string.
        String operatingSystem = System.getProperty("os.name");
        //Only run the service if the current operating system is Windows.
        if (operatingSystem.contains("Windows")) {
            runService();
            try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
            send("init");
        }
        else {
            System.out.println("Razer Chroma support is only available on Windows.");
            System.out.println("You are currently using " + operatingSystem + ".");
        }
    }

    private void runService() {
        //The folder containing the Chromolite Razer Chroma SDK background service executable and associated files.
        //(converts where spaces are represented as '%20' to a regular space characters for compatibility with FileUtils)
        File source = new File(getClass().getClassLoader().getResource("RazerChromaService").getFile().replace("%20", " "));
        //The temporary system location to copy the executable and associated files to.
        File destination = new File(System.getProperty("java.io.tmpdir") + "RazerChromaService");
        try {
            //Copy the directory and contents from the source to the destination.
            FileUtils.copyDirectory(source, destination);
            //Run the executable.
            Runtime.getRuntime().exec(destination.getAbsolutePath() + "/RazerChromaService.exe");
        } catch (IOException e) {
            //If the service is already running, there is no need to run the executable again.
            if (e.toString().contains("cannot access the file because it is being used by another process")) {
                System.out.println("The Chromolite Razer Chroma SDK service is already running.");
            } else e.printStackTrace();
        }
    }

    public void stop() {
        send("stop");
    }

    public void setAll(int r, int g, int b) {
        String message = "setall " + r + " " + g + " " + b;
        send(message);
    }

    private void send(String message) {
        if (message == null) return;
        try {
            byte[] buffer = message.getBytes();
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
