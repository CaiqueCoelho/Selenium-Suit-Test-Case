import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EditarUsauarioTest {
	
	private WebDriver driver;
	
	@Before
	public void inicializa() {
		//Ubuntu
		System.setProperty("webdriver.gecko.driver", "/home/caique_coelho/Documentos/Selenium/geckodriver-v0.19.1-linux64/geckodriver");		
	
		driver = new FirefoxDriver();
	}
	
	@Test
	public void editarNome() {
		
		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		EditUserPage editUserPage = usuarios.editar(1);
		
		UsuariosPage usuariosRetorno = editUserPage.edita("Nome Editado", "");
		
		assertTrue(usuariosRetorno.existeNaListagem("Nome Editado"));
		
	}
	
	@Test
	public void editarEmail() {
		
		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		EditUserPage editUserPage = usuarios.editar(1);
		
		UsuariosPage usuariosRetorno = editUserPage.edita("", "email@editado.com");
		
		assertTrue(usuariosRetorno.existeNaListagem("email@editado.com"));
		
	}
	
	@After
	public void encerra() {
		driver.close();
	}

}
