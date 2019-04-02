class TextInputBar {

  PImage clearButtonUnpressed;
  PImage clearButtonPressed;

  boolean clearIsPressed;
  boolean textBoxPressed;
  boolean textBoxSelected;

  String title;
  float x, y;

  String input;

  float barWidth;
  float paddingGap;
  float leftOffset;

  TextInputBar(String title, String input, float x, float y) {
    this.title = title;
    this.x = x;
    this.y = y;
    this.input = input;

    barWidth = width - (4 * buttonGap);
    paddingGap = buttonHeight/8;
    leftOffset = width/5;

    clearButtonUnpressed = loadImage("clear_button_unpressed.png");
    clearButtonPressed = loadImage("clear_button_pressed.png");

    clearIsPressed = false;
    textBoxPressed = false;
    textBoxSelected = false;
  }

  void drawTextInputBar() {
    colorMode(RGB);

    noStroke();
    fill(#253447);
    rect(x, y, barWidth, buttonHeight, cornerRadius);

    if (textBoxPressed) {
      fill(#aeb7be);
    } else {
      fill(#bcc4cc);
    }
    if (textBoxSelected) {
      strokeWeight(width/130);
      stroke(#03a9f4);
    }
    rect(x + leftOffset, y + paddingGap, barWidth - leftOffset - paddingGap, buttonHeight - (2 * paddingGap), cornerRadius);

    fill(#bcc4cc);
    textSize(buttonTextSize);
    textFont(RobotoMedium);
    textAlign(CENTER);
    text(title, x + leftOffset/2, y + buttonHeight/2 + paddingGap/1.5);
    fill(#253447);
    text(input, buttonGap + leftOffset + (barWidth - leftOffset - paddingGap)/2, y + buttonHeight/2 + paddingGap/1.5);

    clearButtonUnpressed.resize(0, round(buttonHeight - (2 * paddingGap)));
    clearButtonPressed.resize(0, round(buttonHeight - (2 * paddingGap)));
    imageMode(CENTER);
    PImage clearButton;
    if (clearIsPressed) {
      clearButton = clearButtonPressed;
    } else {
      clearButton = clearButtonUnpressed;
    }
    image(clearButton, buttonGap + barWidth + buttonGap + paddingGap, y + buttonHeight/2);
  }

  boolean cursorOverTextBox() {
    return (mouseX > buttonGap + leftOffset
      && mouseX < buttonGap + barWidth - paddingGap
      && mouseY > y + paddingGap + originY
      && mouseY < y + buttonHeight - paddingGap + originY);
  }

  boolean cursorOverClearButton() {
    return (mouseX > buttonGap + barWidth + buttonGap + paddingGap
      && mouseX < width - buttonGap
      && mouseY > y + paddingGap + originY
      && mouseY < y + buttonHeight - paddingGap + originY);
  }

  boolean isSelected() {
    return textBoxSelected;
  }

  void setClearButtonPressedState(boolean pressed) {
    if (pressed) {
      clearIsPressed = true;
    } else {
      clearIsPressed = false;
    }
  }

  void setTextBoxPressedState(boolean pressed) {
    textBoxPressed = pressed;
  }

  void setTextBoxSelectedState(boolean selected) {
    textBoxSelected = selected;
  }

  void clearInput() {
    input = "";
  }
  
  void setInput(String input) {
    this.input = input;
  }

  void addCharacter(char character) {
    input += character;
  }

  String getValue() {
    return input;
  }
}