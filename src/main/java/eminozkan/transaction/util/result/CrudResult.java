package eminozkan.transaction.util.result;

public class CrudResult {
    private CrudResult(){}

    private OperationResult result;

    private OperationFailureReason reason;

    private ResultMessage message;

    public static CrudResult success(){
        return new CrudResult()
                .setResult(OperationResult.SUCCESS);
    }

    public static CrudResult success(String message){
        return new CrudResult()
                .setResult(OperationResult.SUCCESS)
                .setMessage(new ResultMessage(message));
    }

    public static CrudResult failed(OperationFailureReason reason, String message){
        return new CrudResult()
                .setResult(OperationResult.FAILED)
                .setReason(reason)
                .setMessage(new ResultMessage(message));
    }

    public OperationResult getResult() {
        return result;
    }

    public CrudResult setResult(OperationResult result) {
        this.result = result;
        return this;
    }

    public OperationFailureReason getReason() {
        return reason;
    }

    public CrudResult setReason(OperationFailureReason reason) {
        this.reason = reason;
        return this;
    }

    public ResultMessage getMessage() {
        return message;
    }

    public CrudResult setMessage(ResultMessage message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess() {
        return result == OperationResult.SUCCESS;
    }
}
