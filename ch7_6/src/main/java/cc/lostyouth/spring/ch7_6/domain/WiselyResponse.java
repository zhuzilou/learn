package cc.lostyouth.spring.ch7_6.domain;

/**
 * Created by endless on 11/21/2017.
 */
public class WiselyResponse {
    private String responseMessage;

    public WiselyResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
