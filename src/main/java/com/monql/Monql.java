package com.monql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mongodb.DBObject;
import com.monql.grammar.ASTTerm;
import com.monql.grammar.ParseException;
import com.monql.grammar.Query;
import com.monql.grammar.SimpleNode;
import com.monql.operator.Operator;
import com.monql.visitor.TermsQueryVisitor;


public abstract class Monql {

    final protected SimpleNode root;
    
    final protected Map<Integer, Operator> operatorMap;

    public Monql(SimpleNode root, Map<Integer, Operator> operatorMap) {
        this.root = root;
        this.operatorMap = operatorMap;
    }

    public static Monql where(String query) throws ParseException {
        SimpleNode root = new Query(query).parse(); // 进行语法分析
        
        List<ASTTerm> terms = new ArrayList<ASTTerm>();
        root.jjtAccept(TermsQueryVisitor.INSTANCE, terms); // 遍历语法树得到terms，用于简单的参数检测
        
        Map<Integer, Integer> paramNumMap = new HashMap<Integer, Integer>();
        for (ASTTerm term : terms) {
            int paramNum = term.getParamNum();
            Integer count = paramNumMap.get(paramNum);
            if (count == null)
                count = 0;
            paramNumMap.put(paramNum, ++count);
        }
        
        // 不能有重复的paramNum
        for (Entry<Integer, Integer> item : paramNumMap.entrySet()) {
            int count = item.getValue();
            if (count > 1) { 
                int paramNum = item.getKey();
                throw new IllegalArgumentException("paramNum :" + paramNum + " expected 1 but " + count + ".");
            }
        }
        
        // paramNum必须有序
        for (int i = 1; i <= paramNumMap.size(); i++) {
            if (!paramNumMap.containsKey(i)) 
                throw new IllegalArgumentException("need paramNum :" + i + " but not found.");
        }
        
        Map<Integer, Operator> operatorMap = new HashMap<Integer, Operator>();
        for (ASTTerm term : terms) {
            operatorMap.put(term.getParamNum(), term.getOperator());
        }
        return new WhereMonql(root, operatorMap);
    }
    
    abstract public DBObject execute(Object... objs);
    
}


