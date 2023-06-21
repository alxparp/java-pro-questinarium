package repository;

import model.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.dao.QuestionRepository;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {

    private final SessionFactory sessionFactory;

    public QuestionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Question get(int id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Question question = session.get(Question.class, id);

        transaction.commit();
        session.close();

        return question;
    }

    @Override
    public void save(Question question) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(question);

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Question question) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(question);

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
    public List<Question> getByTopic(String topic) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Question> questions = session.createQuery("FROM Question q WHERE q.topic = :topic", Question.class)
                .setParameter("topic", topic)
                .list();

        transaction.commit();
        session.close();

        return questions;
    }

    @Override
    public List<Question> getAllQuestions() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Question> questions = session.createQuery("FROM Question", Question.class).list();

        transaction.commit();
        session.close();

        return questions;
    }

    @Override
    public Question getRndByTopic(String topic) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Question> questions = session.createQuery("FROM Question q WHERE q.topic = :topic ORDER BY RANDOM() LIMIT 1", Question.class)
                .setParameter("topic", topic)
                .list();

        Question question = questions.size() > 0 ? questions.get(0) : null;

        transaction.commit();
        session.close();

        return question;
    }

}
