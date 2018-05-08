package application;

import java.util.Map;

public abstract class Settings {
	protected long id;
	protected Map<String, Object> colours;
	protected Map<String, Object> platforms;
	protected String mode;
	protected int brightness;
	protected int speed;
	protected int numberOfColours;
	
	/**
	 * Default constructor required by the Spring framework.
	 */
	public Settings() { }
    
	/**
	 * Creates a new abstract Settings instance to be implemented as appropriate by each settings implementation. 
	 * This API simply converts JSON input into the appropriate output format for the connected IoT device.
	 * 
	 * @param colours - A map of primary, secondary and tertiary colours to their RGB values.
	 * @param mode - The animation mode for the output LEDs.
	 * @param brightness - The brightness of the output LEDs (0-100).
	 * @param speed - The speed at which the specified mode animation should run at (0-100).
	 * @param numberOfColours - The number of colours to use in the output from the three available colours (1-3).
	 */
    public Settings(Map<String, Object> colours, Map<String, Object> platforms, String mode, int brightness, int speed, int numberOfColours) {
        this.colours = colours;
        this.platforms = platforms;
        this.mode = mode;
        this.brightness = brightness;
        this.speed = speed;
        this.numberOfColours = numberOfColours;      
    }
	
	/**
     * Returns the ID of this settings object. Every time the settings are changed, the ID increases by one. This atomic number is reset 
     * every time the REST API server is reset.
     * 
     * @return The unique ID of this settings object for the current REST API server instance.
     */
    public long getID() {
    	return id;
    }
    
    /**
     * Returns the RGB colour map for primary, secondary and tertiary colours of this settings instance.
     * 
     * @return A map of the primary, secondary, and tertiary RGB colours. 
     */
    public Map<String, Object> getColours() {
        return colours;
    }
    
    /**
     * Returns the platforms that this settings instance is intended for.
     * 
     * @return A map of the intended output platforms.
     */
    public Map<String, Object> getPlatforms() {
        return platforms;
    }
    
    /**
     * Returns the animation mode used for this settings instance defining how the RGB LEDs should be animated.
     * 
     * @return The LED animation mode for this settings instance.
     */
    public String getMode() {
    	return mode;
    }
    
    /**
     * Returns the brightness level of this settings instance defining the output power level to the LEDs.
     * 
     * @return The brightness level on a scale of 0-100.
     */
    public int getBrightness() {
    	return brightness;
    }
    
    /**
     * Returns the speed at which the specified animation is occurring at for this settings instance.
     * 
     * @return The animation mode speed on a scale of 0-100.
     */
    public int getSpeed() {
    	return speed;
    }
    
    /**
     * Returns the number of colours in use by the RGB LEDs for the specified animation mode. Colours are selected in order of primary, 
     * secondary, and then tertiary.
     * 
     * @return The number of colours to use in the animation on a scale of 1-3.
     */
    public int getNumberOfColours() {
    	return numberOfColours;
    }
    
    /**
     * Sets the unique atomic ID for this settings instance for debugging and logging purposes.
     *  
     * @param id - The unique atomic ID of this settings instance.
     */
    public void setID(long id) {
    	this.id = id;
    }
    
    /**
     * Sets the primary, secondary, and tertiary RGB colours for the current settings instance.
     *  
     * @param colours - The RGB colour representation of the primary, secondary, and tertiary colours.
     */
    public void setColours(Map<String, Object> colours) {
        this.colours = colours;
    }
    
    /**
     * Sets the intended output platforms for the current settings instance.
     *  
     * @param platforms - The map of intended platforms.
     */
    public void setPlatforms(Map<String, Object> platforms) {
        this.platforms = platforms;
    }
    
    /**
     * Sets the animation mode of this settings instance.
     * 
     * @param mode - The desired animation mode defining how to animate the connected RGB LEDs.
     */
    public void setMode(String mode) {
    	this.mode = mode;
    }
    
    /**
     * Sets the brightness level on a scale of 0-100 for this settings instance.
     * 
     * @param brightness - The new brightness level on a scale of 0-100.
     */
    public void setBrightness(int brightness) {
    	this.brightness = brightness;
    }
    
    /**
     * Sets the speed of LED animation on a scale of 0-100 for this settings instance.
     * 
     * @param speed - The new speed value on a scale of 0-100.
     */
    public void setSpeed(int speed) {
    	this.speed = speed;
    }
    
    /**
     * Sets the number of colours to select from for the output. Colours are selected in order of primary, secondary, then tertiary.
     * 
     * @param numberOfColours - The number of specified colours to select from for the output animation.
     */
    public void setNumberOfColours(int numberOfColours) {
    	this.numberOfColours = numberOfColours;
    }
    
    /**
     * Outputs the current settings to the connected device. This is an abstract class as each output implementation is different and 
     * is based upon the formatting and data structures required by each type of connected IoT device. 
     */
    public abstract void output();
}
