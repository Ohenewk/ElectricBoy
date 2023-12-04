package app.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class PreConditionFailed extends RuntimeException {

    public PreConditionFailed(String message) {
        super(message);
    }

    public PreConditionFailed(Object a, Object b) {
        this(String.format("Not the same object: %s != %s", a.toString(), b.toString()));
    }

}
