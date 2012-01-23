package com.monql.operator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 小于(<)
 * 
 * @author monql
 * @since 2012-1-23 上午11:25:03
 */
public class LessThanOperator implements Operator {

    static LessThanOperator INSTANCE = new LessThanOperator();
    
    @Override
    public DBObject execute(String key, Object value) {
        return new BasicDBObject(key, new BasicDBObject(Operators.LT, value));
    }
    
}
