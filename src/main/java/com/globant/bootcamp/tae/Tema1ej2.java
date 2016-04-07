package com.globant.bootcamp.tae;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.globant.bootcamp.tae.utils.Constants;
import com.globant.bootcamp.tae.utils.Utiles;

public class Tema1ej2 {
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
	
	@Test(description = "Exercise02-tema1")
	public void exercise02() {
	
		WebElement in_userName = null;
		WebElement in_password = null;
		WebElement in_button = null;
	//	WebElement email_bandeja = null;
				
		driver.get(Constants.TEMA1_EJ2_URL);
		if (Utiles.waitForElement(driver,By.name("loginfmt"),5))
			in_userName = driver.findElement(By.name("loginfmt"));
		if (Utiles.waitForElement(driver,By.name("passwd"),5))
			in_password = driver.findElement(By.name("passwd"));
		
		if (Utiles.waitForElement(driver,By.cssSelector("input[id=\"idSIButton9\"]"),5))
		in_button = driver.findElement(By.cssSelector("input[id=\"idSIButton9\"]"));
		
		in_userName.sendKeys(Constants.TEMA1_EJ2_USER);
		in_password.sendKeys(Constants.TEMA1_EJ2_PASS);
		in_button.click();
		
		String email = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".mailList.InboxTableBody:first-child .FmSender span:first-child"))).getAttribute("email");	
			
		if (email != null)	
			Assert.assertTrue(Utiles.contienePalabra(email,Constants.TEMA1_EJ2_PALABRA), "El remitente no es support.com , es : " + email + ".");
		else
			Assert.fail("No se encontró ningún correo electrónico");

 }
}