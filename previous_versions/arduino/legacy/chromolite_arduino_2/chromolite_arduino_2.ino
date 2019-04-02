#include <OscSerial.h>
#include <Adafruit_NeoPixel.h>
#include <EEPROM.h>

#define PIN 8
#define lightCount 60
Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, PIN, NEO_GRB + NEO_KHZ800);

OscSerial oscSerial;

//Strobe variables.
long delayTime;
boolean strobeLightsOn;

String currentMode;

int wavePosition;

int scanPosition;
boolean scanForward;

boolean blueLEDOn;

//Set the default colours.
int coloursRGB[3][3] = {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}};

//=========================MUSIC MODE=========================
int vol = 0;
float total = 0;
int fadeCol = 0;
int val[30];
int volLast = 0;
int fadeAmt = 0;
int counter = 0;
//============================================================
//=======================SPECTRUM CYCLE=======================
//Spectrum cycle colours
int lightPurple[3]  = { 255, 0, 255 };
int red[3]  = { 255, 0, 0 };
int orange[3]    = { 255, 165, 0 };
int yellow[3]  = { 255, 255, 0 };
int green[3]   = { 0, 255, 0 };
int cyan[3] = { 0, 255, 255 };
int blue[3] = { 0, 0, 255 };
int darkPurple[3] = { 128, 0, 128 };

// Set initial color
int redSpectrum = darkPurple[0];
int greenSpectrum = darkPurple[1];
int blueSpectrum = darkPurple[2];

int spectrumWait = 2;           // 10ms internal crossFade delay; increase for slower fades
int spectrumHold = 0;           // Optional hold when a color is complete, before the next crossFade
int DEBUG = 1;                  // DEBUG counter; if set to 1, will write values back via serial
int spectrumLoopCount = 60;     // How often should DEBUG report?
int spectrumRepeat = 3;         // How many times should we loop before stopping? (0 for no stop)
int spectrumCounter = 0;        // Loop counter for repeat
int fadeNumber = 1;
int spectrumPosition = 0;

// Initialize color variables
int prevRedSpectrum = redSpectrum;
int prevGreenSpectrum = greenSpectrum;
int prevBlueSpectrum = blueSpectrum;
//============================================================

void setup() {
  //Iniitialises LED strips.
  strip.begin();
  strip.show(); // Initialise all pixels to 'off'

  //Initialise the serial commuication.
  Serial.begin(9600);
  oscSerial.begin(Serial);

  //Initialise the blue LED.
  pinMode(2, OUTPUT);

  wavePosition = 0;

  scanPosition = 0;
  scanForward = true;

  delayTime = 0;
  boolean strobeLightsOn = true;

  //setDefaultSettings();

  loadSettings();
}

void loop() {
  if (currentMode.equals("static1")) {
    staticMode(1);
  } else if (currentMode.equals("static2")) {
    staticMode(2);
  } else if (currentMode.equals("static3")) {
    staticMode(3);
  } else if (currentMode.equals("random")) {
    randomMode();
  } else if (currentMode.equals("wave")) {
    waveMode();
  } else if (currentMode.equals("music")) {
    musicMode();
  } else if (currentMode.equals("spectrum")) {
    spectrumMode();
  } else if (currentMode.equals("strobe")) {
    strobeMode();
  } else if (currentMode.equals("scan")) {
    scanMode();
  } else if (currentMode.equals("off")) {
    offMode();
  } else {
    Serial.println("Mode not recognised.");
  }
  oscSerial.listen();
}

void oscEvent(OscMessage &m) {
  m.plug("/mode", changeMode);
  m.plug("/colour", changeColour);
  m.plug("/toggle_blue_led", toggleBlueLED);
  m.plug("/save_settings", saveSettings);
  m.plug("/set_default", setDefaultSettings);
}

void changeMode(OscMessage &m) {
  int mode = m.getInt(0);
  int cycle = m.getInt(1);
  if (mode == 0) {
    if (cycle == 1) {
      currentMode = "static1";
    } else if (cycle == 2) {
      currentMode = "static2";
    } else if (cycle == 3) {
      currentMode = "static3";
    }
  } else if (mode == 1) {
    currentMode = "random";
  } else if (mode == 2) {
    wavePosition = 0;
    currentMode = "wave";
  } else if (mode == 3) {
    currentMode = "music";
  } else if (mode == 4) {
    currentMode = "spectrum";
  } else if (mode == 5) {
    currentMode = "strobe";
  } else if (mode == 6) {
    currentMode = "scan";
  } else if (mode == 8) {
    currentMode = "off";
  }
}

void changeColour(OscMessage &m) {
  int type = m.getInt(0);
  coloursRGB[type][0] = m.getInt(1);
  coloursRGB[type][1] = m.getInt(2);
  coloursRGB[type][2] = m.getInt(3);
}

void toggleBlueLED(OscMessage &m) {
  if (m.getInt(0) == 0) {
    blueLEDOn = true;
    digitalWrite(2, HIGH);
  } else if (m.getInt(0) == 1) {
    blueLEDOn = false;
    digitalWrite(2, LOW);
  }
}

void saveSettings() {
  int mode;
  if (currentMode.equals("static1") || currentMode.equals("static2") || currentMode.equals("static3")) {
    mode = 0;
  } else if (currentMode.equals("random")) {
    mode = 1;
  } else if (currentMode.equals("wave")) {
    mode = 2;
  } else if (currentMode.equals("music")) {
    mode = 3;
  } else if (currentMode.equals("spectrum")) {
    mode = 4;
  } else if (currentMode.equals("strobe")) {
    mode = 5;
  } else if (currentMode.equals("scan")) {
    mode = 6;
  } else if (currentMode.equals("off")) {
    mode = 8;
  }
  EEPROM.write(0, mode);

  int cycle;
  if (currentMode.equals("static1")) {
    cycle = 1;
  } else if (currentMode.equals("static2")) {
    cycle = 2;
  } else if (currentMode.equals("static3")) {
    cycle = 3;
  } else {
    cycle = 0;
  }
  EEPROM.write(1, cycle);

  int index = 2;
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      EEPROM.write(index, coloursRGB[i][j]);
      index++;
    }
  }

  if (blueLEDOn) {
    EEPROM.write(index, 0);
  } else {
    EEPROM.write(index, 1);
  }
}

void loadSettings() {
  if (validSettingsFormat()) {
    int mode = EEPROM.read(0);
    int cycle = EEPROM.read(1);

    if (mode == 0) {
      if (cycle == 1) {
        currentMode = "static1";
      } else if (cycle == 2) {
        currentMode = "static2";
      } else if (cycle == 3) {
        currentMode = "static3";
      }
    } else if (mode == 1) {
      currentMode = "random";
    } else if (mode == 2) {
      wavePosition = 0;
      currentMode = "wave";
    } else if (mode == 3) {
      currentMode = "music";
    } else if (mode == 4) {
      currentMode = "spectrum";
    } else if (mode == 5) {
      currentMode = "strobe";
    } else if (mode == 6) {
      currentMode = "scan";
    } else if (mode == 8) {
      currentMode = "off";
    }

    coloursRGB[0][0] = EEPROM.read(2);
    coloursRGB[0][1] = EEPROM.read(3);
    coloursRGB[0][2] = EEPROM.read(4);
    coloursRGB[1][0] = EEPROM.read(5);
    coloursRGB[1][1] = EEPROM.read(6);
    coloursRGB[1][2] = EEPROM.read(7);
    coloursRGB[2][0] = EEPROM.read(8);
    coloursRGB[2][1] = EEPROM.read(9);
    coloursRGB[3][2] = EEPROM.read(10);

    if (EEPROM.read(11) == 0) {
      blueLEDOn = true;
      digitalWrite(2, HIGH);
    } else if (EEPROM.read(11) == 1) {
      blueLEDOn = false;
      digitalWrite(2, LOW);
    }
  } else {
    setDefaultSettings();
  }
}

boolean validSettingsFormat() {
  if (EEPROM.read(0) < 0 || EEPROM.read(0) > 8) return false;
  if (EEPROM.read(1) != 0 && EEPROM.read(1) != 1 && EEPROM.read(1) != 2 && EEPROM.read(1) != 3) return false;
  if (EEPROM.read(11) != 0 && EEPROM.read(11) != 1) return false;
  return true;
}


void setDefaultSettings() {
  currentMode = "static1";
  blueLEDOn = true;
  digitalWrite(2, HIGH);

  coloursRGB[0][0] = 255;
  coloursRGB[0][1] = 0;
  coloursRGB[0][2] = 0;

  coloursRGB[1][0] = 0;
  coloursRGB[1][1] = 255;
  coloursRGB[1][2] = 0;

  coloursRGB[2][0] = 0;
  coloursRGB[2][1] = 0;
  coloursRGB[2][2] = 255;
}

void staticMode(int cycle) {
  if (cycle == 1) {
    outputColour(coloursRGB[0][0], coloursRGB[0][1], coloursRGB[0][2]);
  } else if (cycle == 2) {
    for (int i = 0; i < 30; i++) {
      strip.setPixelColor(i, coloursRGB[0][0], coloursRGB[0][1], coloursRGB[0][2]);
    }
    for (int i = 30; i < 60; i++) {
      strip.setPixelColor(i, coloursRGB[1][0], coloursRGB[1][1], coloursRGB[1][2]);
    }
  } else if (cycle == 3) {
    for (int i = 0; i < 20; i++) {
      strip.setPixelColor(i, coloursRGB[0][0], coloursRGB[0][1], coloursRGB[0][2]);
    }
    for (int i = 20; i < 40; i++) {
      strip.setPixelColor(i, coloursRGB[1][0], coloursRGB[1][1], coloursRGB[1][2]);
    }
    for (int i = 40; i < 60; i++) {
      strip.setPixelColor(i, coloursRGB[2][0], coloursRGB[2][1], coloursRGB[2][2]);
    }
  }
  strip.show();
}

void randomMode() {
  if (delayTime < millis()) {
    outputColour(random(256), random(256), random(256));
    delayTime = millis() + 500;
  }
}

void waveMode() {
  if (delayTime < millis()) {
    uint16_t i, j;
    if (wavePosition >= 256) {
      wavePosition = 0;
    }
    for (i = 0; i < strip.numPixels(); i++) {
      strip.setPixelColor(i, Wheel(((i * 256 / strip.numPixels()) + wavePosition) & 255));
    }
    wavePosition++;
    strip.show();
    delayTime = millis() + 10;
  }
}

void musicMode() {
  fadeCol = 0;
  total = 0;

  for (int i = 0; i < 80; i++) {
    counter = 0;
    do {
      vol = analogRead(A7);
      if (vol == 0) {
        vol = 1;
      }
      vol = vol * 6;
      counter = counter + 1;
    }
    while (vol == 0);
    total = total + vol;
  }

  vol = total / 80;
  Serial.println(vol);
  vol = map(vol, 20, 255, 0, 20);


  if (volLast > vol) {
    vol = volLast - 4;
  }

  volLast = vol;
  fadeAmt = 0;
  Serial.print(vol);

  int type;

  for (int i = 0; i < 30; i++) {
    if (i < vol) {
      type = 0;
    }
    else if (i < (vol + 8)) {
      type = 1;
    }
    else {
      type = 2;
    }
    strip.setPixelColor((i + 30), strip.Color(coloursRGB[type][0], coloursRGB[type][1], coloursRGB[type][2]));
    strip.setPixelColor((30 - i), strip.Color(coloursRGB[type][0], coloursRGB[type][1], coloursRGB[type][2]));
  }
  strip.show();
}

void spectrumMode() {
  if (fadeNumber == 1) {
    crossFade(lightPurple, 2);
  } else if (fadeNumber == 2) {
    crossFade(red, 3);
  } else if (fadeNumber == 3) {
    crossFade(orange, 4);
  } else if (fadeNumber == 4) {
    crossFade(yellow, 5);
  } else if (fadeNumber == 5) {
    crossFade(green, 6);
  } else if (fadeNumber == 6) {
    crossFade(cyan, 7);
  } else if (fadeNumber == 7) {
    crossFade(blue, 1);
  }
}

void strobeMode() {
  if (delayTime < millis()) {
    if (strobeLightsOn) {
      outputColour(0, 0, 0);
      strobeLightsOn = false;
    } else {
      outputColour(coloursRGB[0][0], coloursRGB[0][1], coloursRGB[0][2]);
      strobeLightsOn = true;
    }
    delayTime = millis() + 60;
  }
}

void scanMode() {
  if (delayTime < millis()) {

    if (scanPosition > (60 - 5)) {
      scanForward = false;
    }

    if (scanForward) {
      for (int i = 0; i < 60; i++) {
        if (i == scanPosition || i == scanPosition + 1 || i == scanPosition + 2 || i == scanPosition + 3 || i == scanPosition + 4) {
          strip.setPixelColor(i, coloursRGB[0][0], coloursRGB[0][1], coloursRGB[0][2]);
        } else {
          strip.setPixelColor(i, 0, 0, 0);
        }
      }
      scanPosition++;
    } else {
      if (scanPosition == 0) {
        scanForward = true;
      } else {

        for (int i = 60; i > 0; i--) {
          if (i == scanPosition || i == scanPosition + 1 || i == scanPosition + 2 || i == scanPosition + 3 || i == scanPosition + 4) {
            strip.setPixelColor(i, coloursRGB[0][0], coloursRGB[0][1], coloursRGB[0][2]);
          } else {
            strip.setPixelColor(i, 0, 0, 0);
          }
        }
        scanPosition--;
      }
    }

    strip.show();

    delayTime = millis() + 10;
  }


  /*
    if (delayTime < millis()) {

    if (scanForward) {
      if (scanPosition < (60 - 5)) {

        for (int i = 0; i < 60; i++) {
          if (i = scanPosition) {
            strip.setPixelColor(i, coloursRGB[0][1], coloursRGB[0][1], coloursRGB[0][2]);
            strip.setPixelColor(i + 1, coloursRGB[0][1], coloursRGB[0][1], coloursRGB[0][2]);
            strip.setPixelColor(i + 2, coloursRGB[0][1], coloursRGB[0][1], coloursRGB[0][2]);
            strip.setPixelColor(i + 3, coloursRGB[0][1], coloursRGB[0][1], coloursRGB[0][2]);
            strip.setPixelColor(i + 4, coloursRGB[0][1], coloursRGB[0][1], coloursRGB[0][2]);
          } else {
            strip.setPixelColor(i, 0, 0, 0);
          }
        }
        strip.show();

        scanPosition++;
      } else {
        scanForward = false;
      }
    } else {
      delay(500);
      scanPosition = 5;
      scanForward = true;
    }

    delayTime = millis() + 60;
    }*/
}

void offMode() {
  outputColour(0, 0, 0);
}

void outputColour(int r, int g, int b) {
  for (int i = 0; i < 60; i++) {
    strip.setPixelColor(i, r, g, b);
  }
  strip.show();
}

uint32_t Wheel(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if (WheelPos < 85) {
    return strip.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  } else if (WheelPos < 170) {
    WheelPos -= 85;
    return strip.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  } else {
    WheelPos -= 170;
    return strip.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
  }
}

void crossFade(int color[3], int nextFadeNumber) {
  if (delayTime < millis()) {
    // Convert to 0-255
    int R = color[0];
    int G = color[1];
    int B = color[2];

    int stepR = calculateStep(prevRedSpectrum, R);
    int stepG = calculateStep(prevGreenSpectrum, G);
    int stepB = calculateStep(prevBlueSpectrum, B);

    if (spectrumPosition > 1020) {
      spectrumPosition = 0;
      // Update current values for next loop
      prevRedSpectrum = redSpectrum;
      prevGreenSpectrum = greenSpectrum;
      prevBlueSpectrum = blueSpectrum;

      fadeNumber = nextFadeNumber;
    } else {
      redSpectrum = calculateVal(stepR, redSpectrum, spectrumPosition);
      greenSpectrum = calculateVal(stepG, greenSpectrum, spectrumPosition);
      blueSpectrum = calculateVal(stepB, blueSpectrum, spectrumPosition);
      outputColour(redSpectrum, greenSpectrum, blueSpectrum);
      delayTime = millis() + spectrumWait;
      spectrumPosition++;
    }
  }

  //delay(spectrumHold); // Pause for optional 'wait' milliseconds before resuming the loop
}

int calculateStep(int prevValue, int endValue) {
  int step = endValue - prevValue; // What's the overall gap?
  if (step) {                      // If its non-zero,
    step = 1020 / step;            //   divide by 1020
  }
  return step;
}

/* The next function is calculateVal. When the loop value, i,
  reaches the step size appropriate for one of the
  colors, it increases or decreases the value of that color by 1.
  (R, G, and B are each calculated separately.)
*/

int calculateVal(int step, int val, int i) {

  if ((step) && i % step == 0) { // If step is non-zero and its time to change a value,
    if (step > 0) {              //   increment the value if step is positive...
      val += 1;
    }
    else if (step < 0) {         //   ...or decrement it if step is negative
      val -= 1;
    }
  }
  // Defensive driving: make sure val stays in the range 0-255
  if (val > 255) {
    val = 255;
  }
  else if (val < 0) {
    val = 0;
  }
  return val;
}

