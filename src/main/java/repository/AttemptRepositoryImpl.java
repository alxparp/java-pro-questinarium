package repository;

import exceptions.AttemptSQLException;
import model.AnswerType;
import model.Attempt;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.dao.AttemptRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttemptRepositoryImpl implements AttemptRepository {

    private final SessionFactory sessionFactory;

    public AttemptRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Attempt get(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Attempt attempt = session.get(Attempt.class, id);

        transaction.commit();
        session.close();

        return attempt;
    }

    @Override
    public void save(Attempt attempt) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(attempt);

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Attempt attempt) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(attempt);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.remove(get(id));

        transaction.commit();
        session.close();
    }

    @Override
    public List<Attempt> getAllAttempts() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Attempt> attempts = session.createQuery("FROM Attempt", Attempt.class).list();

        transaction.commit();
        session.close();

        return attempts;
    }


}
