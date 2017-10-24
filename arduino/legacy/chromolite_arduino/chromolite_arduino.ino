#include <Adafruit_NeoPixel.h>

#define PIN 8
#define lightCount 60

Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, PIN, NEO_GRB + NEO_KHZ800);

long int inByte; 
int wait = 10; //10ms
boolean blueLED = true;
boolean colourPickerMode = true;
boolean musicMode = false;
boolean waveMode = false;
boolean spectrumMode = false;
boolean strobeMode = false;
boolean randomMode = false;
boolean offMode = false;
boolean firstMusic = false;
boolean secondMusic = false;
boolean thirdMusic = false;

boolean strobeOn = true;

//fadeThickness = 0;  // CHANGE THICKNESS (0-10)

int vol = 0;
float total = 0;
int fadeCol = 0;
int val[30];
int volLast = 0;
int fadeAmt = 0;
int counter = 0;

int pickedR = 255;
int pickedG = 255;
int pickedB = 255;

int music1R = 0;
int music1G = 255;
int music1B = 0;

int music2R = 255;
int music2G = 0;
int music2B = 0;

int music3R = 0;
int music3G = 0;
int music3B = 255;

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

int spectrumWait = 2;      // 10ms internal crossFade delay; increase for slower fades
int spectrumHold = 0;       // Optional hold when a color is complete, before the next crossFade
int DEBUG = 1;      // DEBUG counter; if set to 1, will write values back via serial
int spectrumLoopCount = 60; // How often should DEBUG report?
int spectrumRepeat = 3;     // How many times should we loop before stopping? (0 for no stop)
int spectrumCounter = 0;          // Loop counter for repeat
int fadeNumber = 1;

// Initialize color variables
int prevRedSpectrum = redSpectrum;
int prevGreenSpectrum = greenSpectrum;
int prevBlueSpectrum = blueSpectrum;
//============================================================

void setup() {
  //Turns on blue LED.
  pinMode(2, OUTPUT);
  digitalWrite(2, HIGH);

  //Iniitialises LED strips.
  strip.begin();
  strip.show(); // Initialize all pixels to 'off'

  //Initialises serial port.
  Serial.begin(9600);

  outputColour(255,255,255);
}

void outputColour(int red, int green, int blue) {
  for (int i = 0; i < 60; i++) {
    strip.setPixelColor(i, red, green, blue);  
  }
  strip.show();
}


int* getColour() {
  int* colour;
  int i;
  
  i = 0;

  while (i < 4)
  {
    if (Serial.available() > 0) {
        colour[i] = Serial.read();
        i++;
    }
  }
  return colour;
}

void loop()
{
  if (Serial.available() > 0) {
    // get incoming byte:
    inByte = Serial.read();
    Serial.println(inByte);
    
     if (inByte == 'C') {
      Serial.println("inByte is C. - finding pixels and setting colour.");
        int* one;
      one =  getColour();
      
      //1 2 3 not 0 1 2 due to the dud value
      pickedR = one[1];
      pickedG = one[2];
      pickedB = one[3];

      if (firstMusic == true) {
        music1R = pickedR;
        music1G = pickedG;
        music1B = pickedB;
      }

      if (secondMusic == true) {
        music2R = pickedR;
        music2G = pickedG;
        music2B = pickedB;
      }

      if (thirdMusic == true) {
        music3R = pickedR;
        music3G = pickedG;
        music3B = pickedB;
      }
      
      if (colourPickerMode && !firstMusic && !secondMusic && !thirdMusic) {
        outputColour(pickedR, pickedG, pickedB);
      }

      if (randomMode && !firstMusic && !secondMusic && !thirdMusic) {
        randomFlash();
      }

    //Toggles colour picker mode.
    } else if (inByte == 'P') {
      Serial.println("inByte is P - toggling colour picker mode");
      if (colourPickerMode == false) {
        colourPickerMode = true;
        musicMode = false;
        waveMode = false;
        spectrumMode = false;
        strobeMode = false;
        randomMode = false;
        offMode = false;
        firstMusic = false;
        secondMusic = false;
        thirdMusic = false;
        outputColour(pickedR, pickedG, pickedB);
      }

    //Toggles random mode.
    } else if (inByte == 'R') {
      Serial.println("inByte is R - toggling random flash mode");
      if (randomMode == false) {
        randomMode = true;
        colourPickerMode = false;
        musicMode = false;
        waveMode = false;
        spectrumMode = false;
        strobeMode = false;
        offMode = false;
        firstMusic = false;
        secondMusic = false;
        thirdMusic = false;
        outputColour(pickedR, pickedG, pickedB);
      }

    //Toggles wave mode.
    } else if (inByte == 'W') {
      Serial.println("inByte is W - toggling wave mode");
      if (waveMode == false) {
        waveMode = true;
        colourPickerMode = false;
        musicMode = false;
        spectrumMode = false;
        strobeMode = false;
        randomMode = false;
        offMode = false;
        firstMusic = false;
        secondMusic = false;
        thirdMusic = false;
      }

    //Toggles spectrum mode.
    } else if (inByte == 'S') {
      Serial.println("inByte is S - toggling spectrum mode");
      if (spectrumMode == false) {
        Serial.println("Setting spectrum mode to true...");
        spectrumMode = true;
        colourPickerMode = false;
        musicMode = false;
        waveMode = false;
        strobeMode = false;
        randomMode = false;
        offMode = false;
        firstMusic = false;
        secondMusic = false;
        thirdMusic = false;
      }

    //Toggles strobe mode.
    } else if (inByte == 'T') {
      Serial.println("inByte is T - toggling strobe mode");
      if (strobeMode == false) {
        strobeMode = true;
        colourPickerMode = false;
        musicMode = false;
        waveMode = false;
        spectrumMode = false;
        randomMode = false;
        offMode = false;
        firstMusic = false;
        secondMusic = false;
        thirdMusic = false;
      }

    //Toggles off mode.
    } else if (inByte == 'O') {
      Serial.println("inByte is O - toggling off mode");
      if (offMode == false) {
        offMode = true;
        colourPickerMode = false;
        musicMode = false;
        waveMode = false;
        spectrumMode = false;
        strobeMode = false;
        randomMode = false;
        firstMusic = false;
        secondMusic = false;
        thirdMusic = false;
        outputColour(0,0,0);
      }

    //Toggles music mode.
    } else if (inByte == 'M') {
      Serial.println("inByte is M - toggling music mode");
      if (musicMode == false) {
        musicMode = true;
        colourPickerMode = false;
        waveMode = false;
        spectrumMode = false;
        strobeMode = false;
        offMode = false;
        firstMusic = false;
        secondMusic = false;
        thirdMusic = false;
        randomMode = false;
      }

    //Toggles first music colour pick mode.
    } else if (inByte == 'X') {
      Serial.println("inByte is X - toggling first music colour pick mode");
      if (firstMusic == false) {
        firstMusic = true;
        secondMusic = false;
        thirdMusic = false;
      }

    //Toggles second music colour pick mode.
    } else if (inByte == 'Y') {
      Serial.println("inByte is Y - toggling second music colour pick mode");
      if (secondMusic == false) {
        secondMusic = true;
        firstMusic = false;
        thirdMusic = false;
      }

    //Toggles third music colour pick mode.
    } else if (inByte == 'Z') {
      Serial.println("inByte is Z - toggling third music colour pick mode");
      if (thirdMusic == false) {
        thirdMusic = true;
        firstMusic = false;
        secondMusic = false;
      }

    //Toggles blue LED.
    } else if (inByte == 'B') {
      Serial.println("inByte is B - toggling blue led");
      if (blueLED == true) {
        digitalWrite(2, LOW);
        blueLED = false;
      } else if (blueLED == false) {
        digitalWrite(2, HIGH);
        blueLED = true;
      }
    }
  }
  if (randomMode == true) {
    randomFlash();
  }
  if (waveMode == true) {
    wave(5);
  }
  if (spectrumMode == true) {
    Serial.println("Spectrum mode is true");
    Serial.println(spectrumMode);
    spectrumCycle();
  }
  if (strobeMode == true) {
    strobe();
  }
  if (musicMode == true) {
    musicControl();
  }
}

void spectrumCycle() {
  //Spectrum cycle crossfade order
  if (fadeNumber == 1) {
    crossFade(lightPurple);
    fadeNumber = 2;
  } else if (fadeNumber == 2) {
    crossFade(red);
    fadeNumber = 3;
  } else if (fadeNumber == 3) {
    crossFade(orange);
    fadeNumber = 4;
  } else if (fadeNumber == 4) {
    crossFade(yellow);
    fadeNumber = 5;
  } else if (fadeNumber == 5) {
    crossFade(green);
    fadeNumber = 6;
  } else if (fadeNumber == 6) {
    crossFade(cyan);
    fadeNumber = 7;
  } else if (fadeNumber == 7) {
    crossFade(blue);
    fadeNumber = 1;
    /** --Took too long to fade on purple.
  } else if (fadeNumber == 8) {
    crossFade(darkPurple);
    fadeNumber = 1;
    */
  }
}

void strobe() {
  if (strobeOn) {
    outputColour(0,0,0);
    strobeOn = false;
  } else {
    outputColour(pickedR, pickedG, pickedB);
    strobeOn = true;
  }
  delay(60);
}

void randomFlash() {
  /**int red = 255;
  int green = 255;
  int blue = 255;
  while ((red > 180 && green > 180 && blue > 180) || (red < 100 && green < 100 && blue < 100)) {
    red = random(256);
    green = random(256);
    blue = random(256);
  }*/
  outputColour(random(256), random(256), random(256));
  delay(500);
}

void musicControl() {
  fadeCol = 0;
  total = 0;

  for (int i = 0; i < 80; i++) {
    counter = 0;
    do {
      vol = analogRead(A7);
      if (vol == 0) {
        vol = 1;
      }
      vol = vol*6;
      counter = counter + 1;
    }   
    while (vol == 0);
    total = total + vol;
  }
  
  vol = total / 80;
  Serial.println(vol);
  vol = map(vol,20,255,0,20);
  
  
  if (volLast > vol) {
    vol = volLast - 4;
  }
  
  volLast = vol;
  fadeAmt = 0;
  Serial.print(vol);
  
  for (int i = 0; i<30;i++) {
    if (i < vol){ 
      strip.setPixelColor((i+30), strip.Color(music1R, music1G, music1B));
      strip.setPixelColor((30-i), strip.Color(music1R, music1G, music1B));
    }
    else if (i < (vol + 8)) {
      strip.setPixelColor((i+30), strip.Color(music2R, music2G, music2B));
      strip.setPixelColor((30-i), strip.Color(music2R, music2G, music2B)); 
    }
    else {
      strip.setPixelColor((i+30), strip.Color(music3R, music3G, music3B));
      strip.setPixelColor((30-i), strip.Color(music3R, music3G, music3B));  
    }
  }
  strip.show();
}

void wave(uint8_t wait) {
  uint16_t i, j;
  for(j=0; j<256; j++) {
    for(i=0; i< strip.numPixels(); i++) {
      strip.setPixelColor(i, Wheel(((i * 256 / strip.numPixels()) + j) & 255));
    }
    strip.show();
    delay(wait);
  }
}

void rainbowCycle(uint8_t wait) {
  uint16_t i, j;

  for(j=0; j<256; j++) { // 5 cycles of all colors on wheel
    for(i=0; i< strip.numPixels(); i++) {
      strip.setPixelColor(i, Wheel(((i * 256 / strip.numPixels()) + j) & 255));
    }
    strip.show();
    //delay(wait);
    vol = analogRead(A7);
    vol = 501;
    Serial.println(vol);
    if (vol > 500) {
      return;
    }   
  }
}

uint32_t Wheel(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if(WheelPos < 85) {
    return strip.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  } else if(WheelPos < 170) {
    WheelPos -= 85;
   return strip.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  } else {
   WheelPos -= 170;
   return strip.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
  }
}

/* BELOW THIS LINE IS THE MATH -- YOU SHOULDN'T NEED TO CHANGE THIS FOR THE BASICS
* 
* The program works like this:
* Imagine a crossfade that moves the red LED from 0-10, 
*   the green from 0-5, and the blue from 10 to 7, in
*   ten steps.
*   We'd want to count the 10 steps and increase or 
*   decrease color values in evenly stepped increments.
*   Imagine a + indicates raising a value by 1, and a -
*   equals lowering it. Our 10 step fade would look like:
* 
*   1 2 3 4 5 6 7 8 9 10
* R + + + + + + + + + +
* G   +   +   +   +   +
* B     -     -     -
* 
* The red rises from 0 to 10 in ten steps, the green from 
* 0-5 in 5 steps, and the blue falls from 10 to 7 in three steps.
* 
* In the real program, the color percentages are converted to 
* 0-255 values, and there are 1020 steps (255*4).
* 
* To figure out how big a step there should be between one up- or
* down-tick of one of the LED values, we call calculateStep(), 
* which calculates the absolute gap between the start and end values, 
* and then divides that gap by 1020 to determine the size of the step  
* between adjustments in the value.
*/

int calculateStep(int prevValue, int endValue) {
  int step = endValue - prevValue; // What's the overall gap?
  if (step) {                      // If its non-zero, 
    step = 1020/step;              //   divide by 1020
  } 
  return step;
}

/* The next function is calculateVal. When the loop value, i,
*  reaches the step size appropriate for one of the
*  colors, it increases or decreases the value of that color by 1. 
*  (R, G, and B are each calculated separately.)
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

/* crossFade() converts the percentage colors to a 
*  0-255 range, then loops 1020 times, checking to see if  
*  the value needs to be updated each time, then writing
*  the color values to the correct pins.
*/

void crossFade(int color[3]) {
  // Convert to 0-255
  int R = color[0];
  int G = color[1];
  int B = color[2];

  int stepR = calculateStep(prevRedSpectrum, R);
  int stepG = calculateStep(prevGreenSpectrum, G); 
  int stepB = calculateStep(prevBlueSpectrum, B);

  for (int i = 0; i <= 1020; i++) {
    redSpectrum = calculateVal(stepR, redSpectrum, i);
    greenSpectrum = calculateVal(stepG, greenSpectrum, i);
    blueSpectrum = calculateVal(stepB, blueSpectrum, i);
    outputColour(redSpectrum, greenSpectrum, blueSpectrum);
    delay(spectrumWait); // Pause for 'wait' milliseconds before resuming the loop
  }
  // Update current values for next loop
  prevRedSpectrum = redSpectrum; 
  prevGreenSpectrum = greenSpectrum; 
  prevBlueSpectrum = blueSpectrum;
  delay(spectrumHold); // Pause for optional 'wait' milliseconds before resuming the loop
}
