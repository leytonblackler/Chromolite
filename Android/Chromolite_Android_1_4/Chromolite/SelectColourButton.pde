class SelectColourButton implements Button {

  float x, y;
  boolean pressed;

  SelectColourButton() {
    x = buttonGap;
    y = bannerHeight + 7*buttonGap + 5.5*buttonHeight;
    pressed = false;
  }

  public void drawButton() {
    colorMode(RGB);

    fill(#253447);
    if (pressed) {
      stroke(#03a9f4);
      strokeWeight(width/200);
    } else {
      noStroke();
    }
    rect(x, y, buttonWidth, buttonHeight, cornerRadius);
    fill(#bcc4cc);
    textAlign(CENTER, CENTER);
    textSize(buttonTextSize);
    textFont(RobotoMedium);
    if (colourPickerOpen) {
      text("CLOSE COLOUR PICKER", width/2, y + buttonHeight/2 - buttonTextSize/5);
    } else {
      text("OPEN COLOUR PICKER", width/2, y + buttonHeight/2 - buttonTextSize/5);
    }
  }

  public boolean containsCursor() {
    return (mouseX > x && mouseX < x + buttonWidth && mouseY > y && mouseY < y + buttonHeight);
  }

  public void setPressedState(boolean pressed) {
    this.pressed = pressed;
  }
  
  public void setSelectedState(boolean selected) {
    return;
  }
  
  public boolean isSelected() {
    return false;
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