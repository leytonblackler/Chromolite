<p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/README%20Images/chromolite_regular_logo.png" height="200"alt="Chromolite logo."></p>
###About
Chromolite is a universal desktop application that allows control of RGB WS2812B LED strips. The application offers many customisation options and modes through an easy to use interface.

The Chromolite desktop application is written in the Java based programming language Processing 3. The application controls the LED strips through serial communication to an Arduino microcontroller operating the Chromolite purpose built program. The Arduino microcontroller program required to interface with the desktop application is written using the C/C++ based Arduino language. In order to interface with the LEDs, some initial set up is required and is detailed in the setup section below.

###Features
* 8 unique modes:
  * <b>Static:</b> Sets the LED strips to either one, two or three non changing colours.
  * <b>Random:</b> Changes the LED strips to a randomly generated colour at short intervals.
  * <b>Wave:</b> Colours of the spectrum move across the LED strip in a wave-like motion.
  * <b>Music:</b> Reacts to an audio input signal, displaying 3 specified colours.
  * <b>Spectrum:</b> All LEDs gradually cycle through the colour spectrum together.
  * <b>Strobe:</b> Turns all LEDs on and off at a very fast rate to the selected colour.
  * <b>Scan:</b> A short bar rapidly moves across the LED strips from one end to other and back.
  * <b>Off:</b> Turns all LEDs off.
* Remote based control through the Android app to the desktop app over Wi-Fi.
* Support for multiple Android devices connected at a single given time.
* Keyboard shortcut control on the desktop app.
* Spectrum based colour selection.
* Ability to save custom default settings.
* Clean, modern and simple user interface.
* Cross desktop platform compatibility (Windows, macOS and Linux).

###User Interface
<center>

|<p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/README%20Images/chromolite_regular_logo.png" height="120" alt="Chromolite logo."></p>|<p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/README%20Images/chromolite_android_logo.png" height="120" alt="Chromolite Android logo."></p>|
|:-------------:|:-------------:|
|<p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/README%20Images/chromolite_desktop_ui.png" height="450" alt="Chromolite desktop UI."></p>|<p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/README%20Images/chromolite_android_connect_ui.png" height="400" alt="Chromolite Android UI.">   <img src="https://github.com/leytonblackler/Chromolite/blob/master/README%20Images/chromolite_android_main_ui.png" height="400" alt="Chromolite Android UI."></p>|

</center>

<details> 
  <summary><b>Old Desktop UI</b> <i>(for desktop versions 0.1 - 1.0)</i></summary>
  <p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/README%20Images/old_desktop_ui.png" height="500" alt="Old Chromolite desktop UI."></p>
</details>

###Downloads
[Chromolite 1.0 - Windows 64-bit Installer](https://drive.google.com/uc?export=download&confirm=c__-&id=0B8TU7kUyeVimMVc3UkNJRDVicm8)

<strike>Chromolite 1.0 - Windows 32-bit Installer</strike><i> - Coming soon...</i>

<strike>Chromolite 1.0 - macOS</strike><i> - Coming soon...</i>

<strike>Chromolite 1.0 - Linux</strike><i> - Coming soon...</i>

###Set up
<i>Coming soon...</i>

###Demonstration
<i>Coming soon...</i>

###Acknowledgements
<i>Coming soon...</i>

###Change Log
Changes made in each update reflect the code in both the desktop application and Arduino program.

####Desktop Versions
<details> 
  <summary><b>Versions 2.x</b> <i></i></summary>

<details> 
  <summary><b><i>Version 2.0</i></b> <i>(Latest)</i></summary>
  * //
</details>

</details>

<details> 
  <summary><b>Versions 1.x</b> <i></i></summary>
  
<details> 
  <summary><b><i>Version 1.4</i></b></summary>
  * Removed delay when switching from wave, spectrum and random modes.
</details>
  
<details> 
  <summary><b><i>Version 1.3</i></b></summary>
  * Allow dragging across spectrum once the spectrum has been clicked and the cursor moves above or below the spectrum.
  * Fixed flickering delay when dragging over the spectrum (caused by sending data to the LED controller at a rate faster than the Arduino serial port can handle).
</details>
  
<details> 
  <summary><b><i>Version 1.2</i></b></summary>
  * Fixed issue where changing a music mode colour would also change the current colour for static and strobe modes.
</details>

<details> 
  <summary><b><i>Version 1.1</i></b></summary>
  * Fixed issue where sometimes the mode would change randomly when in music mode.
</details>

<details> 
  <summary><b><i>Version 1.0</i></b></summary>
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

####Android Versions

###Known Bugs and Issues
* There are currently no known issues or bugs.

<a href="mailto:leytonblackler@gmail.com?subject=Chromolite%20Bug%20Report">Click here to report a bug.</a>

###Planned Features and Changes
* Ability to control speed for random, wave, spectrum, strobe modes and scan modes.

<a href="mailto:leytonblackler@gmail.com?subject=Chromolite%20Feature%20Suggestion">Click here to suggest a new feature.</a>

###License
MIT License

Copyright &copy; 2016 Leyton Blackler

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
