package data_access;

import entity.Channel.Channel;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.CreateChannel.CreateChannelDataAccessInterface;

import java.io.IOException;


public class FileUserDataAccessObject implements CreateChannelDataAccessInterface {
    private static final String API_TOKEN = "0abe6c776ab4537be2c5ca662b46dba1ac1be4f5";
    private static final String APPLICATION_ID = "39ACFA95-6D71-49B3-B9EF-EDDA2080C415";
    private static final String BASE_URL = "https://api-39ACFA95-6D71-49B3-B9EF-EDDA2080C415.sendbird.com/v3";


    public boolean createChannel(Channel channel) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        // Construct the JSON body
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", channel.getChannelName());
        requestBody.put("channel_url", channel.getChannelUrl());
        requestBody.put("is_ephemeral", channel.isEphemeral());
        requestBody.put("operators", new JSONArray(channel.getOperator())); // Assuming getOperators returns a List<String>

        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url(BASE_URL + "/open_channels")
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
                    System.err.println("Failed to create channel: " + jsonResponse.getString("message"));
                    return false;
                }
            } else {
                System.err.println("Request to create channel failed: " + response);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

