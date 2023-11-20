package data_access;
import entity.Channel.Channel;
import entity.Channel.CommonChannel;
import data_access.FileUserDataAccessObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;


public class Main {


    public static void main(String[] args) {
        FileUserDataAccessObject fileUserDataAccessObject = new FileUserDataAccessObject();

        // Create a new CommonChannel object with the test data
        CommonChannel testChannel = new CommonChannel(
                "Cat House",               // Channel name
                "1234",                    // Channel URL
                new ArrayList<>(Collections.singletonList("Orange Cat")), // Operators
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
