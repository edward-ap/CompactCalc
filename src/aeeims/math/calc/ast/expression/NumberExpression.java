package aeeims.math.calc.ast.expression;

import java.math.BigDecimal;

/*
 * AST number expressions implementation
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class NumberExpression implements Expression {

    private final BigDecimal value;

    public NumberExpression(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal eval() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
