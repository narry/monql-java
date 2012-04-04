package com.monql.visitor;

import java.util.HashMap;
import java.util.Map;

import com.monql.grammar.AndNode;
import com.monql.grammar.Node;
import com.monql.grammar.OrNode;
import com.monql.grammar.RootNode;
import com.monql.grammar.SimpleNode;
import com.monql.grammar.TermNode;
import com.monql.grammar.WhereParserVisitor;


/**
 * 将key相同的term合并
 * 
 * @author monql
 * @since 2012-4-2 下午4:38:11
 */
public class TermsMergerVisitor implements WhereParserVisitor {
    
    public static final WhereParserVisitor INSTANCE = new TermsMergerVisitor();

    @Override
    public Object visit(SimpleNode node, Object data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object visit(RootNode node, Object data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object visit(OrNode node, Object data) {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(AndNode node, Object data) {
        Map<String, TermNode> map = new HashMap<String, TermNode>();
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            Node child = node.jjtGetChild(i);
            if (child instanceof TermNode) {
                TermNode termNode = (TermNode) child;
                String key = termNode.getKey();
                TermNode preTermNode = map.get(key);
                if (preTermNode != null) { // 前面term的key与当前term的key相同
                    preTermNode.setNextTermNode(termNode);
                    termNode.setPreTermNode(preTermNode);
                } 
                map.put(key, termNode);
            } else {
                child.jjtAccept(this, data);
            }
        }
        return null;
    }

    @Override
    public Object visit(TermNode node, Object data) {
        return null;
    }
    
    private TermsMergerVisitor() {
        
    }
    
}
