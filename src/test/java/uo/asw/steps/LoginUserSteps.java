package uo.asw.steps;



import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;




import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uo.asw.Application;
import uo.asw.dashboard.MainController;
import uo.asw.utils.SauceUtils;

import java.util.List;

@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LoginUserSteps {
	
    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    private WebDriver driver;
    
    @Before
    public void run(){
    	driver = SauceUtils.getDriver();
    	driver.get("localhost:8090");
    }
    
    

   
}
