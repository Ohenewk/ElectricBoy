package app.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class PreConditionFailed extends RuntimeException {

    public PreConditionFailed(Object a, Object b) {
        super(String.format("Not the same object: %s != %s", a.toString(), b.toString()));
    }

}
