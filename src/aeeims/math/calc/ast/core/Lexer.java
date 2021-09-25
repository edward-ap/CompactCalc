package aeeims.math.calc.ast.core;

import aeeims.math.calc.ast.lib.Constants;
import aeeims.math.calc.ast.lib.Functions;
import aeeims.math.calc.ast.lib.Variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Lexical analyzer of mathematical expressions
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class Lexer {

    private static final char EOF = '\0';

    public static final Map<String, TokenType> TOKENS;
    static {
        TOKENS = new HashMap<>();
        TOKENS.put("+", TokenType.PLUS);
        TOKENS.put("-", TokenType.MINUS);
        TOKENS.put("*", TokenType.MULTIPLY);
        TOKENS.put("/", TokenType.DIVIDE);
        TOKENS.put("(", TokenType.LPAREN);
        TOKENS.put(")", TokenType.RPAREN);
        TOKENS.put("^", TokenType.POWER);
        TOKENS.put("%", TokenType.MOD);
        TOKENS.put("!", TokenType.FACTORIAL);
        TOKENS.put("&", TokenType.BIT_AND);
        TOKENS.put("|", TokenType.BIT_OR);
        TOKENS.put("~", TokenType.BIT_NOT);
    }

    private final String code;
    private final int codeLength;
    private int currentPos;

    private final List<Token> tokens;

    public Lexer(String code) {
        this.code = code;
        this.codeLength = code.length();
        this.tokens = new ArrayList<>();
        Variables.reset();
    }

    public List<Token> tokenize() {
        while (currentPos < codeLength) {
            final char c = peek();
            if (Character.isDigit(c)) {
                tokenizeNumber();
            } else if (Character.isLetter(c)) {
                tokenizeFuncOrConstant();
            } else if (c == '#') {
                getNext(); // skip #
                tokenizeHexNumber();
            } else if(TOKENS.containsKey(Character.toString(c))) {
                tokenizeReserved();
            } else getNext(); // whitespaces or unsupported
        }
        return tokens;
    }

    private void tokenizeReserved() {
        addToken(TOKENS.get(Character.toString(peek())));
        getNext();
    }

    private void tokenizeNumber() {
        final StringBuilder numberCollector = new StringBuilder();
        char c = peek();
        while (true) {
            if (c == '.') {
                // float number support
                if (numberCollector.indexOf(".") != -1) throw new RuntimeException("Error :: Invalid float number!");
            } else if (c == '!') {
                // factorial support
                if (numberCollector.indexOf(".") != -1) throw new RuntimeException("Error :: Cannot calculate factorial of float number!");
                addToken(TokenType.NUMBER, numberCollector.toString());
                addToken(TokenType.FACTORIAL, "");
                getNext();
                return;
            } else if (!Character.isDigit(c)) {
                break;
            }
            numberCollector.append(c);
            c = getNext();
        }
        addToken(TokenType.NUMBER, numberCollector.toString());
    }

    private void tokenizeHexNumber() {
        final StringBuilder sb = new StringBuilder();
        char current = peek();
        while (Character.isDigit(current) || isHexNumber(current) ) {
            sb.append(current);
            current = getNext();
        }
        addToken(TokenType.HEX_NUMBER, sb.toString());
    }

    private boolean isHexNumber(char current) {
        return "abcdef".indexOf(Character.toLowerCase(current)) != -1;
    }

    private void tokenizeFuncOrConstant() {
        final StringBuilder charsCollector = new StringBuilder();
        char c = peek();
        while (Character.isLetterOrDigit(c) || (c == '_') || (c == '$')) {
            charsCollector.append(c);
            c = getNext();
        }
        final String name = charsCollector.toString();
        if (Constants.isExists(name)) {
            addToken(TokenType.CONST, name);
        } else if (Functions.isExists(name)) {
            addToken(TokenType.FUNC, name);
        } else {
            addToken(TokenType.VAR, name);
        }
    }

    private char getNext() {
        currentPos++;
        return peek();
    }

    private char peek() {
        if (currentPos >= codeLength) return EOF;
        return code.charAt(currentPos);
    }

    private void addToken(TokenType type) {
        addToken(type, "");
    }

    private void addToken(TokenType type, String text) {
        tokens.add(new Token(type, text));
    }

}