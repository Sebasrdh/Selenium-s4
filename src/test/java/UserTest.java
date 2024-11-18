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
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class UserTest {
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

        // Esperar a que la página principal cargue
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navbarDropdown\"]")));

        //Esperar que la página de usuarios cargue
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-listado-usuarios/div/div[1]/div/button")));
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        // Esperar 5 segundos antes de cerrar el navegador
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    @DisplayName("Crear nuevo usuario")
    public void userspage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Seleccionar Mantenedores
        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='navbarDropdown']")));
        dropdownButton.click();

        // Seleccionar la opción "Usuarios" usando el texto visible
        WebElement usuariosOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Usuarios']")));
        usuariosOption.click();

        // Seleccionar Crear Usuario
        WebElement crearUsuarioButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-listado-usuarios/div/div[1]/div/button")));
        crearUsuarioButton.click();
        Thread.sleep(2000);

        // Ubicar elementos del formulario para crear usuario
        WebElement nombreField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-crear-usuario/div/div/div/form/div[1]/div/input")));
        WebElement apellidoField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-crear-usuario/div/div/div/form/div[2]/div/input")));
        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-crear-usuario/div/div/div/form/div[3]/div/input")));
        WebElement claveField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-crear-usuario/div/div/div/form/div[4]/div/input")));
        WebElement confirmarClaveField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-crear-usuario/div/div/div/form/div[5]/div/input")));
        WebElement correoField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-crear-usuario/div/div/div/form/div[6]/div/input")));
        WebElement telefonoField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-crear-usuario/div/div/div/form/div[7]/div/input")));
        WebElement idRolUsuarioField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-crear-usuario/div/div/div/form/div[8]/div/select")));

        // Ingresar los datos en los campos de texto
        clickAndType(nombreField, "Sebastian");
        clickAndType(apellidoField, "Gonzalez");
        clickAndType(loginField, "sgonzalez");
        clickAndType(claveField, "prueba123");
        clickAndType(confirmarClaveField, "prueba123");
        clickAndType(correoField, "sebas@hotmail.com");
        clickAndType(telefonoField, "+56912345678");

        // Seleccionar el rol de usuario
        Select select = new Select(idRolUsuarioField);
        select.selectByIndex(1);

        // Hacer clic en el botón de guardar
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Guardar')]")));
        submitButton.click();

        // Clic en el botón que indica la creación del usuario
        WebElement additionalButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]")));
        additionalButton.click();

    }

    // Método auxiliar para hacer clic en un campo y luego ingresar texto
    private void clickAndType(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    @Test
    @DisplayName("Validar error al dejar campos en blanco")
    public void validateErrorOnEmptyFields() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Seleccionar Mantenedores
        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='navbarDropdown']")));
        dropdownButton.click();

        // Seleccionar la opción "Usuarios" usando el texto visible
        WebElement usuariosOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Usuarios']")));
        usuariosOption.click();

        // Seleccionar Crear Usuario
        WebElement crearUsuarioButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-listado-usuarios/div/div[1]/div/button")));
        crearUsuarioButton.click();
        Thread.sleep(2000);

        // Dejar los campos en blanco y presionar el botón de guardar
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(), 'Guardar')]"));
        submitButton.click();

        // Validar mensajes de error
        WebElement nombreError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-crear-usuario/div/div/div/form/div[1]/div/span")));
        WebElement apellidoError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-crear-usuario/div/div/div/form/div[2]/div/span")));

        assert nombreError.isDisplayed();
        assert apellidoError.isDisplayed();
    }

    @Test
    @DisplayName("Editar un usuario existentes")
    public void editUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Seleccionar Mantenedores
        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='navbarDropdown']")));
        dropdownButton.click();

        // Seleccionar la opción "Usuarios" usando el texto visible
        WebElement usuariosOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Usuarios']")));
        usuariosOption.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-listado-usuarios")));

        // Seleccionar un usuario para editar
        WebElement editButton = driver.findElement(By.xpath("/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-listado-usuarios/div/div[2]/table/tbody/tr[4]/td[6]/a[1]"));
        editButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-editar-usuarios")));

        // Editar los campos del usuario
        WebElement telefonoField = driver.findElement(By.xpath("/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-editar-usuarios/div/div/div/form[1]/div[5]/div/input"));
        telefonoField.clear();
        telefonoField.sendKeys("+56987654321");
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(), 'Guardar')]"));
        submitButton.click();
        // Validar que el mensaje de éxito aparezca
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'El usuario ha sido actualizado exitosamente')]")));
        assert successMessage.isDisplayed();
        // Validar si el texto está visible
        if (successMessage.isDisplayed()) {
            System.out.println("El texto 'El usuario ha sido actualizado exitosamente' está presente y es visible en la página.");
        } else {
            System.out.println("El texto 'El usuario ha sido actualizado exitosamente' no es visible en la página.");
        }
        // Clic en el botón que indica la actualización del usuario
        WebElement additionalButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]")));
        additionalButton.click();

    }

    @Test
    @DisplayName("Eliminar un usuario")
    public void deleteUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Seleccionar Mantenedores
        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='navbarDropdown']")));
        dropdownButton.click();

        // Seleccionar la opción "Usuarios" usando el texto visible
        WebElement usuariosOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Usuarios']")));
        usuariosOption.click();

        // Seleccionar un usuario para eliminar
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-usuarios-layout/main/div/div[2]/app-listado-usuarios/div/div[2]/table/tbody/tr[4]/td[6]/a[2]/i")));
        deleteButton.click();

        // Confirmar eliminación
        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Eliminar')]")));
        confirmDeleteButton.click();

        // Clic en el botón que indica la eliminación del usuario
        WebElement additionalButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]")));
        additionalButton.click();
    }
}