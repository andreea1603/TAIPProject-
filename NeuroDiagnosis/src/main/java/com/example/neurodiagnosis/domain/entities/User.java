package com.example.neurodiagnosis.domain.entities;

import com.example.neurodiagnosis.domain.shared.BaseEntity;
import jakarta.persistence.*;


import java.util.Date;
import java.util.UUID;
@Entity
@Table(name="users", uniqueConstraints=
@UniqueConstraint(columnNames={"emailAddress", "username"}))
@NamedQueries({
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.emailAddress = :email"),
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
})
public class User extends BaseEntity {
    private String emailAddress;
    private String passwordHash;
    private Boolean handedness;
    private String phoneNumber;
    private String firstName;
    private String username;
    private String lastName;
    private Date birthDate;
    private Boolean gender;

    public User (UUID userId) {
        super(userId);
    }

    public User(String emailAddress, String passwordHash, Boolean handedness, String phoneNumber, String firstName, String username, String lastName, Date birthDate, Boolean gender, UUID userId) {
        super(userId);

        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
        this.handedness = handedness;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.username = username;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public User(String emailAddress, String firstName, String username, String lastName, UUID userId) {
        super(userId);

        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.username = username;
        this.lastName = lastName;
    }

    public User() {
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }


    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Boolean getHandedness() {
        return handedness;
    }

    public void setHandedness(Boolean handedness) {
        this.handedness = handedness;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
}
