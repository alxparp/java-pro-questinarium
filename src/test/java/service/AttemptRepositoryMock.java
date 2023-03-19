package service;

import model.Attempt;
import repository.dao.AttemptRepository;

import java.util.List;

public class AttemptRepositoryMock implements AttemptRepository {

    private final List<Attempt> testAttempts;

    public AttemptRepositoryMock(List<Attempt> testAttempts) {
        this.testAttempts = testAttempts;
    }

    @Override
    public Attempt get(int id) {
        return null;
    }

    @Override
    public void save(Attempt attempt) {
        testAttempts.add(attempt);
    }

    @Override
    public void update(Attempt attempt) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Attempt> getAllAttempts() {
        return testAttempts;
    }

}
