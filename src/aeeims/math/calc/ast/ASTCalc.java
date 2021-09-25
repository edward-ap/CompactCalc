package aeeims.math.calc.ast;

import aeeims.math.calc.ast.core.Lexer;
import aeeims.math.calc.ast.core.Parser;
import aeeims.math.calc.ast.core.Token;
import aeeims.math.calc.ast.expression.Expression;
import aeeims.math.calc.ast.lib.Constants;

import java.util.List;

/*
 * Mathematical expression lexer/parser implementing
 * abstract syntax tree method: https://en.wikipedia.org/wiki/Abstract_syntax_tree
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class ASTCalc {

    // expression
    private final String code;
    private Lexer lexer;

    public ASTCalc(String code) {
        this.code = code;
        this.lexer = new Lexer(code);
    }

    public void setVar(String name, Double value) {
        Constants.set(name, value);
    }

    public double execute() {
        List<Token> tokens = lexer.tokenize();
        List<Expression> result = new Parser(tokens).parse();
        return result.get(0).eval();
    }

    public String getExpression() {
        return code;
    }

}
