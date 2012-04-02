package com.monql.operator;

import com.mongodb.DBObject;

/**
 * 操作符接口
 * 
 * @author monql
 * @since 2012-1-25 上午11:09:38
 */
public interface Operator {

    /**
     * 生成该操作符下的DBObject
     * 
     * @param key doc中的key
     * @param value doc中的value
     * @return
     */
    public DBObject execute(Object value);
    
    /**
     * 检查key
     * 
     * @param key doc中的key
     */
    public void checkKey(String key);
    
    /**
     * 检查value
     * 
     * @param value doc中的value
     */
    public void checkValue(Object value);
    
}
