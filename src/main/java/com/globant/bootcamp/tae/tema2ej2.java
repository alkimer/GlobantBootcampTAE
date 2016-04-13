package com.globant.bootcamp.tae;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.globant.bootcamp.tae.PageObjectPattern.HomeGoogle;
import com.globant.bootcamp.tae.PageObjectPattern.HomeGenerico;
import com.globant.bootcamp.tae.utils.Constants;
import com.globant.bootcamp.tae.utils.Utiles;


public class tema2ej2 {

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
		
		@Test(description = "Exercise01-tema2")
		public void exercise01() {
	
		HomeGenerico homeGenerico = null;
		HomeGoogle homeGoogle= PageFactory.initElements(driver, HomeGoogle.class);
		homeGoogle.go();
		homeGoogle.search(Constants.TEMA2_EJ2_TEXTO_BUSQUEDA_1);
		homeGoogle.clickOnSearchButton();
		homeGenerico = homeGoogle.clickOnGoogleResult(Constants.TEMA2_EJ2_NRO_RESULTADO_CLICK);
		
		Assert.assertTrue(homeGenerico.contieneElemento(By.id(Constants.TEMA2_EJ2_CONTROL_LOCATOR)), "No se encuentra el elemento :" + Constants.TEMA2_EJ2_CONTROL_LOCATOR);
	

	 }
}

