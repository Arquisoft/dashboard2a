package uo.asw.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ModificaEmail {

	
	 public void rellenaFormulario(WebDriver driver, String pemail) throws InterruptedException
	   {
			WebElement email = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input"));
			email.click();
			email.clear();
			email.sendKeys(pemail);
			
			//Pulsar el boton de email
			By boton = By.xpath("/html/body/form/table/tbody/tr[2]/td/input");
			driver.findElement(boton).click();	   
			Thread.sleep(5000);
	   }
}
