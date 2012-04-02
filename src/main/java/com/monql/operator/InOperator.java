package com.monql.operator;

import java.util.Collection;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 匹配数组一个或多个值(in)
 * 
 * @author monql
 * @since 2012-1-23 上午11:30:34
 */
public class InOperator extends AbstractOperator {

    static Operator INSTANCE = new InOperator();
    
    @Override
    public DBObject execute(Object value) {
        return new BasicDBObject(Operators.IN, value);
    }
    
    @Override
    public void checkValue(Object value) {
        if (!value.getClass().isArray() && !(value instanceof Collection)) 
            throw new IllegalArgumentException("operator [" + toString() + "] expected an array or a collection argument but a single value argument.");
    }
 
    @Override
    public String toString() {
        return "in";
    }
    
}
