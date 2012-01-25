package com.monql.grammar;

/**
 * 抽象语法树中的and/or节点
 * 
 * @author monql
 * @since 2012-1-25 下午3:36:09
 */
public class ASTAnd extends SimpleNode {

    private String op;

    public Object jjtAccept(QueryVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public ASTAnd(int id) {
        super(id);
    }

    public ASTAnd(Query p, int id) {
        super(p, id);
    }

    @Override
    public String toString() {
        return op != null ? op : super.toString();
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

}
