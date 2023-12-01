package data_access;

import entity.Channel.Channel;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.CreateChannel.CreateChannelDataAccessInterface;
import use_case.ListChannel.ListChannelDataAccessInterface;

import java.io.IOException;


public class FileChannelDataAccessObject implements CreateChannelDataAccessInterface {
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
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {


                return true;
            } else {

                System.err.println("Failed to create channel. HTTP error code: " + response.code());
                return false;
            }
        } catch (IOException e) {
            // Log or handle the exception
            System.err.println("IOException when trying to create channel: " + e.getMessage());
            return false;
        }
    }

    private void logChannelDetails(Channel channel, String message) {
        System.out.println("Channel Name: " + channel.getChannelName());
        System.out.println("Channel URL: " + channel.getChannelUrl());
        System.out.println("Channel Operators: " + channel.getOperator());
        System.out.println("Is Ephemeral: " + channel.isEphemeral());
        System.out.println("API Token Used: " + API_TOKEN);
        System.out.println("Base URL: " + BASE_URL);
        System.out.println(message);
    }
}

