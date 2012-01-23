package com.monql.operator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 大于(>)
 * 
 * @author monql
 * @since 2012-1-23 上午11:27:28
 */
public class GreaterThanOperator implements Operator {

    static GreaterThanOperator INSTANCE = new GreaterThanOperator();
    
    @Override
    public DBObject execute(String key, Object value) {
        return new BasicDBObject(key, new BasicDBObject(Operators.GT, value));
    }
    
}
