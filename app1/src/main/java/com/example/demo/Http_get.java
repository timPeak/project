package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http_get {
    public static void main(String[] args) {
        String zagl = "http://localhost:8081/hello";

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(zagl).openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250); //подключение 250 мс
            connection.setReadTimeout(250);    //чтение 250 мс

            connection.connect();              //подключились к сайту

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) { //если код 200 то запрос выполнился успешно
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); //считываем входной поток (можно указать второй параметр для русского алфавита)

                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    //sb.append("/n"); //для сайтов с кучей информации
                }
                System.out.println("Статус ошибки GET запроса: "+connection.getResponseCode());
                System.out.println("Содержимое сайта: ");
                System.out.println(sb.toString());
            } else {
                System.out.println("код ошибки не 200: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }

        } catch (Throwable exc) {
            exc.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
