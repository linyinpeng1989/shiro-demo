### Shiro支持三种授权（权限校验）方式
1. 编程式：通过写if/else授权代码块完成
    Subject subject = SecurityUtils.getSubject();
    if(subject.hasRole(“admin”)) {
        //有权限
    } else {
        //无权限
    }

2. 注解式：通过在执行的Java方法上放置相应的注解完成
    @RequiresRoles("admin")
    public void hello() {
        //有权限
    }

3. JSP/GSP标签：在JSP/GSP页面通过相应的标签完成
    @RequiresRoles("admin")
    public void hello() {
        //有权限
    }

### 字符串通配符规则
“资源标识符：操作：对象实例ID” 即对哪个资源的哪个实例可以进行什么操作。
其默认支持通配符权限字符串，“:”表示资源/操作/实例的分割；“,”表示操作的分割；“*”表示任意资源/操作/实例。


### Shiro对权限字符串缺失部分的处理
如“user:view”等价于“user:view:*”；而“organization”等价于“organization:*”或者
“organization:*:*”。可以这么理解，这种方式实现了前缀匹配。

另外如“user:*”可以匹配如“user:delete”、“user:delete”可以匹配如“user:delete:1”、
“user:*:1”可以匹配如“user:view:1”、“user”可以匹配“user:view”或“user:view:1”
等。即*可以匹配所有，不加*可以进行前缀匹配；

但是如“*:view”不能匹配
“system:user:view”，需要使用“*:*:view”，即后缀匹配必须指定前缀（多个冒号就需要
多个*来匹配）。