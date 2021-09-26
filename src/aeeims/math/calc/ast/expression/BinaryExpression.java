package aeeims.math.calc.ast.expression;

import java.math.BigDecimal;

/*
 * AST binary expressions implementation
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class BinaryExpression implements Expression {

    private final Expression expr1;
    private final Expression expr2;
    private final char operation;

    public BinaryExpression(char operation, Expression expr1, Expression expr2) {
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public BigDecimal eval() {
        switch(operation) {
            case '-' : return expr1.eval().subtract(expr2.eval());
            case '*' : return expr1.eval().multiply(expr2.eval());
            case '/' : return expr1.eval().divide(expr2.eval());
            case '^' : return expr1.eval().pow(expr2.eval().intValue());
            case '%' : return expr1.eval().remainder(expr2.eval());
            case '!' : {
                BigDecimal fact = new BigDecimal(1);
                for (int i = 2; i <= expr1.eval().intValue(); i++) {
                    fact = fact.multiply(new BigDecimal(i));
                }
                return fact;
            }
            case '~' : return new BigDecimal(~expr1.eval().intValue());
            case '&' : return new BigDecimal(expr1.eval().intValue() & expr2.eval().intValue());
            case '|' : return new BigDecimal(expr1.eval().intValue() | expr2.eval().intValue());
            case '+' :
            default:
                return expr1.eval().add(expr2.eval());
        }
    }

    @Override
    public String toString() {
        return String.format("[%s %c %s]", expr1, operation, expr2);
    }

}