<p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/README%20Images/chromolite_logo.png" height="200"alt="Chromolite logo."></p>
###About
Chromolite is a universal desktop application that allows control of RGB WS2812B LED strips. The application offers many customisation options and modes through an easy to use interface.

The Chromolite desktop application is written in the Java based programming language Processing 3. The application controls the LED strips through serial communication to an Arduino microcontroller operating the Chromolite purpose built program. The Arduino microcontroller program required to interface with the desktop application is written using the C/C++ based Arduino language. In order to interface with the LEDs, some initial set up is required and is detailed in the setup section below.

###Features
* 7 unique modes:
  * <b>Static:</b> Sets the LED strips to a single selected colour.
  * <b>Random:</b> Changes the LED strips to a randomly generated colour at short intervals.
  * <b>Wave:</b> Colours of the spectrum move across the LED strip in a wave-like motion.
  * <b>Music:</b> Reacts to an audio input signal, displaying 3 specified colours.
  * <b>Spectrum:</b> All LEDs gradually cycle through the colour spectrum together.
  * <b>Strobe:</b> Turns all LEDs on and off at a very fast rate to the selected colour.
  * <b>Off:</b> Turns all LEDs off.
* Keyboard shortcut control.
* Spectrum based colour selection.
* Clean and simple user interface.
* Cross platform compatibility (Windows, macOS and Linux).

###User Interface

Desktop | Android
--- | ---
<p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/README%20Images/desktop_user_interface.png" height="400" alt="Chromolite desktop UI."></p> | 2

<p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/README%20Images/desktop_user_interface.png" height="550" alt="Chromolite desktop UI."></p>
<details> 
  <summary><b>Old Desktop UI</b> <i>(for desktop versions 0.1 - 1.0)</i></summary>
  <p align="center"><img src="https://github.com/leytonblackler/Chromolite/blob/master/README%20Images/old_desktop_user_interface.png" height="550" alt="Old Chromolite desktop UI."></p>
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

<details> 
  <summary><b>Version 1.0</b> <i>(Latest)</i></summary>
  * Implemented keyboard shortcut/control functionality.
  * Fixed many bugs and errors.
  * Minor usability and UI tweaks.
</details>

<details> 
  <summary><b>Version 0.9</b></summary>
  * Added music mode colour customisation.
</details>

<details> 
  <summary><b>Version 0.8</b></summary>
  * Added music mode.
</details>

<details> 
  <summary><b>Version 0.7</b></summary>
  * Additionally implemented dragging over spectrum for selection instead of exclusively upon press.
  * Added circle indicator over spectrum to display which colour is selected.
</details>

<details> 
  <summary><b>Version 0.6</b></summary>
  * Added spectrum mode.
  * Added strobe mode.
</details>

<details> 
  <summary><b>Version 0.5</b></summary>
  * Converted previously white user interface into a dark theme.
  * Refined layout of buttons and added mouse over colour change/ button selection colour change.
</details>

<details> 
  <summary><b>Version 0.4</b></summary>
  * Added toggle for the controller LED.
  * Added exit button.
</details>

<details> 
  <summary><b>Version 0.3</b></summary>
  * Added wave mode.
</details>

<details> 
  <summary><b>Version 0.2</b></summary>
  * Created button layout to change between modes.
  * Added random mode.
</details>

<details> 
  <summary><b>Version 0.1</b></summary>
  * Base desktop application structure complete.
  * Base Arduino program structure complete.
  * Static colour selection mode.
</details>

###Known Bugs and Issues
* Will sometimes change out of music mode.
* Selected music colours may randomly change.
* Flickering delay when dragging over spectrum too fast.
* Changing a music colour will also change the current colour for static and strobe modes.

###Planned Features and Changes
* Remove delay when switching from wave, spectrum and random modes.
* Allow dragging over spectrum despite mouse moving above or below the spectrum.
* Ability to control speed for random, wave, spectrum and strobe modes.
* Fix known issues and bugs.

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
