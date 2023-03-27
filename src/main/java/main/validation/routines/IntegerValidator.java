package main.validation.routines;

import main.validation.Validator;

public class IntegerValidator implements Validator {

    private String value;
    private String message;

    public IntegerValidator(String value) {
        this.value = value;
    }

    @Override
    public boolean validate() {
        if (value != null && value.matches("\\d+")) {
            return true;
        }
        setMessage("Only numbers are allowed or number is negative");
        return false;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
