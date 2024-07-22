package eminozkan.transaction.service.request;

public class UserAuthServiceRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public UserAuthServiceRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserAuthServiceRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
