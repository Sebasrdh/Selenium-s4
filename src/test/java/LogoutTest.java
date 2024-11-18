import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:4200/#/auth/login");

        // Ubicar elementos del formulario de inicio de sesión
        WebElement usernameField = driver.findElement(By.xpath("/html/body/app-root/app-auth-layout/div/div/div/app-login-page/form/div[1]/input"));
        WebElement passwordField = driver.findElement(By.xpath("/html/body/app-root/app-auth-layout/div/div/div/app-login-page/form/div[2]/input"));
        WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/app-auth-layout/div/div/div/app-login-page/form/div[3]/div/button"));

        // Ingresar datos en los campos de texto
        usernameField.sendKeys("login1");
        passwordField.sendKeys("login1");

        // Clic en el botón de inicio de sesión
        loginButton.click();
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        // Esperar 5 segundos antes de cerrar el navegador
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    @DisplayName("Cerrar sesión y verificar que se muestra la pantalla de login")
    public void testLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //Tiempo de espera para presionar el botón LogOut
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-inicio/div/div/div[1]/app-menu/div/div/ul/li[6]")));
        logoutButton.click();

        // Verificar que se muestre la pantalla de login
        WebElement loginPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-auth-layout/div/div/div/app-login-page/form/div[3]/div/button")));
        assertTrue(loginPage.isDisplayed(), "La pantalla de login no se muestra.");
    }
}