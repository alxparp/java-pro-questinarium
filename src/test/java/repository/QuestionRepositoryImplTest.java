package repository;

import config.HibConfig;
import model.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.dao.QuestionRepository;
import java.util.List;

public class QuestionRepositoryImplTest {
    private QuestionRepository questionRepository;

    private final Question question = new Question("polymorphism", "what is basic principle");

    private SessionFactory sessionFactory;

    @Before
    public void init() {
        sessionFactory = HibConfig.getSessionJavaConfigFactory();
        questionRepository = new QuestionRepositoryImpl(sessionFactory);
    }

    @Test
    public void getTest() {
        System.out.println(questionRepository.get(1));
    }

    @Test
    public void getByTopicTest() {
        System.out.println(questionRepository.getByTopic("OOP"));
    }

    @Test
    public void saveTest() {
        questionRepository.save(question);
        Question insertedQuestion = getLastRecord();

        Assert.assertEquals(question.getText(), insertedQuestion.getText());
        Assert.assertEquals(question.getTopic(), insertedQuestion.getTopic());

        questionRepository.delete(insertedQuestion.getId());
    }

    @Test
    public void updateTest() {
        String updatedTopic = "inheritance";

        // INSERT
        questionRepository.save(question);

        // UPDATE
        Question insertedQuestion = getLastRecord();
        questionRepository.update(new Question(insertedQuestion.getId(), updatedTopic, question.getText()));

        // GET UPDATED
        Question updatedQuestion = questionRepository.get(insertedQuestion.getId());

        // CHECK
        Assert.assertEquals(updatedTopic, updatedQuestion.getTopic());
        Assert.assertEquals(question.getText(), updatedQuestion.getText());

        // DELETE
        questionRepository.delete(updatedQuestion.getId());
    }

    @Test
    public void deleteTest() {
        questionRepository.save(question);

        Question insertedQuestion = getLastRecord();
        questionRepository.delete(insertedQuestion.getId());

        Assert.assertNull(questionRepository.get(insertedQuestion.getId()));
    }

    private Question getLastRecord() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ;

        List<Question> questions = session.createQuery("FROM Question q ORDER BY q.id DESC", Question.class)
                .list();

        Question lastQuestion = questions.size() > 0 ? questions.get(0) : null;

        transaction.commit();
        session.close();

        return lastQuestion;
    }
}
