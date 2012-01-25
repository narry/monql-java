package com.monql.grammar;

import com.monql.operator.Operator;
import com.monql.operator.OperatorFactory;


/**
 * 抽象语法树中的表达式节点
 * 
 * @author monql
 * @since 2012-1-25 下午3:36:45
 */
public class ASTTerm extends SimpleNode {

    private String key;

    private Operator operator;

    private String paramNum;

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
        return key + operator + paramNum;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperatorFromOp(String op) {
        this.operator = OperatorFactory.getOperator(op);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getParamNum() {
        return paramNum;
    }

    public void setParamNum(String paramNum) {
        this.paramNum = paramNum;
    }

}
