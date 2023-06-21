package main.factory;

import main.factory.query.Query;
import main.factory.query.OperationType;
import main.factory.query.*;
import service.AttemptService;
import service.QuestionService;

public class QueryFactory {

    private final QuestionService questionService;
    private final AttemptService attemptService;

    public QueryFactory(QuestionService questionService, AttemptService attemptService) {
        this.questionService = questionService;
        this.attemptService = attemptService;
    }

    public Query createQuery (OperationType type) {
        Query query = null;

        switch (type) {
            case INSERT:
                query = new Insert(questionService);
                break;
            case DELETE:
                query = new Delete(questionService);
                break;
            case SELECT_FROM_DB:
                query = new FromDB(questionService);
                break;
            case SELECT_FROM_MEMORY:
                query = new FromMemory(questionService);
                break;
            case SHOW_STAT:
                query = new ShowStat(attemptService);
                break;
        }

        return query;
    }

}
