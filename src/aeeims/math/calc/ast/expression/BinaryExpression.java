package aeeims.math.calc.ast.expression;

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
    public double eval() {
        switch(operation) {
            case '-' : return expr1.eval() - expr2.eval();
            case '*' : return expr1.eval() * expr2.eval();
            case '/' : return expr1.eval() / expr2.eval();
            case '^' : return Math.pow(expr1.eval(), expr2.eval());
            case '%' : return expr1.eval() % expr2.eval();
            case '!' : {
                long fact = 1;
                for (int i = 2; i <= expr1.eval(); i++) {
                    fact = fact * i;
                }
                return fact;
            }
            case '~' : return ~ (int) expr1.eval();
            case '&' : return (int) expr1.eval() & (int) expr2.eval();
            case '|' : return (int) expr1.eval() | (int) expr2.eval();
            case '+' :
            default:
                return expr1.eval() + expr2.eval();
        }
    }

    @Override
    public String toString() {
        return String.format("[%s %c %s]", expr1, operation, expr2);
    }

}