package org.selenium.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new SafariDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void positiveLoginTest() {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.id("username")).sendKeys("student");

        driver.findElement(By.id("password")).sendKeys("Password123");

        driver.findElement(By.id("submit")).click();

        wait.until(ExpectedConditions.urlContains("logged-in-successfully"));

        String currentUrl = driver.getCurrentUrl();

        assertTrue(
                currentUrl.contains("practicetestautomation.com/logged-in-successfully"),
                "URL beklenen yönlendirmeye gitmiyor " + currentUrl
        );

        String pageText = driver.findElement(By.tagName("body")).getText();
        assertTrue(
                pageText.contains("Congratulations") || pageText.toLowerCase().contains("successfully logged in"),
                "Sayfada beklenen başarı mesajı bulunamadı"
        );
    }

    @Test
    void negativeLoginTest() {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.id("username")).sendKeys("incorrectUser");

        driver.findElement(By.id("password")).sendKeys("Password123");

        driver.findElement(By.id("submit")).click();

        String pageText = driver.findElement(By.tagName("body")).getText();
        assertTrue(
                pageText.contains("Your username is invalid!"),
                "Sayfada beklenen başarı mesajı bulunamadı"
        );
    }

    @Test
    void invalidPasswordTest() {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.id("username")).sendKeys("student");

        driver.findElement(By.id("password")).sendKeys("incorrectPassword");

        driver.findElement(By.id("submit")).click();

        String pageText = driver.findElement(By.tagName("body")).getText();
        assertTrue(
                pageText.contains("Your password is invalid!"),
                "Sayfada beklenen başarı mesajı bulunamadı"
        );
    }
}