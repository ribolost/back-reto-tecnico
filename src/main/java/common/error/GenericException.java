package common.error;

import java.util.Map;

public class GenericException extends RuntimeException implements TypeableException{

    public String genericDetailedMessage;
    public String exceptionElement;

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String message, String detailedMessage, String exceptionElement){
        super(message);
        this.genericDetailedMessage = detailedMessage;
        this.exceptionElement = exceptionElement;
    }

    @Override
    public ExceptionType getCustomExceptionType() {
        return ExceptionType.GENERAL;
    }

    @Override
    public String getExceptionMessage() {
        return super.getMessage();
    }

    @Override
    public Map<String, String> getDetailedErrorMessage() {
        return Map.of(exceptionElement, genericDetailedMessage);
    }
}
