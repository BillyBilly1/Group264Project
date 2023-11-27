package api;

import java.io.IOException;

public class YandexAPIUsage {

    public static void main(String args[]) throws IOException {

        System.out.println("测试用");

        YandexAPI YA1 = new YandexAPI("https://translate.yandex.net/api/v1.5/tr.json",
                "trnsl.1.1.20231126T225239Z.669240c9ce7a577c.8ef08506935107d72a6e4c36f77882b6e7074caf",
                "zh", "Hello world..." +
                "I have done the assignment. Good Night");
        System.out.println(YA1.getText());
        System.out.println(YA1.translateLang());
        YandexAPI YA2 = new YandexAPI("https://translate.yandex.net/api/v1.5/tr.json",
                "trnsl.1.1.20231126T225239Z.669240c9ce7a577c.8ef08506935107d72a6e4c36f77882b6e7074caf",
                "fr", "早上好, 中午好，晚上好");
        System.out.println();
        System.out.println(YA2.getText());
        System.out.println(YA2.translateLang());

    }
}