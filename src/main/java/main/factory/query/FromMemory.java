package main.factory.query;

import model.Question;
import service.QuestionService;
import java.util.Scanner;

public class FromMemory implements Query {

    private final QuestionService questionService;

    public FromMemory(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void implement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Topic name?");
        Question question = questionService.getRndQuestionByTopic(scanner.next());
        if (question != null)
            System.out.println(question.getText());
        else
            System.out.println("Questions with same topic are missing");
    }
}
