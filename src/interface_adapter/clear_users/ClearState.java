package interface_adapter.clear_users;
import use_case.clear_users.ClearOutputData;

import java.util.ArrayList;


public class ClearState {
    private ArrayList<String> deadList = new ArrayList<>();

    public ClearState(){
    }
    public ArrayList<String>getDeadList() {return this.deadList;}

    public void setDeadList(ClearOutputData clearOutputData){
        this.deadList = clearOutputData.get_deadList();}

    public String listFormat() {
        return String.join(System.getProperty("line.separator"), this.getDeadList());
    }
}


