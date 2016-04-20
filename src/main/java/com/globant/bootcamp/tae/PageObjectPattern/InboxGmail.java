package com.globant.bootcamp.tae.PageObjectPattern;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.globant.bootcamp.tae.utils.Utiles;

public class InboxGmail  {

	private String _baseUrl;
	

	WebDriver driver;
	@FindBy( how = How.CSS, using = ".aio.UKr6le .nU a" )
	WebElement linkRecibidos;
	
	@FindBy( how = How.XPATH, using = "//div[@class = 'z0']//div[@role = 'button']" )
	WebElement botonRedactar;
	
	@FindBy( how = How.CSS, using = ".gbqfif" )
	WebElement searchField;
	
	@FindBy( how = How.CSS, using = ".gbqfb" )
	WebElement searchButton;
	
	@FindBy( how = How.CSS, using = ".gb_b.gb_R" )
	WebElement profileIcon;
		
	@FindBy( how = How.CSS, using = ".aeO .aeQ" )
	WebElement dragLeftMenu;
	
	public InboxGmail ( WebDriver driver)
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
	
	public EmailEditor redactarEmail(  )
	{
		EmailEditor newEmail = null;
		botonRedactar.click();
		
		
		newEmail = PageFactory.initElements(driver, EmailEditor.class);
				
		
		return newEmail;
		
	}
	
	public void logOut()
	{
		(new WebDriverWait(driver,5)).until(ExpectedConditions.elementToBeClickable(this.profileIcon)).click();

		
		
		(new WebDriverWait(driver,5)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".gb_rb div:nth-child(2) a"))).click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SentFolderGmail getFolderEnviados()
	
	{
		
		SentFolderGmail enviados = null;
	
		this.searchField.sendKeys("in:sent");
		this.searchButton.click();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		
		enviados = PageFactory.initElements(driver, SentFolderGmail.class);
		
		return enviados;
		
	}
	
public ReceivedFolderGmail getFolderRecibidos()
	
	{
		
		ReceivedFolderGmail recibidos = null;
	
		this.linkRecibidos.click();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		
		recibidos = PageFactory.initElements(driver, ReceivedFolderGmail.class);
		
		return recibidos;
		
	}
	public String get_baseUrl() {
		return _baseUrl;
	}
	public void set_baseUrl(String _baseUrl) {
		this._baseUrl = _baseUrl;
	}
}
