package uo.asw.selenium.dashboard;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import uo.asw.utils.SeleniumUtils;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class AutenticationTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new HtmlUnitDriver();
        baseUrl = "http://localhost:8090/";
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void successful() throws Exception {
        driver.get(baseUrl + "/");
        SeleniumUtils.textoPresentePagina(driver,
                "Sistema de participacion ciudadana");
        driver.findElement(By.id("userInput")).sendKeys("alcalde");
        driver.findElement(By.id("passwdInput")).sendKeys("1234");
        driver.findElement(By.id("btnEnviar")).click();
        try {
            assertEquals("Dashboard", driver.findElement(By.className("title")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @Test
    public void unsuccessful() throws Exception {
        driver.get(baseUrl + "/");
        SeleniumUtils.textoPresentePagina(driver,
                "Sistema de participacion ciudadana");
        driver.findElement(By.id("userInput")).sendKeys("alcalde");
        driver.findElement(By.id("passwdInput")).sendKeys("alcalde");
        driver.findElement(By.id("btnEnviar")).click();
        SeleniumUtils.textoPresentePagina(driver,
                "Error: no se encuentra el usuario");
        driver.findElement(new By.ByLinkText("Volver a inicio")).click();
        SeleniumUtils.textoPresentePagina(driver,
                "Sistema de participacion ciudadana");

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}

