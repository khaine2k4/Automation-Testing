package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By genderRadio = By.xpath("//label[text()='Male']");
    private By mobileField = By.id("userNumber");
    private By submitButton = By.id("submit");
    private By successModal = By.id("example-modal-sizes-title-lg");

    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

//    public void fillForm(String firstName, String lastName, String email, String mobile) {
//        removeAds(); // ✅ Thêm dòng này để xóa iframe quảng cáo
//
//        type(firstNameField, firstName);
//        type(lastNameField, lastName);
//        type(emailField, email);
//        click(genderRadio);
//        type(mobileField, mobile);
//    }
public void fillForm(String firstName, String lastName, String email, String mobile) {
    type(firstNameField, firstName);
    sleep(500);
    type(lastNameField, lastName);
    sleep(500);
    type(emailField, email);
    sleep(500);

    // Wait + JS Click for radio button
    waitForClickability(genderRadio);
    jsClick(genderRadio); // ✅ this avoids intercept issue
    sleep(500);
    type(mobileField, mobile);
    sleep(500);
}

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void submitForm() {
        WebElement submitBtn = driver.findElement(By.id("submit"));
        click(submitBtn);
    }

    public By getSuccessLocator() {
        return successModal;
    }

    // ✅ Hàm xóa iframe quảng cáo để tránh lỗi click bị chặn
    private void removeAds() {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement iframe : iframes) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", iframe);
        }
    }

}
