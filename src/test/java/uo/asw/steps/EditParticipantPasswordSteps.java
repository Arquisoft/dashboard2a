package uo.asw.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.Before;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import uo.asw.Application;
import uo.asw.selenium.ModificaPassword;
import uo.asw.selenium.RellenarFormularioLogin;
import uo.asw.utils.SauceUtils;
import uo.asw.utils.SeleniumUtils;

@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class EditParticipantPasswordSteps {
	
	private WebDriver driver;

	@Before
	public void run() {
		driver = SauceUtils.getDriver();
		driver.navigate().to("http://localhost:8090/");
	}
	
	@Dado("^que me logueo en la pagina como participante \"([^\"]*)\" con password \"([^\"]*)\"$")
	public void que_me_logueo_en_la_pagina_como_participante_con_password(String user, String password) throws Throwable {
		new RellenarFormularioLogin().rellenaFormulario(driver, user, password);
	}

	@Dado("^pulso en el boton de modificar datos \"([^\"]*)\"$")
	public void pulso_en_el_boton_de_modificar_datos(String link) throws Throwable {
		By boton = By.xpath(link);
		driver.findElement(boton).click();	
		Thread.sleep(5000);
	}

	@Dado("^escribo mi password actual \"([^\"]*)\" y escribo mi password nueva \"([^\"]*)\"$")
	public void escribo_mi_password_actual_y_escribo_mi_password_nueva(String anterior, String nueva) throws Throwable {
	    new ModificaPassword().rellenaFormulario(driver, anterior, nueva);
	}

	@Dado("^pulso en volver a incio \"([^\"]*)\"$")
	public void pulso_en_volver_a_incio(String link) throws Throwable {
		By boton = By.xpath(link);
		driver.findElement(boton).click();	
		Thread.sleep(5000);
	}

	@Dado("^me vuelvo a loguear como participante \"([^\"]*)\" con la nueva password \"([^\"]*)\"$")
	public void me_vuelvo_a_loguear_como_participante_con_la_nueva_password(String user, String password) throws Throwable {
		new RellenarFormularioLogin().rellenaFormulario(driver, user, password);
	}

	@Entonces("^estoy en la pagina principal de usuario$")
	public void estoy_en_la_pagina_principal_de_usuario() throws Throwable {
		SeleniumUtils.textoPresentePagina(driver, "Tu informacion");
	}

}
