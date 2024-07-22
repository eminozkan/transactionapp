package eminozkan.transaction.util.result;

public class ResultMessage {

    private String message;

    public ResultMessage() {

    }

    public ResultMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ResultMessage setMessage(String message) {
        this.message = message;
        return this;
    }
}
