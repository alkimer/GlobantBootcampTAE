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

import com.globant.bootcamp.tae.utils.Constants;
import com.globant.bootcamp.tae.utils.Utiles;
import com.thoughtworks.selenium.Selenium;

public class Tema1ej3 {
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

	@Test(description = "Exercise03-tema1")
	public void exercise02() {
		WebElement in_busqueda = null ;
		WebElement in_button;
		WebElement resultadoGoogle;
		WebElement videoPlayer;
	//	final String URL = "http://www.google.com";
	//	final String texto_a_buscar = "constant concept";
		
		driver.get(Constants.TEMA1_EJ3_URL);
		
		if ( Utiles.waitUntilElementPresent(driver, By.cssSelector("input[id=\"lst-ib\"]"), 10))
		{  
			in_busqueda = driver.findElement(By.cssSelector("input[id=\"lst-ib\"]"));
		}
		Assert.assertTrue(in_busqueda != null, ("no se ha encontrado campo de búsqueda"));

		
		in_busqueda.sendKeys(Constants.TEMA1_EJ3_TEXTO_BUSQUEDA);
		in_button = Utiles.findElement(driver,By.cssSelector("button[name=\"btnG\"]"),10);
		in_button.click();
	
		resultadoGoogle = Utiles.findElement(driver, By.xpath("//div[@id='rso']//div[@class='srg']/div[@class='g'][3]//a"), 10);
		resultadoGoogle.click();
		
		//Busco el elemento videoPlayer
		videoPlayer = Utiles.findElement ( driver , By.id("video-player"), 10);
		
		Assert.assertTrue(videoPlayer != null, "no se ha encontrado el elemento");
	
		
	}
}