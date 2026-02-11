package common.error;

import java.util.Map;

public interface TypeableException {

    ExceptionType getCustomExceptionType();

    String getExceptionMessage();

    Map<String, String> getDetailedErrorMessage();
}
