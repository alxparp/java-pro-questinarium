package main.validation.routines;

import main.validation.Validator;

public class ArrayOutOfBoundValidator implements Validator {

    private int value;
    private int size;
    private String message;

    public ArrayOutOfBoundValidator(int value, int size) {
        this.value = value;
        this.size = size;
    }

    @Override
    public boolean validate() {
        if (value < size && value >= 0)
            return true;
        else {
            setMessage("Select true point");
            return false;
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
