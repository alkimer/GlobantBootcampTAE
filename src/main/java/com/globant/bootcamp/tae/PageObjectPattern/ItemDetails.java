package com.globant.bootcamp.tae.PageObjectPattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemDetails  {

	private String _baseUrl;
	
	@FindBy( id = "productTitle")
	 private WebElement titulo;
	
	public String getTitulo() {
		return titulo.getText();
	}


	WebDriver driver;
	
	
	public ItemDetails ( WebDriver driver)
	{
		this.driver = driver;
	//	this._baseUrl = url;
	}
	
	public String get_baseUrl() {
		return _baseUrl;
	}
	public void set_baseUrl(String _baseUrl) {
		this._baseUrl = _baseUrl;
	}
}
