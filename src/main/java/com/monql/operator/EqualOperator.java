package com.monql.operator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class EqualOperator implements Operator {

    static EqualOperator INSTANCE = new EqualOperator();
    
    @Override
    public DBObject execute(String key, Object value) {
        return new BasicDBObject(key, value);
    }
    
}
