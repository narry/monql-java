package com.monql.visitor;

import java.util.Map;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.monql.grammar.ASTAnd;
import com.monql.grammar.ASTRoot;
import com.monql.grammar.ASTTerm;
import com.monql.grammar.Node;
import com.monql.grammar.QueryVisitor;
import com.monql.grammar.SimpleNode;


/**
 * 生成DBObject
 * 
 * @author monql
 * @since 2012-1-25 下午3:12:59
 */
public class DBObjectQueryVisitor implements QueryVisitor {

    public static final QueryVisitor INSTANCE = new DBObjectQueryVisitor();
    
    @Override
    public Object visit(SimpleNode node, Object data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object visit(ASTRoot node, Object data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object visit(ASTAnd node, Object data) {
        DBObject dbObj = new BasicDBObject();
        if (node.getOp().equals("and")) {
            for (int i = 0; i < node.jjtGetNumChildren(); i++) {
                Node child = node.jjtGetChild(i);
                DBObject dbObjChild = (DBObject) child.jjtAccept(this, data);
                dbObj.putAll(dbObjChild);
            }
        } else if (node.getOp().equals("or")) {
            BasicDBList dbList = new BasicDBList();
            for (int i = 0; i < node.jjtGetNumChildren(); i++) {
                Node child = node.jjtGetChild(i);
                DBObject dbObjChild = (DBObject) child.jjtAccept(this, data);
                dbList.add(dbObjChild);
            }
            dbObj.put("$or", dbList);
        }
        return dbObj;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object visit(ASTTerm node, Object data) {
        String key = node.getKey();
        String paramNum = node.getParamNum();
        return node.getOperator().execute(key, ((Map) data).get(paramNum));
    }

    private DBObjectQueryVisitor() {
    }
    
}
