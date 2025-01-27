# MVC设计模式
1. 开发模式1
   JavaBean(数据封装) + JSP
问题:在jsp页面中直接写java代码,这会导致jsp页面变得臃肿且维护困难
2. 开发模式2
   Servlet + JavaBean + JSP
这就是MVC设计模式

## MVC模式
M:模型层  JavaBean,封装数据  
v:视图层  JSP,页面显示  
C:控制层  Servlet,接受视图层的请求然后找模型层去处理,最后把响应数据返回给视图层
>逻辑清楚,便于维护,对大型项目友好  

![mvc设计模式和javaee三层的对应关系](mvc.png)

### 乱码问题
提交数据时再servlet上设置提交数据的编码格式为request.setContentType("UTF-8");
数据库表的编码格式为utf-8

### 分页功能
* 物理分页
>查询数据库时只查一页的数据就返回
优点:对内存的占用不是很大
缺点:需要频繁访问数据库

* 逻辑分页
>一次全部将数据库中的数据取出,然后放在内存中
优点:访问速度快
缺点:对内存的占用过大,内存可能溢出