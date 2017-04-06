package uo.asw.others;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import uo.asw.Application;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class WEBControllerTest {

	   @Autowired
	    private WebApplicationContext wac;
	 
	    private MockMvc mockMvc;
	    
	    @Before
	    public void init() {
	        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	    }
	    
	    
	    @Test
	    public void accessTest() throws Exception {

		 //Acceso de un citizen 
	      mockMvc.perform(post("/acceso")
			.param("user", "user1")
			.param("password", "1234"))
	      	.andExpect(status().isOk())
	      	.andExpect(model().attributeExists("resultado"))
	      	.andExpect(view().name("viewParticipant"));
	    	
		 //Acceso de un user (alcalde)
	      mockMvc.perform(post("/acceso").param("user", "alcalde").param("password", "1234"))
	      	.andExpect(status().isOk()).andExpect(view().name("panel"));
	    }

	    @Test
	    public void wrongAccessTest() throws Exception {

	      mockMvc.perform(post("/acceso").param("user", "wrongUser").param("password", "1234"))
	      	.andExpect(view().name("error"));

	      mockMvc.perform(post("/acceso").param("user", "user1").param("password", "wrongPassword"))
	      	.andExpect(view().name("error"));

	    }
	    

	    @Test
	    public void showViewTest() throws Exception {
	    	
	        mockMvc.perform(get("/"))
	            .andExpect(status().isOk()).andExpect(view().name("log"));
	        
	        mockMvc.perform(get("/portal"))
            .andExpect(status().isOk()).andExpect(view().name("log"));
	    }
	    
	    @Test
	    public void closeSessionTest() throws Exception {
	    	
	        mockMvc.perform(get("/closeSession"))
	            .andExpect(status().isOk()).andExpect(view().name("log"));
	        
	    }
	    
	  
}
