import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertFalse;

import org.junit.After;

public class ExcluirUsuarioTeste {
	
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
	public void excluirUsuario() {
		
		usuarios.visita();
		boolean deletou = usuarios.excluirUsuario(1, "sdsd");
		usuarios.visita();
		
		assertFalse(deletou);
	}
	
	
	@After
	public void finalizaSelenium() {
		driver.close();
		
	}

}
