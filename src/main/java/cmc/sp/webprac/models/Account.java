package cmc.sp.webprac.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Duration;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_id;

    private Integer individual_client_id;

    private Integer entity_client_id;

    @Column(nullable = false)
    private Timestamp creation_time;

    @Column(nullable = false)
    private BigDecimal balance;

    private String serviced_phone_number;

    @Column(nullable = false)
    private String status;

    private BigDecimal credit_max;

    @Column(name = "credit_interval", columnDefinition = "interval")
    private Duration credit_interval;

    public Account() {
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public Integer getIndividual_client_id() {
        return individual_client_id;
    }

    public void setIndividual_client_id(Integer individual_client_id) {
        this.individual_client_id = individual_client_id;
    }

    public Integer getEntity_client_id() {
        return entity_client_id;
    }

    public void setEntity_client_id(Integer entity_client_id) {
        this.entity_client_id = entity_client_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", individual_client_id=" + individual_client_id +
                ", entity_client_id=" + entity_client_id +
                ", creation_time=" + creation_time +
                ", balance=" + balance +
                ", serviced_phone_number='" + serviced_phone_number + '\'' +
                ", status='" + status + '\'' +
                ", credit_max=" + credit_max +
                ", credit_interval=" + credit_interval +
                '}';
    }
}
