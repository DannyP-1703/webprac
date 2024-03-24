package cmc.sp.webprac.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Duration;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer operation_id;

    @Column(nullable = false)
    private Timestamp operation_time;

    @Column(nullable = false)
    private Integer account_id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private BigDecimal money_amount;

    private Integer service_id;

    public Operation() {
    }

    public Integer getOperation_id() {
        return operation_id;
    }

    public void setOperation_id(Integer operation_id) {
        this.operation_id = operation_id;
    }

    public Timestamp getOperation_time() {
        return operation_time;
    }

    public void setOperation_time(Timestamp operation_time) {
        this.operation_time = operation_time;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMoney_amount() {
        return money_amount;
    }

    public void setMoney_amount(BigDecimal money_amount) {
        this.money_amount = money_amount;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operation_id=" + operation_id +
                ", operation_time=" + operation_time +
                ", account_id=" + account_id +
                ", type='" + type + '\'' +
                ", money_amount=" + money_amount +
                ", service_id=" + service_id +
                '}';
    }
}
