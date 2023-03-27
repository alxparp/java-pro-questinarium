package service;

import model.Question;
import repository.dao.QuestionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionRepositoryMock implements QuestionRepository {

    private final List<Question> testQuestions;

    Map<String, List<Question>> questionsByTopic = new HashMap<>();

    public QuestionRepositoryMock(List<Question> testQuestions) {
        this.testQuestions = testQuestions;
    }

    @Override
    public Question get(int id) {
        return null;
    }

    @Override
    public void save(Question question) {
        testQuestions.add(question);
    }

    @Override
    public void update(Question question) {

    }

    @Override
    public void delete(int id) {
        for(int i = 0; i < testQuestions.size(); i++) {
            if (testQuestions.get(i).getId() == id) {
                testQuestions.remove(testQuestions.get(i));
                return;
            }
        }
    }

    @Override
    public List<Question> getByTopic(String topic) {
        List<Question> questions = new ArrayList<>();
        for (Question question: testQuestions) {
            if (question.getTopic().equals(topic)) {
                questions.add(question);
            }
        }
        return questions;
    }

    @Override
    public List<Question> getAllQuestions() {
        return testQuestions;
    }

    @Override
    public Question getRndByTopic(String topic) {
        List<Question> questions = questionsByTopic.computeIfAbsent(topic, this::getByTopic);
        questionsByTopic.put(topic, questions);
        int randomNum = ThreadLocalRandom.current().nextInt(0, questions.size());
        return questions.get(randomNum);
    }

}
