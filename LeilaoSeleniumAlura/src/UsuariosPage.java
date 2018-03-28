import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertFalse;

public class UsuariosPage {
	
	private WebDriver driver;
	
	public UsuariosPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("http://localhost:8080/usuarios");
	}
	
	public NovoUsuarioPage novo() {
		
		driver.findElement(By.linkText("Novo Usuário")).click();
		return new NovoUsuarioPage(driver);
		
	}
	
	public boolean existeNaListagem(String nome, String email) {
		
		return driver.getPageSource().contains(nome) && driver.getPageSource().contains(email);
		
	}
	
	public boolean existeNaListagem(String texto) {
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Novo Usuário")));
		
		return driver.getPageSource().contains(texto);
		
	}

	
	public boolean excluirUsuario(int posicao, String nome) {
		
		driver.findElements(By.tagName("button")).get(posicao-1).click();
		
		//pega o alert que esta aberto
		Alert alert = driver.switchTo().alert();
		//confirma
		alert.accept();
		
		visita();
		
		return driver.getPageSource().contains(nome);
		
	}
	
	public EditUserPage editar(int position) {
		driver.findElements(By.linkText("editar")).get(position-1).click();
		return new EditUserPage(driver);
	}
	
}
