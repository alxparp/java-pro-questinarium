package service;

import model.AnswerType;
import model.Attempt;
import model.Question;
import repository.dao.AttemptRepository;
import repository.dao.QuestionRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionService {

    private QuestionRepository questionRepository;
    private AttemptRepository attemptRepository;

    private final Map<String, List<Question>> questionsByTopic = new HashMap<>();

    public QuestionService(QuestionRepository questionRepository, AttemptRepository attemptRepository) {
        this.questionRepository = questionRepository;
        this.attemptRepository = attemptRepository;
    }

    public Question getRndQuestionByTopic(String topic) {
        List<Question> questions = questionsByTopic.computeIfAbsent(topic, questionRepository::getByTopic);
        questionsByTopic.put(topic, questions);
        Question question = getRandom(questions);
        AnswerType answerType = (question == null) ? AnswerType.WRONG : AnswerType.CORRECT;
        attemptRepository.save(Attempt.builder()
                .answer(answerType)
                .date(LocalDate.now())
                .topic(topic)
                .build());
        return question;
    }

    public Question getRndQuestion() {
        List<Question> topics = questionRepository.getAllQuestions();
        return getRandom(topics);
    }

    private Question getRandom(List<Question> questions) {
        if (questions.size() != 0) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, questions.size());
            return questions.get(randomNum);
        }
        return null;
    }

    public void save(Question question) {
        questionRepository.save(question);
    }

    public Question getRndQuestionDirectFromDB(String topic) {
        Question question = questionRepository.getRndByTopic(topic);
        AnswerType answerType = (question == null) ? AnswerType.WRONG : AnswerType.CORRECT;
        attemptRepository.save(Attempt.builder()
                .answer(answerType)
                .date(LocalDate.now())
                .topic(topic)
                .build());
        return question;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.getAllQuestions();
    }

    public void delete(int id) {
        questionRepository.delete(id);
    }

}
