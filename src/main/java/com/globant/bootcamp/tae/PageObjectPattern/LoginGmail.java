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

public class LoginGmail {
	
	private final String _baseUrl = "https://www.gmail.com/";
	WebDriver driver;

	
	@FindBy( id = "Email" )
	WebElement email;

	@FindBy( id = "next" )
	WebElement buttonNext;
	
	@FindBy ( id = "account-chooser-link")
	WebElement linkCambioCuenta;

	public LoginGmail()
	{
		//nada
	}
	public LoginGmail(WebDriver driver) {
		this.driver = driver;
	}

	public void go() {
		driver.get(_baseUrl);
	}

	public String getTitle() {
		return driver.getTitle();
	}
	
	
	public void cambiarCuenta()
	
	{
		
		(new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(this.linkCambioCuenta)).click();

	
	}
	
	public void añadirCuenta()
	{
		(new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(By.id("account-chooser-add-account"))).click();

	}
	public void clickOnNextButton()
	{
		(new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(this.buttonNext)).click();
	}
	
	public void ingresarEmail( String email)
	{
		
		(new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOf(this.email)).sendKeys(email);

	}

	public InboxGmail doLogin( String email, String password)
	{
		WebElement passwordField;
		WebElement loginButton;
		InboxGmail inboxGmail = null;
	
		this.ingresarEmail(email);
		this.clickOnNextButton();
		if (Utiles.waitUntilElementPresent(driver, By.id("Passwd"), 10))
		{
			passwordField = driver.findElement(By.id("Passwd"));
			(new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
		
			if ( Utiles.waitUntilElementPresent(driver, By.id("signIn"), 10))
				{
				loginButton = driver.findElement(By.id("signIn"));
				(new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(loginButton)).click();
			 
				   if (Utiles.waitUntilElementPresent(driver, By.xpath("//div[@class = 'z0']//div[@role = 'button']"), 15))
				   {  inboxGmail = PageFactory.initElements(driver, InboxGmail.class);
				   }
				}
			
		}

		return inboxGmail;
	}
	
}
 
 

