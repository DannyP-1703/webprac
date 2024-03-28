package cmc.sp.webprac.dao;

import cmc.sp.webprac.config.DatabaseInit;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DAOTests {

    @Autowired
    private DatabaseInit manager;

    @BeforeEach
    void repopulateTestDatabase() {
        manager.clear();
        manager.populate();
        System.out.println("Repopulating");
    }
}
