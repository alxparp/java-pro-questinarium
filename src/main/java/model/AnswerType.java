package model;

public enum AnswerType {

    CORRECT("CORRECT"),
    WRONG("WRONG");

    private String value;

    AnswerType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
