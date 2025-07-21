package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.AdminUserPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Admin Add User Tests")
public class AdminUserTest extends BaseTest {
    static WebDriverWait wait;
    static AdminUserPage adminUserPage;

    @BeforeAll
    static void setup() {
        adminUserPage = new AdminUserPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @ParameterizedTest(name = "Add User: {0} / {1} / {2} => {6}")
    @CsvFileSource(resources = "/add-user-data.csv", numLinesToSkip = 1)
    @Order(1)
    void testAddUser(String username, String email, String fullName, String phone, String address, String role, String expected) {
        adminUserPage.navigate();
        adminUserPage.openAddUserModal();
        adminUserPage.fillUserForm(username, email, fullName, phone, address, role);
        adminUserPage.submitUserForm();

        if ("success".equals(expected)) {
            // TODO: Kiểm tra user mới xuất hiện trong bảng (có thể kiểm tra bằng email hoặc username)
            // Ví dụ: wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("userTableBody"), email));
        } else {
            try {
                WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(adminUserPage.getErrorLocator()));
                assertTrue(error.getText().length() > 0);
            } catch (TimeoutException e) {
                Assertions.fail("Không tìm thấy thông báo lỗi khi thêm user sai (username: '" + username + "', email: '" + email + "')");
            }
        }
    }
} 