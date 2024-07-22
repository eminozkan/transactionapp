package eminozkan.transaction.controller;

import eminozkan.transaction.model.Gender;
import eminozkan.transaction.service.request.UserRegistrationServiceRequest;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record RegistrationRequest(
        @NotNull
        String username,
        String idNumber,
        @NotNull
        @Length(min = 8)
        String password,
        String fullName,
        String phoneNumber,
        Gender gender,
        String birthDate
) {

    UserRegistrationServiceRequest toServiceRequest() {
        return new UserRegistrationServiceRequest()
                .setUsername(username)
                .setIdNumber(idNumber)
                .setRawPassword(password)
                .setFullName(fullName)
                .setPhoneNumber(phoneNumber)
                .setGender(gender)
                .setBirthDate(LocalDate.parse(birthDate));
    }
}
