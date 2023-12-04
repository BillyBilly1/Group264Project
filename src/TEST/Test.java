package TEST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import data_access.FileChannelDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.Channel.Channel;
import entity.Channel.ChannelFactory;
import entity.Channel.CommonChannel;
import entity.Channel.CommonChannelFactory;
import entity.User.CommonUserFactory;
import entity.User.User;
import interface_adapter.create_channel.CreateChannelPresenter;
import use_case.*;
import use_case.CreateChannel.*;
import entity.*;
import data_access.SendMessageDataAccessObject;
import use_case.InviteMember.InviteMemberInputdata;


public class Test {
    // This test class are used to create test cases to each one of our use case, since all of our use case are
    // Based on API, thus we created variables in each of the test method to mimic the input data passed into interactor
    // If the following test case are passed represent that the use case are successfully triggered and operated.
    // Since more usecase are creating, more test case will be added before due date.



    public static void main(String[] args) {
        Test tester = new Test(); // Create an instance of the Test class
        boolean allTestsPassed = true;
        try {
            boolean result = tester.TestCreateChannel();
            System.out.println("TestCreateChannel result: " + result);
            allTestsPassed = allTestsPassed && result;

        } catch (IOException e) {
            System.err.println("TestCreateChannel failed with IOException: " + e.getMessage());
            e.printStackTrace();
            allTestsPassed = false;
        }

        try {
            boolean result = tester.TestCreateUser();
            System.out.println("TestCreateUser result: " + result);
            allTestsPassed = allTestsPassed && result;

        } catch (IOException e) {
            System.err.println("TestCreateUser failed with IOException: " + e.getMessage());
            e.printStackTrace();
            allTestsPassed = false;
        }

        try {
            boolean result = tester.TestGetPassword();
            System.out.println("TestGetPassword result: " + result);
            allTestsPassed = allTestsPassed && result;
        } catch (IOException e) {
            System.err.println("TestGetGetPassword failed with IOException: " + e.getMessage());
            e.printStackTrace();
            allTestsPassed = false;
        }

        try {
            boolean result = tester.TestViewChannel();
            System.out.println("TestViewChannel result: " + result);
            allTestsPassed = allTestsPassed && result;
        } catch (IOException e) {
            System.err.println("TestViewChannel failed with IOException: " + e.getMessage());
            e.printStackTrace();
            allTestsPassed = false;
        }

        try {
            boolean result = tester.TestSendMessage();
            System.out.println("SendMessage result: " + result);
            allTestsPassed = allTestsPassed && result;
        } catch (IOException e) {
            System.err.println("SendMessage failed with IOException: " + e.getMessage());
            e.printStackTrace();
            allTestsPassed = false;
        }

        try {
            boolean result = tester.TestInviteMembers();
            System.out.println("InviteMember result: " + result);
            allTestsPassed = allTestsPassed && result;
        } catch (IOException e) {
            System.err.println("InviteMember failed with IOException: " + e.getMessage());
            e.printStackTrace();
            allTestsPassed = false;
        }
    }


    public boolean TestCreateChannel() throws IOException {
        // Test CreateChannel Interactor created the channel and successfully send api request.

        List<String> ids = new ArrayList<>();
        String id1 = "T0001";
        ids.add(id1);
        List<String> ope = new ArrayList<>();
        ope.add("0001");
        String name = "TestCreateChannel";
        String url = "T0002";
        String is_distinct = "false";
        String isEphemeral = "false";

        Channel testChannel  = new CommonChannelFactory().create(name, url, is_distinct, isEphemeral);

        boolean testAPI = new FileChannelDataAccessObject().createChannel(testChannel);
        testChannel.setUser_id(ids);
        testChannel.setOperator(ope);


        if (testAPI) {
            System.out.println("API send successfully, passing the build channel into presenter, Create Channel useCase no problem");
            return true;
        }
        else{
            System.out.println("error");
            return false;
        }
    }

    public boolean TestCreateUser() throws IOException {
        String id = "T0003";
        String name = "TestUser3";
        String url  ="t0001";

        User testUser = new CommonUserFactory().create(id, name, url);

        boolean testAPI = new FileUserDataAccessObject().save(testUser);
        if (testAPI) {
            System.out.println("API send successfully, passing the build user into presenter, Create User useCaseInteractor no problem");
            return true;
        }
        else{
            System.out.println("error");
            return false;
        }
    }

    public boolean TestGetPassword() throws IOException {
        // Since our login use case are based on get username, if username and userid are matched you will go into our
        // Main menu, assume that we already have a username with "Test2" adn user_id "T0002"

        String id = "T0002";

        String testAPI = new FileUserDataAccessObject().get_username(id);

        if (Objects.equals(testAPI, "TestUser2")) {
            System.out.println("API send successfully, passing the build password into presenter, successfully get the password," +
                    "get User useCaseInteractor no problem");
            return true;
        }
        else {
            System.out.println("error");
            return false;
        }
    }

    public boolean TestViewChannel() throws  IOException {
        String channel_url = "T0001";

        Channel testChannel = new FileChannelDataAccessObject().viewChannel(channel_url);

        List<String> ids = new ArrayList<>();
        String id1 = "T0001";
        ids.add(id1);
        List<String> ope = new ArrayList<>();
        ope.add("0001");
        String name = "TestCreateChannel";
        String url = "T0001";
        String is_distinct = "false";
        String isEphemeral = "false";

        Channel expectedChannel  = new CommonChannelFactory().create(name, url, is_distinct, isEphemeral);
        expectedChannel.setUser_id(ids);
        expectedChannel.setOperator(ope);
        testChannel.setUser_id(ids);
        testChannel.setOperator(ope);

        if (Objects.equals(testChannel.getChannelUrl(), expectedChannel.getChannelUrl())){
            System.out.println("API send successfully, passing the channel that belongs to the channel_url into presenter, successfully get the password," +
                    "View Channel useCaseInteractor no problem");
            return true;
        }
        else {
            System.out.println("error");
            return false;
        }
    }

    public boolean TestSendMessage() throws IOException{
        String userID = "T0002";
        String message = "Message send";
        String channelType = "group_channels";
        String channelUrl = "T0001";

        boolean result = new SendMessageDataAccessObject().sendMessage(userID, message, channelType, channelUrl);
        if (result) {
            System.out.println("Message send successfully in the corresponding channel, thus send message interactor no problem");
            return true;
        }
        else {
            System.out.println("error");
            return false;
        }
    }

    public boolean TestInviteMembers() throws IOException{
        String userID = "T0002";
        String url = "T0002";

        InviteMemberInputdata action = new InviteMemberInputdata(userID,url);
        boolean result = new FileUserDataAccessObject().invite(action);

        if (result) {
            System.out.println("Invite member into channel successfully");
            return true;
        }
        else {
            System.out.println("error");
            return false;
        }
    }
}
