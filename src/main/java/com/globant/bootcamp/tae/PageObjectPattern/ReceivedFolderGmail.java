package com.globant.bootcamp.tae.PageObjectPattern;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

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

import com.globant.bootcamp.tae.dataprovider.Email;
import com.globant.bootcamp.tae.utils.Constants;
import com.globant.bootcamp.tae.utils.Utiles;

public class ReceivedFolderGmail  {

	private String _baseUrl;

	
	@FindBy( how = How.CSS, using = ".aDP .Cp .F.cf tr" )
	List<WebElement> recibidosWE ;
	
	WebDriver driver;
	
	public ReceivedFolderGmail ( WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
		
	public List<Email> obtenerListaEmailsRecibidos(String to)
	{
		List<Email> listaEmails = new ArrayList<Email>();
		Email email;
		
		if (Utiles.waitUntilElementPresent(driver, By.cssSelector(".aDP .Cp tr"), 15))
		{
			//Supongo que por el tamaño de la ventana de firefox se ven sólo cierta cantidad de emails de la lista
			int sizeBusqueda = (recibidosWE.size() < Constants.MAX_EMAILS_ENVIADOS_MOSTRADOS ? recibidosWE.size() : Constants.MAX_EMAILS_ENVIADOS_MOSTRADOS);
			
			System.out.println("tamanio busqueda " + sizeBusqueda);
			for ( int i = 1 ;i <= sizeBusqueda; i++ )
				{
					
				email = new Email();
				email.setFrom(driver.findElement(By.cssSelector(".aDP .Cp tr:nth-child("+i+") td:nth-child(4) .yW span")).getAttribute("email"));
				email.setTo(to);
				email.setSubject(driver.findElement(By.cssSelector(".aDP .Cp tr:nth-child("+i+") td:nth-child(6) .y6 span:nth-child(1)")).getText());
				email.setBody(driver.findElement(By.cssSelector(".aDP .Cp tr:nth-child("+i+") td:nth-child(6) .y6 span:nth-child(2)")).getText());
				
				System.out.println(email.getFrom());
				System.out.println(email.getSubject());
				System.out.println(email.getBody());

				listaEmails.add(email);
				}
		}
		return listaEmails;
		
	}
	
	public void go() {
		driver.get(_baseUrl);
	}
	public String get_baseUrl() {
		return _baseUrl;
	}
	public void set_baseUrl(String _baseUrl) {
		this._baseUrl = _baseUrl;
	}
}
