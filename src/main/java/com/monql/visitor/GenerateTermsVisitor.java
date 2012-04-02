package com.monql.visitor;

import java.util.List;

import com.monql.grammar.AndNode;
import com.monql.grammar.OrNode;
import com.monql.grammar.RootNode;
import com.monql.grammar.SimpleNode;
import com.monql.grammar.TermNode;
import com.monql.grammar.WhereParserVisitor;


/**
 * 生成terms列表，用于参数验证等
 * 
 * @author monql
 * @since 2012-1-25 下午4:29:42
 */
public class GenerateTermsVisitor implements WhereParserVisitor {

    public static final WhereParserVisitor INSTANCE = new GenerateTermsVisitor();
    
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
        return node.childrenAccept(this, data);
    }
    
    @Override
    public Object visit(OrNode node, Object data) {
        return node.childrenAccept(this, data);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object visit(TermNode node, Object data) {
        ((List) data).add(node);
        return node;
    }

    private GenerateTermsVisitor() {
    }
    
}
