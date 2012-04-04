package com.monql;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.DBObject;
import com.monql.grammar.SimpleNode;
import com.monql.operator.Operator;
import com.monql.visitor.GenerateDBObjectVisitor;

/**
 * sql-->dbobject
 * 
 * @author monql
 * @since 2012-1-25 下午6:56:33
 */
final public class WhereMonql extends Monql {
    
    WhereMonql(SimpleNode root, Map<Integer, Operator> operatorMap) {
        super(root, operatorMap);
    }
    
    @Override
    public DBObject execute(Object... objs) {
        int count = operatorMap.size();
        if (objs.length != operatorMap.size())
            throw new IllegalArgumentException("need " + count + " params but found " + objs.length + ".");
        
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        for (int i = 0; i < objs.length; i++) {
            Object obj = objs[i];
            int paramNum = i + 1;
            operatorMap.get(paramNum).checkValue(obj);
            params.put(paramNum, obj);
        }
        
        DBObject dbObj = (DBObject) root.jjtAccept(GenerateDBObjectVisitor.INSTANCE, params);
        if (dbObj.keySet().isEmpty()) // 除非前面的程序bug，否则不会走到这一步
            throw new IllegalStateException("generated DBObject can't be empty.");
        return dbObj;
    }
    
}
