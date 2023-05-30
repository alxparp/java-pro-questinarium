package service;

import config.HibConfig;
import model.AnswerType;
import model.Attempt;
import model.Question;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import repository.AttemptRepositoryImpl;
import repository.QuestionRepositoryImpl;
import repository.dao.AttemptRepository;
import repository.dao.QuestionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QuestionServiceTest {
    private List<Question> testQuestions = new ArrayList<>(List.of(
            Question.builder().id(1).text("something1").topic("testTopic").build(),
            Question.builder().id(2).text("something2").topic("testTopic").build(),
            Question.builder().id(3).text("something3").topic("testTopic").build()
    ));

    private List<Attempt> testAttempts = new ArrayList<>(List.of(
            Attempt.builder().id(1).answer(AnswerType.CORRECT).date(LocalDate.now()).topic("INCAPSULATION").build(),
            Attempt.builder().id(2).answer(AnswerType.WRONG).date(LocalDate.now()).topic("hello").build(),
            Attempt.builder().id(3).answer(AnswerType.CORRECT).date(LocalDate.now()).topic("INCAPSULATION").build(),
            Attempt.builder().id(4).answer(AnswerType.WRONG).date(LocalDate.now()).topic("Dobryi den, everybody").build()
    ));

    private QuestionRepository questionRepository = new QuestionRepositoryMock(testQuestions);
    private AttemptRepository attemptRepository = new AttemptRepositoryMock(testAttempts);


    @Test
    public void getRndQuestionByTopicTest() {
        SessionFactory sessionFactory = HibConfig.getSessionJavaConfigFactory();

        QuestionService questionService = new QuestionService(
                new QuestionRepositoryImpl(sessionFactory),
                new AttemptRepositoryImpl(sessionFactory));
        System.out.println(questionService.getRndQuestionByTopic("INCAPSULATION"));
    }

    @Test
    public void getRndQuestionTest() {
        QuestionService questionService = new QuestionService(questionRepository, attemptRepository);
        Question rndQuestion = questionService.getRndQuestion();
        Assert.assertTrue(testQuestions.contains(rndQuestion));
    }

    @Test
    public void saveTest() {
        Question question = Question.builder().id(4).text("something").topic("testTopic").build();
        QuestionService questionService = new QuestionService(questionRepository, attemptRepository);
        questionService.save(question);
        Assert.assertTrue(testQuestions.contains(question));
    }

    @Test
    public void deleteTest() {
        Question question = Question.builder().id(4).text("something").topic("testTopic").build();
        QuestionService questionService = new QuestionService(questionRepository, attemptRepository);
        questionService.save(question);
        questionService.delete(question.getId());
        Assert.assertFalse(testQuestions.contains(question));
    }

    @Test
    public void getRndQuestionDirectFromDBTest() {
        QuestionService questionService = new QuestionService(questionRepository, attemptRepository);
        Question rndQuestion = questionService.getRndQuestionDirectFromDB("testTopic");
        Assert.assertTrue(testQuestions.contains(rndQuestion));
    }

    @Test
    public void getAllQuestionsTest() {
        QuestionService questionService = new QuestionService(questionRepository, attemptRepository);
        List<Question> questions = questionService.getAllQuestions();
        Assert.assertEquals(questions, testQuestions);
    }

}
