package com.monql.operator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 不等于(!=)
 * 
 * @author monql
 * @since 2012-1-23 上午11:16:28
 */
public class NotEqualOperator extends AbstractOperator {

    static Operator INSTANCE = new NotEqualOperator();
    
    @Override
    public DBObject execute(String key, Object value) {
        return new BasicDBObject(key, new BasicDBObject(Operators.NE, value));
    }
    
    @Override
    public void checkValue(Object value) {
    }
    
}
