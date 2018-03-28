import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeiloesSystemTest {
	
	private WebDriver driver;
	private LeiloesPage leiloes;
	
	@Before
	public void inicializa() {
		//Ubuntu
		System.setProperty("webdriver.gecko.driver", "/home/caique_coelho/Documentos/Selenium/geckodriver-v0.19.1-linux64/geckodriver");		
	
		driver = new FirefoxDriver();
		leiloes = new LeiloesPage(driver);
		
		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		usuarios.novo().cadastra("Caique Coelho", "caique@coelho.com");
	}
	
	//@Test
	public void deveCadastrarUmLeilao() {
		
		leiloes.visita();
		NovoLeilaoPage novoLeilaoPage = leiloes.novo();
		novoLeilaoPage.preenche("Carro", 30.000, "Caique Coelho", true);
		
		assertTrue(leiloes.existe("Carro", 30.000, "Caique Coelho", true));
		
	}
	
	//@Test
	public void testeNomeObrigatorio() {
		leiloes.visita();
		NovoLeilaoPage novoLeilaoPage = leiloes.novo();
		novoLeilaoPage.preenche("", 300000000, "Caique Coelho", false);
		
		assertTrue(novoLeilaoPage.validacaoNomeObrigatorio());
	}
	
	@Test
	public void testeValorObrigatorio() {
		leiloes.visita();
		NovoLeilaoPage novoLeilaoPage = leiloes.novo();
		novoLeilaoPage.preenche("Caique Coelho", 0.0, "Caique Coelho", false);
		
		assertTrue(novoLeilaoPage.validacaoPrecoObrigatorio());
	}
	
	@After
	public void encerra() {
		driver.close();
	}

}
