package data_access;

import entity.Message.Message;
import entity.Message.CommonMessageFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class SendMessageDataAccessObject {

    private static final String API_TOKEN = "0abe6c776ab4537be2c5ca662b46dba1ac1be4f5";
    private static final String APPLICATION_ID = "39ACFA95-6D71-49B3-B9EF-EDDA2080C415";
    private static final String BASE_URL = "https://api-39ACFA95-6D71-49B3-B9EF-EDDA2080C415.sendbird.com/v3";

    public SendMessageDataAccessObject() throws IOException {}

    public boolean sendMessage(String userId, String message, String channelType, String channelUrl) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");

        String json = "{" + "\"message_type\": \"MESG\","
                + "\"user_id\": \"" + userId + "\","
                + "\"message\": \"" + message + "\""
                + "}";

        RequestBody body = RequestBody.create(mediaType, json);

        Request request = new Request.Builder()
                .url(BASE_URL + "/" + channelType + "/" + channelUrl + "/messages")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Api-Token", API_TOKEN)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return true;
            } else {
                System.out.println("Message send failed: " + response.message());
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Message> getMessages(String channelType, String channelUrl, long messageTs) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/" + channelType + "/" + channelUrl + "/messages").newBuilder();
        urlBuilder.addQueryParameter("message_ts", String.valueOf(messageTs));

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("Api-Token", API_TOKEN)
                .build();

        ArrayList<Message> messages = new ArrayList<>();
        CommonMessageFactory messageFactory = new CommonMessageFactory();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                JSONObject jsonObject = new JSONObject(responseData);
                JSONArray jsonArray = jsonObject.getJSONArray("messages");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonMessage = jsonArray.getJSONObject(i);
                    String userId = jsonMessage.getJSONObject("user").getString("user_id");
                    String messageText = jsonMessage.getString("message");
                    long createdAt = jsonMessage.getLong("created_at");
                    Message messageObject = messageFactory.create(userId, messageText, channelType, channelUrl, createdAt);
                    messages.add(messageObject);
                }
                return messages;
            } else {
                System.out.println("Failed to get messages: " + response.message());
                return messages;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return messages;
        }
    }
}
