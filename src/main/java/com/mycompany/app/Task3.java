package com.mycompany.app;

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
            System.out.println("Задание 3");
            webDriver.get(url);

            WebElement element = webDriver.findElement(By.tagName("pre"));
            String jsonText = element.getText();

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonText);
            JSONObject hourly = (JSONObject) root.get("hourly");

            JSONArray time = (JSONArray) hourly.get("time");
            JSONArray temperature = (JSONArray) hourly.get("temperature_2m");
            JSONArray rain = (JSONArray) hourly.get("rain");

            String table = "Num.\t| Date/time\t\t| Temperature\t| Precipitation (mm)\n";
            table += "--------------------------------------------------------------------\n";

            for (int i = 0; i < time.size(); i++) {
                String dateTime = (String) time.get(i);

                double temp_ = Double.parseDouble(temperature.get(i).toString());
                double rain_ = Double.parseDouble(rain.get(i).toString());

                table += (i + 1) + "\t| " + dateTime + "\t| " + temp_ + "\t\t| " + rain_ + "\n";
            }

            System.out.println(table);
            saveForecast(table);

        } catch (Exception e) {
            System.out.println("Ошибка в Задании 3: " + e.getMessage());
        }
    }

    private static void saveForecast(String textData) {
        try (FileWriter writer = new FileWriter("forecast.txt")) {
            writer.write(textData);
            System.out.println("Прогноз сохранен в файл forecast.txt");
        } catch (IOException e) {
            System.out.println("Не удалось сохранить файл таблицы: " + e.getMessage());
        }
    }
}
