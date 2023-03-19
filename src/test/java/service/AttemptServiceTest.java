package service;

import model.AnswerType;
import model.Attempt;
import model.Question;
import org.junit.Assert;
import org.junit.Test;
import repository.dao.AttemptRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttemptServiceTest {

    private List<Attempt> testAttempts = new ArrayList<>(List.of(
            Attempt.builder().id(1).answer(AnswerType.CORRECT).date(LocalDate.now()).topic("INCAPSULATION").build(),
            Attempt.builder().id(2).answer(AnswerType.WRONG).date(LocalDate.now()).topic("hello").build(),
            Attempt.builder().id(3).answer(AnswerType.CORRECT).date(LocalDate.now()).topic("INCAPSULATION").build(),
            Attempt.builder().id(4).answer(AnswerType.WRONG).date(LocalDate.now()).topic("Dobryi den, everybody").build()
    ));

    private AttemptRepository attemptRepository = new AttemptRepositoryMock(testAttempts);

    @Test
    public void getAllAttemptsTest() {
        AttemptService attemptService = new AttemptService(attemptRepository);
        List<Attempt> attempts = attemptService.getAllAttempts();
        Assert.assertEquals(attempts, testAttempts);
    }

}
