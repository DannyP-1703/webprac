package cmc.sp.webprac.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConnectedServicesDAOTests extends DAOTests {
    @Test
    void getServiceConnection() {
        Assertions.assertEquals(
            connectedServices.getById(1),
            connectedServices.getServiceConnection(account.getById(6), service.getById(2))
        );

        Assertions.assertNull(
                connectedServices.getServiceConnection(account.getById(2), service.getById(1))
        );
    }
}
