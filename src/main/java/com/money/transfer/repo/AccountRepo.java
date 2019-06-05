package com.money.transfer.repo;

import com.money.transfer.entity.Account;
import com.money.transfer.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import static com.money.transfer.config.HibernateConfig.getSessionFactory;

public class AccountRepo {

    SessionFactory sessionFactory = getSessionFactory();


    public void saveAccount(Account account){
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public Account findAccountByAccountNumber(long accountNumber) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Account> cr = cb.createQuery(Account.class);
        Root<Account> root = cr.from(Account.class);
        cr.select(root).where(cb.equal(root.get("accountNumber"), accountNumber));
        Query<Account> query = session.createQuery(cr);
        return query.getSingleResult();
    }

    public List<Account> getAllAccount() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Account> cr = cb.createQuery(Account.class);
        Root<Account> root = cr.from(Account.class);
        cr.select(root);
        return session.createQuery(cr).getResultList();

    }

}
