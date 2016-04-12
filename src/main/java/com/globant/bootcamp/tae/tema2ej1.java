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

import com.globant.bootcamp.tae.PageObjectPattern.HomeAmazon;
import com.globant.bootcamp.tae.PageObjectPattern.ItemDetails;
import com.globant.bootcamp.tae.utils.Constants;
import com.globant.bootcamp.tae.utils.Utiles;


public class tema2ej1 {

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
	
		
		HomeAmazon homeAmazon = PageFactory.initElements(driver, HomeAmazon.class);
		
		//Parte 1 - búsqueda de kindle	
		homeAmazon.go();
		homeAmazon.search(Constants.TEMA2_EJ1_TEXTO_BUSQUEDA_1);
		homeAmazon.clickOnSearchButton();
		ItemDetails itemDetails = PageFactory.initElements(driver, ItemDetails.class);
		itemDetails = homeAmazon.clickOnListItem(5);
		Assert.assertTrue(Utiles.contienePalabra(itemDetails.getTitulo(), Constants.TEMA2_EJ1_TEXTO_BUSQUEDA_1),"El titulo no contiene la palabra de búsqueda :"+ Constants.TEMA2_EJ1_TEXTO_BUSQUEDA_1);
		
		
		//Parte 2 - click en movies y control de 4 resultados
		homeAmazon.go();
		homeAmazon.clickOnLeftMenu();
		homeAmazon.clickOnLeftMenuItemMovies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    homeAmazon.search(Constants.TEMA2_EJ1_TEXTO_BUSQUEDA_2);
		homeAmazon.clickOnSearchButton();
		Assert.assertTrue(homeAmazon.getSearchResultQuantity() >= 4, "Menos de 4 resultados encontrados");
		
		//Parte 3 - click logo y compare title
		
		homeAmazon.clickOnLogo();
		Assert.assertTrue( homeAmazon.getTitle().equals(Constants.TEMA2_EJ1_TITULO), "El titulo no es el esperado , se ha encontrado: " + homeAmazon.getTitle());
	 }
}

