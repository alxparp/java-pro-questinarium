package main;

import config.HibConfig;
import main.factory.query.OperationType;
import main.factory.QueryFactory;
import main.factory.query.Query;
import main.validation.ValidationExecutor;
import main.validation.routines.ArrayOutOfBoundValidator;
import main.validation.routines.IntegerValidator;
import org.hibernate.SessionFactory;
import repository.AttemptRepositoryImpl;
import repository.QuestionRepositoryImpl;
import repository.dao.AttemptRepository;
import repository.dao.QuestionRepository;
import service.AttemptService;
import service.QuestionService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Hibernate
        SessionFactory sessionFactory = HibConfig.getSessionJavaConfigFactory();

        // Repositories
        AttemptRepository attemptRepository = new AttemptRepositoryImpl(sessionFactory);
        QuestionRepository questionRepository = new QuestionRepositoryImpl(sessionFactory);

        // Services
        QuestionService questionService = new QuestionService(questionRepository,attemptRepository);
        AttemptService attemptService = new AttemptService(attemptRepository);

        // Factory
        QueryFactory queryFactory = new QueryFactory(questionService, attemptService);

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Select the number of operation:");
            for (OperationType o : OperationType.values()) {
                System.out.println(o.ordinal() + ") " + o.getValue());
            }

            System.out.print("Operation number: ");
            String operation = scanner.next();

            if (checkIntAndArrayOut(operation)) {
                int opNumber = Integer.parseInt(operation);

                OperationType operationType = OperationType.values()[opNumber];
                Query query = queryFactory.createQuery(operationType);
                query.implement();
            }
            System.out.println("To select the next operation type \"yes\"");
        } while (scanner.next().equals("yes"));
    }

    private static boolean checkIntAndArrayOut(String operation) {
        ValidationExecutor executor = new ValidationExecutor();
        boolean flag = executor.execute(new IntegerValidator(operation));
        return flag && executor.execute(new ArrayOutOfBoundValidator(Integer.parseInt(operation), OperationType.values().length));
    }

}
