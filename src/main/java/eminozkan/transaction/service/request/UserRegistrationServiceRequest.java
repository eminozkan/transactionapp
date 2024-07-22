package eminozkan.transaction.service.request;

import eminozkan.transaction.model.Gender;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public class UserRegistrationServiceRequest {

    @NotNull
    private String username;

    @NotNull
    private String fullName;

    private String idNumber;

    private String phoneNumber;

    private LocalDate birthDate;

    private Gender gender;

    @NotNull
    @Length(min = 8)
    private String rawPassword;

    public String getUsername() {
        return username;
    }

    public UserRegistrationServiceRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public UserRegistrationServiceRequest setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegistrationServiceRequest setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public UserRegistrationServiceRequest setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegistrationServiceRequest setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public UserRegistrationServiceRequest setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public UserRegistrationServiceRequest setGender(Gender gender) {
        this.gender = gender;
        return this;
    }
}
