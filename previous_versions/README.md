<p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/images/chromolite_regular_logo.png" height="200"alt="Chromolite logo."></p>

<details>
<summary><b>ReadMe Quick Access</b><i></i></summary>

<table>
  <tr>
  <td width="200"><a href="https://github.com/leytonblackler/Chromolite#about">About</a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/leytonblackler/Chromolite#features">Features</a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/leytonblackler/Chromolite#user-interface">User Interface</a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/leytonblackler/Chromolite#initial-setup">Initial Setup</a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/leytonblackler/Chromolite#how-to-operate-the-software">How to Operate the Software</a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/leytonblackler/Chromolite#demonstration">Demonstration</a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/leytonblackler/Chromolite#downloads">Downloads</a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/leytonblackler/Chromolite#acknowledgements">Acknowledgements</a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/leytonblackler/Chromolite#change-log">Change Log</a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/leytonblackler/Chromolite#known-bugs-and-issues">Known Bugs and Issues</a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/leytonblackler/Chromolite#planned-features-and-changes">Planned Features and Changes</a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/leytonblackler/Chromolite#license">License</a></td>
  </tr>
</table>
</details>

<h2>About</h2>
Chromolite provides control of RGB WS2812B LED strips through a multi-platform desktop application, or remotely through an Android application. Chromolite offers many customisation options and modes through an easy to use interface.

The Chromolite desktop and Android applications are written in the Java based programming language Processing 3. The desktop application controls the LED strips through serial communication with an Arduino microcontroller set up in configuration to operate the Chromolite purpose built program. The desktop application acts as a server, in which the Chromolite Android application can connect to via Wi-Fi for wireless control. The Arduino microcontroller program required to interface with the desktop application is written using the C/C++ based Arduino language. In order for the Chromolite desktop application to interface with the program on the Arduino microcontroller, some initial circuitry set up is required and is detailed in the setup section below.

<h2>Features</h2>

* 8 unique modes:
  * <b>Static:</b> Sets the LED strips to either one, two or three non-changing colours.
  * <b>Random:</b> Changes the LED strips to a randomly generated colour at short intervals.
  * <b>Wave:</b> Colours of the spectrum move across the LED strip in a wave-like motion.
  * <b>Music:</b> Reacts to an audio input signal, displaying 3 specified colours.
  * <b>Spectrum:</b> All LEDs gradually cycle through the colour spectrum together.
  * <b>Strobe:</b> Turns all LEDs on and off at a very fast rate to the selected colour.
  * <b>Scan:</b> A short bar rapidly moves across the LED strips from one end to other and back.
  * <b>Off:</b> Turns all LEDs off.
* Remote based control through the Android application to the desktop application over Wi-Fi.
* Support for multiple Android devices connected at a single given time.
* Keyboard shortcut control on the desktop application.
* Spectrum based colour selection.
* Ability to save custom default settings to both the desktop computer and Arduino's non-volatile EEPROM memory.
* Clean, modern and simple user interface.
* Cross desktop platform compatibility (Windows, macOS and Linux).

<h2>User Interface</h2>
<center>

|<img src="https://github.com/leytonblackler/Chromolite/blob/master/images/chromolite_regular_logo.png" height="120" alt="Chromolite logo.">|<img src="https://github.com/leytonblackler/Chromolite/blob/master/images/chromolite_android_logo.png" height="120" alt="Chromolite Android logo.">|
|:-------------:|:-------------:|
|<p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/images/chromolite_desktop_ui.png" height="425" alt="Chromolite desktop UI."></p>|<p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/images/chromolite_android_connect_ui.png" height="425" alt="Chromolite Android UI.">   <img src="https://github.com/leytonblackler/Chromolite/blob/master/images/chromolite_android_main_ui.png" height="425" alt="Chromolite Android UI."></p>|

</center>

<details> 
  <summary><b>Old Desktop UI</b> <i>(for desktop versions 0.1 - 1.0)</i></summary>
  <p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/images/old_desktop_ui.png" height="500" alt="Old Chromolite desktop UI."></p>
</details>

<h2>Initial Setup</h2>
<i>Coming soon...</i>

<h2>How to Operate the Software</h2>
Before running the desktop application, the Chromolite compatible Arduino microcontroller must be connected via USB. The program will automatically detect which serial port it is connected to.

<h2>Demonstration</h2>
<i>Coming soon...</i>

<h2>Change Log</h2>
Changes made in each update reflect the code in both the desktop application and Arduino program.

<h4>Desktop Versions</h4>
<details> 
  <summary><b>Versions 2.x</b><i></i></summary>

<details> 
  <summary><b><i>Version 2.0</i></b> <i>(Latest)</i></summary>
  * //
</details>

</details>

<details> 
  <summary><b>Versions 1.x</b><i></i></summary>
  
<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 1.4</i></b><i>(Latest)</i></summary>
  * Removed delay when switching from wave, spectrum and random modes.
</details>
  
<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 1.3</i></b></summary>
  * Allow dragging across spectrum once the spectrum has been clicked and the cursor moves above or below the spectrum.
  * Fixed flickering delay when dragging over the spectrum (caused by sending data to the LED controller at a rate faster than the Arduino serial port can handle).
</details>
  
<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 1.2</i></b></summary>
  * Fixed issue where changing a music mode colour would also change the current colour for static and strobe modes.
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 1.1</i></b></summary>
  * Fixed issue where sometimes the mode would change randomly when in music mode.
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 1.0</i></b></summary>
  * Fixed many bugs and errors: First stable release.
  * Implemented keyboard shortcut/control functionality.
  * Minor usability and UI tweaks.
</details>

</details>

<details> 
  <summary><b>Versions 0.x</b> <i></i></summary>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.9</i></b></summary>
  * Added music mode colour customisation.
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.8</i></b></summary>
  * Added music mode.
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.7</i></b></summary>
  * Additionally implemented dragging over spectrum for selection instead of exclusively upon press.
  * Added circle indicator over spectrum to display which colour is selected.
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.6</i></b></summary>
  * Added spectrum mode.
  * Added strobe mode.
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.5</i></b></summary>
  * Converted previously white user interface into a dark theme.
  * Refined layout of buttons and added mouse over colour change/ button selection colour change.
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.4</i></b></summary>
  * Added toggle for the controller LED.
  * Added exit button.
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.3</i></b></summary>
  * Added wave mode.
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.2</i></b></summary>
  * Created button layout to change between modes.
  * Added random mode.
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.1</i></b></summary>
  * Base desktop application structure complete.
  * Base Arduino program structure complete.
  * Static colour selection mode.
</details>

</details>

<h4>Android Versions</h4>

<details> 
  <summary><b>Versions 1.x</b> <i></i></summary>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 1.4</i></b> <i>(Latest)</i></summary>
  * //
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 1.3</i></b></summary>
  * //
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 1.2</i></b></summary>
  * //
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 1.1</i></b></summary>
  * //
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 1.0</i></b></summary>
  * //
</details>

</details>

<details> 
  <summary><b>Versions 0.x</b> <i></i></summary>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.9</i></b></summary>
  * //
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.8</i></b></summary>
  * //
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.7</i></b></summary>
  * //
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.6</i></b></summary>
  * //
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.5</i></b></summary>
  * //
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.4</i></b></summary>
  * //
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.3</i></b></summary>
  * //
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.2</i></b></summary>
  * //
</details>

<details> 
  <summary><b><i>&nbsp;&nbsp;&nbsp;&nbsp;Version 0.1</i></b></summary>
  * //
</details>

</details>

<h2>Known Bugs and Issues</h2>

* There are currently no known issues or bugs.

<a href="mailto:leytonblackler@gmail.com?subject=Chromolite%20Bug%20Report">Click here to report a bug.</a>

<h2>Planned Features and Changes</h2>

* Ability to control speed for random, wave, spectrum, strobe modes and scan modes.

<a href="mailto:leytonblackler@gmail.com?subject=Chromolite%20Feature%20Suggestion">Click here to suggest a new feature.</a>

<h2>Acknowledgements</h2>

<h4>Corale Studios - <a href="https://github.com/CoraleStudios">github.com/CoralStudios</h4>

<a href="https://github.com/CoraleStudios/Colore"><b>Colore</b></a> - A C# library that utilises the Razer Chroma SDK to provide control of LEDs on a range of Razer Chroma devices. The library is used in this project in the Windows background service that the desktop applciation communicates with via UDP in order to control the LEDs on Razer Chroma devices. This allows utilisation of this C# based library from within Java.

<h4>Andreas Schlegel (sojamo) - http://www.sojamo.de/</h4>

<a href="http://www.sojamo.de/libraries/oscP5/"><b>oscP5</b></a> - A library for the Processing programming environment which implements the Open Sound Control (OSC) network protocol developed at the Center for New Music and Audio Technologies, University of California, Berkeley. The library is used in this project for the purpose of network communication between Android and desktop devices.

<a href="http://www.sojamo.de/libraries/controlP5/"><b>controlP5</b></a> - A library for the Processing programming environment which provides a set of controllers to build a graphical user interface. The library is used in this project for the purpose of creating an editable text field in the desktop application in which the user is able to input a desired network port.

<h4>David Bouchard (deadpixel) - http://www.deadpixel.ca/</h4>

<a href="http://www.deadpixel.ca/arduino-osc/"><b>Arduino-OSC</b></a> - Libraries for the Processing and Arduino programming environments, built upon oscP5, which allows for OSC protocol communciation over the Serial port to the Arduino platform. The library is used in this project for the purpose of communication between the desktop application and the Chromolite compatible Arduino microcontroller.

<h4>Benedikt Groß - http://benedikt-gross.de/log/</h4>

<a href="http://www.looksgood.de/libraries/Ani/"><b>Ani</b></a> - A library for the Processing programming environment which provides a platform for creating smooth easing animations. The library is used in this project for the purpose of animating UI transitions and interactions in the Android application.

<h4>Adafruit Industries - https://www.adafruit.com/</h4>

<a href="http://www.deadpixel.ca/arduino-osc/"><b>NeoPixel</b></a> - A library for the Arduino programming environment which provides the ability to individually interface with each LED in the WS2812B RGB LED strips. The library is used in this project for the purpose of controlling the LED strips connected to the Chromolite compatible Arduino microcontroller.

<h2>License</h4>
MIT License

Copyright &copy; 2016 - 2018 Leyton Blackler

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
