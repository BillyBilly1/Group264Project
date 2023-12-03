package use_case.ViewChannel;

import data_access.FileChannelDataAccessObject;
import entity.Channel.Channel;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        String url = "cxkkk";
        Channel channel = new FileChannelDataAccessObject().viewChannel(url);
        System.out.println(channel.getChannelName());
        List<String> members = new FileChannelDataAccessObject().viewMembers(url);
        List<String> operators = new FileChannelDataAccessObject().getOperator(url);

        channel.setUser_id(members);
        channel.setOperator(operators);
        for (String userId : members) {
            System.out.println("members are " + userId);
        }

        for (String ope: operators) {
            System.out.println(ope);
        }
    }
}
