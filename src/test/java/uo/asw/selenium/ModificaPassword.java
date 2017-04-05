package uo.asw.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ModificaPassword {

	
	 public void rellenaFormulario(WebDriver driver, String anterior, String nueva) throws InterruptedException
	   {
			WebElement passanterior = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input"));
			passanterior.click();
			passanterior.clear();
			passanterior.sendKeys(anterior);
			
			WebElement pasnueva = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input"));
			pasnueva.click();
			pasnueva.clear();
			pasnueva.sendKeys(nueva);
			
			//Pulsar el boton de email
			By boton = By.xpath("/html/body/form/table/tbody/tr[3]/td[1]/input");
			driver.findElement(boton).click();	   
			Thread.sleep(5000);
	   }
}
