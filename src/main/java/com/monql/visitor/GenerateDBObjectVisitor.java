package com.monql.visitor;

import java.util.Map;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.monql.grammar.AndNode;
import com.monql.grammar.Node;
import com.monql.grammar.OrNode;
import com.monql.grammar.RootNode;
import com.monql.grammar.SimpleNode;
import com.monql.grammar.TermNode;
import com.monql.grammar.WhereParserVisitor;


/**
 * 生成DBObject
 * 
 * @author monql
 * @since 2012-1-25 下午3:12:59
 */
public class GenerateDBObjectVisitor implements WhereParserVisitor {

    public static final WhereParserVisitor INSTANCE = new GenerateDBObjectVisitor();
    
    @Override
    public Object visit(SimpleNode node, Object data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object visit(RootNode node, Object data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object visit(AndNode node, Object data) {
        DBObject dbObj = new BasicDBObject();
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            Node child = node.jjtGetChild(i);
            DBObject dbObjChild = (DBObject) child.jjtAccept(this, data);
            dbObj.putAll(dbObjChild);
        }
        return dbObj;
    }
    
    @Override
    public Object visit(OrNode node, Object data) {
        DBObject dbObj = new BasicDBObject();
        BasicDBList dbList = new BasicDBList();
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            Node child = node.jjtGetChild(i);
            DBObject dbObjChild = (DBObject) child.jjtAccept(this, data);
            dbList.add(dbObjChild);
        }
        dbObj.put("$or", dbList);
        return dbObj;
    }
    

    @SuppressWarnings("rawtypes")
    @Override
    public Object visit(TermNode node, Object data) {
        if (node.getPreTermNode() != null) {
            return new BasicDBObject();
        }
        
        String key = node.getKey();
        Integer paramNum = node.getParamNum();
        Object value = ((Map) data).get(paramNum);
        DBObject dbObj = node.getOperator().execute(value);
        if (dbObj == null) { // 相等
            return new BasicDBObject(key, value);
        }
        
        TermNode nextTermNode = node.getNextTermNode();
        while (nextTermNode != null) {
            paramNum = nextTermNode.getParamNum();
            value = ((Map) data).get(paramNum);
            DBObject nextDbObj = nextTermNode.getOperator().execute(value);
            if (nextDbObj != null) {
                dbObj.putAll(nextDbObj);
            }
            nextTermNode = nextTermNode.getNextTermNode();
        }
        return new BasicDBObject(key, dbObj);
    }

    private GenerateDBObjectVisitor() {
    }
    
}




