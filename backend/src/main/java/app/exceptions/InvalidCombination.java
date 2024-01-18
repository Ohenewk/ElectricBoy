package app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidCombination extends RuntimeException {

    public InvalidCombination() {
        super("Both battery and status parameters are present. Please provide only one.");
    }
}
