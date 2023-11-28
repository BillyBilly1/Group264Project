package data_access;
import entity.Channel.CommonChannel;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Main {


    public static void main(String[] args) {
        FileChannelDataAccessObject fileUserDataAccessObject = new FileChannelDataAccessObject();

        // Create a new CommonChannel object with the test data
        ArrayList<String> list = new ArrayList<>();
        list.add("Orange Cat");
        CommonChannel testChannel = new CommonChannel(
                "Cat House",               // Channel name
                "1234",                    // Channel URL
                list, // Operators
                "true",                    // isEphemeral
                LocalDateTime.now()        // Creation time
        );

        // Attempt to create the channel using the data access object
        boolean isChannelCreated = fileUserDataAccessObject.createChannel(testChannel);

        // Print the result of the operation
        if (isChannelCreated) {
            System.out.println("Channel creation successful.");
        } else {
            System.out.println("Channel creation failed.");
        }
    }
}
