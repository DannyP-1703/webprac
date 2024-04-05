package cmc.sp.webprac.dao;

import cmc.sp.webprac.models.Account;
import cmc.sp.webprac.models.EntityClient;
import cmc.sp.webprac.models.IndividualClient;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAO extends AbstractDAO<Account, Integer> {
    public AccountDAO() {
        super(Account.class);
    }

    public List<Account> getAccountsByIndividualClient(IndividualClient client) {
        try (Session session = sessionFactory.openSession()) {
            var cBuilder = session.getCriteriaBuilder();
            var criteria = cBuilder.createQuery(modelClass);
            var root = criteria.from(modelClass);
            criteria.select(root).where(cBuilder.equal(root.get("individual_client"), client));
            return session.createQuery(criteria).getResultList();
        }
    }

    public List<Account> getAccountsByEntityClient(EntityClient client) {
        try (Session session = sessionFactory.openSession()) {
            var cBuilder = session.getCriteriaBuilder();
            var criteria = cBuilder.createQuery(modelClass);
            var root = criteria.from(modelClass);
            criteria.select(root).where(cBuilder.equal(root.get("entity_client"), client));
            return session.createQuery(criteria).getResultList();
        }
    }
}
