package application.global;

import java.util.Map;

import application.Settings;
import application.arduino.ArduinoSettings;
import application.phillips_hue.PhillipsHueSettings;
import application.razer_chroma.RazerChromaSettings;

public class GlobalSettings extends Settings {
	
	/**
	 * Creates a new global settings instance and sends the input information to platforms specified in the request. 
	 * This API simply converts JSON input into the appropriate output format for the connected IoT device.
	 * 
	 * @param colours - A map of primary, secondary and tertiary colours to their RGB values.
	 * @param platforms - A map of platforms to their use status (either true or false).
	 * @param mode - The animation mode for the output LEDs.
	 * @param brightness - The brightness of the output LEDs (0-100).
	 * @param speed - The speed at which the specified mode animation should run at (0-100).
	 * @param numberOfColours - The number of colours to use in the output from the three available colours (1-3).
	 */
    public GlobalSettings(Map<String, Object> colours, Map<String, Object> platforms, String mode, int brightness, int speed, int numberOfColours) {
       super(colours, platforms, mode, brightness, speed, numberOfColours);
    }
    
    /**
	 * Default constructor required by the Spring framework.
	 */
    public GlobalSettings() { }
	
	/**
	 * Calls the appropriate device output methods based upon the platforms specified by the incoming HTTP request.
	 */
	@Override
	public void output() {
		if (platforms.get("arduino").equals(true)) {
			ArduinoSettings arduinoSettings  = new ArduinoSettings(colours, platforms, mode, brightness, speed, numberOfColours);
			arduinoSettings.output();
		}
		if (platforms.get("hue").equals(true)) {
			PhillipsHueSettings hueSettings  = new PhillipsHueSettings(colours, platforms, mode, brightness, speed, numberOfColours);
			hueSettings.output();
		}
		if (platforms.get("chroma").equals(true)) {
			RazerChromaSettings chromaSettings  = new RazerChromaSettings(colours, platforms, mode, brightness, speed, numberOfColours);
			chromaSettings.output();
		}
	}
}
