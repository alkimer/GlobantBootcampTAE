package com.globant.bootcamp.tae.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.globant.bootcamp.tae.dataprovider.Email;

public class Utiles {
	
	public static boolean contienePalabra(String in_texto, String in_palabra)
	
	{
		if (in_texto.toUpperCase().contains(in_palabra.toUpperCase()))
			 return true;
					
			 else
				 return false;
		
		
	}
	
	public static WebElement getStaleElem(By by, WebDriver driver) {
	    try {
	        return driver.findElement(by);
	    } catch ( StaleElementReferenceException | NoSuchElementException  e) {
	        System.out.println("Attempting to recover from " + e.getClass().getSimpleName() + "...");
	        return getStaleElem(by, driver);
	    }
	}
	public static boolean contieneEmail( List<Email> bandeja, Email emailGenerado)
	{
		boolean contiene = false;
		
		for (Email emailTemp :bandeja )
		{
		   System.out.println("BANDEJA FROM:" + emailTemp.getFrom() + " generado FROM:" + emailGenerado.getFrom());
		   System.out.println("BANDEJA to:" + emailTemp.getTo() + " generado TO" + emailGenerado.getTo());
		   System.out.println("BANDEJA subject:" + emailTemp.getSubject() + " generado subject:" + emailGenerado.getSubject());
		   System.out.println("BANDEJA body:" + emailTemp.getBody() + " generado Body:" + emailGenerado.getBody());

			
			if ( emailTemp.getFrom().equals(emailGenerado.getFrom()) &&
				 emailTemp.getTo().equals(emailGenerado.getTo()) &&
				 emailTemp.getSubject().equals(emailGenerado.getSubject()) &&
				 emailTemp.getBody().contains((emailGenerado.getBody()) ) )
			{
				contiene = true;
				break;
			}
		}
		
		return contiene;
		
		
	}
	
	public static boolean waitForElement(WebDriver driver, By locator, int seconds)
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
	
	public static WebElement findElement(WebDriver driver, By locator,int seconds) 
	{
		try
		{
		seconds = ( seconds == 0 ? 1 : seconds);
	
	    driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	    return driver.findElement(locator);
		}
		catch (NoSuchElementException e)
		{
			return null;
		}
	}
	
	 public static boolean waitUntilElementPresent(WebDriver driver, By locator, int maxSeconds) {
		  long initialMillis = System.currentTimeMillis();
		  boolean loading = true;
		  boolean timeout = false;
		  boolean error = false;
		  boolean presente = false;
		  do {   
		   presente = isPresentElement(driver,locator);  
		   long currentMillis = System.currentTimeMillis();
		   if ((currentMillis - initialMillis) / 1000 >= maxSeconds){
		    loading = false;
		    timeout = true;
		   }
		  } while (!presente && !timeout);
		  if(timeout){
		   Reporter.log ("Timeout: "+ maxSeconds + " seconds have passed waiting for page");
		 //  error = true;
		  }
		  return presente;
		 }
	 
	 public static boolean isPresentElement(WebDriver driver, By locator) {
		  return waitForElement(driver, locator, -1);
		 }

}
