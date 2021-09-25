package aeeims.math.calc.test;

import aeeims.math.calc.ast.ASTCalc;
import aeeims.math.calc.rdp.RDPCalc;

public class TestCalc_AST {

    public static void main(String[] args) throws Exception {
        runTest("TEST 01", (2 + 2 * 2), new ASTCalc("2 + 2 * 2"));
        runTest("TEST 02", (2 + 2 - 2 * 2 / 2), new ASTCalc("2 + 2 - 2 * 2 / 2"));
        runTest("TEST 03", (((2 + 2) * 2) / 2), new ASTCalc("((2 + 2) * 2) / 2"));
        runTest("TEST 04", ((8.5 + 0.75 * 2) * 3 - Math.pow(2, 4)), new ASTCalc("(8.5 + 0.75 * 2) * 3 - 2^4"));
        runTest("TEST 05", (Math.sin(Math.toRadians(90))), new ASTCalc("sin(toRadians(90))"));
        runTest("TEST 06", (12 & 27), new ASTCalc("12 & 27"));
        runTest("TEST 07", (3 * Math.PI * 50), new ASTCalc("3 * PI * 50"));
        runTest("TEST 08", (Math.pow(Math.E, 2) + 1), new ASTCalc("E^2 + 1"));
        runTest("TEST 09", (120 + Math.pow(5, 2) * 2 + 1), new ASTCalc("5!+5^2*2+1"));
        runTest("TEST 10", (~7), new ASTCalc("~7"));
        runTest("TEST 11", (32%3), new ASTCalc("32%3"));
        runTest("TEST 12", (20|3), new ASTCalc("20|3"));
        runTest("TEST 13", (4 * Math.sin(90) - 3 * Math.cos(3)), new ASTCalc("4 * sin(90) - 3 * cos(3)"));
        System.out.println( "=== VARIABLE ===" );
        System.out.println( "var a = 154;" );
        // testing internal variables
        double a = 154;
        ASTCalc cc = new ASTCalc("a / 2 / 2");
        cc.setVar("a", a);
        runTest("TEST 14", (a / 2 / 2), cc);
        System.out.println( "=== ERROR CASES ===" );
        try {
            new ASTCalc("3 / (6 - 4").execute();
        } catch (Exception e) {
            System.out.println( "TEST 15 : 3 / (6 - 4 : FAILED! " + e.getMessage());
        }
        try {
            new ASTCalc("2.15.25 + 8").execute();
        } catch (Exception e) {
            System.out.println( "TEST 16 : 2.15.25 + 8 : FAILED! " + e.getMessage());
        }
        try {
            new ASTCalc("x + 2").execute();
        } catch (Exception e) {
            System.out.println( "TEST 17 : x + 2 : FAILED! " + e.getMessage());
        }
        try {
            new RDPCalc("3.5!").execute();
        } catch (Exception e) {
            System.out.println( "TEST 18 : 3.5! : FAILED! " + e.getMessage());
        }
    }

    private static void runTest(String testName, double result, ASTCalc calc) {
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
