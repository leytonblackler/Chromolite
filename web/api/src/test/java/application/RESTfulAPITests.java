package application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class RESTfulAPITests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void globalNoParamShouldReturn400Error() throws Exception {
        this.mockMvc.perform(post("/global")).andDo(print()).andExpect(status().isBadRequest());
    }
    
    @Test
    public void arduinoNoParamShouldReturn400Error() throws Exception {
        this.mockMvc.perform(post("/arduino")).andDo(print()).andExpect(status().isBadRequest());
    }
    
    @Test
    public void hueNoParamShouldReturn400Error() throws Exception {
        this.mockMvc.perform(post("/hue")).andDo(print()).andExpect(status().isBadRequest());
    }
    
    @Test
    public void chromaNoParamShouldReturn400Error() throws Exception {
        this.mockMvc.perform(post("/chroma")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void validGlobalSettingsShouldReturnValidStatus() throws Exception {
    	this.mockMvc.perform(post("/global").contentType(MediaType.APPLICATION_JSON)
    			.content(createValidSettingsObjectInJSON()))
    	        .andExpect(status().isOk());
    }
    
    @Test
    public void validArduinoSettingsShouldReturnValidStatus() throws Exception {
    	this.mockMvc.perform(post("/arduino").contentType(MediaType.APPLICATION_JSON)
    			.content(createValidSettingsObjectInJSON()))
    	        .andExpect(status().isOk());
    }
    
    @Test
    public void validHueSettingsShouldReturnValidStatus() throws Exception {
    	this.mockMvc.perform(post("/hue").contentType(MediaType.APPLICATION_JSON)
    			.content(createValidSettingsObjectInJSON()))
    	        .andExpect(status().isOk());
    }
    
    @Test
    public void validChromaSettingsShouldReturnValidStatus() throws Exception {
    	this.mockMvc.perform(post("/chroma").contentType(MediaType.APPLICATION_JSON)
    			.content(createValidSettingsObjectInJSON()))
    	        .andExpect(status().isOk());
    }
    
    
    private static String createValidSettingsObjectInJSON() {
       return "{" +
    		   		"\"colours\": {" +
    		   			"\"primary\": {\"red\": 255, \"green\": 0, \"blue\": 0}," +
    		   			"\"secondary\": {\"red\": 0, \"green\": 255, \"blue\": 0}," +
    		   			"\"tertiary\": {\"red\": 0, \"green\": 0, \"blue\": 255}" +
    		   		"}," +
    		   		"\"mode\": \"static\"," +
    		   		"\"brightness\": 100," +
    		   		"\"speed\": 50," +
    		   		"\"numberOfColours\": 1," +
    		   		"\"platforms\": {" +
    		   			"\"arduino\": false," +
    		   			"\"chroma\": false," +
    		   			"\"hue\": false" +
    		   		"}," +
    		   		"\"sync\": false," +
    		   		"\"minimised\": false "+ 
    		 "}";
    }

}
