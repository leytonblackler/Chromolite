class IpBox {

  String serverIP;

  float barWidth;
  float paddingGap;
  float leftOffset;

  IpBox() {
    paddingGap = buttonHeight/8;
    leftOffset = bigButtonWidth/3;
    barWidth = bigButtonWidth - leftOffset - paddingGap;
    
    //Finds server IP.
    try {
      inet = InetAddress.getLocalHost();
      serverIP = inet.getHostAddress();
    }
    catch (Exception e) {
      e.printStackTrace();
      serverIP = "Couldn't get IP.";
    }
  }

  void drawBox() {
    noStroke();
    fill(#253447);
    rect(buttonGap, height/2 + 12*buttonGap, buttonWidth*2 + buttonGap, buttonHeight, cornerRadius);

    fill(#bcc4cc);
    rect(buttonGap + leftOffset, height/2 + 12*buttonGap + paddingGap, barWidth, buttonHeight - (2 * paddingGap), cornerRadius);
    
    fill(#bcc4cc);
    textFont(RobotoBold);
    textAlign(CENTER, CENTER);
    text("SERVER IP : ", buttonGap + leftOffset/2, height - 2*buttonGap - textHeight/10);
    fill(#253447);
    text(serverIP, buttonGap + leftOffset + barWidth/2, height - 2*buttonGap - textHeight/10);
  }
}