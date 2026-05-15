package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\software testing (ST-2026)\\chromedriver-win64\\chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();

        try {
            System.out.println("=== Task #1 ===");
            webDriver.get("https://www.calculator.net/password-generator.html");

            Thread.sleep(2000);

            WebElement passwordElement = webDriver.findElement(By.cssSelector(".verybigtext b"));
            String generatedPassword = passwordElement.getText();

            System.out.println("Пароль от сервера: " + generatedPassword);
            System.out.println();

            Task2.run(webDriver);
            Task3.run(webDriver);

            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("Ошибка выполнения Задания №1:");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}
