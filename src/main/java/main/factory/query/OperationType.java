package main.factory.query;

public enum OperationType {

    INSERT("Insert"),
    DELETE("Delete"),
    SELECT_FROM_MEMORY("Get by topic adding all records in memory"),
    SELECT_FROM_DB("Get by topiс directly from db"),
    SHOW_STAT("Show statistics");

    private String value;

    OperationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
