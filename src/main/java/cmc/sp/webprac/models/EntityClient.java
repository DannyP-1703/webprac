package cmc.sp.webprac.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class EntityClient {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer client_id;

    @Column(nullable = false, columnDefinition = "CHAR[13]")
    private String registration_number;

    @Column(nullable = false, columnDefinition = "VARCHAR[100]")
    private String name;

    @Column(nullable = false, columnDefinition = "VARCHAR[100]")
    private String region;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false, columnDefinition = "VARCHAR[100]")
    private String contact_surname;

    @Column(nullable = false, columnDefinition = "VARCHAR[100]")
    private String contact_name;

    @Column(columnDefinition = "VARCHAR[100]")
    private String contact_patronymic;

    @Column(nullable = false, columnDefinition = "CHAR[12]")
    private String contact_phone_number;

    @Column(columnDefinition = "VARCHAR[100]")
    private String email;

    public EntityClient() {
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_surname() {
        return contact_surname;
    }

    public void setContact_surname(String contact_surname) {
        this.contact_surname = contact_surname;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_patronymic() {
        return contact_patronymic;
    }

    public void setContact_patronymic(String contact_patronymic) {
        this.contact_patronymic = contact_patronymic;
    }

    public String getContact_phone_number() {
        return contact_phone_number;
    }

    public void setContact_phone_number(String contact_phone_number) {
        this.contact_phone_number = contact_phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EntityClient{" +
                "client_id=" + client_id +
                ", registration_number='" + registration_number + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", contact_surname='" + contact_surname + '\'' +
                ", contact_phone_number='" + contact_phone_number + '\'' +
                '}';
    }
}
