package aeeims.math.calc.ast.expression;

import java.math.BigDecimal;

/*
 * AST unary expressions to support negative numbers
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class UnaryExpression implements Expression {

    private final Expression expr1;
    private final char operation;

    public UnaryExpression(char operation, Expression expr1) {
        this.expr1 = expr1;
        this.operation = operation;
    }

    @Override
    public BigDecimal eval() {
        if (operation == '-') return expr1.eval().negate();
        return expr1.eval();
    }

    @Override
    public String toString() {
        return String.format("%c%s", operation, expr1);
    }

}