package com.globant.bootcamp.tae;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;

public class Tema1ej4 {
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

	private boolean busquedaBotonVisible()
	{


		/*   	JavascriptExecutor jse = (JavascriptExecutor) driver; 

		   	//Scrolleo la página
		   	for (int second = 0;; second++) {
		   	    if(second >=60){
		   	        break;
		   	    }
		   	   if ( waitForElement(driver, By.cssSelector("#toggle-comments .pager-content"),15))
		   	   {
		   		System.out.println("encontre boton");
		    	boton_comentarios = driver.findElement(By.cssSelector("#toggle-comments .pager-content"));
		    	  if ((boton_comentarios.isDisplayed())) 
		    		  {break;}
		    	  else { System.out.println("NO VISIBLE AÚN");

		    	   	 jse.executeScript("window.scrollBy(0,200)", "");
		    	  }


			   	    try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		   	   }
		   	   else
		   		   break;

		   	}
		 */
		return false;
	}
	@Test(description = "Exercise04-tema1")
	public void exercise02() {
		
		WebElement link_noticia ;
		//WebElement boton_comentarios;
		List<WebElement> comentarios = null;
		final String URL = "http://tn.com.ar/";
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//Busco la segunda noticia del tercer bloque
		if ( waitForElement(driver,By.xpath("//div[contains(@class, 'block-3') and contains(@class, 'clearfix')]//article[2]"),10)) 
		{

			System.out.println( "Noticia encontrada");
			link_noticia = driver.findElement(By.xpath("//div[contains(@class, 'block-3') and contains(@class, 'clearfix')]//article[2]/h2/a"));
			link_noticia.click();

			//busquedaBotonVisible();

			if ( waitForElement(driver, By.cssSelector(".comment-content"),10))
			{
				comentarios = driver.findElements(By.cssSelector(".comment-content > article"));

			}
			else
			{
				System.out.println("sin comentarios");
			}

		}
		else
		{
			System.out.println( "Error: No se encontró noticia.");

		}
		Assert.assertTrue((comentarios != null) && (comentarios.size()) > 1, "Cantidad de comentarios menor que dos" );
	}
}