package cmc.sp.webprac.dao;

import cmc.sp.webprac.enums.AccountStatus;
import cmc.sp.webprac.filters.ClientFilter;
import cmc.sp.webprac.models.IndividualClient;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IndividualClientDAO extends AbstractDAO<IndividualClient, Integer> {
    public IndividualClientDAO() {
        super(IndividualClient.class);
    }

    public Timestamp getRegistrationTime(IndividualClient client) {
        Timestamp registrationTime = Timestamp.valueOf("9999-12-12 23:59:59");
        for (var acc : client.getAccounts()) {
            if (registrationTime.compareTo(acc.getCreation_time()) >= 0) {
                registrationTime = acc.getCreation_time();
            }
        }
        return registrationTime;
    }

    public List<IndividualClient> getFiltered(ClientFilter filter) {
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cBuilder = session.getCriteriaBuilder();
            CriteriaQuery<IndividualClient> criteria = cBuilder.createQuery(modelClass);
            var root = criteria.from(modelClass);

            List<Predicate> predicates = new ArrayList<>();
            if (filter.getPartOfName() != null) {
                predicates.add(cBuilder.like(root.get("fullName"), "%" + filter.getPartOfName() + "%"));
            }
            if (filter.getStartOfRegistrationNumber() != null) {
                predicates.add(cBuilder.like(root.get("passport"), filter.getStartOfRegistrationNumber() + "%"));
            }
            if (filter.getRegion() != null) {
                predicates.add(cBuilder.like(root.get("region"), "%" + filter.getRegion() + "%"));
            }

            if (!predicates.isEmpty()) {
                criteria.select(root).where(predicates.toArray(new Predicate[0]));
            }
            var filteredClients = session.createQuery(criteria).getResultList();

            if (filter.getNumberOfAccounts() != null) {
                filteredClients = filteredClients.stream().filter(
                        (client) -> {
                            var numberOfAccounts = client.getAccounts().size();
                            return numberOfAccounts >= filter.getNumberOfAccounts().min
                                    && numberOfAccounts <= filter.getNumberOfAccounts().max;
                        }
                ).toList();
            }

            if (filter.getHasBlockedAccounts() != null) {
                filteredClients = filteredClients.stream().filter(
                        (client) -> {
                            for (var acc : client.getAccounts()) {
                                if (acc.getStatus() == AccountStatus.BLOCKED) {
                                    return true;
                                }
                            }
                            return false;
                        }
                ).toList();
            }

            if (filter.getClientSince() != null) {
                filteredClients = filteredClients.stream().filter(
                        (client) -> {
                            var registrationTime = this.getRegistrationTime(client);
                            return registrationTime.compareTo(filter.getClientSince().min) >= 0
                                    && registrationTime.compareTo(filter.getClientSince().max) <= 0;
                        }
                ).toList();
            }

            return filteredClients;
        }

    }
}
