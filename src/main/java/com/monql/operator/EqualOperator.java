package com.monql.operator;

import java.util.Collection;

import com.mongodb.DBObject;

/**
 * 等于(=)
 * 
 * @author monql
 * @since 2012-1-23 上午11:14:36
 */
public class EqualOperator extends AbstractOperator {

    static Operator INSTANCE = new EqualOperator();
    
    @Override
    public DBObject execute(Object value) {
        return null;
    }
    
    @Override
    public void checkValue(Object value) {
        if (value.getClass().isArray()) 
            throw new IllegalArgumentException("operator [" + toString() + "] expected a single value argument but an array argument.");
        if (value instanceof Collection)
            throw new IllegalArgumentException("operator [" + toString() + "] expected a single value argument but a collection argument.");
    }
    
    @Override
    public String toString() {
        return "=";
    }
    
}
