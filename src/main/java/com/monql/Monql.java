package com.monql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.monql.grammar.ParseException;
import com.monql.grammar.SimpleNode;
import com.monql.grammar.TermNode;
import com.monql.grammar.WhereParser;
import com.monql.operator.Operator;
import com.monql.visitor.GenerateTermsVisitor;
import com.monql.visitor.TermsMergerVisitor;


public abstract class Monql {

    final protected SimpleNode root; // 抽象语法树根节点
    
    final protected Map<Integer, Operator> operatorMap; // 抽象语法树中的操作符
    
    final private static ConcurrentMap<String, Monql> cache = new ConcurrentHashMap<String, Monql>(); // monql缓存

    public Monql(SimpleNode root, Map<Integer, Operator> operatorMap) {
        this.root = root;
        this.operatorMap = operatorMap;
    }

    public static Monql where(String query) {
        
        Monql monql = cache.get(query);
        if (monql != null) {
            return monql;
        }
        
        SimpleNode root = null;
        try {
            root = new WhereParser(query).parse(); // 进行语法分析
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        
        List<TermNode> terms = new ArrayList<TermNode>();
        root.jjtAccept(GenerateTermsVisitor.INSTANCE, terms); // 遍历语法树得到terms，用于简单的参数检测
        root.jjtAccept(TermsMergerVisitor.INSTANCE, terms); // 遍历语法树得到terms，用于简单的参数检测
        
        for (TermNode termNode : terms) { // 检查key是否合法
            termNode.getOperator().checkKey(termNode.getKey());
        }
        
        Map<Integer, Integer> paramNumMap = new HashMap<Integer, Integer>(); // 统计paramNum(如[:1])的出现次数
        for (TermNode term : terms) {
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
        for (TermNode term : terms) {
            operatorMap.put(term.getParamNum(), term.getOperator());
        }
        
        monql = new WhereMonql(root, operatorMap);
        Monql temp = cache.putIfAbsent(query, monql);
        if ( temp != null )
            return temp;
        return monql;
        
    }
    
    public static DBObject fields(List<String> fields) {
        DBObject dbObjFields = new BasicDBObject();
        if (fields != null) {
            for (String field : fields) {
                dbObjFields.put(field, 1);
            }
        }
        return dbObjFields;
    }
    
    abstract public DBObject execute(Object... objs);
    
}


