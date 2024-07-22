package eminozkan.transaction.util.result;

public class AuthResult {
    private AuthResult(){}

    private OperationResult result;

    private OperationFailureReason reason;

    private ResultMessage message;

    private AuthToken token;

    public static AuthResult success(){
        return new AuthResult()
                .setResult(OperationResult.SUCCESS);
    }

    public static AuthResult success(String message,String token){
        return new AuthResult()
                .setResult(OperationResult.SUCCESS)
                .setMessage(new ResultMessage(message))
                .setToken(new AuthToken(token));
    }

    public static AuthResult failed(OperationFailureReason reason, String message){
        return new AuthResult()
                .setResult(OperationResult.FAILED)
                .setReason(reason)
                .setMessage(new ResultMessage(message));
    }

    public OperationResult getResult() {
        return result;
    }

    public AuthResult setResult(OperationResult result) {
        this.result = result;
        return this;
    }

    public OperationFailureReason getReason() {
        return reason;
    }

    public AuthResult setReason(OperationFailureReason reason) {
        this.reason = reason;
        return this;
    }

    public ResultMessage getMessage() {
        return message;
    }

    public AuthResult setMessage(ResultMessage message) {
        this.message = message;
        return this;
    }

    public AuthToken getToken() {
        return token;
    }

    public AuthResult setToken(AuthToken token) {
        this.token = token;
        return this;
    }


    public boolean isSuccess() {
        return result == OperationResult.SUCCESS;
    }
}
