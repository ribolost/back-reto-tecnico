package common.error;

import java.util.Map;

public class BusinessException extends RuntimeException implements TypeableException {
    private final String element;
    private final String detailedMessage;

    public BusinessException(String message, String detailedMessage, String element) {
        super(message);
        this.element = element;
        this.detailedMessage = detailedMessage;
    }

    @Override
    public ExceptionType getCustomExceptionType() {
        return ExceptionType.BUSINESS;
    }

    @Override
    public String getExceptionMessage() {
        return super.getMessage();
    }

    @Override
    public Map<String, String> getDetailedErrorMessage() {
        return Map.of(element, detailedMessage);
    }
}
