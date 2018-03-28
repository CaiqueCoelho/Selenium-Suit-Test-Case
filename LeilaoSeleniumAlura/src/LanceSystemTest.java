import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LanceSystemTest {
	
	private WebDriver driver;
	private LeiloesPage leiloes;
	
	@Before
	public void inicializa() {
		//Ubuntu
		System.setProperty("webdriver.gecko.driver", "/home/caique_coelho/Documentos/Selenium/geckodriver-v0.19.1-linux64/geckodriver");		
	
		driver = new FirefoxDriver();
		leiloes = new LeiloesPage(driver);
		
		//Cenario padrao
		driver.get("http://localhost:8080/apenas-teste/limpa");
		
		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		usuarios.novo().cadastra("Juraci Figueiredo", "jura@vilela.com.br");
		usuarios.novo().cadastra("Icaro de Paula", "icaro@gamer.com.br");
		
		LeiloesPage leiloesPage = new LeiloesPage(driver);
		leiloesPage.visita();
		leiloesPage.novo().preenche("Geladeira", 100, "Juraci Figueiredo", false);
	}
	
	@Test
	public void deveFazerUmLance() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("exibir")));
		
		DetalhesDoLeilaoPage lances = leiloes.detalhes(1);
		
		lances.lance("Icaro de Paula", 150);
		
		assertTrue(lances.existeLance("Icaro de Paula", 150));
	}
	
	//@After
	public void encerrar() {
		driver.close();
	}

}
