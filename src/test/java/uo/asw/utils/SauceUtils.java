package uo.asw.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;


public class SauceUtils {

	@Value("${local.server.port}")
	protected int port;
	
	private static final Logger logger = Logger.getLogger(SauceUtils.class);
	
	static public WebDriver getDriver() {
		String user;
		String password;
		DesiredCapabilities capabilities;
		WebDriver driver;
		URL saucelabs = null;
		
//		user = "dashboard2a";
//		password = "23891e28-8674-4bba-ab7a-6ac197094a02";
		
		user = System.getenv("SAUCE_USERNAME");
		password = System.getenv("SAUCE_ACCESS_KEY");
		
		
		if (user != null && password != null) {
			try {
				saucelabs = new URL("http://" + user + ":" + password + "@ondemand.saucelabs.com/wd/hub");
			} catch (MalformedURLException e) {
				logger.error("La URL no es válida");
				logger.info("Utilizando el html driver");
				driver = new HtmlUnitDriver();
			}
			
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("platform", "OS X 10.11");
			capabilities.setCapability("version", "45");
			capabilities.setCapability("tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"));
			capabilities.setCapability("name", "test");
			logger.info("Utilizando el driver remoto");
			driver = new RemoteWebDriver(saucelabs, capabilities);
		} else {
			logger.info("Utilizando html driver");
			driver = new HtmlUnitDriver();
		}
		
		return driver;
	}
}