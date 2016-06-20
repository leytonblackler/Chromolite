/**=================================================
Chromolite LED Controller
Version: 1.0
Made by Leyton Blackler
=================================================**/

import processing.serial.*;

PImage image;
PImage icon;
PImage logo;
PFont arialBold8;
PFont arialBold11;
PFont arialBold14;
Serial serialPort;

private boolean portError = false;

private boolean blueLED = true;
private boolean staticMode = true;
private boolean randomMode = false;
private boolean waveMode = false;
private boolean spectrumMode = false;
private boolean strobeMode = false;
private boolean offMode = false;
private boolean musicMode = false;
private boolean firstMusic = false;
private boolean secondMusic = false;
private boolean thirdMusic = false;
private boolean pressedMouse = false;

private color sentColour;
private color firstColour = color(0, 255, 0);
private color secondColour = color(255, 0, 0);
private color thirdColour = color(0, 0, 255);

private int colourX = -1;
private int colourY = -1;
private int prevX = -1;
private int prevY = -1;

private int pressedKey = -1;

void setup() {
  
  //set these to the size of the image
  size(545,629);
  
  //this is the name of your image file saved in the data folder in your
  //processing folder see processing.org for help
  
  image = loadImage("spectrum.png");
  icon = loadImage("icon.png");
  logo = loadImage("logo.png");
  
  //the [0] may be [another number] on your computer
  try {
  serialPort = new Serial(this, Serial.list()[0], 9600);
  } catch (Exception e) {this.portError = true;}
  
  surface.setTitle("Chromolite");
  surface.setIcon(icon);
  
  arialBold11 = createFont("Arial Bold", 11);
  arialBold14 = createFont("Arial Bold", 14);
  arialBold8 = createFont("Arial Bold", 8);
  
}

void draw() {
  if (this.portError == true) {
    surface.setSize(400,100);
    background(#1F1F1F);
    fill(#FFFFFF);
    textFont(arialBold14);
    text("PORT COULD NOT BE FOUND", 95, 45);
    text("PLEASE EXIT AND TRY AGAIN", 95, 65);
    
  } else {
  //Draws background and spectrum image.
  background(#000000);
  image(image,0,40);
  image.loadPixels();
  
  //Draws credit text.
  textFont(arialBold8);
  fill(#888888);
  text("DEVELOPED BY LEYTON BLACKLER", 5, 623);
  text("VERSION: 1.0", 487, 623);
  
  if (this.colourX != -1 && this.colourY != -1) {
    drawSelectorCircle();
  }
  
  drawDividerLine();
  drawHeader();
  
  textFont(arialBold11);
  //4C4C4C
  
  //===================================================
  if (mouseOver("Static Mode")) {
    fill(#4C4C4C);
  } else {
    fill(#1F1F1F);
  }
  
  if (this.staticMode == true) {
    stroke(#0078d7);
  } else {
    stroke(#000000);
  }
  
  rect(5, 505, 100, 50);
    
  fill(#FFFFFF);
  text("STATIC", 36, 528);
  text("MODE", 38, 542);
  fill(#7b7b7b);
  text("1", 95, 550);
  //===================================================
  //===================================================
  if (mouseOver("Random Mode")) {
    fill(#4C4C4C);
  } else {
    fill(#1F1F1F);
  }
  
  if (this.randomMode == true) {
    stroke(#0078d7);
  } else {
    stroke(#000000);
  }
  
  rect(110, 505, 100, 50);
    
  fill(#FFFFFF);
  text("RANDOM", 135, 528);
  text("MODE", 143, 542);
  fill(#7b7b7b);
  text("2", 200, 550);
  //===================================================
  //===================================================
  if (mouseOver("Wave Mode")) {
    fill(#4C4C4C);
  } else {
    fill(#1F1F1F);
  }
  
  if (this.waveMode == true) {
    stroke(#0078d7);
  } else {
    stroke(#000000);
  }
  
  rect(215, 505, 100, 50);
    
  fill(#FFFFFF);
  text("WAVE", 248, 528);
  text("MODE", 248, 542);
  fill(#7b7b7b);
  text("3", 305, 550);
  //===================================================
  //===================================================
  if (mouseOver("Spectrum Mode")) {
    fill(#4C4C4C);
  } else {
    fill(#1F1F1F);
  }
  
  if (this.spectrumMode == true) {
    stroke(#0078d7);
  } else {
    stroke(#000000);
  }
  
  rect(5, 560, 100, 50);
    
  fill(#FFFFFF);
  text("SPECTRUM", 24, 583);
  text("MODE", 38, 597);
  fill(#7b7b7b);
  text("5", 95, 605);
  //===================================================
  //===================================================
  if (mouseOver("Strobe Mode")) {
    fill(#4C4C4C);
  } else {
    fill(#1F1F1F);
  }
  
  if (this.strobeMode == true) {
    stroke(#0078d7);
  } else {
    stroke(#000000);
  }
  
  rect(110, 560, 100, 50);
    
  fill(#FFFFFF);
  text("STROBE", 138, 583);
  text("MODE", 143, 597);
  fill(#7b7b7b);
  text("6", 200, 605);
  //===================================================
  //===================================================
  if (mouseOver("Off Mode")) {
    fill(#4C4C4C);
  } else {
    fill(#1F1F1F);
  }
  
  if (this.offMode == true) {
    stroke(#0078d7);
  } else {
    stroke(#000000);
  }
  
  rect(215, 560, 100, 50);
    
  fill(#FFFFFF);
  text("OFF", 254, 583);
  text("MODE", 248, 597);
  fill(#7b7b7b);
  text("7", 305, 605);
  //===================================================
  //===================================================
  if (mouseOver("Music Mode")) {
    fill(#4C4C4C);
  } else {
    fill(#1F1F1F);
  }
  
  if (this.musicMode == true) {
    stroke(#0078d7);
  } else {
    stroke(#000000);
  }
  
  rect(320, 505, 100, 50);
    
  fill(#FFFFFF);
  text("MUSIC", 352, 528);
  text("MODE", 353, 542);
  fill(#7b7b7b);
  text("4", 410, 550);
  //===================================================
  //===================================================
  if (this.firstMusic == true) {
    stroke(#0078d7);
  } else {
    stroke(#1F1F1F);
  }
  
  fill(this.firstColour);
  rect(320, 560, 30, 50);
  
  if (mouseOver("First Music")) {
    fill(#FFFFFF, 50);
    rect(320, 560, 30, 50);
  }
  //===================================================
  //===================================================
  if (this.secondMusic == true) {
    stroke(#0078d7);
  } else {
    stroke(#1F1F1F);
  }
  
  fill(this.secondColour);
  rect(355, 560, 30, 50);
  
  if (mouseOver("Second Music")) {
    fill(#FFFFFF, 50);
    rect(355, 560, 30, 50);
  }
  //===================================================
  //===================================================
  if (this.thirdMusic == true) {
    stroke(#0078d7);
  } else {
    stroke(#1F1F1F);
  }
  
  fill(this.thirdColour);
  rect(390, 560, 30, 50);
  
  if (mouseOver("Third Music")) {
    fill(#FFFFFF, 50);
    rect(390, 560, 30, 50);
  }
  //===================================================
  //===================================================
  if (mouseOver("Toggle Blue LED")) {
    fill(#4C4C4C);
  } else {
    fill(#1F1F1F);
  }
  
  if (this.blueLED == true) {
    stroke(#0078d7);
  } else {
    stroke(#000000);
  }

  rect(440, 505, 100, 50);
    
  fill(#FFFFFF);
  text("TOGGLE", 469, 528);
  text("BLUE LED", 465, 542);
  //===================================================
  //===================================================
  if (mouseOver("Exit")) {
    fill(#4C4C4C);
  } else {
    fill(#1F1F1F);
  }
  
  stroke(#000000);
  
  rect(440, 560, 100, 50);
    
  fill(#FFFFFF);
  text("EXIT", 478, 589);
  //===================================================
  }
}

/**

P = toggle colour picker mode
M = toggle music mode
B = toggle blue led
W = toggle wave mode
S = toggle spectrum mode
R = toggle strobe mode
O = toggle off mode

**/

boolean mouseOver(String button) {
  if (button.equals("Static Mode")) {
    if (mouseX >= 5 && mouseX <= 105 && mouseY >= 505 && mouseY <= 555) {
      return true;
    } else {
      return false;
    }
  } else if (button.equals("Random Mode")) {
    if (mouseX >= 110 && mouseX <= 210 && mouseY >= 505 && mouseY <= 555) {
      return true;
    } else {
      return false;
    }
  } else if (button.equals("Wave Mode")) {
    if (mouseX >= 215 && mouseX <= 315 && mouseY >= 505 && mouseY <= 555) {
      return true;
    } else {
      return false;
    }
  } else if (button.equals("Spectrum Mode")) {
    if (mouseX >= 5 && mouseX <= 105 && mouseY >= 560 && mouseY <= 615) {
      return true;
    } else {
      return false;
    }
  } else if (button.equals("Strobe Mode")) {
    if (mouseX >= 110 && mouseX <= 210 && mouseY >= 560 && mouseY <= 615) {
      return true;
    } else {
      return false;
    }
  } else if (button.equals("Off Mode")) {
    if (mouseX >= 215 && mouseX <= 315 && mouseY >= 560 && mouseY <= 615) {
      return true;
    } else {
      return false;
    }
  } else if (button.equals("Music Mode")) {
    if (mouseX >= 320 && mouseX <= 420 && mouseY >= 505 && mouseY <= 555) {
      return true;
    } else {
      return false;
    }
  } else if (button.equals("First Music")) {
    if (mouseX >= 320 && mouseX <= 350 && mouseY >= 560 && mouseY <= 615) {
      return true;
    } else {
      return false;
    }
  } else if (button.equals("Second Music")) {
    if (mouseX >= 355 && mouseX <= 385 && mouseY >= 560 && mouseY <= 615) {
      return true;
    } else {
      return false;
    }
  } else if (button.equals("Third Music")) {
    if (mouseX >= 390 && mouseX <= 420 && mouseY >= 560 && mouseY <= 615) {
      return true;
    } else {
      return false;
    }
  } else if (button.equals("Toggle Blue LED")) {
    if (mouseX >=440 && mouseX <= 540 && mouseY >= 505 && mouseY <= 555) {
      return true;
    } else {
      return false;
    }
  } else if (button.equals("Exit")) {
    if (mouseX >=440 && mouseX <= 540 && mouseY >= 560 && mouseY <= 615) {
      return true;
    } else {
      return false;
    }
  }
  return false;
}

void mousePressed() {
  this.pressedMouse = true;
  changeMode();
  this.pressedMouse = false;
}

void mouseDragged() {
  if (mouseY <= 500 && mouseY >= 40) {
    if (this.prevX == -1) {
      this.prevX = mouseX;
    }
    if (this.prevY == -1) {
      this.prevY = mouseY;
    }
    colourX = mouseX;
    colourY = mouseY;
    if ((mouseX > this.prevX + 6 || mouseX < this.prevX - 6) || (mouseY > this.prevY + 6 || mouseY < this.prevY - 6)) {
      sendColor();
      if (this.firstMusic) {
        this.firstColour = this.sentColour;
      } else if (this.secondMusic) {
        this.secondColour = this.sentColour;
      } else if (this.thirdMusic) {
        this.thirdColour = this.sentColour;
      }
      this.prevX = mouseX;
      this.prevY = mouseY;
    }
  }
}

void mouseReleased() {
  this.prevX = -1;
  this.prevY = -1;
}

void keyPressed() {
  this.pressedKey = key;
  changeMode();
}

void changeMode() {
  
  //Mouse pressed on colour picker button.
  if ((mouseOver("Static Mode") && this.pressedMouse) || this.pressedKey == '1') {
      this.staticMode = true;
      this.musicMode = false;
      this.waveMode = false;
      this.spectrumMode = false;
      this.offMode = false;
      this.strobeMode = false;
      this.randomMode = false;
      this.firstMusic = false;
      this.secondMusic = false;
      this.thirdMusic = false;
      this.pressedKey = '0';
      serialPort.write("P");
  }
  
  //Mouse pressed on random mode button.
  if ((mouseOver("Random Mode") && this.pressedMouse) || this.pressedKey == '2') {
      this.randomMode = true;
      this.musicMode = false;
      this.staticMode = false;
      this.waveMode = false;
      this.spectrumMode = false;
      this.offMode = false;
      this.strobeMode = false;
      this.firstMusic = false;
      this.secondMusic = false;
      this.thirdMusic = false;
      this.pressedKey = '0';
      serialPort.write("R");
  }
  
  //Mouse pressed on music mode button.
  if ((mouseOver("Wave Mode") && this.pressedMouse) || this.pressedKey == '3') {
      this.waveMode = true;
      this.staticMode = false;
      this.musicMode = false;
      this.spectrumMode = false;
      this.offMode = false;
      this.strobeMode = false;
      this.randomMode = false;
      this.firstMusic = false;
      this.secondMusic = false;
      this.thirdMusic = false;
      this.pressedKey = '0';
      serialPort.write("W");
  }
  
  //Mouse pressed on music mode button.
  if ((mouseOver("Music Mode") && this.pressedMouse) || this.pressedKey == '4') {
      this.musicMode = true;
      this.staticMode = false;
      this.randomMode = false;
      this.waveMode = false;
      this.spectrumMode = false;
      this.offMode = false;
      this.strobeMode = false;
      this.firstMusic = false;
      this.secondMusic = false;
      this.thirdMusic = false;
      this.pressedKey = '0';
      serialPort.write("M");
  }
  
  //Mouse pressed on music mode button.
  if ((mouseOver("Spectrum Mode") && this.pressedMouse) || this.pressedKey == '5') {
      this.spectrumMode = true;
      this.staticMode = false;
      this.musicMode = false;
      this.waveMode = false;
      this.offMode = false;
      this.strobeMode = false;
      this.randomMode = false;
      this.firstMusic = false;
      this.secondMusic = false;
      this.thirdMusic = false;
      this.pressedKey = '0';
      serialPort.write("S");
  }
  
  //Mouse pressed on strobe mode button.
  if ((mouseOver("Strobe Mode") && this.pressedMouse) || this.pressedKey == '6') {
      this.strobeMode = true;
      this.staticMode = false;
      this.randomMode = false;
      this.musicMode = false;
      this.spectrumMode = false;
      this.waveMode = false;
      this.offMode = false;
      this.firstMusic = false;
      this.secondMusic = false;
      this.thirdMusic = false;
      this.pressedKey = '0';
      serialPort.write("T");
  }
  
  //Mouse pressed on music mode button.
  if ((mouseOver("Off Mode") && this.pressedMouse) || this.pressedKey == '7') {
      this.offMode = true;
      this.staticMode = false;
      this.randomMode = false;
      this.musicMode = false;
      this.waveMode = false;
      this.spectrumMode = false;
      this.strobeMode = false;
      this.randomMode = false;
      this.firstMusic = false;
      this.secondMusic = false;
      this.thirdMusic = false;
      this.pressedKey = '0';
      serialPort.write("O");
  }
  
  //Mouse pressed on the first music colour button.
  if ((mouseOver("First Music") && this.pressedMouse)) {
      this.firstMusic = true;
      this.musicMode = false;
      this.staticMode = false;
      this.randomMode = false;
      this.waveMode = false;
      this.spectrumMode = false;
      this.offMode = false;
      this.strobeMode = false;
      this.secondMusic = false;
      this.thirdMusic = false;
      this.pressedKey = '0';
      serialPort.write("X");
  }
  
  //Mouse pressed on the second music colour button.
  if ((mouseOver("Second Music") && this.pressedMouse)) {
      this.secondMusic = true;
      this.musicMode = false;
      this.staticMode = false;
      this.randomMode = false;
      this.waveMode = false;
      this.spectrumMode = false;
      this.offMode = false;
      this.strobeMode = false;
      this.firstMusic = false;
      this.thirdMusic = false;
      this.pressedKey = '0';
      serialPort.write("Y");
  }
  
  //Mouse pressed on the third music colour button.
  if ((mouseOver("Third Music") && this.pressedMouse)) {
      this.thirdMusic = true;
      this.musicMode = false;
      this.staticMode = false;
      this.randomMode = false;
      this.waveMode = false;
      this.spectrumMode = false;
      this.offMode = false;
      this.strobeMode = false;
      this.firstMusic = false;
      this.secondMusic = false;
      this.pressedKey = '0';
      serialPort.write("Z");
  }
  
  //Mouse pressed on blue LED toggle button.
  if ((mouseOver("Toggle Blue LED") && this.pressedMouse)) {
    if (this.blueLED == true) {
      this.blueLED = false;
    } else {
      this.blueLED = true;
    }
    this.pressedKey = '0';
    serialPort.write("B");
  }
  
  //Mouse pressed on blue LED toggle button.
  if ((mouseOver("Exit") && this.pressedMouse)) {
    stroke(#0078d7);
    fill(#4C4C4C);
    rect(440, 560, 100, 50);
    fill(#FFFFFF);
    text("EXIT", 478, 589);
    exit();
  }
  
  if (mouseY >= 40 && mouseY <= 500 && this.pressedMouse) {
    colourX = mouseX;
    colourY = mouseY;
    sendColor();
    if (this.firstMusic) {
      this.firstColour = this.sentColour;
    } else if (this.secondMusic) {
      this.secondColour = this.sentColour;
    } else if (this.thirdMusic) {
      this.thirdColour = this.sentColour;
    }
  }
}

//Draws circle at selected colour.
void drawSelectorCircle() {
  stroke(#000000);
  strokeWeight(1.5);
  fill(this.sentColour);
  ellipse((this.colourX), (this.colourY), 10, 10);
  
  //Sets stroke weight back to default.
  strokeWeight(1);
}

void drawHeader() {
  image(logo,0,0);
  //Draws line between spectrum and logo.
  strokeWeight(3);
  stroke(#1F1F1F);
  //line(0, 37, 545, 37);
  //line(0, 3, 545, 3);
  strokeWeight(1);
  stroke(#000000);
}

void drawDividerLine() {
  //Draws line between spectrum and controls.
  strokeWeight(3);
  stroke(#1F1F1F);
  line(0, 500-3/2, 545, 500-3/2);
  strokeWeight(1);
  stroke(#000000);
}

void sendColor() {
  try {
    serialPort.write("CL");
    int red = int(red(image.pixels[mouseX+(mouseY-40)*image.width]));
    int green = int(green(image.pixels[mouseX+(mouseY-40)*image.width]));
    int blue = int(blue(image.pixels[mouseX+(mouseY-40)*image.width]));
    serialPort.write(red);
    serialPort.write(green); 
    serialPort.write(blue);
    this.sentColour = color(red, green, blue);
  } catch (Exception e) {}
}
  