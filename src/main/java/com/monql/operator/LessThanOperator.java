package com.monql.operator;

import java.util.Collection;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 小于(<)
 * 
 * @author monql
 * @since 2012-1-23 上午11:25:03
 */
public class LessThanOperator extends AbstractOperator {

    static Operator INSTANCE = new LessThanOperator();
    
    @Override
    public DBObject execute(Object value) {
        return new BasicDBObject(Operators.LT, value);
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
        return "<";
    }
    
}
