package data_access;

import entity.User.User;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.ListChannel.ListChannelDataAccessInterface;
import use_case.Login.LoginDataAccessInterface;
import use_case.Signup.SignupDataAccessInterface;
import use_case.ViewProfile.ViewProfileDataAccessInterface;

import java.io.*;
import java.util.Objects;

public class FileUserDataAccessObject implements SignupDataAccessInterface,
        LoginDataAccessInterface,
        ListChannelDataAccessInterface, ViewProfileDataAccessInterface {
    private static final String API_TOKEN = "0abe6c776ab4537be2c5ca662b46dba1ac1be4f5";
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
        requestBody.put("profile_url", user.getProfileUrl());

        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        System.out.println(requestBody.toString());
        Request request = new Request.Builder()
                .url(BASE_URL + "/users")
                .post(body)
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            // Assuming a successful response includes a JSON body with a status code
            if (response.body() != null) {
                String responseData = response.body().string();
                System.out.println(responseData);
                JSONObject jsonResponse = new JSONObject(responseData);
                if (jsonResponse.has("is_created") ) {
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
        if (user_id == null || Objects.equals(user_id, "")) {
            return null;
        }

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "/users/" + user_id)
                .get()
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            // Assuming a successful response includes a JSON body with a status code
            if (response.body() != null) {
                String responseBody = response.body().string();
                //System.out.println(responseBody);
                JSONObject jsonResponse = new JSONObject(responseBody);
                if (jsonResponse.has("is_active")) {
                    return jsonResponse.getString("nickname"); // Channel created successfully
                } else if (jsonResponse.has("error")) {
                    System.err.println("Failed to login: " + jsonResponse.getString("message"));
                    return null;
                }
            } else {
                System.err.println("Request to login failed: " + response);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public JSONArray list_channel(String user_id) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "/users/group_channels?members_exactly_in=" + user_id)
                .get()
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            // Assuming a successful response includes a JSON body with a status code
            if (response.body() != null) {
                String responseBody = response.body().string();
                //System.out.println(responseBody);
                JSONObject jsonResponse = new JSONObject(responseBody);
                JSONArray channels = jsonResponse.getJSONArray("channels");
                if (channels != null) {
                    return channels;
                } else{
                    return null;
                }
            } else {
                System.err.println("Request to login failed: " + response);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean existsByName(String user_id) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "/users/" + user_id)
                .get()
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                //System.out.println(responseBody);
                JSONObject jsonResponse = new JSONObject(responseBody);
                return true;
            } else if (response.code() == 404) {
                return false;
            } else {
                throw new RuntimeException("Fail to view profile." + response.code());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

