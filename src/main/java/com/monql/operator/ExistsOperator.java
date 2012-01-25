package com.monql.operator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 检查字段是否存在(exits)
 * 
 * @author monql
 * @since 2012-1-23 上午11:43:53
 */
public class ExistsOperator extends AbstractOperator {

    static Operator INSTANCE = new ExistsOperator();
    
    @Override
    public DBObject execute(String key, Object value) {
        return new BasicDBObject(key, new BasicDBObject(Operators.EXISTS, value));
    }
    
    @Override
    public void checkValue(Object value) {
        
    }
    
}
