package eminozkan.transaction.controller.auth;

import eminozkan.transaction.service.auth.AuthenticationService;
import eminozkan.transaction.util.BusinessResultHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name="Authentication Controller", description = "Authentication Endpoints")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PutMapping
    @Operation(summary = "Authentication", description = "Authentication Process")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns JWT Token"),
            @ApiResponse(responseCode = "401", description = "invalid credentials")
    }
   )
    ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request){
        var result = service.authenticate(request.toServiceRequest());
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getToken());
        }
        return BusinessResultHandler.handleResult(result);
    }
}
