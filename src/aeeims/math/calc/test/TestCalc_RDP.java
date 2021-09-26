package aeeims.math.calc.test;

import aeeims.math.calc.rdp.RDPCalc;

import java.math.BigDecimal;

public class TestCalc_RDP {

    public static void main(String[] args) throws Exception {
        runTest("TEST 01", "0.3", new RDPCalc("0.1 + 0.2"));
        runTest("TEST 02", "6", new RDPCalc("2 + 2 * 2"));
        runTest("TEST 03", "2", new RDPCalc("2 + 2 - 2 * 2 / 2"));
        runTest("TEST 04", "4", new RDPCalc("((2 + 2) * 2) / 2"));
        runTest("TEST 05", "14.00", new RDPCalc("(8.5 + 0.75 * 2) * 3 - 2^4"));
        runTest("TEST 06", "1", new RDPCalc("sin(toRadians(90))"));
        runTest("TEST 07", "8", new RDPCalc("12 & 27"));
        runTest("TEST 08", "471.238898038468967399694520281627774238586425781250", new RDPCalc("3 * PI * 50"));
        runTest("TEST 09", "1", new RDPCalc("sqrt(123456789^(256-254))/(123456780+3^(sqrt(36)/2-1))"));
        runTest("TEST 10", "171", new RDPCalc("5!+5^2*2+1"));
        runTest("TEST 11", "-8", new RDPCalc("~7"));
        runTest("TEST 12", "2", new RDPCalc("32%3"));
        runTest("TEST 13", "23", new RDPCalc("20|3"));
        runTest("TEST 14", "6.5459641442035676472954719429253600537776947021484375", new RDPCalc("4 * sin(90) - 3 * cos(3)"));
        System.out.println( "=== VARIABLE ===" );
        System.out.println( "var a = 154;" );
        int a = 154;
        RDPCalc cc = new RDPCalc("a / 2 / 2");
        cc.setVar("a", a);
        runTest("TEST 15", "38.5", cc);
        System.out.println( "=== ERROR CASES ===" );
        try {
            new RDPCalc("3 / (6 - 4").execute();
        } catch (Exception e) {
            System.out.println( "TEST 16 : 3 / (6 - 4 : FAILED! " + e.getMessage());
        }
        try {
            new RDPCalc("2.15.25 + 8").execute();
        } catch (Exception e) {
            System.out.println( "TEST 17 : 2.15.25 + 8 : FAILED! " + e.getMessage());
        }
        try {
            new RDPCalc("x + 2").execute();
        } catch (Exception e) {
            System.out.println( "TEST 18 : x + 2 : FAILED! " + e.getMessage());
        }
        try {
            new RDPCalc("3.5!").execute();
        } catch (Exception e) {
            System.out.println( "TEST 19 : 3.5! : FAILED! " + e.getMessage());
        }
        try {
            new RDPCalc("round(123.12)").execute();
        } catch (Exception e) {
            System.out.println( "TEST 20 : round(123.12) : FAILED! " + e.getMessage());
        }
    }

    private static void runTest(String testName, String result, RDPCalc calc) throws Exception {
        System.out.print( testName + " : " );
        System.out.print( calc.getExpression() + " = " );
        Number calcResult = calc.execute();
        if ( calcResult.toString().equals(result) ) {
            System.out.println( calcResult + " : OK!" );
        } else {
            System.out.println( calcResult + " : FAILED! :: expected " + result );
        }
    }
}
