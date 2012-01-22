package com.monql.grammar;

import java.util.Map;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.monql.operator.OperatorFactory;


public class QueryVisitorImpl implements QueryVisitor {

    public static final QueryVisitor INSTANCE = new QueryVisitorImpl();
    
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
        if (node.op.equals("and")) {
            for (Node child : node.children) {
                DBObject dbObjChild = (DBObject) child.jjtAccept(this, data);
                dbObj.putAll(dbObjChild);
            }
        } else if (node.op.equals("or")) {
            BasicDBList dbList = new BasicDBList();
            for (Node child : node.children) {
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
        String op = node.getOp();
        String key = node.getKey();
        String paramNum = node.getValue();
        return OperatorFactory.getOperator(op).execute(key, ((Map) data).get(paramNum));
    }

    private QueryVisitorImpl() {
    }
    
}
