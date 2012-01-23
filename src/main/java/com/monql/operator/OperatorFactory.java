package com.monql.operator;

/**
 * operator工厂类
 * 
 * @author monql
 * @since 2012-1-23 上午11:52:03
 */
public class OperatorFactory {
    
    public static Operator getOperator(String op) {
        if ("=".equals(op)) {
            return EqualOperator.INSTANCE;
        } else if ("!=".equals(op)) {
            return NotEqualOperator.INSTANCE;
        } else if ("<".equals(op)) {
            return LessThanOperator.INSTANCE;
        } else if ("<=".equals(op)) {
            return LessThanEqualOperator.INSTANCE;
        } else if (">".equals(op)) {
            return GreaterThanOperator.INSTANCE;
        } else if (">=".equals(op)) {
            return GreaterThanEqualOperator.INSTANCE;
        } else if ("in".equals(op)) {
            return InOperator.INSTANCE;
        } else if ("all".equals(op)) {
            return AllOperator.INSTANCE;
        } else if ("exists".equals(op)) {
            return ExistsOperator.INSTANCE;
        } else {
            throw new IllegalStateException("op " + op + " can't be supported");
        }
    }

}
