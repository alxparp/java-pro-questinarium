package service;

import model.Attempt;
import repository.dao.AttemptRepository;

import java.util.List;

public class AttemptService {

    private AttemptRepository attemptRepository;

    public AttemptService(AttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }

    public List<Attempt> getAllAttempts() {
        return attemptRepository.getAllAttempts();
    }

}
