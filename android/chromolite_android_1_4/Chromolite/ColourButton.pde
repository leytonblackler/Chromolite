class ColourButton implements Button {

  String label;
  float x, y;

  boolean pressed;
  boolean selected;

  int[] rgbValues;
  color buttonColour;
  color textColour;
  
  float stroke;

  ColourButton(String label, float x, float y, int r, int g, int b) {
    this.label = label;
    this.x = x;
    this.y = y;

    rgbValues = new int[] {r, g, b};

    pressed = false;
    selected = false;
    
    stroke = width/40;
  }

  public void drawButton() {
    colorMode(RGB);

    calculateColours();

    noStroke();
    if (pressed) {
      //stroke(#03a9f4);
      //strokeWeight(width/200);
      fill(textColour);
    } else {
      fill(buttonColour);
    }

    if (selected) {
      if (pressed) {
        stroke(textColour);
      } else {
        stroke(buttonColour);
      }
      strokeWeight(stroke);
      rect(x, y, colourButtonWidth, buttonHeight/2, cornerRadius/3);
    } else {
      rect(x, y, colourButtonWidth, buttonHeight/2, cornerRadius);
    }
    colorMode(HSB);
    if (pressed) {
      fill(buttonColour);
    } else {
      fill(textColour);
    }
    textAlign(CENTER, CENTER);
    textSize(buttonTextSize);
    textFont(RobotoBold);
    text(label, x + colourButtonWidth/2, y + buttonHeight/4 - textHeight/10);
  }

  public boolean containsCursor() {
    if (selected) {
      return (mouseX > x - stroke && mouseX < x + stroke + colourButtonWidth && mouseY > y - stroke && mouseY < y + stroke + buttonHeight/2);
    } else {
      return (mouseX > x && mouseX < x + colourButtonWidth && mouseY > y && mouseY < y + buttonHeight/2);
    }
  }

  public void setPressedState(boolean pressed) {
    this.pressed = pressed;
  }

  public void setSelectedState(boolean selected) {
    this.selected = selected;
  }

  void calculateColours() {
    buttonColour = color(rgbValues[0], rgbValues[1], rgbValues[2]);

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
    rgbValues[0] = r;
    rgbValues[1] = g;
    rgbValues[2] = b;
  }

  public boolean isSelected() {
    return selected;
  }

  public boolean isSelectable() {
    return false;
  }
  
  public boolean isPressed() {
    return pressed;
  }

  public String getAction() {
    return null;
  }
  
  public void setNumberOfColoursUsed(int coloursUsed) {
    return;
  }
}