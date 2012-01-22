package com.monql;

import com.mongodb.DBObject;
import com.monql.grammar.ParseException;
import com.monql.grammar.Query;
import com.monql.grammar.SimpleNode;


public abstract class Monql {

    protected SimpleNode root;
    
    public Monql(SimpleNode root) {
        this.root = root;
    }

    public static Monql where(String query) throws ParseException {
        SimpleNode root = new Query(query).parse();
        return new WhereMonql(root);
    }
    
    abstract public DBObject execute(Object... objs);
    
}


