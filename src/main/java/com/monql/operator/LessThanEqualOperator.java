package com.monql.operator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 小于等于(<=)
 * 
 * @author monql
 * @since 2012-1-23 上午11:24:48
 */
public class LessThanEqualOperator implements Operator {

    static LessThanEqualOperator INSTANCE = new LessThanEqualOperator();
    
    @Override
    public DBObject execute(String key, Object value) {
        return new BasicDBObject(key, new BasicDBObject(Operators.LTE, value));
    }
    
}
