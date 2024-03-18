package data_access;

import entity.Channel.Channel;
import entity.Channel.ChannelFactory;
import entity.Channel.CommonChannel;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.CreateChannel.CreateChannelDataAccessInterface;
import use_case.GetOperator.GetOperatorDataAccessInterface;
import use_case.ListChannel.ListChannelDataAccessInterface;
import use_case.ViewChannel.ViewChannelDataAccessInterface;
import use_case.ViewMembers.ViewMembersDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileChannelDataAccessObject implements CreateChannelDataAccessInterface, ViewChannelDataAccessInterface,
        ViewMembersDataAccessInterface, GetOperatorDataAccessInterface {
    private static final String API_TOKEN = "b31d960439ff47a156c5bb6580f34ead46b12ef8";
    private static final String APPLICATION_ID = "FD8DA5C8-7E9A-4B60-8399-B6BDE0395593";
    private static final String BASE_URL = "https://api-B6DDF44B-E5A8-4F39-892E-4DE3FB979E62.sendbird.com/v3";

    public FileChannelDataAccessObject() throws IOException {}


    public boolean createChannel(Channel channel) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        // Construct the JSON body
        JSONObject requestBody = new JSONObject();
        requestBody.put("user_ids", channel.getUser_ids());
        requestBody.put("name", channel.getChannelName());
        requestBody.put("channel_url", channel.getChannelUrl());
        requestBody.put("is_distinct", channel.is_distinct());
        requestBody.put("is_ephemeral", channel.isEphemeral());
        requestBody.put("operator_ids", new JSONArray(channel.getOperator())); // Assuming getOperators returns a List<String>

        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url(BASE_URL + "/group_channels")
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



    @Override
    public Channel viewChannel(String channel_url) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/group_channels/" + channel_url)
                .get()
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String jsonResponse =  response.body().string();
                JSONObject responseJson = new JSONObject(jsonResponse);
                JSONObject channelJson = responseJson.getJSONObject("channel");
                String url = channelJson.getString("channel_url");
                String name = channelJson.getString("name");
                String is_distinct = "false";
                String isEphemeral = "false";
                Channel channel = new CommonChannel(name, url, is_distinct, isEphemeral);

                return channel;

            } else {

                System.err.println("Failed to view channel. HTTP error code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            // Log or handle the exception
            System.err.println("IOException when trying to create channel: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<String> viewMembers(String channel_url) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/group_channels/" + channel_url + "/members")
                .get()
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build();
        System.out.println("reached here");
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String jsonResponse =  response.body().string();
                JSONObject responseJson = new JSONObject(jsonResponse);
                JSONArray members = responseJson.getJSONArray("members");
                List<String> user_ids = new ArrayList<String>();
                for (int i = 0; i < members.length(); i++) {
                    JSONObject member = members.getJSONObject(i);
                    String userId = member.getString("user_id");
                    user_ids.add(userId);
                }
                return user_ids;
            } else {

                System.err.println("Failed to view members. HTTP error code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            // Log or handle the exception
            System.err.println("IOException when trying to create channel: " + e.getMessage());
            return null;
        }

    }

    @Override
    public List<String> getOperator(String channel_url) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        Request request = new Request.Builder()
                .url(BASE_URL + "/group_channels/" + channel_url + "/operators")
                .get()
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String jsonResponse =  response.body().string();
                JSONObject responseJson = new JSONObject(jsonResponse);
                JSONArray operators = responseJson.getJSONArray("operators");
                List<String> user_ids = new ArrayList<String>();
                for (int i = 0; i < operators.length(); i++) {
                    JSONObject operator = operators.getJSONObject(i);
                    String userId = operator.getString("user_id");
                    user_ids.add(userId);
                }
                return user_ids;
            } else {

                System.err.println("Failed to view operator. HTTP error code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            // Log or handle the exception
            System.err.println("IOException when trying to create channel: " + e.getMessage());
            return null;
        }
    }
}
