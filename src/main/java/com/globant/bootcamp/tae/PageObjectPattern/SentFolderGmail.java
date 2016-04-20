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

public class SentFolderGmail  {

	private String _baseUrl;

	
	@FindBy( how = How.CSS, using = ".ae4.UI .Cp .F.cf.zt tr" )
    List<WebElement> enviadosWE ;
	
	WebDriver driver;
	
	public SentFolderGmail ( WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	
	
	public List<Email> obtenerListaEmailsEnviados(String from)
	{
		List<Email> listaEmails = new ArrayList<Email>();
		Email email;

		if (Utiles.waitUntilElementPresent(driver, By.cssSelector(".ae4.UI .Cp .F.cf.zt tr"), 15))
		{
			//Supongo que por el tamaño de la ventana de firefox se ven sólo cierta cantidad de emails de la lista
			int sizeBusqueda = (enviadosWE.size() < Constants.MAX_EMAILS_ENVIADOS_MOSTRADOS ? enviadosWE.size() : Constants.MAX_EMAILS_ENVIADOS_MOSTRADOS);
			System.out.println("tamanio busqueda " + sizeBusqueda);
			for ( int i = 1 ;i <= sizeBusqueda ; i++ )
				{
					
				email = new Email();
				email.setFrom(from);
				email.setTo(driver.findElement(By.cssSelector(".ae4.UI .Cp .F.cf.zt tr:nth-child("+i+") td.yX.xY .yW span")).getAttribute("email"));
				email.setSubject(driver.findElement(By.cssSelector(".ae4.UI .Cp .F.cf.zt tr:nth-child("+i+") td.xY.a4W .y6 span:nth-child(1)")).getText());
				email.setBody(driver.findElement(By.cssSelector(".ae4.UI .Cp .F.cf.zt tr:nth-child("+i+") td.xY.a4W .y6 span:nth-child(2)")).getText());
				
				System.out.println("TO: " + email.getTo());
				System.out.println("SUBJECT: " + email.getSubject());
				System.out.println("BODY:" + email.getBody());

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
