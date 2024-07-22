package eminozkan.transaction.util;

import eminozkan.transaction.util.result.AuthResult;
import eminozkan.transaction.util.result.CrudResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BusinessResultHandler {

    public static ResponseEntity<?> handleResult(CrudResult result) {
        return switch (result.getReason()){
            case NOT_FOUND -> new ResponseEntity<>(result.getMessage(), HttpStatus.NOT_FOUND);
            case CONFLICT -> new ResponseEntity<>(result.getMessage(), HttpStatus.CONFLICT);
            case UNAUTHORIZED -> new ResponseEntity<>(result.getMessage(), HttpStatus.UNAUTHORIZED);
            case PRECONDITION_FAILED -> new ResponseEntity<>(result.getMessage(), HttpStatus.PRECONDITION_FAILED);
            default -> new ResponseEntity<>(result.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }

    public static ResponseEntity<?> handleResult(AuthResult result) {
        return switch (result.getReason()){
            case NOT_FOUND -> new ResponseEntity<>(result.getMessage(), HttpStatus.NOT_FOUND);
            case CONFLICT -> new ResponseEntity<>(result.getMessage(), HttpStatus.CONFLICT);
            case UNAUTHORIZED -> new ResponseEntity<>(result.getMessage(), HttpStatus.UNAUTHORIZED);
            case PRECONDITION_FAILED -> new ResponseEntity<>(result.getMessage(), HttpStatus.PRECONDITION_FAILED);
            default -> new ResponseEntity<>(result.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }
}
