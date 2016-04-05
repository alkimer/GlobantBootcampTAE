package com.globant.bootcamp.tae;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;

public class App {
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
	private boolean existsElementID(String id) {
	    try {
	        driver.findElement(By.id(id));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}
	
	private boolean existsElementCSS(String css) {
	    try {
	        driver.findElement(By.cssSelector(css));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}
	@Test(description = "Exercise")
	public void exercise01() {
		WebElement in_userName ;
		WebElement in_password ;
		WebElement in_button;
		WebElement usuario_recibido;
		WebElement mensaje_recibido;
		
		final String header_comp = "Selenium Test Header";
		String resultado;
	//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://tvroom.github.io/selenium-exercises/ex2/");
		in_userName = driver.findElement(By.name("username"));
		in_password = driver.findElement(By.name("password"));
		in_button = driver.findElement(By.cssSelector("input[type=\"submit\"]"));
		
		in_userName.sendKeys("bob");
		in_password.sendKeys("foobaz");
		in_button.click();
		
		
		usuario_recibido = driver.findElement(By.cssSelector("h2.user"));
		
		mensaje_recibido = existsElementCSS("h2.error")? driver.findElement(By.cssSelector("h2.error")) : null ;
	//	mensaje_recibido = driver.findElement(By.cssSelector("h2.error"));
	//	resultado = (header.getText().equals(header_comp)) ? "IGUAL": "DISTINTO" ;
		System.out.println(usuario_recibido.getText() + " : " + (mensaje_recibido != null ? mensaje_recibido.getText() : "Login OK"));
		
	}
}