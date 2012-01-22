package com.monql;

import org.junit.Test;

import com.mongodb.DBObject;
import com.monql.grammar.ParseException;

public class MonqlTest {

    @Test
    public void testWhere() throws ParseException {
        DBObject query = Monql.where("a = :1 and (b = :2 or b = :3)").execute(1, "test", "ok");
        System.out.println(query);
    }

}
