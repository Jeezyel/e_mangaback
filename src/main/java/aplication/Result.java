package aplication;

import jakarta.validation.ConstraintViolation;

import java.util.Set;
import java.util.stream.Collectors;

public class Result {
    private String message;
    private boolean success;

    public Result(String message) {
        this.success = true;
        this.message = message;
    }

    public Result(String message, Boolean success) {

        this.success = success;
        this.message = message;
    }

    public Result(Set<? extends ConstraintViolation<?>> violations) {
        this.success = false;
        this.message = violations.stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.joining(", "));
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
