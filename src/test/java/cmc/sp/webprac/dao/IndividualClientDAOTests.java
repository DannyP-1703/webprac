package cmc.sp.webprac.dao;

import cmc.sp.webprac.models.IndividualClient;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringBootTest
public class IndividualClientDAOTests {
    @Autowired
    private IndividualClientDAO individualClient;

    @Test
    void testGetter() {
        List<IndividualClient> allIndividualClients = individualClient.getAll();
        Assertions.assertEquals(0, allIndividualClients.size());
    }
}
