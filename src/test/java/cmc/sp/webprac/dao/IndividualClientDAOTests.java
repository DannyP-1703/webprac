package cmc.sp.webprac.dao;

import cmc.sp.webprac.filters.ClientFilter;
import cmc.sp.webprac.models.IndividualClient;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class IndividualClientDAOTests extends DAOTests {

    @Test
    void testGetAccounts() {
        Assertions.assertEquals(
                List.of(account.getById(3), account.getById(1)),
                individualClient.getById(3).getAccounts()
        );
    }


    @Test
    void testRegistrationTime() {
        Assertions.assertEquals(
                Timestamp.valueOf("2022-10-07 08:55:45"),
                individualClient.getRegistrationTime(individualClient.getById(5))
        );
    }

    @Test
    void testFilter() {
        Assertions.assertEquals(
                List.of(individualClient.getById(2)),
                individualClient.getFiltered(
                        new ClientFilter.Builder(true, false)
                        .partOfName("Макарович")
                        .startOfRegistrationNumber("451")
                        .region("Москва")
                        .build()
                )
        );

        Assertions.assertEquals(
                List.of(individualClient.getById(2)),
                individualClient.getFiltered(
                        new ClientFilter.Builder(true, false)
                                .partOfName("Макарович")
                                .startOfRegistrationNumber("451")
                                .region("Москва")
                                .build()
                )
        );

        Assertions.assertEquals(
                0,
                individualClient.getFiltered(
                        new ClientFilter.Builder(true, false)
                                .hasBlockedAccounts(true)
                                .build()
                ).size()
        );

        Assertions.assertEquals(
                List.of(individualClient.getById(3)),
                individualClient.getFiltered(
                        new ClientFilter.Builder(true, false)
                                .numberOfAccounts(2, 2)
                                .build()
                )
        );

        Assertions.assertEquals(
                List.of(individualClient.getById(1)),
                individualClient.getFiltered(
                        new ClientFilter.Builder(true, false)
                                .clientSince(Timestamp.valueOf("2018-01-01 00:00:00"), Timestamp.valueOf("2020-12-31 23:59:59"))
                                .build()
                )
        );
    }
}
