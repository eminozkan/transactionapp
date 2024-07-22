package eminozkan.transaction.controller.user;

import eminozkan.transaction.model.UserSession;
import eminozkan.transaction.service.user.UserService;
import eminozkan.transaction.util.BusinessResultHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name="User Controller", description = "Manages User Read,Update,Delete Operations")
public class UserController {

    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Update User Profile", description = "Updates user profile")
    ResponseEntity<?> updateProfile(UserSession session, @RequestBody UpdateUserProfileRequest request){
        var result = userService.updateUserProfile(session.userId(),request.toServiceRequest());
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getMessage());
        }
        return BusinessResultHandler.handleResult(result);
    }

    @GetMapping
    @Operation(summary = "Get User Profile", description = "Gets User Profile")
    ResponseEntity<?> getProfile(UserSession session){
        var user = userService.getProfile(session.userId());
        return ResponseEntity.ok(user);
    }

    @DeleteMapping
    @Operation(summary = "Delete User Profile", description = "Deletes user's profile")
    ResponseEntity<?> deleteProfile(UserSession session){
        userService.deleteProfile(session.userId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(summary = "Change Password", description = "Changes User Password")
    ResponseEntity<?> changePassword(UserSession session, @RequestBody ChangePasswordRequest request){
        var result = userService.changePassword(session.userId(), request.toServiceRequest());
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getMessage());
        }
        return BusinessResultHandler.handleResult(result);
    }

}
