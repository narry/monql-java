package com.monql.operator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 匹配数组中的所有值(all)
 * @author monql
 * @since 2012-1-23 上午11:40:18
 */
public class AllOperator extends AbstractOperator {

    static Operator INSTANCE = new AllOperator();
    
    @Override
    public DBObject execute(String key, Object value) {
        return new BasicDBObject(key, new BasicDBObject(Operators.ALL, value));
    }
    
    @Override
    public void checkValue(Object value) {
        
    }
    
    @Override
    public String toString() {
        return "all";
    }
    
}
