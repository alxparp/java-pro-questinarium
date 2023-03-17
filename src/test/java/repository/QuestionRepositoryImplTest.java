package repository;

import model.Question;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.dao.QuestionRepository;

import java.sql.*;
import java.util.List;

public class QuestionRepositoryImplTest {

    private final String user = "postgres";
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String password = "root";
    private Connection connection;
    private QuestionRepository questionRepository;

    private final Question question = new Question("polymorphism", "what is basic principle");
    private String lastRecord =
            """
                    select * from question where id = (select max(id) from question)
            """;

    @Before
    public void init() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        questionRepository = new QuestionRepositoryImpl(connection);
    }

    @After
    public void destroy() throws SQLException {
        connection.close();
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
        Question insertedQuestion = getLastRecord(questionRepository);

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
        Question insertedQuestion = getLastRecord(questionRepository);
        questionRepository.update(new Question(insertedQuestion.getId(), updatedTopic, question.getText()));

        // GET UPDATED
        Question updatedQuestion = questionRepository.get(insertedQuestion.getId());

        // CHECK
        Assert.assertEquals(updatedTopic, updatedQuestion.getTopic());
        Assert.assertEquals(question.getText(), updatedQuestion.getText());

        // DELETE
        questionRepository.delete(updatedQuestion.getId());
    }

    @Test (expected = RuntimeException.class)
    public void deleteTest() {
        questionRepository.save(question);

        Question insertedQuestion = getLastRecord(questionRepository);
        questionRepository.delete(insertedQuestion.getId());

        questionRepository.get(insertedQuestion.getId());
    }

    private Question getLastRecord(QuestionRepository questionRepository) {
        try {
            PreparedStatement ps = connection.prepareStatement(lastRecord);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return Question.builder()
                    .id(rs.getInt("id"))
                    .topic(rs.getString("topic"))
                    .text(rs.getString("text"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
