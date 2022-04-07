package calculator;

import java.io.Serializable;

public enum StatusCode implements Serializable {
    OK,
    SyntaxError,
    DivisionByZero,
    IncorrectInputError
}
