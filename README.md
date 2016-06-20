<p align="center"><img src="http://i.imgur.com/07zs70m.png" height="150"alt="Chromolite logo."></p>
###About
Chromolite is a universal desktop application that allows control of RGB WS2812B LED strips. The application offers many customisation options and modes through an easy to use interface.

The Chromolite desktop application is written in the Java based programming language Processing 3. The application controls the LED strips through serial communication to an Arduino micrcontroller operating the Chromolite purpose built program. The Arduino microcontroller program required to interface with the desktop application is written using the C/C++ based Arduino language. In order to interface with the LEDs, some initial set up is required and is detailed in the setup section below.

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
<p align="center"><img src="http://i.imgur.com/LP5VDhT.png" height="550" alt="Chromolite desktop UI."></p>

###Downloads
[Chromolite 1.0 - Windows 64-bit Installer](https://drive.google.com/uc?export=download&confirm=c__-&id=0B8TU7kUyeVimMVc3UkNJRDVicm8)

###Set up
<i>Coming soon...</i>

###Demonstration
<i>Coming soon...</i>

###Acknowledments
<i>Coming soon...</i>

###License
Please refer to the LICENSE.md file for information regarding the licensing details.

###Change Log
Changes made in each update reflect the code in both the desktop applciation and Arduino program.
* <b>Version 1.0</b> <i>(Latest)</i>
  * Implemented keyboard shortcut/control functionality.
  * Fixed many bugs and errors.
  * Minor usability and UI tweaks.
* <b>Version 0.9</b>
  * Added music mode colour customisation.
* <b>Version 0.8</b>
  * Added music mode.
* <b>Version 0.7</b>
  * Additionally implemented dragging over spectrum for selection instead of exclusively upon press.
  * Added circle indicator over spectrum to display which colour is selected.
* <b>Version 0.6</b>
  * Added spectrum mode.
  * Added strobe mode.
* <b>Version 0.5</b>
  * Converted previosuly white user interface into a dark theme.
  * Refined layout of buttons and added mouseover colour change/ button selection colour change.
* <b>Version 0.4</b>
  * Added toggle for the controller LED.
  * Added exit button.
* <b>Version 0.3</b>
  * Added wave mode.
* <b>Version 0.2</b>
  * Created button layout to change between modes.
  * Added random mode.
* <b>Version 0.1</b>
  * Base desktop application structure complete.
  * Base Arduino program structure compelte.
  * Static colour selection mode.

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
