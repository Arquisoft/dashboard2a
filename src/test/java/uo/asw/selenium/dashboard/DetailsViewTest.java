package uo.asw.selenium.dashboard;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import uo.asw.Application;
import uo.asw.utils.SauceUtils;
import uo.asw.utils.SeleniumUtils;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
public class DetailsViewTest {

    //    private web
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = SauceUtils.getDriver();
        baseUrl = "http://localhost:8090/";
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void test1() throws Exception {
        driver.navigate().to(baseUrl);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        // nos logueamos
        driver.findElement(By.id("userInput")).sendKeys("alcalde");
        driver.findElement(By.id("passwdInput")).sendKeys("1234");
        driver.findElement(By.id("btnEnviar")).click();

        // Verificamos que estamos en la pagina principal:
        assertEquals("Dashboard", driver.findElement(By.className("title")).getText());
        driver.findElement(By.id("suggestionsSection")); // commentsSection

        driver.findElement(By.xpath("//*[contains(@id,'suggestion1')]/td[3]/a")).click();

        SeleniumUtils.EsperaCargaPagina(driver,"text", "Detalles de la sugerencia", 5);
    }

    @Test
    public void test2() throws Exception {
        driver.navigate().to(baseUrl);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        // nos logueamos
        driver.findElement(By.id("userInput")).sendKeys("alcalde");
        driver.findElement(By.id("passwdInput")).sendKeys("1234");
        driver.findElement(By.id("btnEnviar")).click();

        // Verificamos que estamos en la pagina principal:
        assertEquals("Dashboard", driver.findElement(By.className("title")).getText());
        driver.findElement(By.id("commentsSection")); // commentsSection

        driver.findElement(By.xpath("//*[contains(@id,'comment1')]/td[3]/a")).click();

        SeleniumUtils.EsperaCargaPagina(driver,"text", "Detalles del comentario", 5);    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}

