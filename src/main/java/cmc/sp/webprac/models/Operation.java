package cmc.sp.webprac.models;

import cmc.sp.webprac.enums.OperationType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Duration;
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

    @Column(nullable = false)
    private Integer account_id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationType type;

    @Column(nullable = true)
    private BigDecimal money_amount;

    private Integer service_id;

    public Operation() {
    }

    public Operation(Integer operation_id, Timestamp operation_time, Integer account_id, OperationType type, BigDecimal money_amount, Integer service_id) {
        this.operation_id = operation_id;
        this.operation_time = operation_time;
        this.account_id = account_id;
        this.type = type;
        this.money_amount = money_amount;
        this.service_id = service_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(operation_id, operation.operation_id) && Objects.equals(operation_time, operation.operation_time) && Objects.equals(account_id, operation.account_id) && type == operation.type && Objects.equals(money_amount, operation.money_amount) && Objects.equals(service_id, operation.service_id);
    }
}
