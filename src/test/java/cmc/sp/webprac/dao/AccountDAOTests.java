package cmc.sp.webprac.dao;

import cmc.sp.webprac.models.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AccountDAOTests extends DAOTests {
    @Test
    void testGetAccountsByClient() {
        Assertions.assertEquals(
                List.of(account.getById(3), account.getById(1)),
                account.getAccountsByIndividualClient(individualClient.getById(3))
        );

        Assertions.assertEquals(
                new ArrayList<Account>(),
                account.getAccountsByEntityClient(entityClient.getById(1))
        );
    }
}
