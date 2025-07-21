package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
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

    @ParameterizedTest(name = "Register: {0} / {1} / {2} => {3}")
    @CsvFileSource(resources = "/registration-data.csv", numLinesToSkip = 1)
    @Order(1)
    void testRegistration(String name, String email, String password, String expected) {
        regPage.navigate();
        name = (name == null) ? "" : name.trim();
        email = (email == null) ? "" : email.trim();
        password = (password == null) ? "" : password.trim();

        regPage.fillForm(name, email, password);
        regPage.submitForm();

        if ("success".equals(expected)) {
            // TODO: Bổ sung kiểm tra thành công nếu có (ví dụ: kiểm tra URL hoặc alert thành công)
        } else {
            try {
                WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(regPage.getErrorLocator()));
                assertTrue(error.getText().length() > 0);
            } catch (TimeoutException e) {
                Assertions.fail("Không tìm thấy thông báo lỗi khi đăng ký sai (name: '" + name + "', email: '" + email + "', password: '" + password + "')");
            }
        }
    }
}
