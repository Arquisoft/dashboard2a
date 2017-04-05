package uo.asw.steps;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import uo.asw.selenium.RellenarFormularioLogin;
import uo.asw.utils.SauceUtils;
import uo.asw.utils.SeleniumUtils;

public class LoginParticipantSteps {
	
	private WebDriver driver;

	@Before
	public void run() {
		driver = SauceUtils.getDriver();
		driver.navigate().to("http://localhost:8090/");
	}

	@Dado("^un formulario de login de inicio$")
	public void un_formulario_de_login_de_inicio() throws Throwable {
		SeleniumUtils.textoPresentePagina(driver, "Inicio de sesion");
	}

	@Cuando("^me logueo como participante \"([^\"]*)\" con password \"([^\"]*)\"$")
	public void me_logueo_como_participante_con_password(String user, String password) throws Throwable {
		new RellenarFormularioLogin().rellenaFormulario(driver, user, password);
	}

	@Entonces("^voy a la pagina del participante$")
	public void voy_a_la_pagina_del_participante() throws Throwable {
		SeleniumUtils.textoPresentePagina(driver, "Tu informacion");
	}
}
