package com.example.neurodiagnosis.domain.entities;

import com.example.neurodiagnosis.domain.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name="users", uniqueConstraints=
@UniqueConstraint(columnNames={"emailAddress", "username"}))
@NamedQueries({
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.emailAddress = :email"),
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "User.countUsers", query = "SELECT u FROM User u"),
})
@Getter
@Setter
public class User extends BaseEntity {
    private String emailAddress;
    private String passwordHash;
    private Boolean handedness;
    private String phoneNumber;
    private String firstName;
    private String username;
    private String lastName;
    private Boolean gender;

    // Address info: fields used in test
    private String country;
    private String province;
    private String city;
    private int age;
    private String marriedStatus;
    private String ethnicity;

    public User(UUID id, String emailAddress, String passwordHash, Boolean handedness, String phoneNumber, String firstName, String username, String lastName, Boolean gender, String country, String province, String city, int age, String marriedStatus, String ethnicity, String mriScanResult) {
        super(id);
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
        this.handedness = handedness;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.username = username;
        this.lastName = lastName;
        this.gender = gender;
        this.country = country;
        this.province = province;
        this.city = city;
        this.age = age;
        this.marriedStatus = marriedStatus;
        this.ethnicity = ethnicity;
        MriScanResult = mriScanResult;
    }

    private String MriScanResult;

    public User (UUID userId) {
        super(userId);
    }

    public User(UUID id, String emailAddress, String passwordHash, Boolean handedness, String phoneNumber, String firstName, String username, String lastName, Boolean gender, String country, String province, String city, String ethnicity) {
        super(id);
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
        this.handedness = handedness;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.username = username;
        this.lastName = lastName;
        this.gender = gender;
        this.country = country;
        this.province = province;
        this.city = city;
        this.ethnicity = ethnicity;
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

    @Override
    public String toString() {
        return "User{" +
                "emailAddress='" + emailAddress + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", handedness=" + handedness +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", ethnicity='" + ethnicity + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return emailAddress.equals(user.emailAddress) && passwordHash.equals(user.passwordHash) && Objects.equals(handedness, user.handedness) && Objects.equals(phoneNumber, user.phoneNumber) && firstName.equals(user.firstName) && username.equals(user.username) && lastName.equals(user.lastName)&& Objects.equals(gender, user.gender) && Objects.equals(country, user.country) && Objects.equals(province, user.province) && Objects.equals(city, user.city) && Objects.equals(ethnicity, user.ethnicity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, passwordHash, handedness, phoneNumber, firstName, username, lastName, gender, country, province, city, ethnicity);
    }
    public String getMriScanResult() {
        return MriScanResult;
    }

    public void setMriScanResult(String mriScanResult) {
        MriScanResult = mriScanResult;
    }

}
