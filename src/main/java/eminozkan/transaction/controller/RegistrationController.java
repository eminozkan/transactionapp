package eminozkan.transaction.controller;

import eminozkan.transaction.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import eminozkan.transaction.util.BusinessResultHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Registration Controller", description = "Handles User Register Operations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register User", description = "User Registration Process")
    ResponseEntity<?> register(@RequestBody RegistrationRequest request){
        var result = registrationService.register(request.toServiceRequest());
        if (result.isSuccess()){
            return ResponseEntity.status(201)
                    .body(result.getMessage());
        }

        return BusinessResultHandler.handleResult(result);
    }
}
