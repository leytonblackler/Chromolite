/**==========================
 Chromolite Desktop
 Version: 2.6
 Made by Leyton Blackler
 =========================**/

import oscP5.*;
import netP5.*;
import controlP5.*;
import java.net.InetAddress;
import javax.swing.*;
import java.lang.IllegalArgumentException;

SerialController serialController;

InetAddress inet;
OscP5 oscP5;

ControlP5 cp5;

boolean serialConnectionEstablished;

int textfieldPort;
int currentPort;

ArrayList<Integer> usedPorts;

ModeButton currentMode;

ArrayList<String> connectedIPs;
String mostRecentIP;

PImage iconLogo;
PImage bannerLogo;
PImage spectrum;
PImage[] resetButton;
PImage[] loadButton;
PImage[] saveButton;

PFont RobotoRegular;
PFont RobotoMedium;
PFont RobotoBold;
PFont RobotoBlack;

float bannerHeight;
float buttonGap;
float buttonWidth;
float bigButtonWidth;
float buttonHeight;
float cornerRadius;
float buttonTextSize;
float textHeight;
float colourButtonWidth;
float selectorCircleSize;

boolean holdingDown;
boolean spectrumPressed;
boolean resetPressed;
boolean loadPressed;
boolean savePressed;
boolean savedSettingsExists;
boolean loadingSettings;

TryAgainButton tryAgainButton;

ModeButton staticModeButton, randomModeButton, waveModeButton, musicModeButton, spectrumModeButton, strobeModeButton, scanModeButton, placeholderButton2, toggleBlueLEDButton, offButton;
ArrayList<ModeButton> modeButtons;

ColourButton primaryColourButton;
ColourButton secondaryColourButton;
ColourButton tertiaryColourButton;
ArrayList<ColourButton> colourButtons;

ColourButton currentColourButton;

int[][] coloursRGB;

PVector[] colourSelectorLocations;

float colourSendDelay;

IpBox ipBox;
PortBox portBox;

int reset;

String[] loadedSettings;

void setup() {
  size(600, 750);
  reset = -1;

  iconLogo = loadImage("icon_logo.png");

  surface.setTitle("Chromolite");
  surface.setIcon(iconLogo);

  serialController = new SerialController(this);
  if (serialController.hasConnectionError()) {

    serialConnectionEstablished = false;
    surface.setSize(350, 150);
    textHeight = height/11;

    initialiseFonts();

    tryAgainButton = new TryAgainButton();
  } else {
    serialConnectionEstablished = true;

    surface.setSize(600, 750);

    if (loadStrings("saved_settings.txt") == null) {
      println("Could not find any saved settings.");
      savedSettingsExists = false;
    } else {
      savedSettingsExists = true;
    }

    bannerLogo = loadImage("banner_logo.png");
    spectrum = loadImage("spectrum.png");

    resetButton = new PImage[] {loadImage("reset.png"), loadImage("reset-hover.png")};
    loadButton = new PImage[] {loadImage("load.png"), loadImage("load-hover.png")};
    saveButton = new PImage[] {loadImage("save.png"), loadImage("save-hover.png")};

    currentPort = 5000;
    textfieldPort = currentPort;

    usedPorts = new ArrayList<Integer> ();

    oscP5 = new OscP5(this, currentPort);
    connectedIPs = new ArrayList<String> ();
    mostRecentIP = null;

    cp5 = new ControlP5(this);

    cp5.setColorForeground(#bcc4cc);
    cp5.setColorBackground(#bcc4cc);
    cp5.setColorActive(#bcc4cc);

    bannerHeight = 40;
    buttonGap = (height/2)/15;
    buttonWidth = (width - 5 * buttonGap) / 4;
    bigButtonWidth = buttonWidth*2 + buttonGap;
    buttonHeight = 2*((height/2)/15);
    cornerRadius = width/130;
    colourButtonWidth = (width - 4*buttonGap) / 3;
    selectorCircleSize = width/60;

    textHeight = height/65;

    initialiseFonts();

    holdingDown = false;
    spectrumPressed = false;
    resetPressed = false;
    loadPressed = false;
    savePressed = false;
    loadingSettings = false;

    staticModeButton = new ModeButton("STATIC", "static1", buttonGap, height/2 + 3*buttonGap, true, true, 1, '1');
    randomModeButton = new ModeButton("RANDOM", "random", 2*buttonGap + buttonWidth, height/2 + 3*buttonGap, true, true, 0, '2');
    waveModeButton = new ModeButton("WAVE", "wave", buttonGap*3 + 2*buttonWidth, height/2 + 3*buttonGap, true, true, 0, '3');
    musicModeButton = new ModeButton("MUSIC", "music", 4*buttonGap + 3*buttonWidth, height/2 + 3*buttonGap, true, true, 3, '4');
    spectrumModeButton = new ModeButton("SPECTRUM", "spectrum", buttonGap, height/2 + 4*buttonGap + buttonHeight, true, true, 0, '5');
    strobeModeButton = new ModeButton("STROBE", "strobe", 2*buttonGap + buttonWidth, height/2 + 4*buttonGap + buttonHeight, true, true, 1, '6');
    scanModeButton = new ModeButton("SCAN", "scan", buttonGap*3 + 2*buttonWidth, height/2 + 4*buttonGap + buttonHeight, true, true, 1, '7');
    placeholderButton2 = new ModeButton("N/A", "null", 4*buttonGap + 3*buttonWidth, height/2 + 4*buttonGap + buttonHeight, false, true, 0, '8');
    toggleBlueLEDButton = new ModeButton("TOGGLE BLUE LED", "toggle_blue_led", buttonGap, height/2 + 5*buttonGap + 2*buttonHeight, true, false, 0, 'T');
    if (!savedSettingsExists) {
      setBlueLED(true);
    }
    offButton = new ModeButton("OFF", "off", buttonGap*3 + 2*buttonWidth, height/2 + 5*buttonGap + 2*buttonHeight, true, false, 0, '0');
    modeButtons = new ArrayList<ModeButton> ();
    modeButtons.add(staticModeButton);
    modeButtons.add(randomModeButton);
    modeButtons.add(waveModeButton);
    modeButtons.add(musicModeButton);
    modeButtons.add(spectrumModeButton);
    modeButtons.add(strobeModeButton);
    modeButtons.add(scanModeButton);
    modeButtons.add(placeholderButton2);
    modeButtons.add(toggleBlueLEDButton);
    modeButtons.add(offButton);

    if (!savedSettingsExists) {
      setCurrentMode(staticModeButton);
    }

    primaryColourButton = new ColourButton("PRIMARY", buttonGap, height/2 + buttonGap);
    secondaryColourButton = new ColourButton("SECONDARY", 2*buttonGap + colourButtonWidth, height/2 + buttonGap);
    tertiaryColourButton = new ColourButton("TERTIARY", 3*buttonGap + 2*colourButtonWidth, height/2 + buttonGap);

    colourButtons = new ArrayList<ColourButton> ();
    colourButtons.add(primaryColourButton);
    colourButtons.add(secondaryColourButton);
    colourButtons.add(tertiaryColourButton);

    currentColourButton = primaryColourButton;
    currentColourButton.setSelectedState(true);

    coloursRGB = new int[][] {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}};

    colourSendDelay = 0;

    spectrum.resize(width, (int) (height/2 - bannerHeight));
    spectrum.loadPixels();

    resetButton[0].resize(0, (int) (bannerHeight/1.5));
    resetButton[1].resize(0, (int) (bannerHeight/1.5));
    loadButton[0].resize(0, (int) (bannerHeight/1.5));
    loadButton[1].resize(0, (int) (bannerHeight/1.5));
    saveButton[0].resize(0, (int) (bannerHeight/1.5));
    saveButton[1].resize(0, (int) (bannerHeight/1.5));

    colourSelectorLocations = new PVector[3];
    colourSelectorLocations[0] = new PVector(width/6, bannerHeight + spectrum.height/2);
    colourSelectorLocations[1] = new PVector(3 * (width/6), bannerHeight + spectrum.height/2);
    colourSelectorLocations[2] = new PVector(5 * (width/6), bannerHeight + spectrum.height/2);

    ipBox = new IpBox();
    portBox = new PortBox();

    if (savedSettingsExists) {
      loadSettings();
    }
  }
}

void draw() {
  if (reset == 0) {
    background(#121b25);
    reset = 1;
    return;
  } else if (reset == 1) {
    setup();
  }

  if (!serialConnectionEstablished) {
    background(#121b25);

    iconLogo.resize(0, round(height/4));
    imageMode(CENTER);
    image(iconLogo, width/2, height/5);

    textFont(RobotoRegular);
    fill(#bcc4cc);

    textAlign(CENTER, BOTTOM);
    text("The LED controller could not be detected.", width/2, height/2 - height/25);
    textAlign(CENTER, TOP);
    text("Check USB connection.", width/2, height/2 - height/25);

    tryAgainButton.drawButton();
  } else {

    if (holdingDown && spectrumPressed) {
      setColour();
    }

    if (currentPort != textfieldPort && portBox.portNumberIsValid() && !portBox.getTextfield().isFocus()) {
      sendDisconnect();
      usedPorts.add(currentPort);
      currentPort = textfieldPort;
      oscP5.stop();
      delay(1000);
      oscP5 = new OscP5(this, currentPort);
    }

    noStroke();
    background(#121b25);//#1c2939);

    imageMode(CORNER);
    image(spectrum, 0, bannerHeight);

    stroke(#121b25);
    strokeWeight(1.5);
    textAlign(CENTER, CENTER);
    textFont(RobotoBlack);

    for (int i = 0; i < colourSelectorLocations.length; i++) {
      fill(coloursRGB[i][0], coloursRGB[i][1], coloursRGB[i][2]);
      ellipse(colourSelectorLocations[i].x, colourSelectorLocations[i].y, selectorCircleSize, selectorCircleSize);
      fill(#121b25);
      String label = "";
      if (i == 0) label = "P";
      else if (i == 1) label = "S";
      else if (i == 2) label = "T";
      text(label, colourSelectorLocations[i].x + selectorCircleSize/1.2, colourSelectorLocations[i].y + selectorCircleSize/1.2);
    }

    primaryColourButton.drawButton();
    secondaryColourButton.drawButton();
    tertiaryColourButton.drawButton();

    for (ModeButton button : modeButtons) {
      button.drawButton();
    }

    ipBox.drawBox();
    portBox.drawBox();

    noStroke();
    fill(#121b25);
    rect(0, 0, width, bannerHeight);
    bannerLogo.resize(0, round(0.6 * bannerHeight));
    imageMode(CENTER);
    image(bannerLogo, width/2, bannerHeight/2);

    if (mouseOverResetButton() || resetPressed) {
      image(resetButton[1], width - 4*resetButton[0].width, bannerHeight/2);
    } else {
      image(resetButton[0], width - 4*resetButton[0].width, bannerHeight/2);
    }

    if (mouseOverLoadButton() || loadPressed) {
      image(loadButton[1], width - 2.5*loadButton[0].width, bannerHeight/2);
    } else {
      image(loadButton[0], width - 2.5*loadButton[0].width, bannerHeight/2);
    }

    if (mouseOverSaveButton() || savePressed) {
      image(saveButton[1], width - saveButton[0].width, bannerHeight/2);
    } else {
      image(saveButton[0], width - saveButton[0].width, bannerHeight/2);
    }

    fill(#121b25);
    rect(0, height/2, width, buttonGap/5);
  }
}

void mouseClicked() {
  if (!serialConnectionEstablished) return;

  if (portBox.containsCursor()) {
    portBox.getTextfield().setFocus(true);
  }
}

void mousePressed() {
  if (!serialConnectionEstablished) {
    if (tryAgainButton.containsCursor()) {
      tryAgainButton.setPressedState(true);
    }
  } else {

    if (spectrumContainsCursor()) {
      spectrumPressed = true;
    }

    if (mouseOverResetButton()) {
      resetPressed = true;
    }

    if (mouseOverLoadButton()) {
      loadPressed = true;
    }

    if (mouseOverSaveButton()) {
      savePressed = true;
    }

    for (ModeButton button : modeButtons) {
      if (button.containsCursor()) {
        button.setPressedState(true);
      }
    }

    for (ColourButton button : colourButtons) {
      if (button.containsCursor()) {
        button.setPressedState(true);
      }
    }
  }

  holdingDown = true;
}

void mouseReleased() {
  if (!serialConnectionEstablished) {
    if (tryAgainButton.containsCursor() && tryAgainButton.isPressed()) {
      background(#121b25);
      reset = 1;
    }
    tryAgainButton.setPressedState(false);
  } else {

    if (mouseOverResetButton() && resetPressed) {
      resetSettings();
    }

    if (mouseOverLoadButton() && loadPressed) {
      loadSettings();
    }

    if (mouseOverSaveButton() && savePressed) {
      saveSettings();
    }

    spectrumPressed = false;
    resetPressed = false;
    loadPressed = false;
    savePressed = false;

    for (ModeButton button : modeButtons) {
      if (button.containsCursor() && button.isSelectable() && button.isPressed()) {
        if (button == toggleBlueLEDButton) {
          if (toggleBlueLEDButton.isSelected()) {
            setBlueLED(false);
          } else {
            setBlueLED(true);
          }
        } else {
          if (button == staticModeButton) {
            if (button.isSelected()) {
              if (button.getAction().equals("static1")) {
                button.setNumberOfColoursUsed(2);
              } else if (button.getAction().equals("static2")) {
                button.setNumberOfColoursUsed(3);
              } else if (button.getAction().equals("static3")) {
                button.setNumberOfColoursUsed(1);
              }
            }
          }
          setCurrentMode(button);
        }
      }
    }

    for (ColourButton button : colourButtons) {
      if (button.containsCursor() && button.isSelectable() && button.isPressed()) {
        setCurrentColourButton(button);
      }
    }

    for (ModeButton button : modeButtons) {
      button.setPressedState(false);
    }

    for (ColourButton button : colourButtons) {
      button.setPressedState(false);
    }
  }

  holdingDown = false;
}

void setCurrentMode(ModeButton mode) {
  sendMode(mode.getAction());

  if (currentMode ==  mode) return;

  currentMode = mode;
  mode.setSelectedState(true);

  for (ModeButton button : modeButtons) {
    if (button != currentMode && button.isSelected() && button != toggleBlueLEDButton) {
      button.setSelectedState(false);
    }
  }
}

void setCurrentColourButton(ColourButton newColourButton) {
  if (currentColourButton ==  newColourButton) return;
  else {
    currentColourButton = newColourButton;
    newColourButton.setSelectedState(true);
  }

  for (ColourButton button : colourButtons) {
    if (button != currentColourButton) {
      button.setSelectedState(false);
    }
  }
}

void setColour() {
  for (int i = 0; i < colourButtons.size(); i++) {
    if (colourButtons.get(i).isSelected()) {
      color pixelColour = color(100);

      if (spectrumContainsCursor()) {
        if (mouseX < 0) {
          colourSelectorLocations[i].x = 0;
          pixelColour = spectrum.pixels[(int) ((mouseY - bannerHeight) * spectrum.width + 0)];
        } else if (mouseX > width) {
          colourSelectorLocations[i].x = width;
          pixelColour = spectrum.pixels[(int) ((mouseY - bannerHeight) * spectrum.width + width - 1)];
        } else {
          colourSelectorLocations[i].x = mouseX;
          pixelColour = spectrum.pixels[(int) ((mouseY - bannerHeight) * spectrum.width + mouseX)];
        }
        colourSelectorLocations[i].y = mouseY;
      } else if (cursorAboveSpectrum()) {
        pixelColour = color(255);
        if (mouseX < 0) {
          colourSelectorLocations[i].x = 0;
        } else if (mouseX > width) {
          colourSelectorLocations[i].x = width;
        } else {
          colourSelectorLocations[i].x = mouseX;
        }
        colourSelectorLocations[i].y = bannerHeight;
      } else if (cursorBelowSpectrum()) {
        pixelColour = color(0);
        if (mouseX < 0) {
          colourSelectorLocations[i].x = 0;
        } else if (mouseX > width) {
          colourSelectorLocations[i].x = width;
        } else {
          colourSelectorLocations[i].x = mouseX;
        }
        colourSelectorLocations[i].y = height/2;
      }

      coloursRGB[i][0] = int(red((pixelColour)));
      coloursRGB[i][1] = int(green((pixelColour)));
      coloursRGB[i][2] = int(blue((pixelColour)));
      colourButtons.get(i).setColour(coloursRGB[i][0], coloursRGB[i][1], coloursRGB[i][2]);

      sendColour(i, coloursRGB[i][0], coloursRGB[i][1], coloursRGB[i][2], colourSelectorLocations[i].x / ((float) width), ((colourSelectorLocations[i].y - bannerHeight) / (spectrum.height)), null);
    }
  }
}

void oscEvent(OscMessage incomingMessage) {
  println();
  incomingMessage.print();

  verifySenderIP(incomingMessage.toString());

  if (incomingMessage.addrPattern().equals("/mode")) {
    println("Changing mode.");
    println(incomingMessage.get(0).stringValue());
    if (incomingMessage.get(0).stringValue().equals("toggle_blue_led")) {
      toggleBlueLEDButton.setSelectedState(!toggleBlueLEDButton.isSelected());
      if (toggleBlueLEDButton.isSelected()) {
        serialController.setBlueLED(true);
      } else {
        serialController.setBlueLED(false);
      }
    } else {
      for (ModeButton button : modeButtons) {
        if (button.getAction().equals(incomingMessage.get(0).stringValue()) || (button.getAction().contains("static") && incomingMessage.get(0).stringValue().contains("static"))) {
          if (incomingMessage.get(0).stringValue().contains("static")) {
            if (incomingMessage.get(0).stringValue().equals("static1")) {
              staticModeButton.setNumberOfColoursUsed(1);
            } else if (incomingMessage.get(0).stringValue().equals("static2")) {
              staticModeButton.setNumberOfColoursUsed(2);
            } else if (incomingMessage.get(0).stringValue().equals("static3")) {
              staticModeButton.setNumberOfColoursUsed(3);
            }
          }
          setCurrentMode(button);
        }
      }
    }
  } else if (incomingMessage.addrPattern().equals("/toggle_blue_led")) {
    if (incomingMessage.get(0).intValue() == 0) {
      setBlueLED(true);
    } else if (incomingMessage.get(0).intValue() == 1) {
      setBlueLED(false);
    }
  } else if (incomingMessage.addrPattern().equals("/colour")) {
    println("Changing colour.");
    int type = incomingMessage.get(0).intValue();
    coloursRGB[type][0] = incomingMessage.get(1).intValue();
    coloursRGB[type][1] = incomingMessage.get(2).intValue();
    coloursRGB[type][2] = incomingMessage.get(3).intValue();
    colourSelectorLocations[type].x = width * incomingMessage.get(4).floatValue();
    colourSelectorLocations[type].y = bannerHeight + spectrum.height * incomingMessage.get(5).floatValue();
    println(incomingMessage.get(1).intValue());
    println(incomingMessage.get(2).intValue());
    println(incomingMessage.get(3).intValue());

    sendColour(type, incomingMessage.get(1).intValue(), incomingMessage.get(2).intValue(), incomingMessage.get(3).intValue(), incomingMessage.get(4).floatValue(), incomingMessage.get(5).floatValue(), mostRecentIP);
  } else if (incomingMessage.addrPattern().equals("/connect")) {
    println("received connect");

    OscMessage replyMessage = new OscMessage("/connected");
    if (toggleBlueLEDButton.isSelected()) {
      replyMessage.add("on");
    } else {
      replyMessage.add("off");
    }
    replyMessage.add(currentMode.getAction());

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        replyMessage.add(coloursRGB[i][j]);
      }
    }

    for (int i = 0; i < 3; i++) {
      replyMessage.add(colourSelectorLocations[i].x / ((float) width));
      replyMessage.add((colourSelectorLocations[i].y - bannerHeight) / (spectrum.height));
    }

    replyMessage.add(staticModeButton.getColoursUsed());

    println();
    replyMessage.print();

    NetAddress destinationLocation;

    for (String ip : connectedIPs) {
      destinationLocation = new NetAddress(ip, currentPort);
      oscP5.send(replyMessage, destinationLocation);
    }
  } else {
    println("Incoming message address pattern not recognised.");
  }
}

void verifySenderIP(String messageDetails) {
  String senderIP = messageDetails.substring(1, messageDetails.indexOf(':'));
  println("Sender IP: " + senderIP);

  mostRecentIP = senderIP;

  if (!connectedIPs.contains(senderIP)) {
    connectedIPs.add(senderIP);
  }
}

void sendColour(int type, int r, int g, int b, float xP, float yP, String excludeIP) {
  OscMessage message = new OscMessage("/colour");
  message.add(type);
  message.add(r);
  message.add(g);
  message.add(b);
  message.add(xP);
  message.add(yP);
  for (String ip : connectedIPs) {
    if (excludeIP == null) {
      oscP5.send(message, new NetAddress(ip, currentPort));
    } else {
      if (ip != excludeIP) {
        oscP5.send(message, new NetAddress(ip, currentPort));
      }
    }
  }

  if (colourSendDelay < millis() || loadingSettings) {
    serialController.sendColour(type, r, g, b);
    if (!loadingSettings) {
      colourSendDelay = millis() + 40;
    }
  }
}

void sendMode(String mode) {
  println("Sending '" + mode + "'.");
  OscMessage message = new OscMessage("/mode");
  message.add(mode);
  for (String ip : connectedIPs) {
    oscP5.send(message, new NetAddress(ip, currentPort));
  }

  serialController.sendMode(mode);
}

void setBlueLED(boolean state) {
  toggleBlueLEDButton.setSelectedState(state);

  OscMessage message = new OscMessage("/toggle_blue_led");
  if (state == true) {
    message.add(0);
  } else {
    message.add(1);
  }
  for (String ip : connectedIPs) {
    oscP5.send(message, new NetAddress(ip, currentPort));
  }

  serialController.setBlueLED(state);
}

void sendDisconnect() {
  OscMessage message = new OscMessage("/disconnect");
  for (String ip : connectedIPs) {
    oscP5.send(message, new NetAddress(ip, currentPort));
  }
}

boolean spectrumContainsCursor() {
  return (mouseY > bannerHeight && mouseY < height/2);
}

boolean cursorAboveSpectrum() {
  return (mouseY <= bannerHeight);
}

boolean cursorBelowSpectrum() {
  return (mouseY >= height/2);
}

void initialiseFonts() {
  RobotoRegular = createFont("Roboto-Regular.ttf", textHeight);
  RobotoMedium = createFont("Roboto-Medium.ttf", textHeight);
  RobotoBold = createFont("Roboto-Bold.ttf", textHeight);
  RobotoBlack = createFont("Roboto-Black.ttf", textHeight);
}

void keyPressed() {
  if (portBox.getTextfield().isFocus()) return;

  if (key == 't' || key == 'T') {
    setBlueLED(!toggleBlueLEDButton.isSelected());
  } else {
    for (ModeButton button : modeButtons) {
      if (key == button.getKey()) {
        if (button == staticModeButton) {
          if (button.isSelected()) {
            if (button.getAction().equals("static1")) {
              button.setNumberOfColoursUsed(2);
            } else if (button.getAction().equals("static2")) {
              button.setNumberOfColoursUsed(3);
            } else if (button.getAction().equals("static3")) {
              button.setNumberOfColoursUsed(1);
            }
          }
        }
        setCurrentMode(button);
      }
    }
  }
}

boolean mouseOverResetButton() {
  return (mouseX > width - 4*resetButton[0].width - resetButton[0].width/2
    && mouseX < width - 4*resetButton[0].width + resetButton[0].width/2
    && mouseY > bannerHeight/2 - resetButton[0].height/2
    && mouseY < bannerHeight/2 + resetButton[0].height/2);
}

boolean mouseOverLoadButton() {
  return (mouseX > width - 2.5*loadButton[0].width - loadButton[0].width/2
    && mouseX < width - 2.5*loadButton[0].width + loadButton[0].width/2
    && mouseY > bannerHeight/2 - loadButton[0].height/2
    && mouseY < bannerHeight/2 + loadButton[0].height/2);
}

boolean mouseOverSaveButton() {
  return (mouseX > width - saveButton[0].width - saveButton[0].width/2
    && mouseX < width - saveButton[0].width + saveButton[0].width/2
    && mouseY > bannerHeight/2 - saveButton[0].height/2
    && mouseY < bannerHeight/2 + saveButton[0].height/2);
}

void resetSettings() {
  staticModeButton.setNumberOfColoursUsed(1);
  setCurrentMode(staticModeButton);
  setBlueLED(true);
  coloursRGB = new int[][] {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}};
  for (int i = 0; i < 3; i++) {
    sendColour(i, coloursRGB[i][0], coloursRGB[i][1], coloursRGB[i][2], colourSelectorLocations[i].x / ((float) width), ((colourSelectorLocations[i].y - bannerHeight) / (spectrum.height)), null);
  }
  colourSelectorLocations[0] = new PVector(width/6, bannerHeight + spectrum.height/2);
  colourSelectorLocations[1] = new PVector(3 * (width/6), bannerHeight + spectrum.height/2);
  colourSelectorLocations[2] = new PVector(5 * (width/6), bannerHeight + spectrum.height/2);

  serialController.setDefault();
}

void saveSettings() {
  String[] settings = new String[17];

  settings[0] = currentMode.getAction();

  settings[1] = String.valueOf(toggleBlueLEDButton.isSelected());

  int index = 2;
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      settings[index] = String.valueOf(coloursRGB[i][j]);
      index++;
    }
  }

  for (int i = 0; i < 3; i++) {
    settings[index] = String.valueOf(colourSelectorLocations[i].x);
    settings[index + 1] = String.valueOf(colourSelectorLocations[i].y);
    index += 2;
  }

  saveStrings("saved_settings.txt", settings);

  serialController.saveSettings();
}

void loadSettings() {
  loadingSettings = true;
  try {

    loadedSettings = loadStrings("saved_settings.txt");

    if (loadedSettings == null) return;

    boolean validMode = false;
    for (ModeButton button : modeButtons) {
      if (button.getAction().equals(loadedSettings[0]) || (button.getAction().contains("static") && loadedSettings[0].contains("static"))) {
        if (loadedSettings[0].contains("static")) {
          if (loadedSettings[0].equals("static1")) {
            staticModeButton.setNumberOfColoursUsed(1);
          } else if (loadedSettings[0].equals("static2")) {
            staticModeButton.setNumberOfColoursUsed(2);
          } else if (loadedSettings[0].equals("static3")) {
            staticModeButton.setNumberOfColoursUsed(3);
          }
        }
        setCurrentMode(button);
        validMode = true;
      }
    }

    if (!validMode) {
      throw new IllegalArgumentException("Loaded mode is invalid.");
    }

    setBlueLED(Boolean.parseBoolean(loadedSettings[1]));

    int index = 2;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        coloursRGB[i][j] = Integer.parseInt(loadedSettings[index]);
        index++;
      }
    }

    for (int i =0; i < 3; i++) {
      sendColour(i, coloursRGB[i][0], coloursRGB[i][1], coloursRGB[i][2], colourSelectorLocations[i].x / ((float) width), ((colourSelectorLocations[i].y - bannerHeight) / (spectrum.height)), null);
    }

    for (int i = 0; i < 3; i++) {
      colourSelectorLocations[i].x = Float.parseFloat(loadedSettings[index]);
      colourSelectorLocations[i].y = Float.parseFloat(loadedSettings[index + 1]);
      index += 2;
    }
  }
  catch (Exception error) {
    println("Error reading from saved settings file.");
    println(error);
    delay(2000);
    exit();
  }
  loadingSettings = false;
}