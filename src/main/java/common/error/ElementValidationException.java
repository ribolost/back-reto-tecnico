package common.error;


import jakarta.persistence.PersistenceException;

import java.util.Map;

public class ElementValidationException extends PersistenceException implements TypeableException {
    private final Map<String, String> constraintErrors;

    public ElementValidationException(String message, Map<String,String> violations){
        super(message);
        this.constraintErrors = violations;
    }
    @Override
    public ExceptionType getCustomExceptionType() {
        return ExceptionType.VALIDATION;
    }

    @Override
    public String getExceptionMessage(){
        return super.getMessage();
    }

    public Map<String, String> getDetailedErrorMessage(){
        return this.constraintErrors;
    }

}
