package cmc.sp.webprac.dao;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public abstract class AbstractDAO<T, ID extends Serializable> {
    protected SessionFactory sessionFactory;
    protected Class<T> modelClass;

    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactoryBean) {
        this.sessionFactory = sessionFactoryBean.getObject();
    }

    public AbstractDAO(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    public T getById(final ID id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(modelClass, id);
        }
    }

    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = cBuilder.createQuery(modelClass);
            criteria.select(criteria.from(modelClass));
            return session.createQuery(criteria).getResultList();
        }
    }

}
