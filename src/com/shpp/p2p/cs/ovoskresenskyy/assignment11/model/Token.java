package com.shpp.p2p.cs.ovoskresenskyy.assignment11.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Token {

    private String value;
    public List<String> values = new ArrayList<>();

    private boolean isDigit; //2.0
    private boolean isFunction; //sin(a)
    private boolean isExpression; //2 + sin(a)

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return isDigit == token.isDigit
                && isFunction == token.isFunction
                && isExpression == token.isExpression
                && Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, isDigit, isFunction, isExpression);
    }

    @Override
    public String toString() {
        return "Token = " + values.toString();
    }
}
