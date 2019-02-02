# swift

Git上有一个比较火的MyBatis插件：MyBatis-Plus，使用时感觉Table类的表名和属性都需要使用注解配置，正好也看了部分MyBatis的源码，借鉴着
MyBatis-Plus写了这个工具，方便自己的使用。

### 如何使用

类似MyBatis-Plus

#### 1 创建数据库

```log
CREATE TABLE `user` (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  user_name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名';
  PRIMARY KEY (id)
);
```

#### 2 创建Java类

User

```java
@Data
public class User {
    private Long id;
    private String userName;
}
```

UserMapper

```java
public interface UserMapper extends BaseMapper<User>{
    
}
```

Swift会将泛型User类解析成对应的数据库表，并根据User表为BaseMapper中的方法生成对应的SQL语句

解析表的规则,默认如下：

表名=类名下划线格式 ，User -> user

字段名=类属性名下划线格式 ， id -> id 、userName -> user_name 

生成SQL规则，默认如下，只处理非空列

如下

```java
User user = new User();
user.serId(1);

// INSERT INTO user (id) VALUES (#{id})
userMapper.insert(user);

user.setUserName("ly");

// INSERT INTO user (id,user_name) VALUES (#{id},#{userName})
userMapper.insert(user);
```
