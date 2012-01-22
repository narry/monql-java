package com.monql.grammar;

public class ASTAnd extends SimpleNode {

    String op;

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
