package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Login Tests for pet_spa_system1")
public class LoginTest extends BaseTest {
    static WebDriverWait wait;
    static LoginPage loginPage;

    @BeforeAll
    static void initPage() {
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @ParameterizedTest(name = "Login: {0} / {1} => {2}")
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    @Order(1)
    void testLoginFromCSV(String email, String password, String expected) {
        loginPage.navigate();
        email = (email == null) ? "" : email.trim();
        password = (password == null) ? "" : password.trim();

        loginPage.login(email, password);

        if ("success".equals(expected)) {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://localhost:7231/Login/Login")));
            assertTrue(!driver.getCurrentUrl().contains("/Login/Login"));
        } else {
            try {
                WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getErrorLocator()));
                assertTrue(error.getText().length() > 0);
            } catch (TimeoutException e) {
                Assertions.fail("Không tìm thấy thông báo lỗi khi đăng nhập sai (email: '" + email + "', password: '" + password + "')");
            }
        }
    }
}
