package uo.asw.steps;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import uo.asw.Application;
import uo.asw.selenium.RellenarFormularioLogin;
import uo.asw.utils.SauceUtils;
import uo.asw.utils.SeleniumUtils;

import static org.junit.Assert.assertEquals;


@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class DetallesSugerencia {


    private WebDriver driver;

    @Before
    public void run() {
        driver = SauceUtils.getDriver();
        driver.navigate().to("http://localhost:8090/");
    }

    @Dado("^que me logueo como alcalde \"([^\"]*)\" con password \"([^\"]*)\"$")
    public void me_logueo_como_alcalde(String user, String password) throws Throwable {
        new RellenarFormularioLogin().rellenaFormulario(driver, user, password);
    }

    @Y("^accedo al dashboard$")
    public void accedo_al_dashboard() throws Throwable {
        assertEquals("Dashboard", driver.findElement(By.className("title")).getText());
    }

    @Cuando("^hago click sobre la primera sugerencia de la lista$")
    public void click_sobre_primera_sugerencia() throws Throwable {
        driver.findElement(By.id("suggestionsSection"));
        driver.findElement(By.xpath("//*[contains(@id,'suggestion1')]/td[3]/a")).click();
    }

    @Cuando("^accedo a la vista de detalles de la misma$")
    public void accedo_a_la_vista_de_detalles() throws Throwable {
        SeleniumUtils.EsperaCargaPagina(driver, "text", "Detalles de la sugerencia", 5);
    }

}
