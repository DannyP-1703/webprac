package cmc.sp.webprac.dao;

import cmc.sp.webprac.filters.OperationFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
public class OperationDAOTests extends DAOTests {
    @Test
    void testGetOperationsByAccount() {
        Assertions.assertEquals(
                List.of(operation.getById(10), operation.getById(13)),
                operation.getFilteredOperationsByAccount(account.getById(7), null)
        );
    }

    @Test
    void testGetFilteredOperations() {
        var filter = new OperationFilter.Builder()
                .interval(Timestamp.valueOf("2022-05-27 00:00:00"), Timestamp.valueOf("2023-05-27 00:00:00"))
                .build();

        Assertions.assertEquals(
                List.of(operation.getById(8), operation.getById(9)),
                operation.getFilteredOperationsByAccount(account.getById(6), filter)
        );
    }
}
