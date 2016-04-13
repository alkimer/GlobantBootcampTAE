package com.globant.bootcamp.tae.PageObjectPattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globant.bootcamp.tae.utils.Utiles;

public class HomeGenerico  {

	private String _baseUrl;
	

	WebDriver driver;
	
	
	public HomeGenerico ( WebDriver driver)
	{
		this.driver = driver;
	}
	
	public boolean contieneElemento(By locator)
	{ 
		boolean contiene = false;
		
		if (Utiles.waitUntilElementPresent(driver, locator, 10))
		{
			contiene = true;
		}
		return contiene;
	}
	public String get_baseUrl() {
		return _baseUrl;
	}
	public void set_baseUrl(String _baseUrl) {
		this._baseUrl = _baseUrl;
	}
}
