package cmc.sp.webprac.dao;

import cmc.sp.webprac.models.IndividualClient;
import org.springframework.stereotype.Repository;

@Repository
public class IndividualClientDAO extends AbstractDAO<IndividualClient, Integer> {
    public IndividualClientDAO() {
        super(IndividualClient.class);
    }
}
