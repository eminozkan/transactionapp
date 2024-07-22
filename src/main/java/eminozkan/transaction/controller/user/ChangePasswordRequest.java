package eminozkan.transaction.controller.user;

import eminozkan.transaction.service.request.ChangePasswordServiceRequest;

public record ChangePasswordRequest(
        String oldPassword,
        String newPassword,
        String newPasswordAgain
) {
    ChangePasswordServiceRequest toServiceRequest(){
        return new ChangePasswordServiceRequest()
                .setOldPassword(oldPassword)
                .setNewPassword(newPassword)
                .setNewPasswordAgain(newPasswordAgain);
    }
}
