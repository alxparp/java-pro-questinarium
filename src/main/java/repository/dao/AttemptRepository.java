package repository.dao;

import model.Attempt;

import java.util.List;

public interface AttemptRepository {

    Attempt get(int id);
    void save(Attempt attempt);
    void update(Attempt attempt);
    void delete(int id);

    List<Attempt> getAllAttempts();

}
