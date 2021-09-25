package aeeims.math.calc.ast.core;

/*
 * Token types for calculation mathematical expressions
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public enum TokenType {

    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    POWER,
    MOD,
    FACTORIAL,
    BIT_AND,
    BIT_OR,
    BIT_NOT,

    NUMBER,
    HEX_NUMBER,

    LPAREN,
    RPAREN,

    CONST,
    FUNC,
    VAR,

    EOF

}
