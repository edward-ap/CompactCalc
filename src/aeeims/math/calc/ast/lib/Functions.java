package aeeims.math.calc.ast.lib;

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

    public static double execute(String key, double value) {
        switch (key) {
            // sine function
            case "sin" : return Math.sin(value);
            // hyperbolic sine function
            case "sinh": return Math.sinh(value);
            // cosine function
            case "cos": return Math.cos(value);
            // tangent function
            case "tan": return Math.tan(value);
            // cotangent function
            case "ctg": return 1 / Math.tan(value);
            // absolute value of a number
            case "abs": return Math.abs(value);
            // logarithm of a given number x
            case "ln": return Math.log(value);
            // base 10 logarithm of a number
            case "lg": return Math.log10(value);
            // square root
            case "sqrt": return Math.sqrt(value);
            // to radians
            case "toRadians": return Math.toRadians(value);
            // to degrees
            case "toDegrees": return Math.toDegrees(value);
        }
        throw new RuntimeException("Error :: Cannot recognize function '" + key + "'");
    }

}
