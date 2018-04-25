package application.phillips_hue;

import java.util.Map;

import application.AbstractSettings;

public class PhillipsHueSettings extends AbstractSettings {
	/**
	 * Creates a new Phillips Hue settings instance and sends the input information to the Phillips Hue devices in the correct format. 
	 * This API simply converts JSON input into the appropriate output format for the connected IoT device.
	 * 
	 * @param colours - A map of primary, secondary and tertiary colours to their RGB values.
	 * @param platforms - A map of platforms to their use status (either true or false).
	 * @param mode - The animation mode for the output LEDs.
	 * @param brightness - The brightness of the output LEDs (0-100).
	 * @param speed - The speed at which the specified mode animation should run at (0-100).
	 * @param numberOfColours - The number of colours to use in the output from the three available colours (1-3).
	 */
    public PhillipsHueSettings(Map<String, Object> colours, Map<String, Object> platforms, String mode, int brightness, int speed, int numberOfColours) {
       super(colours, platforms, mode, brightness, speed, numberOfColours);
    }
    
    public PhillipsHueSettings() { }

	@Override
	public void output() {
		// TODO Auto-generated method stub
		System.out.print("Output Phillips Hue");
	}
}
