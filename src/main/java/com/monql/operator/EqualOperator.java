package com.monql.operator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 等于(=)
 * 
 * @author monql
 * @since 2012-1-23 上午11:14:36
 */
public class EqualOperator implements Operator {

    static EqualOperator INSTANCE = new EqualOperator();
    
    @Override
    public DBObject execute(String key, Object value) {
        return new BasicDBObject(key, value);
    }
    
}
