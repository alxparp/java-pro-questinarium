package repository;

import exceptions.AttemptSQLException;
import model.AnswerType;
import model.Attempt;
import repository.dao.AttemptRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttemptRepositoryImpl implements AttemptRepository {

    private Connection connection;

    public AttemptRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private final String saveAttempt =
            """
                insert into attempt (answer, date, topic)
                values (?,?,?)
            """;

    private final String getAllAttempts =
            """
                select * from attempt
            """;


    @Override
    public Attempt get(int id) {
        return null;
    }

    @Override
    public void save(Attempt attempt) {
        try {
            PreparedStatement ps = connection.prepareStatement(saveAttempt);
            ps.setString(1, attempt.getAnswer().getValue());
            ps.setDate(2, Date.valueOf(attempt.getDate()));
            ps.setString(3, attempt.getTopic());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AttemptSQLException(e.getMessage());
        }
    }

    @Override
    public void update(Attempt attempt) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Attempt> getAllAttempts() {
        try {
            PreparedStatement ps = connection.prepareStatement(getAllAttempts);
            ResultSet rs = ps.executeQuery();
            List<Attempt> attempts = new ArrayList<>();
            while(rs.next()) {
                attempts.add(Attempt.builder()
                        .id(rs.getInt("id"))
                        .answer(AnswerType.valueOf(rs.getString("answer").trim()))
                        .topic(rs.getString("topic"))
                        .date(rs.getDate("date").toLocalDate())
                        .build());
            }
            return attempts;
        } catch (SQLException e) {
            throw new AttemptSQLException(e.getMessage());
        }
    }


}
