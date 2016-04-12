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

public class HomeAmazon {
	
	private final String _baseUrl = "http://www.amazon.com/";
	WebDriver driver;
	
	@FindBy(id = "nav-logo")
	WebElement logoAmazon;
	
	@FindBy(id = "twotabsearchtextbox")
	WebElement searchBox;
	
	@FindBy(id = "nav-link-shopall")
	WebElement leftMenu;
	
	@FindBy(how = How.CSS , using = "input.nav-input")
	WebElement searchButton;

	public HomeAmazon()
	{
		//nada
	}
	public HomeAmazon(WebDriver driver) {
		this.driver = driver;
	}

	public void go() {
		driver.get(_baseUrl);
	}

	public String getTitle() {
		return driver.getTitle();
	}
	
	public ItemDetails clickOnListItem(int numeroItem)
	{
		WebElement titulo_item ;
		ItemDetails itemDetails = null;
	 if (Utiles.waitUntilElementPresent(driver,By.xpath("//ul[@id='s-results-list-atf']//li["+numeroItem+"]//div[@class='s-item-container']//a"),10))

		{
			titulo_item = driver.findElement( By.xpath("//ul[@id='s-results-list-atf']//li["+numeroItem+"]//div[@class='s-item-container']//a"));
			titulo_item.click();
			itemDetails = PageFactory.initElements(driver, ItemDetails.class);
			itemDetails.set_baseUrl(driver.getCurrentUrl());
	
			
		}
		
	 return itemDetails;
		
	}
	
	public void clickOnLogo()
	{
		logoAmazon.click();
	}
	
	public void search(String query) {
		searchBox.sendKeys(query);
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}
	
	public void clickOnLeftMenu()
	{
	//	Actions builder = new Actions(driver);
	//	builder.moveToElement(leftMenu).perform();
	//	do{
			leftMenu.click();
	 //   }
//	while(	!Utiles.waitUntilElementPresent(driver,By.cssSelector("#shopAllLinks td:nth-child(2) div:nth-child(4) ul li:first-child"),2));
	
		
	}

	public void clickOnLeftMenuItemMovies()
	{
		WebDriverWait wait;
		WebElement movies = null;
	//	if (Utiles.waitUntilElementPresent(driver,By.cssSelector("#shopAllLinks td:nth-child(2) div:nth-child(4) ul li:first-child"),2000))
		
		
		if ( Utiles.waitUntilElementPresent(driver,By.linkText("Movies & TV"),10))
		{
		movies  = new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.linkText("Movies & TV")));
		movies.click();
		}
		 else
			System.out.println("No se encuentra el link 'Movies & TV'");

	}
	
	public int getSearchResultQuantity()
	{  //cantidad de resultados de la búsqueda
				
	if (Utiles.waitUntilElementPresent(driver,By.cssSelector("#s-results-list-atf li"),10))
			
		{
		List<WebElement> resultados= driver.findElements(By.cssSelector("#s-results-list-atf li")); 	
		return resultados.size();

		}
		
		return 0;
	}
}
 
 

