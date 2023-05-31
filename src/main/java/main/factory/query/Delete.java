package main.factory.query;

import main.notification.Notification;
import main.validation.ValidationExecutor;
import main.validation.routines.IntegerValidator;
import model.Question;
import service.QuestionService;
import java.util.List;
import java.util.Scanner;

public class Delete implements Query {

    private final QuestionService questionService;

    public Delete(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void implement() {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = questionService.getAllQuestions();
        printQuestions(questions);

        System.out.print("Select question id: ");
        String questionId = scanner.next();

        if (new ValidationExecutor().execute(new IntegerValidator(questionId))) {
            int id = Integer.parseInt(questionId);

            if (Notification.confirmDeleteOperation()) {
                questionService.delete(id);
                System.out.println("The record was successfully deleted");
            }
        }
    }

    private void printQuestions(List<Question> questions) {
        System.out.println("------------------");
        for (Question question: questions) {
            System.out.println(question.getId() + " | " + question.getTopic() + " | " + question.getText());
        }
        System.out.println("------------------");
    }

}
