package com.monql.operator;

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
    public DBObject execute(String key, Object value) {
        return new BasicDBObject(key, new BasicDBObject(Operators.IN, value));
    }
    
    @Override
    public void checkValue(Object value) {
    }
 
    @Override
    public String toString() {
        return "in";
    }
    
}
