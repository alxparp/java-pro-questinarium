package main.factory.query;

import lombok.Cleanup;
import model.Question;
import service.QuestionService;

import java.util.Scanner;

public class Insert implements Query {

    private QuestionService questionService;

    public Insert(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void implement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the topic: ");
        String topic = scanner.nextLine();

        System.out.println("Type the text: ");
        String text = scanner.nextLine();

        Question question = Question.builder().topic(topic).text(text).build();
        questionService.save(question);

        System.out.println("Question successfully saved");
    }

}
