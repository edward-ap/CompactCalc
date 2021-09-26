package aeeims.math.calc.ast.expression;

import aeeims.math.calc.ast.lib.Constants;

import java.math.BigDecimal;

/*
 * AST constants expressions to manage pre-defined constants
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class ConstantExpression implements Expression {

    private final String name;

    public ConstantExpression(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal eval() {
        if (!Constants.isExists(name)) throw new RuntimeException("Error :: Cannot recognize constant '" + name + "'");
        return new BigDecimal(Constants.get(name).toString());
    }

    @Override
    public String toString() {
        return name;
    }
}
