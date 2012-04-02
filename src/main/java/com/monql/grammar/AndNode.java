package com.monql.grammar;

/**
 * 抽象语法树中的and/or节点
 * 
 * @author monql
 * @since 2012-1-25 下午3:36:09
 */
public class AndNode extends SimpleNode {

    public Object jjtAccept(WhereParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public AndNode(int id) {
        super(id);
    }

    public AndNode(WhereParser p, int id) {
        super(p, id);
    }

}
