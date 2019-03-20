# swift

The name swift comes from the logo of MyBatis, the Swifttail. 
It can get the usual CURD functionality through inheritance like Jpa.

### how to use

#### table

```mysql
CREATE TABLE foo (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  string_value VARCHAR (64) ,
  del TINYINT NOT NULL DEFAULT 0 COMMENT 'del_flag',
  PRIMARY KEY (id)
);
```

#### JavaBean

User

```java
@Data
public class Foo {
    private Long id;
    private String stringValue;
    private Integer del;
}
```

#### mapper

```java
@Mapper
public interface FooMapper extends BaseMapper<Foo, Long> {
}
```

Swift will generate MapperStatement based on JavaBean. 
The default rule is to replace the hump rule with an underscore rule.

For example:

Foo -> foo
id -> id
stringValue -> string_value
del -> del
