package com.krishna.hibernate.dao;
import com.krishna.hibernate.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO {

    SessionFactory sessionFactory;

    public CustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Persis
     * It should be there inside of transaction or else it won't persist data
     * {@link PersistentObjectException} - when pass detached entity to persist method
     */
    public void persist(Customer object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.persist(object);
//        session.evict(object);
//        session.persist(object);
        transaction.commit();
        session.close();
    }

    /**
     * Save
     * Purpose for persist entity same as like persist method but if pass detached entity, it will try to save as duplicate entry.
     * @param object
     */
    public void save(Customer object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Long id1 = (Long) session.save(object);
        session.evict(object);
        Long id2 = (Long) session.save(object);
        transaction.commit();
        session.close();
    }

    /**
     * merge
     * Purpose for update persistent entity instance value when pass detached entity and not like save for duplicate entry.
     * @param object
     */
    public void merge(Customer object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Long id1 = (Long) session.save(object);
        session.evict(object);
        object.setName("nameupdated");
        Object merge = session.merge(object);
        transaction.commit();
        session.close();
    }

    /**
     * update
     * Purpose for update persistent entity instance value when pass detached entity and not like save for duplicate entry.
     * {@link PersistentObjectException} - when pass transient entity to update method
     * @param object
     */
    public void update(Customer object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Long id1 = (Long) session.save(object);
        session.evict(object);
        object.setName("nameupdated");
        session.update(object);
        transaction.commit();
        session.close();
    }

    /**
     * saveOrUpdate
     * Purpose for save or update and also pass detached entity
     * @param object
     */
    public void saveOrUpdate(Customer object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.saveOrUpdate(object);
        transaction.commit();
        session.close();
    }
}
