# swift

Git上有一个比较火的MyBatis插件：MyBatis-Plus，使用时感觉Table类的表名和属性都需要使用注解配置，正好也看了部分MyBatis的源码，借鉴着
MyBatis-Plus写了这个工具，方便自己的使用。

使用方式：

Mapper继承BaseMapper，对于泛型T将解析为Table，表名为类名，列名为字段名，统一转换为下划线格式，例如：

```java
@Data
public class User {
    private Long id;
    private String userName;
}
```

被解析为表`user`，字段为`id`和`user_name`

BaseMapper的方法会根据Table生成对于SQL。