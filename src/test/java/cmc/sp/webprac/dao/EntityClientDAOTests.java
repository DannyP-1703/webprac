package cmc.sp.webprac.dao;

import cmc.sp.webprac.filters.ClientFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
public class EntityClientDAOTests extends DAOTests {
    @Test
    void testRegistrationTime() {
        Assertions.assertEquals(
                Timestamp.valueOf("2021-03-16 19:51:59"),
                entityClient.getRegistrationTime(entityClient.getById(2))
        );
    }

    @Test
    void testFilter() {
        Assertions.assertEquals(
                List.of(entityClient.getById(3)),
                entityClient.getFiltered(
                        new ClientFilter.Builder(false, true)
                                .partOfName("ЗАО")
                                .startOfRegistrationNumber("41")
                                .region("Тюменская область")
                                .build()
                )
        );

        Assertions.assertEquals(
                List.of(entityClient.getById(4)),
                entityClient.getFiltered(
                        new ClientFilter.Builder(false, true)
                                .hasBlockedAccounts(true)
                                .build()
                )
        );



        Assertions.assertEquals(
                entityClient.getAll(),
                entityClient.getFiltered(
                        new ClientFilter.Builder(false, true)
                                .numberOfAccounts(0, 2)
                                .build()
                )
        );

        Assertions.assertEquals(
                List.of(entityClient.getById(2), entityClient.getById(5)),
                entityClient.getFiltered(
                        new ClientFilter.Builder(false, true)
                                .clientSince(Timestamp.valueOf("2018-01-01 00:00:00"), Timestamp.valueOf("2023-01-01 23:59:59"))
                                .build()
                )
        );
    }
}
