package use_case.GetOperator;

import java.util.List;

public interface GetOperatorDataAccessInterface {

    List<String> getOperator(String channel_url);
}
