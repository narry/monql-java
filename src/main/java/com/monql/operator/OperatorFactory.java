package com.monql.operator;


public class OperatorFactory {
    
    public static Operator getOperator(String op) {
        if ("=".equals(op)) {
            return EqualOperator.INSTANCE;
        } else if ("!=".equals(op)) {
            return NotEqualOperator.INSTANCE;
        }
        return null;
    }

}
