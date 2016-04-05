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
	private boolean existsElementID(String id) {
	    try {
	        driver.findElement(By.id(id));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}
	
	private boolean existsElementCSS(String css) {
	    try {
	        driver.findElement(By.cssSelector(css));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}
	
	public boolean waitForElement(WebDriver driver, By locator, int seconds)
	{
	
				if (seconds > 0){
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
	
	public WebElement findElement(WebDriver driver, By locator, int seconds) {
		try {
		seconds = (seconds == 0 ? 1 : seconds);
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		return driver.findElement(locator);
		} catch (NoSuchElementException e) {
		return null;
		}
		}
//   public static boolean isDisplayedElementByXpath(String elementName) {
//		return waitForElement(driver, By.xpath(elementName), nullWait);
//		}
	
	private boolean contienePalabra(String in_texto, String in_palabra)
	
	{
		boolean resultado;
		if (in_texto.contains(in_palabra))
			 return true;
					
			 else
				 return false;
		
		
	}
	@Test(description = "Exercise03-tema1")
	public void exercise02() {
		WebElement in_busqueda ;
		WebElement in_button;
		WebElement resultadoGoogle;
		WebElement videoPlayer;
		final String URL = "http://www.google.com";
		final String texto_a_buscar = "constant concept";
		
		driver.get(URL);
		in_busqueda = driver.findElement(By.cssSelector("input[id=\"lst-ib\"]"));
		in_busqueda.sendKeys(texto_a_buscar);
		in_button = findElement(driver,By.cssSelector("button[name=\"btnG\"]"),10);
		in_button.click();
	
		resultadoGoogle = findElement(driver, By.xpath("//div[@id='rso']//div[@class='srg']/div[@class='g'][3]//a"), 10);
		resultadoGoogle.click();
		
		//Busco el elemento videoPlayer
		videoPlayer = findElement ( driver , By.id("video-player"), 10);
		
		Assert.assertTrue(videoPlayer != null, "no se encontró el elemento");
	
		
	}
}