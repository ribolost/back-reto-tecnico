package web.exception;

import common.error.ExceptionType;

import java.util.Map;

public record ExceptionResponse(ExceptionType type, String message, Map<String, String> detailedError) {

}
