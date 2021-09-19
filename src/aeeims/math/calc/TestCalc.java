package aeeims.math.calc;

public class TestCalc {

    public static void main(String[] args) throws Exception {
        runTest("TEST 01", (2 + 2 * 2), new CompactCalc("2 + 2 * 2"));
        runTest("TEST 02", (2 + 2 - 2 * 2 / 2), new CompactCalc("2 + 2 - 2 * 2 / 2"));
        runTest("TEST 03", (((2 + 2) * 2) / 2), new CompactCalc("((2 + 2) * 2) / 2"));
        runTest("TEST 04", ((8.5 + 0.75 * 2) * 3 - Math.pow(2, 4)), new CompactCalc("(8.5 + 0.75 * 2) * 3 - 2^4"));
        runTest("TEST 05", (Math.sin(Math.toRadians(90))), new CompactCalc("sin(toRadians(90))"));
        runTest("TEST 06", (12 & 27), new CompactCalc("12 & 27"));
        runTest("TEST 07", (3 * Math.PI * 50), new CompactCalc("3 * PI * 50"));
        runTest("TEST 08", (Math.pow(Math.E, 2) + 1), new CompactCalc("E^2 + 1"));
        runTest("TEST 09", (120 + Math.pow(5, 2) * 2 + 1), new CompactCalc("5!+5^2*2+1"));
        runTest("TEST 10", (~7), new CompactCalc("~7"));
        runTest("TEST 11", (32%3), new CompactCalc("32%3"));
        runTest("TEST 12", (20|3), new CompactCalc("20|3"));
        runTest("TEST 13", (4 * Math.sin(90) - 3 * Math.cos(3)), new CompactCalc("4 * sin(90) - 3 * cos(3)"));
        System.out.println( "=== VARIABLE ===" );
        System.out.println( "var a = 154;" );
        // testing internal variables
        double a = 154;
        CompactCalc cc = new CompactCalc("a / 2 / 2");
        cc.setVar("a", a);
        runTest("TEST 14", (a / 2 / 2), cc);
        System.out.println( "=== ERROR CASES ===" );
        try {
            new CompactCalc("3 / (6 - 4").execute();
        } catch (Exception e) {
            System.out.println( "TEST 15 : 3 / (6 - 4 : FAILED! " + e.getMessage());
        }
        try {
            new CompactCalc("2.15.25 + 8").execute();
        } catch (Exception e) {
            System.out.println( "TEST 16 : 2.15.25 + 8 : FAILED! " + e.getMessage());
        }
        try {
            new CompactCalc("x + 2").execute();
        } catch (Exception e) {
            System.out.println( "TEST 17 : x + 2 : FAILED! " + e.getMessage());
        }
    }

    private static void runTest(String testName, double result, CompactCalc calc) throws Exception {
        System.out.print( testName + " : " );
        System.out.print( calc.getExpression() + " = " );
        double calcResult = calc.execute();
        if ( calcResult == result ) {
            System.out.println( calcResult + " : OK!" );
        } else {
            System.out.println( calcResult + " : FAILED! :: expected " + result );
        }
    }
}
