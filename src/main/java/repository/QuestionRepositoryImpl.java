package repository;

import model.Question;
import repository.dao.QuestionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {

    private String findById =
            """
                    select * from question where id = ?
            """;

    private String findByTopic =
            """
                    select * from question where topic = ?
            """;

    private String saveQuestion =
            """
                    insert into question(topic, text) values (?,?)
            """;

    private String updateQuestion =
            """
                    update question set topic = ?, text = ? where id = ?
            """;

    private String deleteById =
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

    @Override
    public void save(Question question) {
        try {
            PreparedStatement ps = connection.prepareStatement(saveQuestion);
            ps.setString(1, question.getTopic());
            ps.setString(2, question.getText());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(deleteById);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Question> getByTopic(String topic) {
        try {
            PreparedStatement ps = connection.prepareStatement(findByTopic);
            ps.setString(1, topic);
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
