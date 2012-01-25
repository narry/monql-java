package com.monql.visitor;

import java.util.List;

import com.monql.grammar.ASTAnd;
import com.monql.grammar.ASTRoot;
import com.monql.grammar.ASTTerm;
import com.monql.grammar.QueryVisitor;
import com.monql.grammar.SimpleNode;


/**
 * 生成terms列表，用于参数验证等
 * 
 * @author monql
 * @since 2012-1-25 下午4:29:42
 */
public class TermsQueryVisitor implements QueryVisitor {

    public static final QueryVisitor INSTANCE = new TermsQueryVisitor();
    
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
        return node.childrenAccept(this, data);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object visit(ASTTerm node, Object data) {
        ((List) data).add(node);
        return node;
    }

    private TermsQueryVisitor() {
    }
    
}
