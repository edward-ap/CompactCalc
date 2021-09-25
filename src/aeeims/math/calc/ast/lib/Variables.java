package aeeims.math.calc.ast.lib;

import java.util.HashMap;
import java.util.Map;

/*
 * Runtime variables holder
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class Variables {

    private static Map<String, Double> VARS;

    public Variables() {
        VARS = new HashMap<>();
    }

    public static boolean isExists(String name) {
        return !VARS.isEmpty() && VARS.containsKey(name);
    }

    public static double get(String name) {
        if (!isExists(name)) throw new RuntimeException("Error :: Cannot recognize variable '" + name + "'");
        return VARS.get(name);
    }

    public static void set(String name, double value) {
        VARS.put(name, value);
    }

    public static void reset() {
        VARS = new HashMap<>();
    }
}
