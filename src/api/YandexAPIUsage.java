package api;

import java.io.IOException;

public class YandexAPIUsage {

    public static void main(String args[]) throws IOException {

        System.out.println("测试用");

        YandexAPI YA1 = new YandexAPI(
                "zh", "Hello world..." +
                "I have done the assignment. Good Night");
        System.out.println(YA1.getText());
        System.out.println(YA1.translate());
        YandexAPI YA2 = new YandexAPI(
                "fr", "早上好, 中午好，晚上好");
        System.out.println();
        System.out.println(YA2.getText());
        System.out.println(YA2.translate());

    }
}