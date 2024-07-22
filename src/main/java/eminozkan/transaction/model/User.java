package eminozkan.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Table(name = "users")
@Schema(name = "User Model", description = "Represents Users as Java class")
public class User implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(name = "userId", description = "User Id",example = "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
    private String userId;

    @Column(name = "user_name",unique = true)
    @Schema(name = "username", description = "User Name", example = "eminozkan")
    private String username;


    @JsonIgnore
    @Column(name = "user_password")
    @Schema(name = "passwordHash", description = "Hashed Password",example = "$2a$08$rVVqChnvltIub4bUI0QGDeicwgkbupX6Pc1zy7VBCJT7XH7AUfCJi")
    private String passwordHash;

    @Column(name = "full_name")
    @Schema(name = "fullname",description = "Person's Full Name", example = "Emin Özkan")
    private String fullName;

    @Column(name = "id_number")
    @Schema(name = "idNumber", description = "Person's citizenship id number", example = "25512962530")
    private String idNumber;

    @Column(name = "phone_number")
    @Schema(name = "phoneNumber", description = "Person's phone number", example = "+905350624914")
    private String phoneNumber;

    @Column(name = "gender")
    @Schema(name = "gender", description = "Person's gender",example = "Male")
    private Gender gender;

    @Column(name = "birth_date")
    @Schema(name = "birthDate", description = "Person's birth date", example = "02/10/2001")
    private LocalDate birthDate;

    public String getUserId() {
        return userId;
    }

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public User setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public User setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public User setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
