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
            System.out.println("Задание 1");
            webDriver.get("https://www.calculator.net/password-generator.html");

            WebElement passwordField = webDriver.findElement(By.className("verybigtext"));
            String text = passwordField.getText();

            System.out.println("Пароль от сервера: " + text);

            Task2.run(webDriver);
            Task3.run(webDriver);

        } catch (Exception e) {
            System.out.println("Ошибка выполнения Задания 1: " + e.getMessage());
        } finally {
            webDriver.quit();
        }
    }
}
