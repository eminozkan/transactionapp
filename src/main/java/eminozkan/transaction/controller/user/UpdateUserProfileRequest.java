package eminozkan.transaction.controller.user;

import eminozkan.transaction.model.Gender;
import eminozkan.transaction.service.request.UserProfileUpdateServiceRequest;

import java.time.LocalDate;

public record UpdateUserProfileRequest(String fullName,

                                       String idNumber,
                                       String phoneNumber,

                                       String birthDate,

                                       Gender gender) {
    UserProfileUpdateServiceRequest toServiceRequest(){
        LocalDate birth = birthDate == null ? null : LocalDate.parse(birthDate);
        return new UserProfileUpdateServiceRequest()
                .setBirthDate(birth)
                .setGender(gender)
                .setFullName(fullName)
                .setPhoneNumber(phoneNumber)
                .setIdNumber(idNumber);
    }
}
