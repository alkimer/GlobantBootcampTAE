package com.globant.bootcamp.tae.PageObjectPattern;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Utiles {
	
	public static boolean contienePalabra(String in_texto, String in_palabra)
	
	{
		boolean resultado;
		if (in_texto.toUpperCase().contains(in_palabra.toUpperCase()))
			 return true;
					
			 else
				 return false;
		
		
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

}
