package com.mycompany.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task3 {

    public static void run(WebDriver webDriver) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

        try {
            System.out.println("=== Task #3 ===");
            webDriver.get(url);

            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonStr);
            JSONObject hourly = (JSONObject) root.get("hourly");

            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            StringBuilder tableBuilder = new StringBuilder();
            tableBuilder.append(String.format("| %-3s | %-16s | %-11s | %-12s |\n", "N", "Date/Time", "Temperature", "Precipitation (mm)"));
            tableBuilder.append("| --- | ---------------- | ----------- | ------------ |\n");

            for (int i = 0; i < times.size(); i++) {
                String dateTime = (String) times.get(i);
                double temp = ((Number) temperatures.get(i)).doubleValue();
                double rain = ((Number) rains.get(i)).doubleValue();

                tableBuilder.append(String.format("| %-3d | %-16s | %-11.1f | %-12.2f |\n", (i + 1), dateTime, temp, rain));
            }

            String finalTable = tableBuilder.toString();
            System.out.println(finalTable);

            saveToFile(finalTable);

        } catch (Exception e) {
            System.out.println("Ошибка выполнения Задания №3: " + e.toString());
        }
    }

    private static void saveToFile(String content) {
        try {
            File dir = new File("result");
            if (!dir.exists()) {
                dir.mkdir();
            }
            FileWriter writer = new FileWriter("result/forecast.txt");
            writer.write(content);
            writer.close();
            System.out.println("Результат успешно сохранен в файл 'result/forecast.txt'");
        } catch (IOException e) {
            System.out.println("Не удалось сохранить файл таблицы: " + e.getMessage());
        }
    }
}
