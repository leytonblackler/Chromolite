package application;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import application.arduino.ArduinoSettings;

@CrossOrigin()
@RestController
public class Controller {
	
	private final AtomicLong counter = new AtomicLong();

	/**
	 * Maps the '/global' API endpoint to contstruct a new Settings instance.
	 * 
	 * @param settings - The JSON object defining the input settings.
	 * 
	 * @return A JSON-formatted HTTP response.
	 */
	@RequestMapping(value = "/global", method = RequestMethod.POST)
    @ResponseBody
	public <T extends AbstractSettings> ResponseEntity<AbstractSettings> global(@RequestBody T settings) {
		System.out.println(settings);
    	
		settings.setID(counter.incrementAndGet());
		
		settings.output();
    	
        return new ResponseEntity<AbstractSettings>(settings, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/arduino", method = RequestMethod.POST)
    @ResponseBody
	public <T extends AbstractSettings> ResponseEntity<AbstractSettings> arduino(@RequestBody T settings) {
		System.out.println(settings);
    	
		settings.setID(counter.incrementAndGet());
		
		settings.output();
    	
        return new ResponseEntity<AbstractSettings>(settings, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hue", method = RequestMethod.POST)
    @ResponseBody
	public <T extends AbstractSettings> ResponseEntity<AbstractSettings> hue(@RequestBody T settings) {
		System.out.println(settings);
    	
		settings.setID(counter.incrementAndGet());
		
		settings.output();
    	
        return new ResponseEntity<AbstractSettings>(settings, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chroma", method = RequestMethod.POST)
    @ResponseBody
	public <T extends AbstractSettings> ResponseEntity<AbstractSettings> chroma(@RequestBody T settings) {
		System.out.println(settings);
    	
		settings.setID(counter.incrementAndGet());
		
		settings.output();
    	
        return new ResponseEntity<AbstractSettings>(settings, HttpStatus.OK);
	}
}
