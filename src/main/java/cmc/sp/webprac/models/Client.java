package cmc.sp.webprac.models;

import java.util.List;

public class Client {
    private Integer client_id;
    private ClientType type;
    private String name;
    private String registration_number;
    private String region;
    private String contact_phone_number;
    private String email;
    private List<Account> accounts;

    public Client() {
    }

    enum ClientType {
        INDIVIDUAL(0),
        ENTITY(1);
        private final Integer value;

        ClientType(Integer value) {
            this.value = value;
        }

        public Integer value() {
            return value;
        }
    }

    public Client(IndividualClient individualClient) {
        this.client_id = individualClient.getClient_id();
        this.type = ClientType.INDIVIDUAL;
        this.name = individualClient.getFullName();
        this.registration_number = individualClient.getPassport();
        this.region = individualClient.getRegion();
        this.contact_phone_number = individualClient.getContact_phone_number();
        this.email = individualClient.getEmail();
        this.accounts = individualClient.getAccounts();
    }

    public Client(EntityClient entityClient) {
        this.client_id = entityClient.getClient_id();
        this.type = ClientType.ENTITY;
        this.name = entityClient.getName();
        this.registration_number = entityClient.getRegistration_number();
        this.region = entityClient.getRegion();
        this.contact_phone_number = entityClient.getContact_phone_number();
        this.email = entityClient.getEmail();
        this.accounts = entityClient.getAccounts();
    }

    public Integer getType() {
        return type.value();
    }

    public Integer getClient_id() {
        return client_id;
    }

    public String getName() {
        return name;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public String getRegion() {
        return region;
    }

    public String getContact_phone_number() {
        return contact_phone_number;
    }

    public String getEmail() {
        return email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setContact_phone_number(String contact_phone_number) {
        this.contact_phone_number = contact_phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


}
