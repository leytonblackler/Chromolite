package application;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import application.arduino.ArduinoSettings;
import application.global.GlobalSettings;
import application.phillips_hue.PhillipsHueSettings;
import application.razer_chroma.RazerChromaSettings;

@CrossOrigin()
@RestController
public class Controller {
	@SuppressWarnings("serial")
	private final Map<String, AtomicLong> counter = new HashMap<String, AtomicLong>() {{
		put("global", new AtomicLong());
		put("arduino", new AtomicLong());
		put("hue", new AtomicLong());
		put("chroma", new AtomicLong());
	}};

	/**
	 * Maps the '/global' API endpoint to construct a new GlobalSettings instance for controlling multiple outputs at once.
	 * 
	 * @param settings - The JSON object defining the input settings.
	 * 
	 * @return A JSON-formatted HTTP response.
	 */
	@RequestMapping(value = "/global", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<GlobalSettings> global(@RequestBody GlobalSettings settings) {
		System.out.println(settings);
    	
		settings.setID(counter.get("global").incrementAndGet());
		
		settings.output();
    	
        return new ResponseEntity<GlobalSettings>(settings, HttpStatus.OK);
	}
	
	/**
	 * Maps the '/arduino' API endpoint to construct a new ArduinoSettings instance for controlling the Arduino output.
	 * 
	 * @param settings - The JSON object defining the input settings.
	 * 
	 * @return A JSON-formatted HTTP response.
	 */
	@RequestMapping(value = "/arduino", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<ArduinoSettings> arduino(@RequestBody ArduinoSettings settings) {
		System.out.println(settings);
    	
		settings.setID(counter.get("arduino").incrementAndGet());
		
		settings.output();
    	
        return new ResponseEntity<ArduinoSettings>(settings, HttpStatus.OK);
	}
	
	/**
	 * Maps the '/hue' API endpoint to construct a new ArduinoSettings instance for controlling the Phillips Hue output.
	 * 
	 * @param settings - The JSON object defining the input settings.
	 * 
	 * @return A JSON-formatted HTTP response.
	 */
	@RequestMapping(value = "/hue", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<PhillipsHueSettings> hue(@RequestBody PhillipsHueSettings settings) {
		System.out.println(settings);
    	
		settings.setID(counter.get("hue").incrementAndGet());
		
		settings.output();
    	
        return new ResponseEntity<PhillipsHueSettings>(settings, HttpStatus.OK);
	}
	
	/**
	 * Maps the '/chroma' API endpoint to construct a new ArduinoSettings instance for controlling the Razer Chroma output.
	 * 
	 * @param settings - The JSON object defining the input settings.
	 * 
	 * @return A JSON-formatted HTTP response.
	 */
	@RequestMapping(value = "/chroma", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<RazerChromaSettings> chroma(@RequestBody RazerChromaSettings settings) {
		System.out.println(settings);
    	
		settings.setID(counter.get("chroma").incrementAndGet());
		
		settings.output();
    	
        return new ResponseEntity<RazerChromaSettings>(settings, HttpStatus.OK);
	}
}
