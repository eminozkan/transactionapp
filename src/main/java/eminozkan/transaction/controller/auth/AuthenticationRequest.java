package eminozkan.transaction.controller.auth;

import eminozkan.transaction.service.request.UserAuthServiceRequest;

public record AuthenticationRequest(
        String username,
        String password
) {
    UserAuthServiceRequest toServiceRequest(){
        return new UserAuthServiceRequest()
                .setPassword(password)
                .setUsername(username);
    }
}
