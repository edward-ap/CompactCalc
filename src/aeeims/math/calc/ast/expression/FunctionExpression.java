package aeeims.math.calc.ast.expression;

import aeeims.math.calc.ast.lib.Constants;
import aeeims.math.calc.ast.lib.Functions;

/*
 * AST function expressions to invoke pre-defined functions
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class FunctionExpression implements Expression {

    private final String name;
    private final Expression expr;

    public FunctionExpression(String name, Expression expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public double eval() {
        if (!Functions.isExists(name)) throw new RuntimeException("Error :: Cannot recognize function '" + name + "'");
        return Functions.execute(name, expr.eval());
    }

    @Override
    public String toString() {
        return name;
    }

}
