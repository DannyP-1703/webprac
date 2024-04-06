package cmc.sp.webprac.dao;

import cmc.sp.webprac.filters.OperationFilter;
import cmc.sp.webprac.models.Account;
import cmc.sp.webprac.models.Operation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OperationDAO extends AbstractDAO<Operation, Integer>{
    public OperationDAO() {
        super(Operation.class);
    }

    public List<Operation> getFilteredOperationsByAccount(Account account, OperationFilter filter) {
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cBuilder = session.getCriteriaBuilder();
            var criteria = cBuilder.createQuery(modelClass);
            var root = criteria.from(modelClass);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cBuilder.equal(root.get("account"), account));

            if (filter != null && filter.getInterval() != null) {
                predicates.add(cBuilder.between(
                        root.get("operation_time"),
                        filter.getInterval().min,
                        filter.getInterval().max
                ));
            }

            criteria.select(root).where(predicates.toArray(new Predicate[0]));

            return session.createQuery(criteria).getResultList();
        }
    }
}
