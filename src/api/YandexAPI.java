package api;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class YandexAPI {

    private final String API_URL;

    private final String API_KEY;

    private final String targetLang;

    private final String text;

    public String encode(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }

    public String decode(String text) {
        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }


    public YandexAPI(String apiUrl, String apiKey, String targetLang, String text) {
        API_URL = apiUrl;
        API_KEY = apiKey;
        this.targetLang = targetLang;
        this.text = encode(text);
    }

    public String getText() {return decode(text);}


    public String detectLang() throws IOException {
        String urlStr = API_URL + "/detect?key=" + API_KEY + "&text=" + text;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getString("lang");

        } else {
            throw new RuntimeException("Failed to detect language: HTTP error code " + responseCode);
        }
    }

    public String translateLang() throws IOException {

        String originalLang = detectLang();

        String urlStr = API_URL + "/translate?key=" + API_KEY + "&text=" + text +
                "&lang=" + originalLang + "-" + targetLang;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray textArray = jsonResponse.getJSONArray("text");

            StringBuilder translatedText = new StringBuilder();
            for (int i = 0; i < textArray.length(); i++) {
                translatedText.append(textArray.getString(i)).append(" ");
            }

            return translatedText.toString().trim();

        } else {
            throw new RuntimeException("Failed to Translate: HTTP error code " + responseCode);
        }


    }
    }











