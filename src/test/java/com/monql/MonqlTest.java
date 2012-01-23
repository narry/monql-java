package com.monql;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.monql.grammar.ParseException;

public class MonqlTest {

    @Test
    public void testEqual() throws ParseException {
        String content = "hello";
        DBObject actual = Monql.where("a = :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", content);
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testNotEqual() throws ParseException {
        String content = "hello";
        DBObject actual = Monql.where("a != :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$ne", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testLessThan() throws ParseException {
        String content = "hello";
        DBObject actual = Monql.where("a < :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$lt", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testLessThanEqual() throws ParseException {
        String content = "hello";
        DBObject actual = Monql.where("a <= :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$lte", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testGreaterThan() throws ParseException {
        int content = 22;
        DBObject actual = Monql.where("a > :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$gt", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testGreaterThanEqual() throws ParseException {
        int content = 22;
        DBObject actual = Monql.where("a >= :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$gte", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testIn() throws ParseException {
        List<Integer> content = Arrays.asList(1, 2, 3);
        DBObject actual = Monql.where("a in :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$in", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testAll() throws ParseException {
        List<Integer> content = Arrays.asList(1, 2, 3);
        DBObject actual = Monql.where("a all :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$all", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testExists() throws ParseException {
        boolean content = true;
        DBObject actual = Monql.where("a exists :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$exists", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testAnd() throws ParseException {
        String content = "test";
        int content2 = 100;
        DBObject actual = Monql.where("a = :1 and b = :2").execute(content, content2);
        DBObject expected = new BasicDBObject().append("a", content).append("b", content2);
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testOr() throws ParseException {
        String content = "test";
        int content2 = 100;
        DBObject actual = Monql.where("a = :1 or b = :2").execute(content, content2);
        BasicDBList list = new BasicDBList();
        list.add(new BasicDBObject().append("a", content));
        list.add(new BasicDBObject().append("b", content2));
        DBObject expected = new BasicDBObject().append("$or", list);
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testCompound() throws ParseException {
        String name = "bob";
        int a = 100;
        int b = 2;
        DBObject actual = Monql.where("name = :1 and (a = :2 or b = :3)").execute(name, a, b);
        BasicDBList list = new BasicDBList();
        list.add(new BasicDBObject().append("a", a));
        list.add(new BasicDBObject().append("b", b));
        DBObject expected = new BasicDBObject().append("name", name).append("$or", list);
        Assert.assertEquals(expected.toString(), actual.toString());
        System.out.println(actual);
        System.out.println(expected);
    }

}
