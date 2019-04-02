class ModeButton implements Button {

  float x, y;
  String label, action;

  boolean pressed;
  boolean selected;
  boolean selectable;
  
  int coloursUsed;

  ModeButton(String label, String action, float x, float y, boolean selectable, int coloursUsed) {
    this.label = label;
    this.action = action;
    this.x = x;
    this.y = y;
    this.coloursUsed = coloursUsed;

    pressed = false;
    selected = false;
    this.selectable = selectable;
  }

  public void drawButton() {
    colorMode(RGB);

    fill(currentButtonColour());

    if (pressed) {
      stroke(#03a9f4);
      strokeWeight(width/200);
    } else {
      noStroke();
    }
    rect(x, y, smallButtonWidth, buttonHeight, cornerRadius);
    
    if (selected) {
      fill(#1c2939);
    } else {
      fill(#bcc4cc);
    }
    
    if (!selectable) {
      fill(#495764);
    }
    
    textAlign(CENTER, CENTER);
    textSize(buttonTextSize);
    textFont(RobotoMedium);
    text(label, x + smallButtonWidth/2, y + buttonHeight/2 - buttonTextSize/5);
    
    if (coloursUsed > 0) {
      noStroke();

      if (coloursUsed >= 1) {
        fill(coloursRGB[0][0], coloursRGB[0][1], coloursRGB[0][2]);
      } else {
        fill(currentButtonColour());
      }
      rect(x + 1, y + buttonHeight - buttonHeight/6, smallButtonWidth/3 + smallButtonWidth/6, buttonHeight/6, cornerRadius);

      if (coloursUsed >= 3) {
        fill(coloursRGB[2][0], coloursRGB[2][1], coloursRGB[2][2]);
      } else {
        fill(currentButtonColour());
      }
      rect(x + 2*(smallButtonWidth/3) - smallButtonWidth/6, y + buttonHeight - buttonHeight/6, smallButtonWidth/3 + smallButtonWidth/6, buttonHeight/6, cornerRadius);

      if (coloursUsed >= 2) {
        fill(coloursRGB[1][0], coloursRGB[1][1], coloursRGB[1][2]);
      } else {
        fill(currentButtonColour());
      }
      rect(x + smallButtonWidth/3, y + buttonHeight - buttonHeight/6, smallButtonWidth/3, buttonHeight/6);

      fill(currentButtonColour());
      if (pressed && !selected) {
        rect(x + 3, y + buttonHeight/1.5, smallButtonWidth - 6, buttonHeight/4);
      } else {
        rect(x, y + buttonHeight/1.5, smallButtonWidth, buttonHeight/4);
      }
    }
  }

  public boolean containsCursor() {
    return (mouseX > x && mouseX < x + smallButtonWidth && mouseY > y && mouseY < y + buttonHeight);
  }

  public void setPressedState(boolean pressed) {
    this.pressed = pressed;
  }

  public void setSelectedState(boolean selected) {
    this.selected = selected;
  }
  
  public boolean isSelected() {
    return selected;
  }
  
  public boolean isSelectable() {
    return selectable;
  }
  
  public boolean isPressed() {
    return pressed;
  }
  
  public String getAction() {
    return action;
  }
  
  color currentButtonColour() {
    if (selected) {
      return #03a9f4;
    } else {
      return #253447;
    }
  }
  
  public void setNumberOfColoursUsed(int coloursUsed) {
    if (label != "STATIC") return;
    
    this.coloursUsed = coloursUsed;
    if (coloursUsed == 1) {
      action = "static1";
    } else if (coloursUsed == 2) {
      action = "static2";
    } else if (coloursUsed == 3) {
      action = "static3";
    }
  }
}