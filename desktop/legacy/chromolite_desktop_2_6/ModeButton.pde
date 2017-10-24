class ModeButton {

  float x, y;
  String label, action;
  boolean selectable;

  boolean pressed;
  boolean hovering;
  boolean selected;

  float modeButtonWidth;

  int coloursUsed;
  
  char shortcutKey;

  ModeButton(String label, String action, float x, float y, boolean selectable, boolean smallSize, int coloursUsed, char shortcutKey) {

    this.label = label;
    this.action = action;
    this.selectable = selectable;
    this.x = x;
    this.y = y;
    this.coloursUsed = coloursUsed;
    this.shortcutKey = shortcutKey;

    if (smallSize) {
      modeButtonWidth = buttonWidth;
    } else {
      modeButtonWidth = 2*buttonWidth + buttonGap;
    }

    pressed = false;
    hovering = false;
    selected = false;
  }

  void drawButton() {
    colorMode(RGB);

    if (selectable) {
      if (selected) {
        if (containsCursor()) {
          fill(#1cb1f4);
        } else {
          fill(#03a9f4);
        }
      } else {
        if (containsCursor()) {
          fill(#2c3b4f);
        } else {
          fill(#253447);
        }
      }
    } else {
      fill(#253447);
    }

    stroke(#03a9f4);
    if ((pressed && selectable) && (!selected || action.equals("toggle_blue_led"))) {
      strokeWeight(buttonHeight/25);
    } else {
      noStroke();
    }

    rect(x, y, modeButtonWidth, buttonHeight, cornerRadius);

    if (selected) {
      fill(#1c2939);
    } else {
      fill(#bcc4cc);
    }

    if (!selectable) {
      fill(#495764);
    }

    textAlign(CENTER, CENTER);
    textFont(RobotoBold);
    text(label, x + modeButtonWidth/2, y + buttonHeight/2 - textHeight/10);
    text(shortcutKey, x + modeButtonWidth - buttonGap/3, y + buttonGap/3);

    if (coloursUsed > 0) {
      noStroke();

      if (coloursUsed >= 1) {
        fill(coloursRGB[0][0], coloursRGB[0][1], coloursRGB[0][2]);
      } else {
        fill(currentButtonColour());
      }
      rect(x + 1, y + buttonHeight - buttonHeight/6, modeButtonWidth/3 + modeButtonWidth/6, buttonHeight/6, cornerRadius);

      if (coloursUsed >= 3) {
        fill(coloursRGB[2][0], coloursRGB[2][1], coloursRGB[2][2]);
      } else {
        fill(currentButtonColour());
      }
      rect(x + 2*(modeButtonWidth/3) - modeButtonWidth/6, y + buttonHeight - buttonHeight/6, modeButtonWidth/3 + modeButtonWidth/6, buttonHeight/6, cornerRadius);

      if (coloursUsed >= 2) {
        fill(coloursRGB[1][0], coloursRGB[1][1], coloursRGB[1][2]);
      } else {
        fill(currentButtonColour());
      }
      rect(x + modeButtonWidth/3, y + buttonHeight - buttonHeight/6, modeButtonWidth/3, buttonHeight/6);

      fill(currentButtonColour());
      if (pressed && !selected) {
        rect(x + 1, y + buttonHeight/1.5, modeButtonWidth - 2, buttonHeight/4);
      } else {
        rect(x, y + buttonHeight/1.5, modeButtonWidth, buttonHeight/4);
      }
    }
  }

  boolean containsCursor() {
    return (mouseX > x && mouseX < x + modeButtonWidth && mouseY > y && mouseY < y + buttonHeight);
  }

  void setPressedState(boolean pressed) {
    this.pressed = pressed;
  }

  void setSelectedState(boolean selected) {
    this.selected = selected;
  }

  boolean isSelected() {
    return selected;
  }

  boolean isSelectable() {
    return selectable;
  }

  String getAction() {
    return action;
  }

  boolean isPressed() {
    return pressed;
  }
  
  char getKey() {
    return shortcutKey;
  }

  color currentButtonColour() {
    if (selected) {
      if (containsCursor()) {
        return #1cb1f4;
      } else {
        return #03a9f4;
      }
    } else {
      if (containsCursor()) {
        return #2c3b4f;
      } else {
        return #253447;
      }
    }
  }
  
  void setNumberOfColoursUsed(int coloursUsed) {
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
  
  int getColoursUsed() {
    return coloursUsed;
  }
}