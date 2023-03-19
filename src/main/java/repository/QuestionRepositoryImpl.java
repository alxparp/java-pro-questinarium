package repository;

import exceptions.QuestionSQLException;
import model.Question;
import repository.dao.QuestionRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {

    private final String findById =
            """
                    select * from question where id = ?
            """;

    private final String findByTopic =
            """
                    select * from question where topic = ?
            """;

    private final String getAllQuest =
            """
                    select * from question
            """;

    private final String getRndByTopic =
            """
                    select * from question 
                    where topic = ? 
                    order by RANDOM() 
                    limit 1
            """;

    private final String saveQuestion =
            """
                    insert into question(topic, text) values (?,?)
            """;

    private final String updateQuestion =
            """
                    update question set topic = ?, text = ? where id = ?
            """;

    private final String deleteById =
            """
                    delete from question where id = ?
            """;

    private Connection connection;

    public QuestionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Question get(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(findById);
            ps.setInt(1, id);
            List<Question> questions = getQuestionsList(ps);
            return questions.size() > 0 ? questions.get(0) : null;
        } catch (SQLException e) {
            throw new QuestionSQLException(e.getMessage());
        }
    }

    @Override
    public void save(Question question) {
        try {
            PreparedStatement ps = connection.prepareStatement(saveQuestion);
            ps.setString(1, question.getTopic());
            ps.setString(2, question.getText());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new QuestionSQLException(e.getMessage());
        }
    }

    @Override
    public void update(Question question) {
        try {
            PreparedStatement ps = connection.prepareStatement(updateQuestion);
            ps.setString(1, question.getTopic());
            ps.setString(2, question.getText());
            ps.setInt(3, question.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new QuestionSQLException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(deleteById);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new QuestionSQLException(e.getMessage());
        }
    }

    @Override
    public List<Question> getByTopic(String topic) {
        try {
            PreparedStatement ps = connection.prepareStatement(findByTopic);
            ps.setString(1, topic);
            return getQuestionsList(ps);
        } catch (SQLException e) {
            throw new QuestionSQLException(e.getMessage());
        }
    }

    @Override
    public List<Question> getAllQuestions() {
        try {
            PreparedStatement ps = connection.prepareStatement(getAllQuest);
            return getQuestionsList(ps);
        } catch (SQLException e) {
            throw new QuestionSQLException(e.getMessage());
        }
    }

    @Override
    public Question getRndByTopic(String topic) {
        try {
            PreparedStatement ps = connection.prepareStatement(getRndByTopic);
            ps.setString(1, topic);
            List<Question> questions = getQuestionsList(ps);
            return questions.size() > 0 ? questions.get(0) : null;
        } catch (SQLException e) {
            throw new QuestionSQLException(e.getMessage());
        }
    }

    private List<Question> getQuestionsList(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        List<Question> questions = new ArrayList<>();
        while(rs.next()) {
            questions.add(Question.builder()
                    .id(rs.getInt("id"))
                    .topic(rs.getString("topic"))
                    .text(rs.getString("text"))
                    .build());
        }
        return questions;
    }


}
