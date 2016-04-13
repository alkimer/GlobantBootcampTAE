package com.globant.bootcamp.tae.PageObjectPattern;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.globant.bootcamp.tae.utils.Utiles;

public class HomeGoogle {
	
	private final String _baseUrl = "http://www.google.com/";
	WebDriver driver;

	
	@FindBy(how = How.CSS, using =  "input[id=\"lst-ib\"]" )
	WebElement searchBox;
		
	@FindBy(how = How.CSS , using = "button[name=\"btnG\"]")
	WebElement searchButton;

	public HomeGoogle()
	{
		//nada
	}
	public HomeGoogle(WebDriver driver) {
		this.driver = driver;
	}

	public void go() {
		driver.get(_baseUrl);
	}

	public String getTitle() {
		return driver.getTitle();
	}
	
	public HomeGenerico clickOnGoogleResult(int numeroItem)
	{
	WebElement itemResultado;
	HomeGenerico homeGenerico = null;
	if (Utiles.waitUntilElementPresent(driver, By.xpath("//div[@id='rso']//div[@class='srg']/div[@class='g'][3]//a"), 10))
			{
		   itemResultado = driver.findElement(By.xpath("//div[@id='rso']//div[@class='srg']/div[@class='g']["+numeroItem+"]//a"));
		   itemResultado.click();
		   
		   homeGenerico = PageFactory.initElements(driver, HomeGenerico.class);
		   homeGenerico.set_baseUrl(driver.getCurrentUrl());
			}
		return homeGenerico;
	}
	
		
	public void search(String query) {
		searchBox.sendKeys(query);
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}
	
	
	
}
 
 

