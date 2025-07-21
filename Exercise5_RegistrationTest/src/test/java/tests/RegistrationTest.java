package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Registration Form Tests")
public class RegistrationTest extends BaseTest {

    static RegistrationPage regPage;
    static WebDriverWait wait;

    @BeforeAll
    static void setup() {
        regPage = new RegistrationPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @Order(1)
    @DisplayName("Should register successfully")
    void testValidRegistration() {
        regPage.navigate();
        regPage.fillForm("Khai", "Nguyen", "khai@test.com", "0987654321");
        regPage.submitForm();

        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(regPage.getSuccessLocator()));
        assertTrue(modal.getText().contains("Thanks for submitting the form"));
    }
}
