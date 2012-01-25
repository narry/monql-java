package com.monql.operator;


public abstract class AbstractOperator implements Operator {

    /**
     * Key names in inserted documents are limited as follows:
     *   The '$' character must not be the first character in the key name.
     *   The '.' character must not appear anywhere in the key name.
     */
    @Override
    public void checkKey(String key) {
        
        if (key == null)
            throw new IllegalArgumentException("key name must not be null.");
        
        if (key.length() == 0) 
            throw new IllegalArgumentException("key name's length must not be 0");
        
        if (key.charAt(0) == '$') 
            throw new IllegalArgumentException("The '$' character must not be the first character in the key name.");
        
        if (key.indexOf('.') > -1) 
            throw new IllegalArgumentException("The '.' character must not appear anywhere in the key name.");
    }
    
}
