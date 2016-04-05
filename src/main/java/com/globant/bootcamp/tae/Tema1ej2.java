package com.globant.bootcamp.tae;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;

public class Tema1ej2 {
	private WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) {
		try {
			driver = new FirefoxDriver();
		} catch (Exception e) {
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestContext context) {
		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
		}
	}
	
	public boolean waitForElement(WebDriver driver, By locator, int seconds)
	{	
		if (seconds > 0)
		{
			System.out.println("Waiting for element "+ locator +" for " + seconds + "seconds.");
		}
		else
		{
			System.out.println("Checking if element "+ locator +" is present.");
		}


		try {
			seconds = (seconds == 0 ? 1 : seconds);
			driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
			driver.findElement(locator);
			return true;
		} 
		catch (NoSuchElementException e) 
		{
			return false;
		}

	} 

	
	private boolean contienePalabra(String in_texto, String in_palabra)
	
	{
		boolean resultado;
		if ((in_texto != null ) && (in_palabra != null ) && (in_texto.contains(in_palabra)))
			 return true;
					
			 else
				 return false;
		
		
	}
	@Test(description = "Exercise02-tema1")
	public void exercise02() {
		WebElement comentario ;
	
	//	final String palabra = "Noticia";
	
		WebElement in_userName ;
		WebElement in_password ;
		WebElement in_button;
		WebElement usuario_recibido;
		WebElement mensaje_recibido;
		WebElement email_bandeja = null;
		final String URL = "http://live.com";
		final String palabra = "support.com";
		final String texto_a_comparar = "Noticia";
		String texto_comentario;
		driver.get(URL);
		in_userName = driver.findElement(By.name("loginfmt"));
		in_password = driver.findElement(By.name("passwd"));
		
		
		in_button = driver.findElement(By.cssSelector("input[id=\"idSIButton9\"]"));
		
		in_userName.sendKeys("alkimer@hotmail.com");
		in_password.sendKeys("inicio01");
		in_button.click();
		
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//Obtengo primer email de la lista
		if (waitForElement(driver, By.cssSelector(".mailList.InboxTableBody:first-child .FmSender span:first-child"), 10)) 
		{
			email_bandeja = driver.findElement(By.cssSelector(".mailList.InboxTableBody:first-child .FmSender span:first-child"));
		
	
		}
		Assert.assertTrue(contienePalabra(email_bandeja.getAttribute("email"), palabra), "El remitente no es support.com , es : " + email_bandeja.getAttribute("email") + ".");

	}
}