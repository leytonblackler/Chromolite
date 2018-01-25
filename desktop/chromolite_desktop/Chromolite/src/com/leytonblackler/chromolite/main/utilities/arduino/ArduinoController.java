package com.leytonblackler.chromolite.main.utilities.arduino;

import arduino.Arduino;
import com.fazecast.jSerialComm.SerialPort;

import java.io.PrintWriter;
import java.util.Scanner;

public class ArduinoController {

    //temp
    public static ArduinoController instance;
    public static ArduinoController getInstance() {
        return instance;
    }

    public static final int BAUD_RATE = 9600;

    private SerialPort serialPort;

    public ArduinoController() {
        instance = this;
        SerialPort[] serialPorts = SerialPort.getCommPorts();
        if (serialPorts.length >= 1) {
            serialPort = SerialPort.getCommPorts()[0];
            serialPort.setBaudRate(BAUD_RATE);
            System.out.println(serialPort.getDescriptivePortName());
        }
    }

    public void sendTest(String text) {
        send(text);
    }

    public void setSingleColour(int r, int g, int b) {
        //
    }

    public void setLayout(int[] layout) {
        //
    }

    private void send(String string) {
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        try {
            Thread.sleep(5);
        } catch(Exception e) {
            e.printStackTrace();
        }
        PrintWriter pout = new PrintWriter(serialPort.getOutputStream());
        pout.print(string + '$');
        pout.flush();
    }

    public void connect() {
        serialPort = SerialPort.getCommPorts()[0];
        if (serialPort.openPort()) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String serialRead(){
        //will be an infinite loop if incoming data is not bound
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        String out="";
        Scanner in = new Scanner(serialPort.getInputStream());
        try
        {
            while(in.hasNext())
                out += (in.next()+"\n");
            in.close();
        } catch (Exception e) { e.printStackTrace(); }
        return out;
    }

    public void disconnect() {
        if (serialPort == null) {
            throw new IllegalStateException();
        } else {
            serialPort.closePort();
        }
    }

}
