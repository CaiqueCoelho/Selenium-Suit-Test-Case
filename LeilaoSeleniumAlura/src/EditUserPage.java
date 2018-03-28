import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditUserPage {
	
	private WebDriver driver;
	
	public EditUserPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get("http://localhost:8080/usuarios/13/edit");
	}
	
	public UsuariosPage edita(String nome, String email) {
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usuario.nome")));
		
		WebElement txtNome = driver.findElement(By.name("usuario.nome"));
		WebElement txtEmail = driver.findElement(By.name("usuario.email"));
		
		if(!nome.isEmpty()) {
			txtNome.clear();
			txtNome.sendKeys(nome);
		}
		
		if(!email.isEmpty()) {
			txtEmail.clear();
			txtEmail.sendKeys(email);
		}
		
		txtNome.submit();
		
		return new UsuariosPage(driver);
		
	}

}
