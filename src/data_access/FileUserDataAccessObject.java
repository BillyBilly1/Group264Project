package data_access;

import entity.User.User;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.InviteMember.InviteMemberDataAccessInterface;
import use_case.InviteMember.InviteMemberInputdata;
import use_case.ListChannel.ListChannelDataAccessInterface;
import use_case.Login.LoginDataAccessInterface;
import use_case.RemoveMember.RemoveMemberDataAccessInterface;
import use_case.RemoveMember.RemoveMemberInputdata;
import use_case.Signup.SignupDataAccessInterface;
import use_case.ViewProfile.ViewProfileDataAccessInterface;

import java.io.*;

import java.util.ArrayList;
import java.util.Objects;

public class FileUserDataAccessObject implements SignupDataAccessInterface,
        LoginDataAccessInterface, RemoveMemberDataAccessInterface,
        ListChannelDataAccessInterface, ViewProfileDataAccessInterface,
        InviteMemberDataAccessInterface{

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
        if (user_id == null ) {
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
                .url(BASE_URL + "/group_channels?members_include_in=" + user_id)
                .get()
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);

                // 检查 jsonResponse 是否包含 "channels" 键
                if (jsonResponse.has("channels")) {
                    return jsonResponse.getJSONArray("channels");
                } else {
                    // 如果没有 "channels" 键，可以返回一个空的 JSONArray 或处理错误
                    return new JSONArray();  // 返回空的 JSONArray
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


    //@Override
    public boolean invite(InviteMemberInputdata inviteMemberInputdata){
        String user_id = inviteMemberInputdata.getUser_id();
        String channel_url = inviteMemberInputdata.getChannel_url();

        ArrayList<String> user_ids = new ArrayList<String>();
        user_ids.add(user_id);


        MediaType mediaType = MediaType.parse("application/json");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        JSONObject requestBody = new JSONObject();
        requestBody.put("user_ids", user_ids);
        requestBody.put("channel_url", channel_url);

        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url(BASE_URL + "/group_channels/"+channel_url + "/invite")
                .post(body)
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
                if (jsonResponse.has("channel_url") ){
                    return true;
                } else {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;

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

    @Override
    public boolean remove(RemoveMemberInputdata removeMemberInputdata){
        String user_id = removeMemberInputdata.getUser_id();
        String channel_url = removeMemberInputdata.getChannel_url();

        ArrayList<String> user_ids = new ArrayList<String>();
        user_ids.add(user_id);


        MediaType mediaType = MediaType.parse("application/json");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        JSONObject requestBody = new JSONObject();
        requestBody.put("user_ids", user_ids);
        requestBody.put("channel_url", channel_url);

        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url(BASE_URL + "/group_channels/"+ channel_url + "/leave")
                .post(body)
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            // Assuming a successful response includes a JSON body with a status code
            if (response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                if (jsonResponse.has("message") ){
                    return false;
                } else {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean is_member(RemoveMemberInputdata removeMemberInputdata){
        String user_id = removeMemberInputdata.getUser_id();
        String channel_url = removeMemberInputdata.getChannel_url();


        MediaType mediaType = MediaType.parse("application/json");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        JSONObject requestBody = new JSONObject();

        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url(BASE_URL + "/group_channels/" + channel_url +"/members/" + user_id)
                .post(body)
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            // Assuming a successful response includes a JSON body with a status code
            if (response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                if(jsonResponse.getBoolean("is_member")){
                    return true;
                } else {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}


