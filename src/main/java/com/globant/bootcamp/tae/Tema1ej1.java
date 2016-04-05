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

public class Tema1ej1 {
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
	
	private boolean contienePalabra(String in_texto, String in_palabra)
	
	{
		boolean resultado;
		if (in_texto.contains(in_palabra))
			 return true;
					
			 else
				 return false;
		
		
	}
	@Test(description = "Exercise01-tema1")
	public void exercise01() {
		WebElement comentario ;
		final String URL = "http://labrujula24.com/noticias/2016/23073_Mira-el-video-de-la-patada-criminal-que-estremecio-al-mundo-del-rugby";
		final String palabra = "Noticia";
	
		final String texto_a_comparar = "Noticia";
		String texto_comentario;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='column-left']//iframe[@class='fb_ltr']")));

		comentario = driver.findElement(By.cssSelector("._5mdd"));
	
		System.out.println("comentario leido : " + comentario.getText());
		Assert.assertTrue(contienePalabra(comentario.getText(), palabra), "No se encontr� la palabra: " + palabra + ".");
		
		
	}
}