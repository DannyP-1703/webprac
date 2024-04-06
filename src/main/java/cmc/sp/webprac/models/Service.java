package cmc.sp.webprac.models;

import cmc.sp.webprac.enums.SubscriptionType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.hibernate.dialect.PostgreSQLIntervalSecondJdbcType;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Objects;

@Entity
@Table(name = "service")
public class Service {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer service_id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private SubscriptionType subscription_type;

    private BigDecimal activation_fee;

    private BigDecimal subscription_fee;

    private BigDecimal deactivation_fee;

    @Column(columnDefinition = "interval")
    @JdbcType(PostgreSQLIntervalSecondJdbcType.class)
    private Duration duration;

    private Short package_phone;

    private Short package_internet;

    private Short package_message;

    private String description;

    public Service() {
    }

    public Service(Integer service_id, String name, SubscriptionType subscription_type, BigDecimal activation_fee, BigDecimal subscription_fee, BigDecimal deactivation_fee, Duration duration, Short package_phone, Short package_internet, Short package_message, String description) {
        this.service_id = service_id;
        this.name = name;
        this.subscription_type = subscription_type;
        this.activation_fee = activation_fee;
        this.subscription_fee = subscription_fee;
        this.deactivation_fee = deactivation_fee;
        this.duration = duration;
        this.package_phone = package_phone;
        this.package_internet = package_internet;
        this.package_message = package_message;
        this.description = description;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubscriptionType getSubscription_type() {
        return subscription_type;
    }

    public void setSubscription_type(SubscriptionType subscription_type) {
        this.subscription_type = subscription_type;
    }

    public BigDecimal getActivation_fee() {
        return activation_fee;
    }

    public void setActivation_fee(BigDecimal activation_fee) {
        this.activation_fee = activation_fee;
    }

    public BigDecimal getSubscription_fee() {
        return subscription_fee;
    }

    public void setSubscription_fee(BigDecimal subscription_fee) {
        this.subscription_fee = subscription_fee;
    }

    public BigDecimal getDeactivation_fee() {
        return deactivation_fee;
    }

    public void setDeactivation_fee(BigDecimal deactivation_fee) {
        this.deactivation_fee = deactivation_fee;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Short getPackage_phone() {
        return package_phone;
    }

    public void setPackage_phone(Short package_phone) {
        this.package_phone = package_phone;
    }

    public Short getPackage_internet() {
        return package_internet;
    }

    public void setPackage_internet(Short package_internet) {
        this.package_internet = package_internet;
    }

    public Short getPackage_message() {
        return package_message;
    }

    public void setPackage_message(Short package_message) {
        this.package_message = package_message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Service{" +
                "service_id=" + service_id +
                ", name='" + name + '\'' +
                ", subscription_type='" + subscription_type + '\'' +
                ", activation_fee=" + activation_fee +
                ", subscription_fee=" + subscription_fee +
                ", deactivation_fee=" + deactivation_fee +
                ", duration=" + duration +
                ", package_phone=" + package_phone +
                ", package_internet=" + package_internet +
                ", package_message=" + package_message +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(service_id, service.service_id) && Objects.equals(name, service.name) && Objects.equals(subscription_type, service.subscription_type) && Objects.equals(activation_fee, service.activation_fee) && Objects.equals(subscription_fee, service.subscription_fee) && Objects.equals(deactivation_fee, service.deactivation_fee) && Objects.equals(duration, service.duration) && Objects.equals(package_phone, service.package_phone) && Objects.equals(package_internet, service.package_internet) && Objects.equals(package_message, service.package_message) && Objects.equals(description, service.description);
    }
}
