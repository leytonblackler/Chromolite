class ConnectButton implements Button {

  boolean pressed;
  
  ConnectButton() {
    pressed = false;
  }
  
  public void drawButton() {
    colorMode(RGB);
    
    if (connected) {
      return;
    }
    
    fill(#253447);
    if (pressed && !connecting) {
      stroke(#03a9f4);
      strokeWeight(width/200);
    } else {
      noStroke();
    }
    
    rect(buttonGap, height/2 - buttonHeight/2 + (2 * buttonHeight) + (2* buttonGap), buttonWidth, buttonHeight, cornerRadius);
    
    if (connecting) {
      fill(#495764);
    } else {
      fill(#bcc4cc);
    }
    textAlign(CENTER, CENTER);
    textSize(buttonTextSize);
    textFont(RobotoMedium);
    text("CONNECT", width/2, height/2 + (2 * buttonHeight) + (2* buttonGap) - buttonTextSize/5);
  }
  
  public boolean containsCursor() {
    
    return (mouseX > buttonGap
      && mouseX < (buttonWidth + buttonGap)
      && mouseY > (height/2 - buttonHeight/2) + (2 * buttonHeight) + (2* buttonGap) + originY
      && mouseY < (height/2 + buttonHeight/2) + (2 * buttonHeight) + (2* buttonGap) + originY);
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