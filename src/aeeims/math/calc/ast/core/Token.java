package aeeims.math.calc.ast.core;

/*
 * Token instance for the Lexical analysis
 *
 * @author Ed Aponasko
 * @version 1.0, January 2021
 */
public final class Token {

    private TokenType type;
    private String token;

    public Token(TokenType type, String tokenName) {
        this.token = tokenName;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " " + token;
    }

}
