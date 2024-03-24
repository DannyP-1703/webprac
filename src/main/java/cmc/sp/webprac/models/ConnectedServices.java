package cmc.sp.webprac.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "connected_service")
public class ConnectedServices {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer connection_id;

    @Column(nullable = false)
    private Integer account_id;

    @Column(nullable = false)
    private Integer service_id;

    @Column(nullable = false)
    private Timestamp connection_time;

    private Timestamp disconnection_time;

    public ConnectedServices() {
    }

    public Integer getConnection_id() {
        return connection_id;
    }

    public void setConnection_id(Integer connection_id) {
        this.connection_id = connection_id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public Timestamp getConnection_time() {
        return connection_time;
    }

    public void setConnection_time(Timestamp connection_time) {
        this.connection_time = connection_time;
    }

    public Timestamp getDisconnection_time() {
        return disconnection_time;
    }

    public void setDisconnection_time(Timestamp disconnection_time) {
        this.disconnection_time = disconnection_time;
    }

    @Override
    public String toString() {
        return "ConnectedServices{" +
                "connection_id=" + connection_id +
                ", account_id=" + account_id +
                ", service_id=" + service_id +
                ", connection_time=" + connection_time +
                ", disconnection_time=" + disconnection_time +
                '}';
    }
}
