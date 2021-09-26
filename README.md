<h1>CompactCalc</h1>
Java examples for parsing/calculating complex mathematical expressions.<br><br>
<b>AST</b> : Implementing  
<a href="https://en.wikipedia.org/wiki/Abstract_syntax_tree">abstract syntax tree</a> method with lexical analyzer and parser.<br><br>
<b>RDP</b> : Implementing 
<a href="https://en.wikipedia.org/wiki/Recursive_descent_parser">recursive descent parser</a> method. All in one class. 
Check my <a href="https://github.com/edward-ap/ZUtils">example of usage RDP calculator</a> as webMethods Integraiton Server service.
<h1>Usage</h1>
System.out.println( new RDPCalc("(8.5 + 0.75 * 2) * 3 - 2^4").execute() );
<h1>Output of test.TestCalc_AST/RDP class</h1>
TEST 01 : 0.1 + 0.2 = 0.3 : OK!<br>
TEST 02 : 2 + 2 * 2 = 6 : OK!<br>
TEST 03 : 2 + 2 - 2 * 2 / 2 = 2 : OK!<br>
TEST 04 : ((2 + 2) * 2) / 2 = 4 : OK!<br>
TEST 05 : (8.5 + 0.75 * 2) * 3 - 2^4 = 14.00 : OK!<br>
TEST 06 : sin(toRadians(90)) = 1 : OK!<br>
TEST 07 : 12 & 27 = 8 : OK!<br>
TEST 08 : 3 * PI * 50 = 471.238898038468967399694520281627774238586425781250 : OK!<br>
TEST 09 : sqrt(123456789^(256-254))/(123456780+3^(sqrt(36)/2-1)) = 1 : OK!<br>
TEST 10 : 5!+5^2*2+1 = 171 : OK!<br>
TEST 11 : ~7 = -8 : OK!<br>
TEST 12 : 32%3 = 2 : OK!<br>
TEST 13 : 20|3 = 23 : OK!<br>
TEST 14 : 4 * sin(90) - 3 * cos(3) = 6.5459641442035676472954719429253600537776947021484375 : OK!<br>
=== VARIABLE ===<br>
var a = 154;<br>
TEST 15 : a / 2 / 2 = 38.5 : OK!<br>
=== ERROR CASES ===<br>
TEST 16 : 3 / (6 - 4 : FAILED! Error :: Expected closing parenthesis<br>
TEST 17 : 2.15.25 + 8 : FAILED! Error :: Invalid float number!<br>
TEST 18 : x + 2 : FAILED! Error :: Cannot recognize constant 'x'<br>
TEST 19 : 3.5! : FAILED! Error :: Cannot calculate factorial of float number!<br>
TEST 20 : round(123.12) : FAILED! Error :: Cannot recognize function 'round'<br>
