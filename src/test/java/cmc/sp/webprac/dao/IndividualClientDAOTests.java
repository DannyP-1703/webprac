package cmc.sp.webprac.dao;

import cmc.sp.webprac.models.IndividualClient;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringBootTest
public class IndividualClientDAOTests extends DAOTests {

    @Autowired
    private IndividualClientDAO individualClient;

    @Test
    void testGetters() {
        List<IndividualClient> allIndividualClients = individualClient.getAll();
        Assertions.assertEquals(5, allIndividualClients.size());

        IndividualClient client3 = individualClient.getById(3);
        IndividualClient expectedClient3 = new IndividualClient(
                3,
                "4035759570",
                "Гуляева",
                "Ксения",
                "Даниловна",
                "респ. Татарстан",
                "+74997714077",
                "geentbbt94@yahoo.com"
        );
        Assertions.assertEquals(expectedClient3, client3);
    }
}
