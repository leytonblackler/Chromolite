class TryAgainButton {

  float x, y;

  boolean pressed;
  boolean hovering;

  float buttonGap;
  float buttonWidth;
  float buttonHeight;

  TryAgainButton() {

    buttonGap = width / 24;
    buttonWidth = width - (2 * buttonGap);
    buttonHeight = height/2 - (3 * buttonGap);

    pressed = false;
    hovering = false;
  }

  void drawButton() {
    colorMode(RGB);

    if (containsCursor()) {
      fill(#2c3b4f);
    } else {
      fill(#253447);
    }
    
    stroke(#03a9f4);
    if (pressed) {
      strokeWeight(buttonHeight/20);
    } else {
      noStroke();
    }
    
    rect(buttonGap, height/2 + 2*buttonGap, buttonWidth, buttonHeight, width/80);
    
    fill(#bcc4cc);
    textAlign(CENTER, CENTER);
    textFont(RobotoBold);
    textSize(10);
    text("TRY AGAIN", buttonGap + buttonWidth/2, height/2 + 2*buttonGap + buttonHeight/2 - buttonGap/10);
  }

  boolean containsCursor() {
    return (mouseX > buttonGap && mouseX < width - buttonGap && mouseY > height/2 + 2*buttonGap && mouseY < height - buttonGap);
  }

  void setPressedState(boolean pressed) {
    this.pressed = pressed;
  }

  boolean isPressed() {
    return pressed;
  }
}