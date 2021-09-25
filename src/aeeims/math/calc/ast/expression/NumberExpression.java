package aeeims.math.calc.ast.expression;

/*
 * AST number expressions implementation
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class NumberExpression implements Expression {

    private final double value;

    public NumberExpression(double value) {
        this.value = value;
    }

    @Override
    public double eval() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

}
