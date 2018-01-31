package com.leytonblackler.chromolite.main.utilities.arduino;

import arduino.Arduino;
import com.fazecast.jSerialComm.SerialPort;

import java.io.PrintWriter;
import java.util.Scanner;

public class ArduinoController {

    public static final int LEDS = 60;

    public static final int BAUD_RATE = 520000;

    private SerialPort serialPort;

    public void setSingleColour(int r, int g, int b) {
        int[][] layout = new int[LEDS][3];
        for (int led = 0; led < LEDS; led++) {
            layout[led][0] = r;
            layout[led][1] = g;
            layout[led][2] = b;
        }
        send(layoutToString(layout));
    }

    public void setLayout(int[][] layout) {
        send(layoutToString(layout));
    }

    private String layoutToString(int[][] layout) {
        String string = "";
        for (int colour = 0 ; colour < layout.length; colour++) {
            for (int value = 0; value < 3; value++) {
                string += Integer.toString(layout[colour][value]);
                if (colour != layout.length - 1 || value != 2) {
                    string += ",";
                }
            }
        }
        return string;
    }

    private void send(String output) {
        output += '$';
        serialPort.writeBytes(output.getBytes(), output.getBytes().length);
    }

    public void connect() {
        //TODO: Select port / avoid exception if no ports available
        serialPort = SerialPort.getCommPorts()[0];
        serialPort.setBaudRate(BAUD_RATE);
        if (serialPort.openPort()) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {
        if (serialPort == null) {
            throw new IllegalStateException();
        } else {
            serialPort.closePort();
        }
    }

}
