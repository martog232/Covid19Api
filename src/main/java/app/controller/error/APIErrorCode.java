package app.controller.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum APIErrorCode {
    ENTITY_NOT_FOUND            ("404", "Entity not found",            "An entity could not be found."),

    METHOD_ARG_NOT_VALID          ("400", "Method argument not valid",   "Country Code must contain only two capital letters.");


    private final String code;
    private final String message;
    private final String description;
}
