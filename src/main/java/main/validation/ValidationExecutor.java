package main.validation;


public class ValidationExecutor {
    public boolean execute(Validator validator) {
        if (!validator.validate()) {
            System.out.println(validator.getMessage());
            return false;
        }
        return true;
    }
}
