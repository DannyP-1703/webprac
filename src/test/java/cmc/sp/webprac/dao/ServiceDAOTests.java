package cmc.sp.webprac.dao;

import cmc.sp.webprac.enums.SubscriptionType;
import cmc.sp.webprac.filters.ServiceFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class ServiceDAOTests extends DAOTests {
    @Test
    void testGetFilteredServices() {
        Assertions.assertEquals(
                List.of(service.getById(3)),
                service.getFilteredServices(
                        new ServiceFilter.Builder()
                        .searchText("Yandex")
                        .build()
                )
        );

        Assertions.assertEquals(
                List.of(service.getById(1)),
                service.getFilteredServices(
                        new ServiceFilter.Builder()
                                .searchText("Тариф")
                                .subsctiptionFee(new BigDecimal(0), new BigDecimal(1000))
                                .build()
                )
        );

        Assertions.assertEquals(
                List.of(service.getById(2)),
                service.getFilteredServices(
                        new ServiceFilter.Builder()
                                .subscriptionTypes(List.of(SubscriptionType.ONETIME))
                                .build()
                )
        );
    }
}
