package data_access;

import entity.Message.Message;
import entity.Message.CommonMessageFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.SendMessage.SendMessageDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class SendMessageDataAccessObject implements SendMessageDataAccessInterface {

    private static final String API_TOKEN = "b31d960439ff47a156c5bb6580f34ead46b12ef8";
    private static final String APPLICATION_ID = "FD8DA5C8-7E9A-4B60-8399-B6BDE0395593";
    private static final String BASE_URL = "https://api-B6DDF44B-E5A8-4F39-892E-4DE3FB979E62.sendbird.com/v3";

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
        if (messageTs == 0) {
            urlBuilder.addQueryParameter("message_ts", String.valueOf(System.currentTimeMillis()));
            urlBuilder.addQueryParameter("prev_limit", "200");
            urlBuilder.addQueryParameter("next_limit", "0");}
        else {
            urlBuilder.addQueryParameter("message_ts", String.valueOf(messageTs));
            urlBuilder.addQueryParameter("prev_limit", "0");
            urlBuilder.addQueryParameter("next_limit", "200");}

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

                    // 检查是否存在 user 对象
                    if (!jsonMessage.has("user")) {
                        continue; // 跳过此消息
                    }

                    JSONObject userObject = jsonMessage.getJSONObject("user");
                    String userId = userObject.getString("user_id");
                    String messageText = jsonMessage.getString("message");
                    long createdAt = jsonMessage.getLong("created_at");
                    Message messageObject = messageFactory.create(userId, messageText, channelType, channelUrl, createdAt);
                    messages.add(messageObject);
                }
                return messages;
            } else {
                System.out.println("Failed to get messages: " + response.message());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

