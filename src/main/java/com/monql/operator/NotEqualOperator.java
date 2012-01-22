package com.monql.operator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class NotEqualOperator implements Operator {

    static NotEqualOperator INSTANCE = new NotEqualOperator();
    
    @Override
    public DBObject execute(String key, Object value) {
        return new BasicDBObject(key, new BasicDBObject(MongoOps.$NE, value));
    }
    
}
