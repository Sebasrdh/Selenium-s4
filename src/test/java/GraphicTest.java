import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GraphicTest {
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
    @Description ("Validación del grafico y texto visible.")
    public void graficovisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Esperar a que el gráfico sea visible
        By chartLocator = By.xpath("/html/body/app-root/app-inicio/div/div/div[2]/div/div[1]/app-entrevistas-estadisticas/div/canvas");
        WebElement chartElement = wait.until(ExpectedConditions.visibilityOfElementLocated(chartLocator));

        // Validar si el gráfico está visible
        if (chartElement.isDisplayed()) {
            System.out.println("El gráfico está presente y es visible en la página.");
        } else {
            System.out.println("El gráfico no es visible en la página.");
        }

        // Validar si el texto "Entrevistas los últimos 12 meses" está presente
        By textLocator = By.xpath("/html/body/app-root/app-inicio/div/div/div[2]/div/div[1]/app-entrevistas-estadisticas/h3[contains(text(), 'Entrevistas los últimos 12 meses')]");
        WebElement textElement = wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));

        // Validar si el texto está visible
        if (textElement.isDisplayed()) {
            System.out.println("El texto 'Entrevistas los últimos 12 meses' está presente y es visible en la página.");
        } else {
            System.out.println("El texto 'Entrevistas los últimos 12 meses' no es visible en la página.");
        }
    }
}