package aeeims.math.calc.ast.lib;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
 * pre-defined functions
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class Functions {

    private static List<String> FUNCTIONS;

    static {
        FUNCTIONS = new ArrayList<>();
        FUNCTIONS.add("sin");
        FUNCTIONS.add("sinh");
        FUNCTIONS.add("cos");
        FUNCTIONS.add("tan");
        FUNCTIONS.add("ctg");
        FUNCTIONS.add("abs");
        FUNCTIONS.add("ln");
        FUNCTIONS.add("lg");
        FUNCTIONS.add("sqrt");
        FUNCTIONS.add("toRadians");
        FUNCTIONS.add("toDegrees");
    }

    public static boolean isExists(String key) {
        return FUNCTIONS.contains(key);
    }

    public static BigDecimal execute(String key, BigDecimal value) {
        switch (key) {
            // sine function
            case "sin" : return new BigDecimal(Math.sin(value.doubleValue()));
            // hyperbolic sine function
            case "sinh": return new BigDecimal(Math.sinh(value.doubleValue()));
            // cosine function
            case "cos": return new BigDecimal(Math.cos(value.doubleValue()));
            // tangent function
            case "tan": return new BigDecimal(Math.tan(value.doubleValue()));
            // cotangent function
            case "ctg": return new BigDecimal(1).divide(new BigDecimal(Math.tan(value.doubleValue())) );
            // absolute value of a number
            case "abs": return new BigDecimal(Math.abs(value.doubleValue()));
            // logarithm of a given number x
            case "ln": return new BigDecimal(Math.log(value.doubleValue()));
            // base 10 logarithm of a number
            case "lg": return new BigDecimal(Math.log10(value.doubleValue()));
            // square root
            case "sqrt": return new BigDecimal(Math.sqrt(value.doubleValue()));
            // to radians
            case "toRadians": return new BigDecimal(Math.toRadians(value.doubleValue()));
            // to degrees
            case "toDegrees": return new BigDecimal(Math.toDegrees(value.doubleValue()));
        }
        throw new RuntimeException("Error :: Cannot recognize function '" + key + "'");
    }

}
