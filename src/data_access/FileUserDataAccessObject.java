package data_access;

import entity.User.User;
import entity.User.UserFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.Login.LoginDataAccessInterface;
import use_case.Signup.SignupDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignupDataAccessInterface, LoginDataAccessInterface {
    private static final String API_TOKEN = "0abe6c776ab4537be2c5ca662b46dba1ac1be4f5";
    private static final String APPLICATION_ID = "39ACFA95-6D71-49B3-B9EF-EDDA2080C415";
    private static final String BASE_URL = "https://api-39ACFA95-6D71-49B3-B9EF-EDDA2080C415.sendbird.com/v3";


    public FileUserDataAccessObject() throws IOException {
    }

    public boolean save(User user) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        // Construct the JSON body
        JSONObject requestBody = new JSONObject();
        requestBody.put("user_id", user.getUser_Id());
        requestBody.put("nickname", user.getNickName());

        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url(BASE_URL + "/users")
                .method("POST", body)
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            // Assuming a successful response includes a JSON body with a status code
            if (response.isSuccessful() && response.body() != null) {
                String responseData = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseData);
                int statusCode = jsonResponse.getInt("status_code");
                if (statusCode == 200) {
                    return true; // Channel created successfully
                } else {
                    System.err.println("Failed to create user: " + jsonResponse.getString("message"));
                    return false;
                }
            } else {
                System.err.println("Request to create user failed: " + response);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        }
        @Override
        public String get_username(String user_id) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");

            // Construct the JSON body
            JSONObject requestBody = new JSONObject();

            RequestBody body = RequestBody.create(mediaType, requestBody.toString());
            Request request = new Request.Builder()
                    .url(BASE_URL + "/users/" + user_id)
                    .method("POST", body)
                    .addHeader("Api-Token", API_TOKEN)
                    .addHeader("Content-Type", "application/json")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                // Assuming a successful response includes a JSON body with a status code
                if (response.isSuccessful() && response.body() != null) {
                    String responseData = response.body().string();
                    JSONObject jsonResponse = new JSONObject(responseData);
                    int statusCode = jsonResponse.getInt("status_code");
                    if (statusCode == 200) {
                        return jsonResponse.getString("user_id"); // Channel created successfully
                    } else {
                        System.err.println("Failed to create channel: " + jsonResponse.getString("message"));
                        return null;
                    }
                } else {
                    System.err.println("Request to create channel failed: " + response);
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
}

