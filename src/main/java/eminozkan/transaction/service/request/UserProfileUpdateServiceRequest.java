package eminozkan.transaction.service.request;

import eminozkan.transaction.model.Gender;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public class UserProfileUpdateServiceRequest {
    private String fullName;

    private String idNumber;

    private String phoneNumber;

    private LocalDate birthDate;

    private Gender gender;

    public String getFullName() {
        return fullName;
    }

    public UserProfileUpdateServiceRequest setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public UserProfileUpdateServiceRequest setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserProfileUpdateServiceRequest setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public UserProfileUpdateServiceRequest setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public UserProfileUpdateServiceRequest setGender(Gender gender) {
        this.gender = gender;
        return this;
    }
}
