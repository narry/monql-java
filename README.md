# monql-java

mongodb java工具，使用类似sql的语法生成mongodb查询对象

##设计思想

使用[mongo-java-driver](https://github.com/mongodb/mongo-java-driver)对[mongodb](http://www.mongodb.org/)中的数据进行查找，更新，删除操作时需要构造[DBObject](https://github.com/mongodb/mongo-java-driver/blob/master/src/main/com/mongodb/DBObject.java)对象。简单的DBObject对象构造起来容易，如(a=1)可以使用`new BasicDBObject("a", 1)`构造，但如果查询条件比较复杂，则DBObject对象的构造就不那么容易了。monql的诞生就是为了快速，灵活的构造DBObject对象。<br>
monql使用一种“直白的语言”构造DBObject对象，如(a = 1 or (b = "test" and c = "xxx"))，假设我们直接使用new的方式，会写不少代码，而且书写代码的过程中容易出现错误。使用monql的话，则可以直接将a = 1 or (b = "test" and c = "xxx")照搬，使用`Monql.where("a = :1 or (b = :2 and c = :3)").execute(1, "test", "xxx")`即可完成。其中:1,:2,:3代表需要传入参数的位置，在本例中，参数1会放入:1的位置，参数"test"会放入:2的位置，参数"xxx"会放入:3的位置。

##使用示例

1. 相等，如(a=1)<br>
普通方式`new BasicDBObject("a", 1)` <br>
使用monql`Monql.where("a = :1").execute(1)`
<br>
1. 大于，如(a>200)<br>
普通方式`new BasicDBObject().append("a", new BasicDBObject().append("$gt", 200))`<br>
使用monql`Monql.where("a > :1").execute(200)`
<br>
1. 与运算，如（a="hello" and b="world")<br>
普通方式`new BasicDBObject().append("a", "hello").append("b", "world")`<br>
使用monql`Monql.where("a = :1 and b = :2").execute("hello", "world")`
<br>
1. 或运算，如（a=100 or b="test")<br>
普通方式<br>
        `BasicDBList list = new BasicDBList();`<br>
        `list.add(new BasicDBObject().append("a", 100));`<br>
        `list.add(new BasicDBObject().append("b", "test"));`<br>
        `DBObject expected = new BasicDBObject().append("$or", list);`<br>
使用monql`Monql.where("a = :1 or b = :2").execute(100, "test")`
<br>
1. 目前支持的运算符<br>
or($or)<br>
=<br>
!=($ne)<br>
<($lt)<br>
<=($lte)<br>
>($gt)<br>
>=($gte)<br>
in($in)<br>
all($all)<br>
exists($exists)
<br>
1. [更多测试用例](https://github.com/javacc/monql-java/blob/master/src/test/java/com/monql/MonqlTest.java)
