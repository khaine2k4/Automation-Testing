package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Locators cho hệ thống thực tế
    private By nameField = By.name("Register.Name");
    private By emailField = By.name("Register.Email");
    private By passwordField = By.name("Register.Password");
    private By registerButton = By.xpath("//button[contains(text(),'Đăng Ký')]");
    private By errorMsg = By.cssSelector(".text-danger");
    // Nếu có thông báo thành công, bổ sung locator phù hợp
    // private By successMsg = By.cssSelector(".alert-success");

    public void navigate() {
        navigateTo("https://localhost:7231/Login/Login");
    }

    public void fillForm(String name, String email, String password) {
        type(nameField, name);
        type(emailField, email);
        type(passwordField, password);
    }

    public void submitForm() {
        click(registerButton);
    }

    public By getErrorLocator() {
        return errorMsg;
    }

    // Nếu có thông báo thành công, bổ sung hàm này:
    // public By getSuccessLocator() { return successMsg; }
}
