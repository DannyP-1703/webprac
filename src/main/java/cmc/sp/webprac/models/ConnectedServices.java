package cmc.sp.webprac.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "connected_service")
public class ConnectedServices {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer connection_id;

    @ManyToOne
    @JoinColumn(nullable = false, name="account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(nullable = false, name="service_id")
    private Service service;

    @Column(nullable = false)
    private Timestamp connection_time;

    public ConnectedServices() {
    }

    public ConnectedServices(Integer connection_id, Account account, Service service, Timestamp connection_time) {
        this.connection_id = connection_id;
        this.account = account;
        this.service = service;
        this.connection_time = connection_time;
    }

    public Integer getConnection_id() {
        return connection_id;
    }

    public void setConnection_id(Integer connection_id) {
        this.connection_id = connection_id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Timestamp getConnection_time() {
        return connection_time;
    }

    public void setConnection_time(Timestamp connection_time) {
        this.connection_time = connection_time;
    }

    @Override
    public String toString() {
        return "ConnectedServices{" +
                "connection_id=" + connection_id +
                ", account_id=" + account.getAccount_id() +
                ", service_id=" + service.getService_id() +
                ", connection_time=" + connection_time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectedServices that = (ConnectedServices) o;
        return Objects.equals(connection_id, that.connection_id)
                && Objects.equals(account, that.account)
                && Objects.equals(service, that.service)
                && Objects.equals(connection_time, that.connection_time);
    }
}
