package com.monql.operator;

import java.util.Collection;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 大于(>)
 * 
 * @author monql
 * @since 2012-1-23 上午11:27:28
 */
public class GreaterThanOperator extends AbstractOperator {

    static Operator INSTANCE = new GreaterThanOperator();
    
    @Override
    public DBObject execute(String key, Object value) {
        return new BasicDBObject(key, new BasicDBObject(Operators.GT, value));
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
        return ">";
    }
    
}
