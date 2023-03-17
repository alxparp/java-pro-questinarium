package service;

import org.junit.Before;
import org.junit.Test;
import repository.QuestionRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionServiceTest {

    private String user = "postgres";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String password = "root";
    private Connection connection;

    @Before
    public void init() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    @Test
    public void getRndQuestionByTopicTest() {
        QuestionService questionService = new QuestionService(new QuestionRepositoryImpl(connection));
        System.out.println(questionService.getRndQuestionByTopic("INCAPSULATION"));
    }

}
