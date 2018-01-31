#include <OscSerial.h>
#include <Adafruit_NeoPixel.h>

//Define the baud rate to receive data from the desktop client.
#define BAUD_RATE 290000

//Define the pin connected to the built in LED.
#define BUILT_IN_LED 3

//Define the pin connected to the led strips data connection.
#define LED_STRIPS 8

//Define the brightness for the built in LED (0 - 255).
#define BUILT_IN_LED_BRIGHTNESS 10

//Define the default LED strip length.
#define DEFAULT_LEDS 60

//Define the character which indicates setting a single colour.
#define SINGLE 's'

//Define the character which indicates setting a layout.
#define LAYOUT 'l'

Adafruit_NeoPixel ledStrips = Adafruit_NeoPixel(DEFAULT_LEDS, LED_STRIPS, NEO_GRB + NEO_KHZ800);

int ledStripLength = DEFAULT_LEDS;

OscSerial oscSerial;

void setup() {
  
  //Set the pin connected to the built in LED as an output.
  pinMode(BUILT_IN_LED, OUTPUT);
  
  //Turn on the built in LED at a low brightness.
  analogWrite(BUILT_IN_LED, BUILT_IN_LED_BRIGHTNESS);

  //Iniitialise the LED strips.
  ledStrips.begin();

  //Initialise the serial commuication.
  Serial.begin(BAUD_RATE);
  Serial.setTimeout(20);
  
}

void loop() {
  if (Serial.available() > 0) {
    String received = Serial.readStringUntil('$');
    char settings[received.length()];
    received.toCharArray(settings, received.length());
    parseSettings(settings);
    Serial.flush();
  }
}

void parseSettings(char settings[]) {
  
  //Create a 2D array to store the RGB colour values in.
  int colours[ledStripLength][3];

  int colour = 0;
  int value = 0;

  //https://stackoverflow.com/questions/30806085/parsing-string-separated-by-commas-in-c-arduino
  char* stringPtr;
  stringPtr = strtok(settings, ",");
  while (stringPtr != NULL)
  {
    //Serial.println(stringPtr);
    if (value >= 3) {
      value = 0;
      colour++;
    }  
    colours[colour][value] = atoi(stringPtr);
    value++;

    
    stringPtr = strtok(NULL, ",");
  }

  setLEDs(colours);
  
}

void setLEDs(int colours[][3]) {
  for (int led = 0; led < ledStripLength; led++) {
    ledStrips.setPixelColor(led, colours[led][0], colours[led][1], colours[led][2]);
    //ledStrips.setPixelColor(led, 0, 255, 0);
  }
  ledStrips.show();
}

