class ColourButton {
  String label;
  float x, y;

  int type;

  boolean pressed;
  boolean selected;

  color buttonColour;
  color textColour;
  color mouseoverColour;

  float stroke;

  ColourButton(String label, float x, float y) {
    this.label = label;
    this.x = x;
    this.y = y;

    if (label.equals("PRIMARY")) {
      type = 0;
    } else if (label.equals("SECONDARY")) {
      type = 1;
    } else if (label.equals("TERTIARY")) {
      type = 2;
    } else {
      type = -1;
    }

    pressed = false;
    selected = false;

    stroke = width/80;
  }

  public void drawButton() {
    colorMode(RGB);

    calculateColours();

    textAlign(CENTER, CENTER);
    textFont(RobotoBold);

    if (selected) {
      //selected
      stroke(buttonColour);
      strokeWeight(stroke);
      fill(buttonColour);
      rect(x, y, colourButtonWidth, buttonHeight/2, cornerRadius/5);
      fill(textColour);
      text(label, x + colourButtonWidth/2, y + buttonHeight/4 - textHeight/10);
      
    } else {
      //not selected
      if (pressed) {
        stroke(buttonColour);
        strokeWeight(buttonHeight/25);
        fill(textColour);
        rect(x, y, colourButtonWidth, buttonHeight/2, cornerRadius);
        fill(buttonColour);
        text(label, x + colourButtonWidth/2, y + buttonHeight/4 - textHeight/10);
        
      } else if (containsCursor()) {
        noStroke();
        fill(textColour);
        rect(x, y, colourButtonWidth, buttonHeight/2, cornerRadius);
        fill(buttonColour);
        text(label, x + colourButtonWidth/2, y + buttonHeight/4 - textHeight/10);
        
      } else {
        noStroke();
        fill(buttonColour);
        rect(x, y, colourButtonWidth, buttonHeight/2, cornerRadius);
        fill(textColour);
        text(label, x + colourButtonWidth/2, y + buttonHeight/4 - textHeight/10);
      }
    }
  }

  boolean containsCursor() {
    if (selected) {
      return (mouseX > x - stroke && mouseX < x + stroke + colourButtonWidth && mouseY > y - stroke && mouseY < y + stroke + buttonHeight/2);
    } else {
      return (mouseX > x && mouseX < x + colourButtonWidth && mouseY > y && mouseY < y + buttonHeight/2);
    }
  }

  void setPressedState(boolean pressed) {
    this.pressed = pressed;
  }

  void setSelectedState(boolean selected) {
    this.selected = selected;
  }

  void calculateColours() {
    buttonColour = color(coloursRGB[type][0], coloursRGB[type][1], coloursRGB[type][2]);

    colorMode(HSB);
    if (brightness(buttonColour) > 127) {
      textColour = color((int) hue(buttonColour), (int) saturation(buttonColour), (int) brightness(buttonColour)/2);
    } else if (brightness(buttonColour) < 30){
      textColour = color(#bcc4cc);
    } else {
      textColour = color((int) hue(buttonColour), (int) saturation(buttonColour), (int) brightness(buttonColour)*2);
    }
  }
  
  void setColour(int r, int g, int b) {
    buttonColour = color(r, g, b);
  }

  boolean isSelected() {
    return selected;
  }

  boolean isSelectable() {
    return true;
  }
  
  boolean isPressed() {
    return pressed;
  }
}