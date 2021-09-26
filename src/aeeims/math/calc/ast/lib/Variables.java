package aeeims.math.calc.ast.lib;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/*
 * Runtime variables holder
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class Variables {

    private static Map<String, BigDecimal> VARS;

    public Variables() {
        VARS = new HashMap<>();
    }

    public static boolean isExists(String name) {
        return !VARS.isEmpty() && VARS.containsKey(name);
    }

    public static BigDecimal get(String name) {
        if (!isExists(name)) throw new RuntimeException("Error :: Cannot recognize variable '" + name + "'");
        return VARS.get(name);
    }

    public static void set(String name, BigDecimal value) {
        VARS.put(name, value);
    }

    public static void reset() {
        VARS = new HashMap<>();
    }
}
