package main.factory.query;

import model.Question;
import service.QuestionService;
import java.util.Scanner;

public class FromDB implements Query {

    private final QuestionService questionService;

    public FromDB(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void implement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Topic name?");
        Question question = questionService.getRndQuestionDirectFromDB(scanner.next());
        if (question != null)
            System.out.println(question.getText());
        else
            System.out.println("Questions with same topic are missing");
    }
}
