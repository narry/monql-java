package com.monql.operator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 大于等于(>=)
 * 
 * @author monql
 * @since 2012-1-23 上午11:28:18
 */
public class GreaterThanEqualOperator implements Operator {

    static GreaterThanEqualOperator INSTANCE = new GreaterThanEqualOperator();
    
    @Override
    public DBObject execute(String key, Object value) {
        return new BasicDBObject(key, new BasicDBObject(Operators.GTE, value));
    }
    
}
