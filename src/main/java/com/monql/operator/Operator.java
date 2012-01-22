package com.monql.operator;

import com.mongodb.DBObject;


public interface Operator {

    public DBObject execute(String key, Object value);
    
}
