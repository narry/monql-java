package com.monql;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.DBObject;
import com.monql.grammar.QueryVisitorImpl;
import com.monql.grammar.SimpleNode;


final public class WhereMonql extends Monql {
    
    WhereMonql(SimpleNode root) {
        super(root);
    }
    
    @Override
    public DBObject execute(Object... objs) {
        Map<String, Object> params = new HashMap<String, Object>();
        for (int i = 0; i < objs.length; i++) {
            Object obj = objs[i];
            params.put(":" + (i + 1), obj);
        }
        return (DBObject) root.jjtAccept(QueryVisitorImpl.INSTANCE, params);
    }
    
}
