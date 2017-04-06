package uo.asw.steps;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Y;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
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
public class DetallesComentario {


    private WebDriver driver;

    @Before
    public void run() {
        driver = SauceUtils.getDriver();
        driver.navigate().to("http://localhost:8090/");
    }

   /* @Dado("^que me logueo como alcalde \"([^\"]*)\" con password \"([^\"]*)\"$")
    public void me_logueo_como_alcalde(String user, String password) throws Throwable {
        new RellenarFormularioLogin().rellenaFormulario(driver, user, password);
    }*/

  /*  @Y("^accedo al dashboard$")
    public void accedo_al_dashboard_() throws Throwable {
        assertEquals("Dashboard", driver.findElement(By.className("title")).getText());
    }*/

    @Cuando("^hago click sobre el primer comentario de la lista$")
    public void click_sobre_primera_sugerencia() throws Throwable {
        //driver.findElement(By.id("commentsSection"));
      /*  Point hoverItem = driver.findElement(By.id("commentsSection")).getLocation();
        ((JavascriptExecutor) driver).executeScript("return window.title;");
        Thread.sleep(6000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + (hoverItem.getY()) + ");");
        driver.findElement(By.xpath("//*[contains(@id,'comment1')]/td[3]/a")).click();
*/
    }

    @Cuando("^accedo a la vista de detalles de ese comentario$")
    public void accedo_a_la_vista_de_detalles() throws Throwable {
      //  SeleniumUtils.EsperaCargaPagina(driver, "text", "Detalles del comentario", 5);
    }

}
