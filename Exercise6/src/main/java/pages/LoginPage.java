package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators theo HTML thực tế
    private By emailField = By.name("Login.Email");
    private By passwordField = By.name("Login.Password");
    private By loginButton = By.xpath("//button[contains(text(),'Đăng Nhập')]");
    private By errorMsg = By.cssSelector(".text-danger");
    // Nếu có thông báo thành công, bổ sung locator phù hợp
    // private By successMsg = By.cssSelector(".alert-success");

    // Actions
    public void navigate() {
        navigateTo("https://localhost:7231/Login/Login");
    }

    public void login(String email, String password) {
        type(emailField, email);
        type(passwordField, password);
        click(loginButton);
    }

    public By getErrorLocator() {
        return errorMsg;
    }

    // Nếu có thông báo thành công, bổ sung hàm này:
    // public By getSuccessLocator() { return successMsg; }

    public String getMessageText(By locator) {
        return getText(locator);
    }
}