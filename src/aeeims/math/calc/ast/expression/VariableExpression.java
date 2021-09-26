package aeeims.math.calc.ast.expression;

import aeeims.math.calc.ast.lib.Variables;

import java.math.BigDecimal;

/*
 * AST variable expressions to manage runtime variables
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class VariableExpression implements Expression {

    private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal eval() {
        if (!Variables.isExists(name)) throw new RuntimeException("Error :: Cannot recognize variable '" + name + "'");
        return Variables.get(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
