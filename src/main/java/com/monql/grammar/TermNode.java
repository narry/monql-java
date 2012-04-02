package com.monql.grammar;

import com.monql.operator.Operator;
import com.monql.operator.OperatorFactory;


/**
 * 抽象语法树中的表达式节点
 * 
 * @author monql
 * @since 2012-1-25 下午3:36:45
 */
public class TermNode extends SimpleNode {

    private String key;

    private Operator operator;

    private Integer paramNum;
    
    private TermNode preTermNode; 
    
    private TermNode nextTermNode;

    public Object jjtAccept(WhereParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public TermNode(int id) {
        super(id);
    }

    public TermNode(WhereParser p, int id) {
        super(p, id);
    }

    @Override
    public String toString() {
        return key + " " + operator + " :" + paramNum;
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

    public Integer getParamNum() {
        return paramNum;
    }

    public void setParamNum(String paramNum) {
        this.paramNum = Integer.valueOf(paramNum.substring(1));
    }

    public TermNode getPreTermNode() {
        return preTermNode;
    }
    
    public void setPreTermNode(TermNode preTermNode) {
        this.preTermNode = preTermNode;
    }

    public TermNode getNextTermNode() {
        return nextTermNode;
    }
    
    public void setNextTermNode(TermNode nextTermNode) {
        this.nextTermNode = nextTermNode;
    }

}
