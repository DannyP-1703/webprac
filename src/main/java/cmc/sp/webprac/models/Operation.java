package cmc.sp.webprac.models;

import cmc.sp.webprac.enums.OperationType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer operation_id;

    @Column(nullable = false)
    private Timestamp operation_time;

    @ManyToOne
    @JoinColumn(name="account_id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationType type;

    private BigDecimal money_amount;

    @ManyToOne
    @JoinColumn(name="service_id")
    private Service service;

    public Operation() {
    }

    public Operation(Integer operation_id, Timestamp operation_time, Account account, OperationType type, BigDecimal money_amount, Service service) {
        this.operation_id = operation_id;
        this.operation_time = operation_time;
        this.account = account;
        this.type = type;
        this.money_amount = money_amount;
        this.service = service;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public BigDecimal getMoney_amount() {
        return money_amount;
    }

    public void setMoney_amount(BigDecimal money_amount) {
        this.money_amount = money_amount;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operation_id=" + operation_id +
                ", operation_time=" + operation_time +
                ", account=" + account +
                ", type='" + type + '\'' +
                ", money_amount=" + money_amount +
                ", service=" + service +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(operation_id, operation.operation_id)
                && Objects.equals(operation_time, operation.operation_time)
                && Objects.equals(account, operation.account)
                && type == operation.type
                && Objects.equals(money_amount, operation.money_amount)
                && Objects.equals(service, operation.service);
    }
}
