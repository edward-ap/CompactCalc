package aeeims.math.calc.ast.core;

import aeeims.math.calc.ast.expression.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
 * Mathematical expressions parser, implementing recursive descent parser method.
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class Parser {

    private static final Token EOF = new Token(TokenType.EOF, "");

    private final List<Token> tokens;
    private final int size;

    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.size = tokens.size();
    }

    public List<Expression> parse() {
        final List<Expression> result = new ArrayList<>();
        while(!nextMatch(TokenType.EOF)) {
            result.add(addition());
        }
        return result;
    }

    private Expression expression() {
        return addition();
    }

    private Expression addition() {
        Expression result = multiplication();
        while (true) {
            if (nextMatch(TokenType.PLUS)) {
                result = new BinaryExpression('+', result, multiplication());
                continue;
            }
            if (nextMatch(TokenType.MINUS)) {
                result = new BinaryExpression('-', result, multiplication());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression multiplication() {
        Expression result = exponentiation();
        while (true) {
            if (nextMatch(TokenType.MULTIPLY)) {
                result = new BinaryExpression('*', result, exponentiation());
                continue;
            }
            if (nextMatch(TokenType.DIVIDE)) {
                result = new BinaryExpression('/', result, exponentiation());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression exponentiation() {
        Expression result = unary();
        while (true) {
            if (nextMatch(TokenType.POWER)) {
                result = new BinaryExpression('^', result, unary());
                continue;
            }
            if (nextMatch(TokenType.MOD)) {
                result = new BinaryExpression('%', result, unary());
                continue;
            }
            if (nextMatch(TokenType.FACTORIAL)) {
                result = new BinaryExpression('!', result, null);
                continue;
            }
            if (nextMatch(TokenType.BIT_AND)) {
                result = new BinaryExpression('&', result, expression());
                continue;
            }
            if (nextMatch(TokenType.BIT_OR)) {
                result = new BinaryExpression('|', result, expression());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression unary() {
        if (nextMatch(TokenType.MINUS)) {
            return new UnaryExpression('-', primary());
        }
        if (nextMatch(TokenType.BIT_NOT)) {
            return new BinaryExpression('~', expression(), null);
        }
        if (nextMatch(TokenType.PLUS)) {
            return primary();
        }
        return primary();
    }

    private Expression primary() {
        final Token current = getNext();
        if (nextMatch(TokenType.NUMBER)) {
            return new NumberExpression(new BigDecimal(current.getToken()));
        }
        if (nextMatch(TokenType.CONST)) {
            return new ConstantExpression(current.getToken());
        }
        if (nextMatch(TokenType.VAR)) {
            return new VariableExpression(current.getToken());
        }
        if (nextMatch(TokenType.FUNC)) {
            if (nextMatch(TokenType.LPAREN)) {
                Expression result = expression();
                if (!nextMatch(TokenType.RPAREN)) throw new RuntimeException("Error :: Expected closing parenthesis after function param");
                return new FunctionExpression(current.getToken(), result);
            } else {
                throw new RuntimeException("Error :: Expected opening parenthesis after function name");
            }
        }
        if (nextMatch(TokenType.HEX_NUMBER)) {
            return new NumberExpression(new BigDecimal(Long.parseLong(current.getToken(), 16)));
        }
        if (nextMatch(TokenType.LPAREN)) {
            Expression result = expression();
            if (!nextMatch(TokenType.RPAREN)) throw new RuntimeException("Error :: Expected closing parenthesis");
            return result;
        }
        throw new RuntimeException("Unknown expression");
    }

    private Token consume(TokenType type) {
        final Token current = getNext();
        if (type != current.getType()) throw new RuntimeException("Token " + current + " does not match " + type);
        pos++;
        return current;
    }

    private boolean nextMatch(TokenType type) {
        final Token t = getNext();
        if (type != t.getType()) return false;
        pos++;
        return true;
    }

    private Token getNext() {
        if (pos >= size) return EOF;
        return tokens.get(pos);
    }

}