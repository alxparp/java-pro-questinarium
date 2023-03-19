package main.factory.query;

import model.Attempt;
import service.AttemptService;
import java.util.List;

public class ShowStat implements Query {

    private AttemptService attemptService;

    public ShowStat(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    public void implement() {
        List<Attempt> attempts = attemptService.getAllAttempts();
        System.out.println("------------------");
        for (Attempt attempt: attempts) {
            System.out.println(attempt.getId() + " | " + attempt.getAnswer() + " | " + attempt.getDate() + " | " + attempt.getTopic());
        }
        System.out.println("------------------");
    }
}
