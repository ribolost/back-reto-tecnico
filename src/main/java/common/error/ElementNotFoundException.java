package common.error;

import java.util.Map;

public class ElementNotFoundException extends RuntimeException implements TypeableException {
    private final String elementNotFound;

    public ElementNotFoundException(String message, String elementNotFound) {
        super(message);
        this.elementNotFound = elementNotFound;
    }

    @Override
    public ExceptionType getCustomExceptionType() {
        return ExceptionType.NOT_FOUND;
    }

    @Override
    public String getExceptionMessage() {
        return super.getMessage();
    }

    @Override
    public Map<String, String> getDetailedErrorMessage() {
        return Map.of(elementNotFound, "Element not found.");
    }
}
