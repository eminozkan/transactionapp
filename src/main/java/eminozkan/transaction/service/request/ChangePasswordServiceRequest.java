package eminozkan.transaction.service.request;

public class ChangePasswordServiceRequest {
    private String oldPassword;
    private String newPassword;
    private String newPasswordAgain;

    public String getOldPassword() {
        return oldPassword;
    }

    public ChangePasswordServiceRequest setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public ChangePasswordServiceRequest setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public String getNewPasswordAgain() {
        return newPasswordAgain;
    }

    public ChangePasswordServiceRequest setNewPasswordAgain(String newPasswordAgain) {
        this.newPasswordAgain = newPasswordAgain;
        return this;
    }
}
