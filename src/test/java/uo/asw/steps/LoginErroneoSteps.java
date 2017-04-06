package uo.asw.steps;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
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


@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LoginErroneoSteps {


    private WebDriver driver;

    @Before
    public void run() {
        driver = SauceUtils.getDriver();
        driver.navigate().to("http://localhost:8090/");
    }

    @Dado("^el formulario de registro de la pagina principal$")
    public void un_formulario_principal() throws Throwable {
        SeleniumUtils.textoPresentePagina(driver,
                "Sistema de participacion ciudadana");
        SeleniumUtils.textoPresentePagina(driver, "Inicio de sesion");
    }

    @Cuando("^me logueo como usuario que no existe$")
    public void me_logueo_como_usuario_inexistente() throws Throwable {
        new RellenarFormularioLogin().rellenaFormulario(driver, "inexistente", "fake");
    }

    @Entonces("^se me muestra un error$")
    public void se_me_muestra_un_error() throws Throwable {
        SeleniumUtils.textoPresentePagina(driver,
                "Error: no se encuentra el usuario");
        driver.findElement(new By.ByLinkText("Volver a inicio")).click();


    }

}
