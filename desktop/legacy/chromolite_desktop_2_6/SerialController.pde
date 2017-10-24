import processing.serial.*;

class SerialController {

  Serial serial;
  OscSerial osc;

  boolean connectionError;

  SerialController(PApplet parent) {

    connectionError = false;

    try {
      serial = new Serial(parent, Serial.list()[0], 9600);
    } 
    catch (Exception e) {
      connectionError = true;
    }

    if (!connectionError) {
      osc = new OscSerial(parent, serial);
      //osc.plug(this, "myFunction", "/helloFromArduino");
    }
  }

  void sendMode(String mode) {
    OscMessage message = new OscMessage("/mode");
    if (mode.equals("static1") || mode.equals("static2") || mode.equals("static3")) {
      message.add(0);
    } else if (mode.equals("random")) {
      message.add(1);
    } else if (mode.equals("wave")) {
      message.add(2);
    } else if (mode.equals("music")) {
      message.add(3);
    } else if (mode.equals("spectrum")) {
      message.add(4);
    } else if (mode.equals("strobe")) {
      message.add(5);
    } else if (mode.equals("scan")) {
      message.add(6);
    } else if (mode.equals(" ")) {
      message.add(7);
    } else if (mode.equals("off")) {
      message.add(8);
    }

    if (mode.equals("static1")) {
      message.add(1);
    } else if (mode.equals("static2")) {
      message.add(2);
    } else if (mode.equals("static3")) {
      message.add(3);
    } else {
      message.add(-1);
    }

    osc.send(message);
  }

  void sendColour(int type, int r, int g, int b) {
    OscMessage message = new OscMessage("/colour");
    message.add(type);
    message.add(r);
    message.add(g);
    message.add(b);

    osc.send(message);
  }

  void setBlueLED(boolean state) {
    OscMessage message = new OscMessage("/toggle_blue_led");
    if (state == true) {
      message.add(0);
    } else {
      message.add(1);
    }

    osc.send(message);
  }
  
  void saveSettings() {
    OscMessage message = new OscMessage("/save_settings");
    osc.send(message);
  }
  
  void setDefault() {
    OscMessage message = new OscMessage("/set_default");
    osc.send(message);
  }

  boolean hasConnectionError() {
    return connectionError;
  }
}