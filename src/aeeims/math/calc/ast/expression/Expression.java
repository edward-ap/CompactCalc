package aeeims.math.calc.ast.expression;

import java.math.BigDecimal;

/*
 * AST expression interface
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public interface Expression {

    BigDecimal eval();

}
