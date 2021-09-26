package aeeims.math.calc.ast.lib;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/*
 * Pre-defined constants
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public class Constants {

    private static Map<String, BigDecimal> CONSTANTS;

    static {
        CONSTANTS = new HashMap<>();
        CONSTANTS.put("PI", new BigDecimal(Math.PI));
        CONSTANTS.put("E", new BigDecimal(Math.E));
    }

    public static boolean isExists(String key) {
        return CONSTANTS.containsKey(key);
    }

    public static Number get(String key) {
        if (!isExists(key)) return 0.0;
        return CONSTANTS.get(key);
    }

    public static void set(String key, BigDecimal value) {
        CONSTANTS.put(key, value);
    }

}
