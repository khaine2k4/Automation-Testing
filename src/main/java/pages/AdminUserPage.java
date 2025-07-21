package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminUserPage extends BasePage {
    public AdminUserPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By addUserBtn = By.xpath("//button[contains(.,'Add New User')]");
    private By usernameField = By.id("edit-username");
    private By emailField = By.id("edit-email");
    private By fullNameField = By.id("edit-fullname");
    private By phoneField = By.id("edit-phone");
    private By addressField = By.id("edit-address");
    private By roleSelect = By.id("edit-role");
    private By saveBtn = By.xpath("//button[@type='submit' and contains(.,'Save')]");
    private By modal = By.id("editUserModal");
    private By errorMsg = By.cssSelector(".text-danger, .alert-danger");

    public void navigate() {
        navigateTo("https://localhost:7231/Admin/List_Customer");
    }

    public void openAddUserModal() {
        click(addUserBtn);
        waitForVisibility(modal);
    }

    public void fillUserForm(String username, String email, String fullName, String phone, String address, String roleValue) {
        type(usernameField, username);
        type(emailField, email);
        type(fullNameField, fullName);
        type(phoneField, phone);
        type(addressField, address);
        selectByValue(roleSelect, roleValue); // "1"=Admin, "2"=Customer, "3"=Employee
    }

    public void submitUserForm() {
        click(saveBtn);
    }

    public By getErrorLocator() {
        return errorMsg;
    }
} 