package org.selenium.selenium;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

class SeleniumExampleTest {

    @Test
    void exampleDotCom_accessible_varOrYok() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://example.com");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.titleContains("Example Domain"));

            boolean accessible = driver.getTitle() != null && driver.getTitle().contains("Example Domain");
            if (accessible) {
                System.out.println("var");
            } else {
                System.out.println("yok");
            }
            assertTrue(accessible, "https://example.com should be accessible");
        } catch (Exception e) {
            System.out.println("yok");
            fail("Erişim sağlanamadı: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
