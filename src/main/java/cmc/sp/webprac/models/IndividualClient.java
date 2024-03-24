package cmc.sp.webprac.models;

import jakarta.persistence.*;

@Entity
@Table(name = "individual_client")
public class IndividualClient {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer client_id;

    @Column(nullable = false, columnDefinition = "CHAR[10]")
    private String passport;

    @Column(nullable = false, columnDefinition = "VARCHAR[100]")
    private String surname;

    @Column(nullable = false, columnDefinition = "VARCHAR[100]")
    private String name;

    @Column(columnDefinition = "VARCHAR[100]")
    private String patronymic;

    @Column(nullable = false, columnDefinition = "VARCHAR[100]")
    private String region;

    @Column(nullable = false, columnDefinition = "CHAR[12]")
    private String contact_phone_number;

    @Column(columnDefinition = "VARCHAR[100]")
    private String email;

    public IndividualClient() {
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
        return "Individual{" +
                "client_id=" + client_id +
                ", passport='" + passport + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", region='" + region + '\'' +
                ", contact_phone_number='" + contact_phone_number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
