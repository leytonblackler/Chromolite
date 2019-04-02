import java.net.Socket;

class PortBox {

  Textfield portTextfield;

  float barWidth;
  float paddingGap;
  float leftOffset;

  float textboxInitialWidth;
  float textboxInitialX;

  float x, y;

  PortBox() {

    paddingGap = buttonHeight/8;
    leftOffset = bigButtonWidth/3;
    barWidth = bigButtonWidth - leftOffset - paddingGap;

    x = 2*buttonGap + bigButtonWidth;
    y = height/2 + 12*buttonGap;

    textboxInitialWidth = barWidth/2 - paddingGap;
    textboxInitialX = x + leftOffset + barWidth/2;

    portTextfield = cp5.addTextfield("port")
      .setPosition(textboxInitialX, y + 2*paddingGap)
      .setSize(round(textboxInitialWidth), round(buttonHeight - 4*paddingGap))
      .setFont(RobotoBold)
      .setColor(#253447)
      .setColorCursor(#253447)
      .setAutoClear(false)
      .setInputFilter(ControlP5.INTEGER)
      .setText(Integer.toString(textfieldPort))
    ;

    portTextfield.getCaptionLabel().hide();
  }

  void drawBox() {
    if (portTextfield.getText().length() > 5) {
      portTextfield.setText(portTextfield.getText().substring(0, 5));
    }
    float yShift = (portTextfield.getText().length()/2) * width/100;
    portTextfield.setPosition(textboxInitialX - yShift, y + 2*paddingGap);
    portTextfield.setSize(round(textboxInitialWidth - yShift), round(buttonHeight - 4*paddingGap));

    if (portTextfield.getText().equals("")) {
      textfieldPort = -1;
    } else {
      textfieldPort = Integer.parseInt(portTextfield.getText());
    }

    noStroke();
    fill(#253447);
    rect(x, height/2 + 12*buttonGap, buttonWidth*2 + buttonGap, buttonHeight, cornerRadius);

    fill(#bcc4cc);
    stroke(#d50000);
    if (portNumberIsValid()) {
      noStroke();
    } else {
      strokeWeight(buttonHeight/25);
    }
    rect(x + leftOffset, height/2 + 12*buttonGap + paddingGap, barWidth, buttonHeight - (2 * paddingGap), cornerRadius);

    fill(#bcc4cc);
    textFont(RobotoBold);
    textAlign(CENTER, CENTER);
    text("PORT : ", x + leftOffset/2, height - 2*buttonGap - textHeight/10);
    fill(#253447);
  }

  boolean portNumberIsValid() {
    return (textfieldPort != -1 && textfieldPort < 49152 && !usedPorts.contains(textfieldPort));
  }
  
  boolean containsCursor() {
    return (mouseX > x + leftOffset && mouseX < x + bigButtonWidth - paddingGap && mouseY > y + paddingGap && mouseY < y + buttonHeight - paddingGap);
  }
  
  Textfield getTextfield() {
    return portTextfield;
  }
  
}