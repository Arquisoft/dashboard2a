package uo.asw.steps;

import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import uo.asw.Application;
import uo.asw.selenium.RellenarFormularioLogin;
import uo.asw.utils.SauceUtils;
import uo.asw.utils.SeleniumUtils;

@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LoginConcejalSteps {

	private WebDriver driver;

	@Before
	public void run() {
		driver = SauceUtils.getDriver();
		driver.navigate().to("http://localhost:8090/");
	}

	@Dado("^una pagina con un formulario de login$")
	public void un_formulario_de_login() throws Throwable {
		SeleniumUtils.textoPresentePagina(driver, "Inicio de sesion");
	}

	@Cuando("^me logueo como concejal \"([^\"]*)\" con password \"([^\"]*)\"$")
	public void me_logueo_como_usuario_con_password(String user, String password) throws Throwable {
		new RellenarFormularioLogin().rellenaFormulario(driver, user, password);
	}
	
	@Entonces("^voy al dashboard del concejal$")
	public void voy_al_dashboard_del_concejal() throws Throwable {
		SeleniumUtils.textoPresentePagina(driver, "Dashboard");
		SeleniumUtils.textoPresentePagina(driver, "Comentarios");
	}
}
