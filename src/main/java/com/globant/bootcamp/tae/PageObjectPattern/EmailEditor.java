package com.globant.bootcamp.tae.PageObjectPattern;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.globant.bootcamp.tae.utils.*;

import com.globant.bootcamp.tae.utils.Utiles;

public class EmailEditor  {

	private String _baseUrl;
	
	WebDriver driver;
	
	//@FindBy( how = How.CSS, using = ".wO.nr.l1" )
	@FindBy( how = How.CSS, using = ".wO.nr.l1 .vO" )
	WebElement to;

	@FindBy( how = How.CSS, using = ".aoD.az6 .aoT[name=\"subjectbox\"]" )
	WebElement subject;
	
	@FindBy( how = How.CSS, using = ".Ar.Au div[role='textbox']" )
	WebElement mailBody;
	
	@FindBy( how = How.CSS, using = ".n1tfz .J-J5-Ji div[role='button']" )
	WebElement buttonSendEmail;
	
	
	public EmailEditor ( WebDriver driver)
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
	
	public void pressButtonSendEmail()
	{
		
		(new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(this.buttonSendEmail)).click();
		
	}
	
	public void crearEmail(  String to, String subject, String body )
	{
		WebElement destinatariosMinimized ;
		WebElement destinatariosMaximized;
		boolean salir = false;
	while (!salir)	
	{
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	if (Utiles.waitUntilElementPresent(driver, By.cssSelector(".aoD.hl"),3) )
	{
		System.out.println("Campo DESTINATARIOS: OK");
		destinatariosMinimized = driver.findElement(By.cssSelector(".aoD.hl"));
		System.out.println(destinatariosMinimized);
		if (Utiles.getStaleElem(By.cssSelector(".aoD.hl"), driver).isDisplayed())
		{
			
			destinatariosMinimized.click();
		}
	
	}
	
		if (Utiles.waitUntilElementPresent(driver, By.cssSelector(".wO.nr.l1 .vO"),3) )

	{
		System.out.println("Campo PARA: OK");

		destinatariosMaximized = driver.findElement(By.cssSelector(".wO.nr.l1 .vO"));
		if (destinatariosMaximized.isDisplayed()) 
			{
			
			System.out.println("PARA IS DISPALYED");
			salir = true;
			}
	}
	}
	(new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOf(this.to)).sendKeys(to);
	(new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOf(this.subject)).sendKeys(subject);
	(new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOf(this.mailBody)).sendKeys(body);
		
			
	}
	
	public String get_baseUrl() {
		return _baseUrl;
	}
	public void set_baseUrl(String _baseUrl) {
		this._baseUrl = _baseUrl;
	}
}
