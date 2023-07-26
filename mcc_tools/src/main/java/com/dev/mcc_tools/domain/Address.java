package com.dev.mcc_tools.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Date;


@Entity
@Table(name = "addresses")
@CrossOrigin
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int addressID;
    @NotBlank
    private String address1;
    private String address2;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String zipCode;
    @NotBlank
    private int profileID;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm")
    private Date date_created;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm")
    private Date date_updated;

    @PrePersist
    protected void onCreate(){
        this.date_created = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.date_updated = new Date();
    }
    public int getAddressID() {
        return addressID;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public int getProfileID() {
        return profileID;
    }

    public Date getDate_created() {
        return date_created;
    }

    public Date getDate_updated() {
        return date_updated;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public void setDate_updated(Date date_updated) {
        this.date_updated = date_updated;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", profileID=" + profileID +
                ", date_created=" + date_created +
                ", date_updated=" + date_updated +
                '}';
    }
}