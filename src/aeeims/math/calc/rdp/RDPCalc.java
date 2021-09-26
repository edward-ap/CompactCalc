package aeeims.math.calc.rdp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/*
 * Mathematical expression parser/calculator - all in one class
 * with implementation of recursive descent parser method.
 *
 * @author Ed Aponasko
 * @version 1.0, June 2021
 */
public class RDPCalc {

    private static final char EOF = '\0';

    // List of pre-defined constants like pi, e... etc.
    private static Map<String, BigDecimal> CONSTANTS;
    static {
        CONSTANTS = new HashMap<>();
        CONSTANTS.put("PI", new BigDecimal(Math.PI));
        CONSTANTS.put("E", new BigDecimal(Math.E));
    }

    // expression
    private final String code;

    // length of expression
    private final int length;

    // our current position within the expression
    private int pos;

    public RDPCalc(String code) {
        this.code = code;
        this.length = code.length();
    }

    public void setVar(String name, Number value) {
        CONSTANTS.put(name, new BigDecimal(value.toString()));
    }

    public Number execute() throws Exception {
        if (code.isEmpty()) throw new Exception("Error :: Empty expression!");
        BigDecimal result = expression();
        if (peek() != EOF)
            throw new Exception("Error :: Cannot parse following code : " + code.substring(pos));
        return result;
    }

    public String getExpression() {
        return code;
    }

    /*
        Wrapper for calculated expression
     */
    private BigDecimal expression() {
        return addition();
    }

    /*
        Method to handle addition and subtraction
     */
    private BigDecimal addition() {
        BigDecimal result = multiplication();
        while (true) {
            char next = peek();
            switch(next) {
                case '+' :
                    getNext();
                    result = result.add(multiplication());
                    continue;
                case '-' :
                    getNext();
                    result = result.subtract(multiplication());
                    continue;
            }
            break;
        }
        return result;
    }

    /*
        Method to handle multiplication, division, modulus and bitwise operations
        & - bitwise AND
            A bitwise AND is a binary operation that takes two equal-length binary representations
            and performs the logical AND operation on each pair of the corresponding bits,
            which is equivalent to multiplying them.
                    0101 (decimal 5)
                AND 0011 (decimal 3)
                  = 0001 (decimal 1)
        | - bitwise OR
            A bitwise OR is a binary operation that takes two bit patterns of equal length and
            performs the logical inclusive OR operation on each pair of corresponding bits.
                    0101 (decimal 5)
                 OR 0011 (decimal 3)
                  = 0111 (decimal 7)
        ~ - bitwise NOT
            The bitwise NOT, or complement, is a unary operation that performs logical negation
            on each bit, forming the ones' complement of the given binary value.
            Bits that are 0 become 1, and those that are 1 become 0.
                NOT 0111  (decimal 7)
                  = 1000  (decimal 8)
     */
    private BigDecimal multiplication() {
        BigDecimal result = exponentiation();
        while (true) {
            char next = peek();
            switch(next) {
                case '*' :
                    getNext();
                    result = result.multiply(exponentiation());
                    continue;
                case '/' :
                    getNext();
                    result = result.divide(exponentiation());
                    continue;
                case '%' :
                    getNext();
                    result = result.remainder(exponentiation());
                    continue;
                case '~' :
                    getNext();
                    result = new BigDecimal(~(int) expression().intValue());
                    continue;
                case '&' :
                    getNext();
                    result = new BigDecimal(result.intValue() & expression().intValue());
                    continue;
                case '|' :
                    getNext();
                    result = new BigDecimal(result.intValue() | expression().intValue());
                    continue;
            }
            break;
        }
        return result;
    }

    /*
        Method to care about exponents
     */
    private BigDecimal exponentiation() {
        BigDecimal result = parenthesis();
        while (true) {
            char next = peek();
            switch(next) {
                case '^' :
                    getNext();
                    result = result.pow(parenthesis().intValue());
                    continue;
                case '!' :
                    if (result.doubleValue() % 1 != 0) throw new RuntimeException("Error :: Cannot calculate factorial of float number!");
                    getNext();
                    BigDecimal fact = new BigDecimal(1);
                    for (int i = 2; i <= result.intValue(); i++) {
                        fact = fact.multiply(BigDecimal.valueOf(i));
                    }
                    result = fact;
                    continue;
            }
            break;
        }
        return result;
    }

    /*
        Method to handle opening and closing parenthesis in expression
     */
    private BigDecimal parenthesis() {
        char current = peek();
        if (current == '(') {
            getNext(); // skipping (
            BigDecimal result = expression();
            current = peek();
            if (current != ')') throw new RuntimeException("Error :: Expected closing parenthesis");
            getNext(); // skipping )
            return result;
        }
        return primary();
    }

    /*
        Primary method to handle Numbers, Float numbers, unary operator, constants and pre-defined functions
     */
    private BigDecimal primary() {
        final char current = peek();
        if (current == '-') {
            // unary minus
            getNext();
            return tokenizeNumber().negate();
        }
        if (Character.isDigit(current)) {
            return tokenizeNumber();
        }
        if (Character.isLetter(current)) {
            return tokenizeFuncOrConstant();
        }
        return new BigDecimal(0);
    }

    /*
        Helper to take care of all constants and functions
     */
    private BigDecimal tokenizeFuncOrConstant() {
        final StringBuilder buffer = new StringBuilder();
        char current = peek();
        while (Character.isLetterOrDigit(current) || (current == '_') || (current == '$')) {
            buffer.append(current);
            current = getNext();
        }
        final String word = buffer.toString();
        // check for constants
        if (CONSTANTS.containsKey(word)) {
            return CONSTANTS.get(word);
        }
        // constant not found, check for function
        BigDecimal result = null;
        if (current == '(') {
            getNext(); // skipping (
            double value = expression().doubleValue();
            switch (word) {
                // sine function
                case "sin" : result = new BigDecimal(Math.sin(value)); break;
                // hyperbolic sine function
                case "sinh": result = new BigDecimal(Math.sinh(value)); break;
                // cosine function
                case "cos": result = new BigDecimal(Math.cos(value)); break;
                // tangent function
                case "tan": result = new BigDecimal(Math.tan(value)); break;
                // cotangent function
                case "ctg": result = new BigDecimal(1).divide(new BigDecimal(Math.tan(value)) ); break;
                // absolute value of a number
                case "abs": result = new BigDecimal(Math.abs(value)); break;
                // logarithm of a given number x
                case "ln": result = new BigDecimal(Math.log(value)); break;
                // base 10 logarithm of a number
                case "lg": result = new BigDecimal(Math.log10(value)); break;
                // square root
                case "sqrt": result = new BigDecimal(Math.sqrt(value)); break;
                // to radians
                case "toRadians": result = new BigDecimal(Math.toRadians(value)); break;
                // to degrees
                case "toDegrees": result = new BigDecimal(Math.toDegrees(value) ); break;
            }
            if (result == null) throw new RuntimeException("Error :: Cannot recognize function '" + word + "'");
            current = peek();
            if (current != ')') throw new RuntimeException("Error :: Expected closing parenthesis");
            getNext(); // skipping )
            return result;
        } else {
            throw new RuntimeException("Error :: Cannot recognize constant '" + word + "'");
        }
    }

    /*
        Numbers and floats parser
     */
    private BigDecimal tokenizeNumber() {
        final StringBuilder sb = new StringBuilder();
        char current = peek();
        while (true) {
            // float number support
            if (current == '.') {
                if (sb.indexOf(".") != -1) throw new RuntimeException("Error :: Invalid float number!");
            } else if (!Character.isDigit(current)) {
                break;
            }
            sb.append(current);
            current = getNext();
        }
        return new BigDecimal(sb.toString());
    }

    /*
        Method to return next char from the provided expression
     */
    private char getNext() {
        pos++;
        return peek();
    }

    /*
        Method to peek the next char in the provided expression
     */
    private char peek() {
        final int position = pos;
        if (position >= length) return EOF;
        final char current = code.charAt(position);
        if (" \n\r\t".indexOf(current) > -1) {
            // skip whitespaces
            pos++;
            return peek();
        }
        return current;
    }
}
