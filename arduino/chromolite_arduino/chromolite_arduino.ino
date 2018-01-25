#include <OscSerial.h>
#include <Adafruit_NeoPixel.h>

//Define the pin connected to the built in LED.
#define BUILT_IN_LED 3

//Define the pin connected to the led strips data connection.
#define LED_STRIPS 8

//Define the brightness for the built in LED (0 - 255).
#define BUILT_IN_LED_BRIGHTNESS 10

//Define the default LED strip length.
#define DEFAULT_LEDS 60

Adafruit_NeoPixel ledStrips = Adafruit_NeoPixel(DEFAULT_LEDS, LED_STRIPS, NEO_GRB + NEO_KHZ800);

int ledStripLength = DEFAULT_LEDS;

OscSerial oscSerial;

void setup() {
  //Set the pin connected to the built in LED as an output.
  pinMode(BUILT_IN_LED, OUTPUT);
  
  //Turn on the built in LED at a low brightness.
  //analogWrite(BUILT_IN_LED, BUILT_IN_LED_BRIGHTNESS);

  //Iniitialise the LED strips.
  ledStrips.begin();
  ledStrips.show();

  //Initialise the serial commuication.
  Serial.begin(9600);
  //oscSerial.begin(Serial);
  
}

void loop() {
  if (Serial.available() > 0) {
    String value = Serial.readString();
    if (value == "22") {
      analogWrite(BUILT_IN_LED, 255);
      setSingle(255, 0, 0);
    } else {
      analogWrite(BUILT_IN_LED, 0);
      setSingle(0, 0, 0);
    }
  }
}

/*void oscEvent(OscMessage &message) {
  message.plug("/set_single", setSingle);
  message.plug("/set_layout", setLayout);
}*/

void setSingle(int r, int g, int b) {
  //int colour[3] = {message.getInt(0), message.getInt(1), message.getInt(2)};
  for (int led = 0; led < ledStripLength; led++) {
    ledStrips.setPixelColor(led, r, g, b);
  }
  ledStrips.show();
}

void setLayout(OscMessage &message) {
  for (int led = 0; led < ledStripLength; led++) {
    ledStrips.setPixelColor(led, message.getInt(led * 3), message.getInt((led * 3) + 1), message.getInt((led * 3) + 2));
  }
  ledStrips.show();
}

