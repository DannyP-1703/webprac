package cmc.sp.webprac.dao;

import cmc.sp.webprac.filters.ServiceFilter;
import cmc.sp.webprac.models.Service;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceDAO extends AbstractDAO<Service, Integer>{
    public ServiceDAO() {
        super(Service.class);
    }

    public List<Service> getFilteredServices(ServiceFilter filter) {
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cBuilder = session.getCriteriaBuilder();
            var criteria = cBuilder.createQuery(modelClass);
            var root = criteria.from(modelClass);

            List<Predicate> predicates = new ArrayList<>();

            if (filter.getSubscriptionTypes() != null) {
                var inExpression = cBuilder.in(root.get("subscription_type").as(String.class));
                for (var type : filter.getSubscriptionTypes()) {
                    inExpression.value(type.name());
                }
                predicates.add(inExpression);
            }

            if (filter.getSearchText() != null) {
                predicates.add(cBuilder.or(
                        cBuilder.like(root.get("name"), "%" + filter.getSearchText() + "%"),
                        cBuilder.like(root.get("description"), "%" + filter.getSearchText() + "%")
                ));
            }

            if (filter.getSubscriptionFee() != null) {
                predicates.add(cBuilder.between(
                        root.get("subscription_fee"),
                        filter.getSubscriptionFee().min,
                        filter.getSubscriptionFee().max
                ));
            }

            if (!predicates.isEmpty()) {
                criteria.select(root).where(predicates.toArray(new Predicate[0]));
            }
            return session.createQuery(criteria).getResultList();
        }
    }

}
