import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:4200/#/auth/login");
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        // Esperar 10 segundos antes de cerrar el navegador
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void iniciodeSesion() {
        // Se ingresa a la página web
        driver.get("http://localhost:4200/#/auth/login");

        // Ubicar elementos del formulario de inicio de sesión
        WebElement usernameField = driver.findElement(By.xpath("/html/body/app-root/app-auth-layout/div/div/div/app-login-page/form/div[1]/input")); // Se debe reemplazar "username" con el ID o XPath
        WebElement passwordField = driver.findElement(By.xpath("/html/body/app-root/app-auth-layout/div/div/div/app-login-page/form/div[2]/input")); // Se debe reemplazar "password" con el ID o XPath
        WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/app-auth-layout/div/div/div/app-login-page/form/div[3]/div/button")); // Se debe reemplazar "loginbutton" con el ID o XPath

        // Ingresar datos en los campos de texto
        usernameField.sendKeys("login1");
        passwordField.sendKeys("login1");

        // Clic en el botón de inicio de sesión
        loginButton.click();
    }
}