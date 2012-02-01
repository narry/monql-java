package com.monql;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MonqlTest {

    @Test
    public void testEqual() {
        String content = "hello";
        DBObject actual = Monql.where("a = :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", content);
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testNotEqual() {
        String content = "hello";
        DBObject actual = Monql.where("a != :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$ne", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testLessThan() {
        String content = "hello";
        DBObject actual = Monql.where("a < :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$lt", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testLessThanEqual() {
        String content = "hello";
        DBObject actual = Monql.where("a <= :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$lte", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testGreaterThan() {
        int content = 22;
        DBObject actual = Monql.where("a > :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$gt", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testGreaterThanEqual() {
        int content = 22;
        DBObject actual = Monql.where("a >= :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$gte", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testIn() {
        List<Integer> content = Arrays.asList(1, 2, 3);
        DBObject actual = Monql.where("a in :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$in", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testAll() {
        List<Integer> content = Arrays.asList(1, 2, 3);
        DBObject actual = Monql.where("a all :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$all", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testExists() {
        boolean content = true;
        DBObject actual = Monql.where("a exists :1").execute(content);
        DBObject expected = new BasicDBObject().append("a", new BasicDBObject().append("$exists", content));
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testAnd() {
        String content = "test";
        int content2 = 100;
        DBObject actual = Monql.where("a = :1 and b = :2").execute(content, content2);
        DBObject expected = new BasicDBObject().append("a", content).append("b", content2);
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void testOr() {
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
    public void testCompound() {
        String name = "bob";
        int a = 100;
        int b = 2;
        DBObject actual = Monql.where("name = :1 and (a = :2 or b = :3)").execute(name, a, b);
        BasicDBList list = new BasicDBList();
        list.add(new BasicDBObject().append("a", a));
        list.add(new BasicDBObject().append("b", b));
        DBObject expected = new BasicDBObject().append("name", name).append("$or", list);
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        Monql.where("a = :1").execute(Arrays.asList(100, 200));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException2() {
        Monql.where("a exists :1").execute(1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException3() {
        Monql.where("a in :1").execute(1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMultiParamNum() {
        Monql.where("a = :1 or b = :1");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testErrorParamNum() {
        Monql.where("a = :1 or b = :3");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParseException() {
        Monql.where("a > 1");
    }
    
    @Test
    public void testCache() {
        Monql a = Monql.where("a > :1");
        Monql b = Monql.where("a > :1");
        Monql c = Monql.where("a > :1");
        Assert.assertEquals(a, b);
        Assert.assertEquals(a, c);
    }

}
