package cmc.sp.webprac.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="entity_client")
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

    @OneToMany(mappedBy = "entity_client", fetch = FetchType.EAGER)
    private List<Account> accounts;

    public EntityClient() {
    }

    public EntityClient(Integer client_id, String registration_number, String name, String region, String address, String contact_surname, String contact_name, String contact_patronymic, String contact_phone_number, String email) {
        this.client_id = client_id;
        this.registration_number = registration_number;
        this.name = name;
        this.region = region;
        this.address = address;
        this.contact_surname = contact_surname;
        this.contact_name = contact_name;
        this.contact_patronymic = contact_patronymic;
        this.contact_phone_number = contact_phone_number;
        this.email = email;
        this.accounts = null;
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

    public List<Account> getAccounts() {
        return accounts;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityClient that = (EntityClient) o;
        return Objects.equals(client_id, that.client_id)
                && Objects.equals(registration_number, that.registration_number)
                && Objects.equals(name, that.name)
                && Objects.equals(region, that.region)
                && Objects.equals(address, that.address)
                && Objects.equals(contact_surname, that.contact_surname)
                && Objects.equals(contact_name, that.contact_name)
                && Objects.equals(contact_patronymic, that.contact_patronymic)
                && Objects.equals(contact_phone_number, that.contact_phone_number)
                && Objects.equals(email, that.email);
    }
}
