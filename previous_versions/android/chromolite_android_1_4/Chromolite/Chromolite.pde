/**=================================================
 Chromolite Android
 Version: 1.4
 Made by Leyton Blackler
 =================================================**/

import android.widget.Toast;
import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.EditText;
import android.os.Environment;

import oscP5.*;
import netP5.*;
import java.net.InetAddress;

import de.looksgood.ani.*;

float ANIMATION_SPEED = 0.4;
float CONNECTION_TIMEOUT = 5;

//String directory = new String(Environment.getExternalStorageDirectory().getAbsolutePath()) + "/ChromoliteSettings";
//String savedIPFile = "savedIP.txt";  

OscP5 oscP5;
NetAddress desktopLocation;

float timeToTimeOut;

float xPercent = 0;

PImage logo;
PImage bannerLogo;
PImage spectrum;
PImage back;

PFont RobotoRegular;
PFont RobotoMedium;
PFont RobotoBold;
PFont RobotoBlack;

String ipAddress = "192.168.";
String port = "5000";

boolean connecting = false;
boolean connected = false;
boolean holdingDown = false;
boolean colourPickerOpen = false;
boolean keyboardVisible = false;
boolean timedOut = true;
boolean attemptConnection = false;

float cornerRadius;

float originY;
float spectrumY;

float textHeight;

float spectrumInEndTime;
float spectrumOutEndTime;

float bannerHeight;
float buttonHeight;
float buttonGap;
float buttonWidth;
float smallButtonWidth;
float colourButtonWidth;
float buttonTextSize;
float selectorCircleSize;

float timeTransformDownDone = -1;

ArrayList<Button> buttons;
ArrayList<Button> modeButtons;
ArrayList<ColourButton> colourButtons;
Button connectButton;
Button selectColourButton;

int[][] coloursRGB = new int[][] {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}};

PVector[] colourSelectorLocations;

Button staticModeButton, randomModeButton, waveModeButton, musicModeButton, spectrumModeButton, strobeModeButton, scanModeButton, placeholderButton2, toggleBlueLEDButton, offButton;

Button currentMode;

ColourButton primaryColourButton, secondaryColourButton, tertiaryColourButton;
ColourButton currentColourButton;

TextInputBar ipEntryBar;
TextInputBar portEntryBar;

AniSequence transformUp;
AniSequence transformDown;
AniSequence spectrumIn;
AniSequence spectrumOut;

int staticColoursUsed;

void setup() {
  orientation(PORTRAIT);
  //size(600, 400);
  //size(360, 640);
  fullScreen();
  background(#1c2939);

  Ani.init(this);

  logo = loadImage("logo.png");
  bannerLogo = loadImage("banner_logo.png");
  spectrum = loadImage("spectrum.png");
  back = loadImage("back.png");

  //loadIP();

  buttonHeight = height/12;
  buttonGap = width/13;
  buttonWidth = width - (2 * buttonGap);
  smallButtonWidth = (buttonWidth - buttonGap) / 2;
  colourButtonWidth = (buttonWidth - 2 * buttonGap)/3;
  bannerHeight = height/16;
  cornerRadius = height/100;
  buttonTextSize = buttonHeight/5;
  selectorCircleSize = width/35;

  spectrum.resize(width, round(5*buttonGap + 5*buttonHeight));
  spectrum.loadPixels();

  textHeight = height/40;
  RobotoRegular = createFont("Roboto-Regular.ttf", textHeight);
  RobotoMedium = createFont("Roboto-Medium.ttf", textHeight);
  RobotoBold = createFont("Roboto-Bold.ttf", textHeight);
  RobotoBlack = createFont("Roboto-Black.ttf", textHeight);

  buttons = new ArrayList<Button> ();
  modeButtons = new ArrayList<Button> ();
  colourButtons = new ArrayList<ColourButton> ();

  connectButton = new ConnectButton();
  selectColourButton = new SelectColourButton();
  buttons.add(connectButton);
  buttons.add(selectColourButton);

  staticModeButton = new ModeButton("STATIC", "static1", buttonGap, (height/16) + buttonGap, true, 1);
  randomModeButton = new ModeButton("RANDOM", "random", 2*buttonGap + smallButtonWidth, (height/16) + buttonGap, true, 0);
  waveModeButton = new ModeButton("WAVE", "wave", buttonGap, (height/16) + 2*buttonGap + buttonHeight, true, 0);
  musicModeButton = new ModeButton("MUSIC", "music", 2*buttonGap + smallButtonWidth, (height/16) + 2*buttonGap + buttonHeight, true, 3);
  spectrumModeButton = new ModeButton("SPECTRUM", "spectrum", buttonGap, (height/16) + 3*buttonGap + 2*buttonHeight, true, 0);
  strobeModeButton = new ModeButton("STROBE", "strobe", 2*buttonGap + smallButtonWidth, (height/16) + 3*buttonGap + 2*buttonHeight, true, 1);
  scanModeButton = new ModeButton("SCAN", "scan", buttonGap, (height/16) + 4*buttonGap + 3*buttonHeight, true, 1);
  placeholderButton2 = new ModeButton("N/A", "null", 2*buttonGap + smallButtonWidth, (height/16) + 4*buttonGap + 3*buttonHeight, false, 0);
  toggleBlueLEDButton = new ModeButton("TOGGLE BLUE LED", "toggle_blue_led", buttonGap, (height/16) + 5*buttonGap + 4*buttonHeight, true, 0);
  toggleBlueLEDButton.setSelectedState(true);
  offButton = new ModeButton("OFF", "off", 2*buttonGap + smallButtonWidth, (height/16) + 5*buttonGap + 4*buttonHeight, true, 0);
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

  setCurrentMode(staticModeButton);

  primaryColourButton = new ColourButton("PRIMARY", buttonGap, (height/16) + 6*buttonGap + 5*buttonHeight, 255, 0, 0);
  secondaryColourButton = new ColourButton("SECONDARY", buttonGap + colourButtonWidth + buttonGap, (height/16) + 6*buttonGap + 5*buttonHeight, 0, 255, 0);
  tertiaryColourButton = new ColourButton("TERTIARY", buttonGap + 2*colourButtonWidth + 2*buttonGap, (height/16) + 6*buttonGap + 5*buttonHeight, 0, 0, 255);
  currentColourButton = primaryColourButton;
  colourButtons.add(primaryColourButton);
  colourButtons.add(secondaryColourButton);
  colourButtons.add(tertiaryColourButton);

  colourSelectorLocations = new PVector[3];
  colourSelectorLocations[0] = new PVector(0, 0);
  colourSelectorLocations[1] = new PVector(0, 0);
  colourSelectorLocations[2] = new PVector(0, 0);

  ipEntryBar = new TextInputBar("IP :", ipAddress, buttonGap, height/2 - buttonHeight/2);
  portEntryBar = new TextInputBar("PORT :", port, buttonGap, height/2 - buttonHeight/2 + buttonGap + buttonHeight);

  connecting = false;
  connected = false;
  holdingDown = false;
  colourPickerOpen = false;
  keyboardVisible = false;
  timedOut = true;
  attemptConnection = false;

  originY = 0;
  spectrumY = -height + height/16;
  createAnimationSequences();

  oscP5 = new OscP5(this, Integer.parseInt(port));
  timeToTimeOut = 0;
}

void draw() {

  colorMode(RGB);

  if (millis() > timeToTimeOut && connecting && !connected && !timedOut) {
    showToast("Could not connect. Connection timed out.");
    timedOut = true;
    connecting = false;
  }

  translate(0, originY);

  background(#1c2939);
  noStroke();

  if (keyboardVisible && (connecting || connected)) {
    originY = 0;
    hideKeyboard();
  }

  if (connected) {

    drawButtons();
    drawModeButtons();
    drawColourButtons();

    if (colourPickerOpen || spectrumOut.isPlaying()) {
      imageMode(CORNER);
      image(spectrum, 0, spectrumY);

      colorMode(RGB);
      stroke(#121b25);
      strokeWeight(7);
      textAlign(CENTER, CENTER);
      textFont(RobotoBlack);
      textSize(buttonTextSize/1.1);

      for (int i = 0; i < colourSelectorLocations.length; i++) {
        fill(coloursRGB[i][0], coloursRGB[i][1], coloursRGB[i][2]);
        ellipse(colourSelectorLocations[i].x, spectrumY + colourSelectorLocations[i].y, selectorCircleSize, selectorCircleSize);
        fill(#121b25);
        String label = "";
        if (i == 0) label = "P";
        else if (i == 1) label = "S";
        else if (i == 2) label = "T";
        text(label, colourSelectorLocations[i].x + selectorCircleSize/1.25, spectrumY + colourSelectorLocations[i].y + selectorCircleSize/1.25);
      }
    }

    fill(#1c2939);
    noStroke();
    rect(0, bannerHeight + spectrum.height, width, buttonGap/2);

    fill(#121b25);
    noStroke();
    rect(0, 0, width, bannerHeight);
    bannerLogo.resize(0, round(0.6 * bannerHeight));
    imageMode(CENTER);
    image(bannerLogo, width/2, bannerHeight/2);

    back.resize(0, round(0.5 * bannerHeight));
    image(back, buttonGap/2, bannerHeight/2);
  } else {
    float logoHeight = height/5;
    logo.resize(0, round(logoHeight));
    imageMode(CENTER);
    image(logo, width/2, height/2 - logoHeight);
    connectButton.drawButton();
    ipEntryBar.drawTextInputBar();
    portEntryBar.drawTextInputBar();
  }

  if (attemptConnection && millis() > timeTransformDownDone) {
    attemptConnection();
  }
}

void drawButtons() {
  for (Button button : buttons) {
    button.drawButton();
  }
}

void drawModeButtons() {
  for (Button button : modeButtons) {
    button.drawButton();
  }
}

void drawColourButtons() {
  for (ColourButton button : colourButtons) {
    button.drawButton();
  }
}

void mouseDragged() {
  setColour();
}

void mousePressed() {
  setColour();

  for (Button button : buttons) {
    if (button.containsCursor()) {
      button.setPressedState(true);
    }
  }

  for (Button button : modeButtons) {
    if (button.containsCursor() && !colourPickerOpen) {
      button.setPressedState(true);
    }
  }

  if (colourPickerOpen) {
    for (ColourButton button : colourButtons) {
      if (button.containsCursor()) {
        button.setPressedState(true);
      }
    }
  }

  if (ipEntryBar.cursorOverTextBox()) {
    ipEntryBar.setTextBoxPressedState(true);
  }

  if (portEntryBar.cursorOverTextBox()) {
    portEntryBar.setTextBoxPressedState(true);
  }

  if (ipEntryBar.cursorOverClearButton()) {
    ipEntryBar.setClearButtonPressedState(true);
  }

  if (portEntryBar.cursorOverClearButton()) {
    portEntryBar.setClearButtonPressedState(true);
  }

  holdingDown = true;
}

void mouseReleased() {
  if (connected && mouseX > 0 && mouseX < buttonGap && mouseY > 0 && mouseY < bannerHeight) {
    setup();
  }

  if (!colourPickerOpen && connected) {

    for (Button button : modeButtons) {
      if (button.containsCursor() && button.isSelectable() && button.isPressed()) {

        if (button == staticModeButton) {
          if (staticModeButton.isSelected()) {
            if (staticModeButton.getAction().equals("static1")) {
              staticModeButton.setNumberOfColoursUsed(2);
            } else if (staticModeButton.getAction().equals("static2")) {
              staticModeButton.setNumberOfColoursUsed(3);
            } else if (staticModeButton.getAction().equals("static3")) {
              staticModeButton.setNumberOfColoursUsed(1);
            }
          }
        }
        setCurrentMode(button);
        if (button == toggleBlueLEDButton) {
          sendBlueLED();
        } else {
          sendMode(button.getAction());
        }
      } else if (button.containsCursor() && !button.isSelectable() && button.isPressed()) {
        showToast("Mode not currently available.");
      }
    }

    for (Button button : modeButtons) {
      if (button != currentMode && button.isSelected() && button != toggleBlueLEDButton) {
        button.setSelectedState(false);
      }
    }
  }

  if (colourPickerOpen && connected) {
    ColourButton newSelection = null;
    for (ColourButton button : colourButtons) {
      if (button.containsCursor() && button.isPressed()) {
        newSelection = button;
        button.setSelectedState(true);
        currentColourButton = button;
      }
    }

    if (newSelection != null) {
      for (Button button : colourButtons) {
        if (button != newSelection && button.isSelected()) {
          button.setSelectedState(false);
        }
      }
    }
  }

  if (ipEntryBar.cursorOverClearButton()) {
    ipEntryBar.clearInput();
  }

  if (portEntryBar.cursorOverClearButton()) {
    portEntryBar.clearInput();
  }

  ipEntryBar.setTextBoxPressedState(false);
  portEntryBar.setTextBoxPressedState(false);
  ipEntryBar.setClearButtonPressedState(false);
  portEntryBar.setClearButtonPressedState(false);

  if (!connecting && !connected) {
    if (keyboardVisible) {
      if (ipEntryBar.cursorOverTextBox()) {
        if (ipEntryBar.isSelected()) {
          createAnimationSequences();
          transformDown.start();
          timeTransformDownDone = millis() + transformDown.getDuration()*1000;
          hideKeyboard();
          ipEntryBar.setTextBoxSelectedState(false);
        } else {
          ipEntryBar.setTextBoxSelectedState(true);
          portEntryBar.setTextBoxSelectedState(false);
        }
      } else if (portEntryBar.cursorOverTextBox()) {
        if (portEntryBar.isSelected()) {
          createAnimationSequences();
          transformDown.start();
          timeTransformDownDone = millis() + transformDown.getDuration()*1000;
          hideKeyboard();
          portEntryBar.setTextBoxSelectedState(false);
        } else {
          portEntryBar.setTextBoxSelectedState(true);
          ipEntryBar.setTextBoxSelectedState(false);
        }
      }
    } else if (ipEntryBar.cursorOverTextBox() || portEntryBar.cursorOverTextBox()) {
      if (ipEntryBar.cursorOverTextBox()) {
        ipEntryBar.setTextBoxSelectedState(true);
      } else if (portEntryBar.cursorOverTextBox()) {
        portEntryBar.setTextBoxSelectedState(true);
      }
      createAnimationSequences();
      transformUp.start();
      showKeyboard();
    }
  }

  if (connectButton.containsCursor() && !connecting && !connected && connectButton.isPressed()) {
    if (ipEntryBar.getValue() == "" || portEntryBar.getValue() == "") {
      if (ipEntryBar.getValue() == "" && portEntryBar.getValue() != "") {
        showToast("Please enter an IP address.");
      } else if (ipEntryBar.getValue() != "" && portEntryBar.getValue() == "") {
        showToast("Please enter a port number.");
      } else {
        showToast("Please enter an IP address and port number.");
      }
    } else {
      ipEntryBar.setTextBoxSelectedState(false);
      portEntryBar.setTextBoxSelectedState(false);
      if (keyboardVisible) {
        createAnimationSequences();
        transformDown.start();
        timeTransformDownDone = millis() + transformDown.getDuration()*1000;
        hideKeyboard();
      } else {
        timeTransformDownDone = millis() + 100;
      }
      showToast("Connecting...");
      connecting = true;
      attemptConnection = true;
    }
  }

  if (selectColourButton.containsCursor() && connected && selectColourButton.isPressed()) {
    if (colourPickerOpen) {
      createAnimationSequences();
      spectrumOut.start();
      for (ColourButton button : colourButtons) {
        button.setSelectedState(false);
      }
      colourPickerOpen = false;
    } else {
      createAnimationSequences();
      spectrumIn.start();
      currentColourButton.setSelectedState(true);
      colourPickerOpen = true;
    }
  }

  for (Button button : buttons) {
    button.setPressedState(false);
  }

  for (Button button : modeButtons) {
    button.setPressedState(false);
  }

  for (ColourButton button : colourButtons) {
    button.setPressedState(false);
  }

  holdingDown = false;
}

void setColour() {
  if (colourPickerOpen && mouseY > bannerHeight && mouseY < bannerHeight + spectrum.height) {
    for (int i = 0; i < colourButtons.size(); i++) {
      if (colourButtons.get(i).isSelected()) {
        //index = y * width + x
        color pixelColour = spectrum.pixels[(int) ((mouseY - bannerHeight) * spectrum.width + mouseX)];
        coloursRGB[i][0] = int(red((pixelColour)));
        coloursRGB[i][1] = int(green((pixelColour)));
        coloursRGB[i][2] = int(blue((pixelColour)));
        colourButtons.get(i).setColour(coloursRGB[i][0], coloursRGB[i][1], coloursRGB[i][2]);
        sendColour(i, coloursRGB[i][0], coloursRGB[i][1], coloursRGB[i][2]);
      }
    }
  }
}

void attemptConnection() {

  ipAddress = ipEntryBar.getValue();
  //saveIP();

  port = portEntryBar.getValue();

  oscP5 = new OscP5(this, Integer.parseInt(port));
  desktopLocation = new NetAddress(ipAddress, Integer.parseInt(port));

  timeToTimeOut = millis() + CONNECTION_TIMEOUT * 1000;
  sendConnectionRequest();

  timedOut = false;
  attemptConnection = false;
}

void sendConnectionRequest() {
  OscMessage message = new OscMessage("/connect");
  oscP5.send(message, desktopLocation);
}

void sendColour(int type, int r, int g, int b) {
  OscMessage message = new OscMessage("/colour");
  message.add(type);
  message.add(r);
  message.add(g);
  message.add(b);
  message.add(mouseX / ((float) width));
  message.add((mouseY - bannerHeight) / spectrum.height);

  oscP5.send(message, desktopLocation);
}

void sendMode(String mode) {
  OscMessage message = new OscMessage("/mode");
  message.add(mode);
  oscP5.send(message, desktopLocation);
}

void sendBlueLED() {
  OscMessage message = new OscMessage("/toggle_blue_led");
  if (toggleBlueLEDButton.isSelected()) {
    message.add(0);
  } else {
    message.add(1);
  }
  oscP5.send(message, desktopLocation);
}

void oscEvent(OscMessage incomingMessage) {
  if (incomingMessage.addrPattern().equals("/connected")) {
    connected = true;
    if (incomingMessage.get(0).stringValue().equals("on")) {
      toggleBlueLEDButton.setSelectedState(true);
    } else if (incomingMessage.get(0).stringValue().equals("off")) {
      toggleBlueLEDButton.setSelectedState(false);
    }

    String mode = incomingMessage.get(1).stringValue();
    for (Button button : modeButtons) {
      if ((button.getAction().equals(mode) || (button.getAction().contains("static") && mode.contains("static"))) && !button.getAction().equals("toggle_blue_led")) {
        button.setSelectedState(true);
        currentMode = button;
      } else if (!button.getAction().equals("toggle_blue_led")) {
        button.setSelectedState(false);
      }
    }

    int index = 2;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        coloursRGB[i][j] = incomingMessage.get(index).intValue();
        index++;
      }
    }

    for (int i = 0; i < colourButtons.size(); i++) {
      colourButtons.get(i).setColour(coloursRGB[i][0], coloursRGB[i][1], coloursRGB[i][2]);
    }

    for (int i = 0; i < 3; i++) {
      colourSelectorLocations[i].x = width * incomingMessage.get(index).floatValue();
      colourSelectorLocations[i].y = spectrum.height * incomingMessage.get(index + 1).floatValue();
      index += 2;
    }

    staticModeButton.setNumberOfColoursUsed(incomingMessage.get(17).intValue());

    showToast("Connected!");
  } else if (incomingMessage.addrPattern().equals("/disconnect")) {
    setup();
  } else if (incomingMessage.addrPattern().equals("/mode")) {
    for (Button button : modeButtons) {
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
  } else if (incomingMessage.addrPattern().equals("/toggle_blue_led")) {
    if (incomingMessage.get(0).intValue() == 0) {
      toggleBlueLEDButton.setSelectedState(true);
    } else if (incomingMessage.get(0).intValue() == 1) {
      toggleBlueLEDButton.setSelectedState(false);
    }
  } else if (incomingMessage.addrPattern().equals("/colour")) {
    int type = incomingMessage.get(0).intValue();
    coloursRGB[type][0] = incomingMessage.get(1).intValue();
    coloursRGB[type][1] = incomingMessage.get(2).intValue();
    coloursRGB[type][2] = incomingMessage.get(3).intValue();
    colourButtons.get(type).setColour(coloursRGB[type][0], coloursRGB[type][1], coloursRGB[type][2]);
    colourSelectorLocations[type].x = width * incomingMessage.get(4).floatValue();
    colourSelectorLocations[type].y = spectrum.height * incomingMessage.get(5).floatValue();
  }
}

void keyPressed() {
  if (key == '.'
    || key == '0'
    || key == '1'
    || key == '2'
    || key == '3'
    || key == '4'
    || key == '5'
    || key == '6'
    || key == '7'
    || key == '8'
    || key == '9'
    || keyCode == 67) {
    if (keyboardVisible) {
      TextInputBar currentInputBox;
      if (ipEntryBar.isSelected()) {
        currentInputBox = ipEntryBar;
      } else if (portEntryBar.isSelected()) {
        currentInputBox = portEntryBar;
      } else {
        return;
      }
      if (keyCode == 67 && currentInputBox.getValue().length() > 0) {
        currentInputBox.setInput(currentInputBox.getValue().substring(0, max(0, currentInputBox.getValue().length()-1)));
      } else if (keyCode != 67) {
        currentInputBox.addCharacter(key);
      }
    }
  }
}

void setCurrentMode(Button mode) {
  if (currentMode ==  mode) return;
  else {
    if (mode == toggleBlueLEDButton) {
      if (mode.isSelected()) {
        mode.setSelectedState(false);
      } else {
        mode.setSelectedState(true);
      }
    } else {
      currentMode = mode;
      mode.setSelectedState(true);
    }
  }

  for (Button button : modeButtons) {
    if (button != currentMode && button.isSelected() && button != toggleBlueLEDButton) {
      button.setSelectedState(false);
    }
  }
}

void createAnimationSequences() {
  transformUp = new AniSequence(this);
  transformUp.beginSequence();
  transformUp.add(Ani.to(this, ANIMATION_SPEED, "originY", -height/5, Ani.QUINT_IN_OUT));
  transformUp.endSequence();

  transformDown = new AniSequence(this);
  transformDown.beginSequence();
  transformDown.add(Ani.to(this, ANIMATION_SPEED, "originY", 0, Ani.QUINT_IN_OUT));
  transformDown.endSequence();

  spectrumOut = new AniSequence(this);
  spectrumOut.beginSequence();
  spectrumOut.add(Ani.to(this, ANIMATION_SPEED, "spectrumY", -height + height/16, Ani.QUINT_IN_OUT));
  spectrumOut.endSequence();

  spectrumIn = new AniSequence(this);
  spectrumIn.beginSequence();
  spectrumIn.add(Ani.to(this, ANIMATION_SPEED, "spectrumY", height/16, Ani.QUINT_IN_OUT));
  spectrumIn.endSequence();
}

void showToast(final String message) {
  final Activity act = this.getActivity();

  Runnable run = new Runnable() {
    public void run() {
      android.widget.Toast.makeText(act.getApplicationContext(), message, android.widget.Toast.LENGTH_SHORT).show();
    }
  };

  act.runOnUiThread(run);
}

void showKeyboard() {
  InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
  imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
  keyboardVisible = true;
}

void hideKeyboard() {
  InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
  imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
  keyboardVisible = false;
}

//======EXPERIMENTAL IP SETTINGS SAVING======
/*
void saveIP() {
 
 File settingsFolder = new File(directory);  
 if (!settingsFolder.exists()) {
 settingsFolder.mkdirs();
 }
 
 showToast(directory.toString());
 delay(2000);
 
 try {
 PrintWriter output = createWriter(directory + "/" + savedIPFile); 
 output.println(this.ipAddress);
 output.flush();
 output.close();
 } 
 catch (Exception e) {
 showToast(e.toString());
 }
 }
 
 void loadIP() {
 File loadedIP = new File(directory + "/" + savedIPFile);
 if (loadedIP.exists()) {
 this.ipAddress = loadStrings(loadedIP.getAbsoluteFile())[0];
 } else {
 this.ipAddress = "1234";
 }
 }*/