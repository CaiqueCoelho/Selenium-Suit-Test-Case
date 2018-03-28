import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CadastroUsuarioTeste {

	private WebDriver driver;
	private UsuariosPage usuarios;
	
	@Before
	public void inicializarSelenium() {
		//Ubuntu
		System.setProperty("webdriver.gecko.driver", "/home/caique_coelho/Documentos/Selenium/geckodriver-v0.19.1-linux64/geckodriver");		

		driver = new FirefoxDriver();
		this.usuarios = new UsuariosPage(driver);
		
	}
	
	
	@Test
	public void cadastroComSucesso() {
		
		usuarios.visita();
		usuarios.novo().cadastra("Caique de Paula", "caiquedpfc@gmail.com");
		
		assertTrue(usuarios.existeNaListagem("Caique de Paula", "caiquedpfc@gmail.com"));
		
	}
	
	//@Test
	public void cadastroSemNome() {
		
		WebElement nome = driver.findElement(By.name("usuario.nome"));
		WebElement email = driver.findElement(By.name("usuario.email"));
		WebElement salvar = driver.findElement(By.id("btnSalvar"));
		
		nome.sendKeys("");
		email.sendKeys("lucia@gmail.com");
		
		salvar.click();
		
		boolean falhouCadastro = driver.getPageSource().contains("Nome obrigatorio!");
		
		assertTrue(falhouCadastro);
	}
	
	//@Test
	public void cadastroSemNomeESemEmail() {
		
		WebElement nome = driver.findElement(By.name("usuario.nome"));
		WebElement email = driver.findElement(By.name("usuario.email"));
		WebElement salvar = driver.findElement(By.id("btnSalvar"));
		
		nome.sendKeys("");
		email.sendKeys("");
		
		salvar.click();
		
		boolean semNome = driver.getPageSource().contains("Nome obrigatorio!");
		boolean semEmail = driver.getPageSource().contains("E-mail obrigatorio!");
		
		assertTrue(semNome);
		assertTrue(semEmail);
		
	}
	
	@After
	public void finalizaSelenium() {
		driver.close();
		
	}
	
	
		
	
}
