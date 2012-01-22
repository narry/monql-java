package com.monql.grammar;


public class ASTTerm extends SimpleNode {

    private String key;

    private String op;

    private String value;

    public Object jjtAccept(QueryVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public ASTTerm(int id) {
        super(id);
    }

    public ASTTerm(Query p, int id) {
        super(p, id);
    }

    @Override
    public String toString() {
        return key + op + value;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
