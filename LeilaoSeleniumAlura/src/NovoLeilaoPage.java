import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NovoLeilaoPage {
	
	private final WebDriver driver;
	
	public NovoLeilaoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void preenche(String nome, double valor, String usuario, boolean usado) {
		
		WebElement txtNome = driver.findElement(By.name("leilao.nome"));
		WebElement txtValor = driver.findElement(By.name("leilao.valorInicial"));
		//WebElement btnSalvar = driver.findElement(By.tagName("Salvar!"));
		
		txtNome.sendKeys(nome);
		txtValor.sendKeys(String.valueOf(valor));
		
		Select cbUsuario = new Select(driver.findElement(By.name("leilao.usuario.id")));
		cbUsuario.selectByVisibleText(usuario);
		
		WebElement status = driver.findElement(By.name("leilao.usado"));
		if(usado) {
			status.click();
		}
		
		txtNome.submit();
		
	}
	
	public boolean validacaoNomeObrigatorio() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("leilao.usado")));
		
		return driver.getPageSource().contains("Nome obrigatorio!");
	}
	
	public boolean validacaoPrecoObrigatorio() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("leilao.usado")));
		
		Thread.currentThread();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return driver.getPageSource().contains("Valor inicial deve ser maior que zero!");
	}
	

}
