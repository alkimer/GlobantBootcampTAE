package com.globant.bootcamp.tae;
import java.util.ArrayList;
import java.util.List;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.globant.bootcamp.tae.PageObjectPattern.EmailEditor;
import com.globant.bootcamp.tae.PageObjectPattern.LoginGmail;
import com.globant.bootcamp.tae.PageObjectPattern.InboxGmail;
import com.globant.bootcamp.tae.PageObjectPattern.ReceivedFolderGmail;
import com.globant.bootcamp.tae.PageObjectPattern.SentFolderGmail;
import com.globant.bootcamp.tae.dataprovider.Email;
import com.globant.bootcamp.tae.dataprovider.Usuario;
import com.globant.bootcamp.tae.utils.Constants;
import com.globant.bootcamp.tae.utils.Utiles;


public class trabajoFinal {

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
		
		@DataProvider
		public Object[][] getUserData()
		{
		//Rows - Number of times your test has to be repeated.
		//Columns - Number of parameters in test data.
			
		Usuario usuarioOrigen = new Usuario("origen.bootcamp.tae@gmail.com","TaeBoot111");
		Usuario usuarioDestino = new Usuario("destino.bootcamp.tae@gmail.com","TaeBoot111");

		Email mail;
		ArrayList<Email> listaEmails = new ArrayList<Email>();
		
			mail = new Email();
			mail.setFrom(usuarioOrigen.getEmail());
			mail.setTo(usuarioDestino.getEmail());
			mail.setSubject("HOLA");
			mail.setBody("CUERPO 1 DE LA LISTA" );
			
			listaEmails.add(mail);
			
			mail = new Email();
			mail.setFrom(usuarioOrigen.getEmail());
			mail.setTo(usuarioDestino.getEmail());
			mail.setSubject("CHAU");
			mail.setBody("CUERPO 2 DE LA LISTA" );
			
			listaEmails.add(mail);
			
			mail = new Email();
			mail.setFrom(usuarioOrigen.getEmail());
			mail.setTo(usuarioDestino.getEmail());
			mail.setSubject("hola de nuevo...");
			mail.setBody("CUERPO 3 DE LA LISTA" );
			
			listaEmails.add(mail);
			
			Object[][] data = new Object[2][3];

		// Primera corrida
		data[0][0] = usuarioOrigen;
		data[0][1] = usuarioDestino;
		data[0][2] = listaEmails;
		
		
		listaEmails = new ArrayList<Email>();

		mail = new Email();
		mail.setFrom(usuarioOrigen.getEmail());
		mail.setTo(usuarioDestino.getEmail());
		mail.setSubject("HOLA 4");
		mail.setBody("CUERPO 4 DE LA LISTA" );
		
		listaEmails.add(mail);
		
		mail = new Email();
		mail.setFrom(usuarioOrigen.getEmail());
		mail.setTo(usuarioDestino.getEmail());
		mail.setSubject("CHAU 5");
		mail.setBody("CUERPO 5 DE LA LISTA" );
		
		listaEmails.add(mail);
		
		mail = new Email();
		mail.setFrom(usuarioOrigen.getEmail());
		mail.setTo(usuarioDestino.getEmail());
		mail.setSubject("hola de nuevo... 6");
		mail.setBody("CUERPO 6 DE LA LISTA" );
		
		listaEmails.add(mail);
		
		//segunda corrida
		data[1][0] = usuarioOrigen;
		data[1][1] = usuarioDestino;
		data[1][2] = listaEmails;
		
		return data;
		}
		
		@Test(dataProvider="getUserData")
		public void exercise01(Usuario usuarioOrigen, Usuario usuarioDestino, ArrayList<Email> listaMails) {
	
	    InboxGmail inboxGmail;
	    EmailEditor emailEditor;
	    SentFolderGmail folderEnviados;
	    ReceivedFolderGmail folderRecibidos;
	    List<Email> listaEmailsEnviados;
	    List<Email> listaEmailsRecibidos;

	    
		LoginGmail loginGmail= PageFactory.initElements(driver, LoginGmail.class);
		loginGmail.go();
		
		inboxGmail = loginGmail.doLogin(usuarioOrigen.getEmail(), usuarioOrigen.getPassword());
		//Envío email
		
		for ( Email tempEmail : listaMails)
		{emailEditor = inboxGmail.redactarEmail();
		emailEditor.crearEmail(tempEmail.getTo(), tempEmail.getSubject(),tempEmail.getBody());
		emailEditor.pressButtonSendEmail();
		}
		
		//Controlo que estén enviados
		System.out.println("BUSCANDO : Enviados...");
		folderEnviados = inboxGmail.getFolderEnviados();
		listaEmailsEnviados = folderEnviados.obtenerListaEmailsEnviados(usuarioOrigen.getEmail());
		
		for ( Email tempEmail : listaMails)
		{
		Assert.assertTrue(Utiles.contieneEmail(listaEmailsEnviados,tempEmail), "NO ESTÁ EL EMAIL");
		}
		
		//Deslogueo de la cuenta
		inboxGmail.logOut();
		loginGmail.cambiarCuenta();
		loginGmail.añadirCuenta();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		

	    /////////////Loguearse en cuenta destino
		inboxGmail = loginGmail.doLogin(usuarioDestino.getEmail(), usuarioDestino.getPassword());
		
		System.out.println("BUSCANDO : Recibidos...");
		folderRecibidos = inboxGmail.getFolderRecibidos();
		
		listaEmailsRecibidos = folderRecibidos.obtenerListaEmailsRecibidos(usuarioDestino.getEmail());
		
		//Controlo que los emails estén en la lista de Recibidos
		for ( Email tempEmail : listaMails)
		{
		Assert.assertTrue(Utiles.contieneEmail(listaEmailsRecibidos, tempEmail), "NO se encontro el email en Recibidos");
		}
		inboxGmail.logOut();
		loginGmail.añadirCuenta();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		
	 }
}

