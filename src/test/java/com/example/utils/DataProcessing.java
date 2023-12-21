package com.example.utils;

import org.openqa.selenium.By;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import static com.example.context.Context.getDriver;

public class DataProcessing {

    public static Map<String, String> processDataTable(Map<String, String> dataTable) {
        Map<String, String> processedData = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : dataTable.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            processedData.put(key, processPlaceholder(value));
        }
        return processedData;
    }

    private static String processPlaceholder(String placeholder) {
        return switch (placeholder.toLowerCase()) {
            case "today" -> LocalDate.now().toString();
            case "randomnumber" -> String.valueOf(new Random().nextInt(1,100));
            case "emptystring" -> "";
            case "null" -> null;
            case "textofelement" -> getDriver().findElement(By.className("login_logo")).getText();
            default -> placeholder;
        };
    }
}
