package use_case.ListChannel;

public class ListChannelInputData {
    final private String user_id;

    public ListChannelInputData(String user_id){
        this.user_id = user_id;
    }

    String getUser_id(){return this.user_id;}
}
