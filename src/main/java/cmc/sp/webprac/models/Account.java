package cmc.sp.webprac.models;

import cmc.sp.webprac.enums.AccountStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_id;

    @ManyToOne
    @JoinColumn(name="individual_client_id", referencedColumnName="client_id")
    private IndividualClient individual_client;

    @ManyToOne
    @JoinColumn(name="entity_client_id", referencedColumnName="client_id")
    private EntityClient entity_client;

    @Column(nullable = false)
    private Timestamp creation_time;

    @Column(nullable = false)
    private BigDecimal balance;

    private String serviced_phone_number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Column(nullable = false)
    private BigDecimal credit_max;

    @Column(name = "credit_interval", columnDefinition = "interval")
    private Duration credit_interval;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<ConnectedServices> connectedServices;

    public Account() {
    }

    public Account(Integer account_id, IndividualClient individual_client, EntityClient entity_client, Timestamp creation_time, BigDecimal balance, String serviced_phone_number, AccountStatus status, BigDecimal credit_max, Duration credit_interval) {
        this.account_id = account_id;
        this.individual_client = individual_client;
        this.entity_client = entity_client;
        this.creation_time = creation_time;
        this.balance = balance;
        this.serviced_phone_number = serviced_phone_number;
        this.status = status;
        this.credit_max = credit_max;
        this.credit_interval = credit_interval;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public IndividualClient getIndividual_client() {
        return individual_client;
    }

    public void setIndividual_client(IndividualClient individual_client) {
        this.individual_client = individual_client;
    }

    public EntityClient getEntity_client() {
        return entity_client;
    }

    public void setEntity_client(EntityClient entity_client) {
        this.entity_client = entity_client;
    }

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getServiced_phone_number() {
        return serviced_phone_number;
    }

    public void setServiced_phone_number(String serviced_phone_number) {
        this.serviced_phone_number = serviced_phone_number;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public BigDecimal getCredit_max() {
        return credit_max;
    }

    public void setCredit_max(BigDecimal credit_max) {
        this.credit_max = credit_max;
    }

    public Duration getCredit_interval() {
        return credit_interval;
    }

    public void setCredit_interval(Duration credit_interval) {
        this.credit_interval = credit_interval;
    }

    public List<ConnectedServices> getConnectedServices() {
        return connectedServices;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", individual_client" + individual_client +
                ", entity_client=" + entity_client +
                ", creation_time=" + creation_time +
                ", balance=" + balance +
                ", serviced_phone_number='" + serviced_phone_number + '\'' +
                ", status='" + status + '\'' +
                ", credit_max=" + credit_max +
                ", credit_interval=" + credit_interval +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(account_id, account.account_id)
                && Objects.equals(individual_client, account.individual_client)
                && Objects.equals(entity_client, account.entity_client)
                && Objects.equals(creation_time, account.creation_time)
                && Objects.equals(balance, account.balance)
                && Objects.equals(serviced_phone_number, account.serviced_phone_number)
                && Objects.equals(status, account.status)
                && Objects.equals(credit_max, account.credit_max)
                && Objects.equals(credit_interval, account.credit_interval);
    }
}
