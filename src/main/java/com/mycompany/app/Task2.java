package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {

    public static void run(WebDriver webDriver) {
        try {
            System.out.println("Задание 2");
            webDriver.get("https://api.ipify.org/?format=json");

            WebElement element = webDriver.findElement(By.tagName("pre"));
            String jsonStr = element.getText();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonStr);

            String ip = (String) jsonObject.get("ip");
            System.out.println("IP4-адрес клиента: " + ip);

        } catch (Exception e) {
            System.out.println("Ошибка в Задании 2: " + e.getMessage());
        }
    }
}
