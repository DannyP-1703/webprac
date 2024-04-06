package cmc.sp.webprac.dao;

import cmc.sp.webprac.models.Account;
import cmc.sp.webprac.models.ConnectedServices;
import cmc.sp.webprac.models.Service;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectedServicesDAO extends AbstractDAO<ConnectedServices, Integer>{
    public ConnectedServicesDAO() {
        super(ConnectedServices.class);
    }

    public ConnectedServices getServiceConnection(Account account, Service service) {
        try (Session session = sessionFactory.openSession()) {
            var cBuilder = session.getCriteriaBuilder();
            var criteria = cBuilder.createQuery(modelClass);
            var root = criteria.from(modelClass);
            criteria.select(root).where(
                    cBuilder.equal(root.get("account"), account),
                    cBuilder.equal(root.get("service"), service)
            );
            return session.createQuery(criteria).getSingleResultOrNull();
        }
    }
}
