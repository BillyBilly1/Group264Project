package Message;

public interface Message {
    String getUser_id();

    String getMessage();

    String getChannel_type();

    String getChannel_url();

    String getData();

    Boolean getSendPush();
}
