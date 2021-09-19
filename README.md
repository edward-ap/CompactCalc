<h1>CompactCalc</h1>
Java mathematical parser/calculator for complex expressions. Implements 
<a href="https://en.wikipedia.org/wiki/Recursive_descent_parser" title="Recursive descent parser">recursive descent parser method</a>.
<h1>Usage</h1>
System.out.println( new CompactCalc("(8.5 + 0.75 * 2) * 3 - 2^4").execute() );
<h1>Output of TestCalc class</h1>
TEST 01 : 2 + 2 * 2 = 6.0 : OK!<br>
TEST 02 : 2 + 2 - 2 * 2 / 2 = 2.0 : OK!<br>
TEST 03 : ((2 + 2) * 2) / 2 = 4.0 : OK!<br>
TEST 04 : (8.5 + 0.75 * 2) * 3 - 2^4 = 14.0 : OK!<br>
TEST 05 : sin(toRadians(90)) = 1.0 : OK!<br>
TEST 06 : 12 & 27 = 8.0 : OK!<br>
TEST 07 : 3 * PI * 50 = 471.23889803846896 : OK!<br>
TEST 08 : E^2 + 1 = 8.389056098930649 : OK!<br>
TEST 09 : 5!+5^2*2+1 = 171.0 : OK!<br>
TEST 10 : ~7 = -8.0 : OK!<br>
TEST 11 : 32%3 = 2.0 : OK!<br>
TEST 12 : 20|3 = 23.0 : OK!<br>
TEST 13 : 4 * sin(90) - 3 * cos(3) = 6.545964144203568 : OK!<br>
=== VARIABLE ===<br>
var a = 154;<br>
TEST 14 : a / 2 / 2 = 38.5 : OK!<br>
=== ERROR CASES ===<br>
TEST 15 : 3 / (6 - 4 : FAILED! Error :: Expected closing parenthesis<br>
TEST 16 : 2.15.25 + 8 : FAILED! Error :: Invalid float number!<br>
TEST 17 : x + 2 : FAILED! Error :: Cannot recognize constant 'x'<br>
