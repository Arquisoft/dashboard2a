package uo.asw.steps;

import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.Before;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import uo.asw.Application;
import uo.asw.selenium.ModificaEmail;
import uo.asw.selenium.RellenarFormularioLogin;
import uo.asw.utils.SauceUtils;
import uo.asw.utils.SeleniumUtils;

@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class EditParticipantMailSteps {

	private WebDriver driver;

	@Before
	public void run() {
		driver = SauceUtils.getDriver();
		driver.navigate().to("http://localhost:8090/");
	}

	
	@Dado("^que me logueo como participante \"([^\"]*)\" con password \"([^\"]*)\"$")
	public void que_me_logueo_como_participante_con_password(String user, String password) throws Throwable {
		new RellenarFormularioLogin().rellenaFormulario(driver, user, password);
	}

	@Dado("^modifico el email \"([^\"]*)\"$")
	public void modifico_el_email(String email) throws Throwable {
	   new ModificaEmail().rellenaFormulario(driver, email);
	}

	@Entonces("^el email se actualiza$")
	public void el_email_se_actualiza() throws Throwable {
	    SeleniumUtils.textoPresentePagina(driver, "prueba@mail.com");
	}


}
