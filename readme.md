# JSP+Servlet培训班作业管理系统

## 系列文章链接地址：

[慕课网](https://www.imooc.com/article/16001).
[CSDN](https://blog.csdn.net/woshisangsang/article/category/6564033).

## 必须读的后话：
#### 这部分文字是写完《JSP+Servlet培训班作业管理系统》很久后发布的，主要是因为当时写的时候是随性写的，想到哪写到哪儿，以至于前期写的非常适合新手学习，后期跑偏了非常不适合新手看，并且内容实现的不全面，一些常用的功能如权限管理、数据导入/导出、表格数据的搜索也都没有涉及。
#### 当然，直到《JSP+Servlet培训班作业管理系统[9] -登录功能的实现》之前的部分还是非常适合新手看的，后面的就不建议看了。
#### 当然，在我有时间的情况下，后续会推出一个完全依靠JSP+Servlet+HTML（基本不含CSS/JS）的系统，以方便大家学习如何打通前端和后端。
#### 在此之后，在我有时间的情况下，会推出一个依靠JSP+Servlet+HTML+Javascript（jQuery）+jqgrid（非常常用且好用的网页表格插件）实现的系统，让大家体会通过前端技术将浏览器转化为客户端的网站编程思想。
#### 在此之后，在我有时间的情况下，会推出一个依靠Java Spring SpringMVC MyBatis三大流行框架+前端Bootstrap+Javascript（jQuery）+jqgrid的网站，这个基本上就非常贴近实战了。

### 好的，其实想说的是，本系列请看到《JSP+Servlet培训班作业管理系统[9] -登录功能的实现》就可以了。

本系列主要是讲述如何用JSP和Servlet开发一个培训班管理系统，核心功能就是学生提交作业、教师批阅作业。在本系列开始之前有一下几点说明：
```
0，本系列承接【猫哥带你去战斗-Java Web开发】，依赖技术为简单的HTML+CSS+JSP+Servlet+MySql
1，先实现、后完善
2，该有的都会有，最后应该是可用的
3，本系列不关注前端，也不具备优美的前台界面
```
本篇的主要内容就是需求分析。

好的，因为需求就是猫哥自己提的，所以此处把需求说明白就行了。

1，系统名称

```
培训班作业管理系统
```
2，用户群体

```
培训学校校长、教师、学生
```
3，主要功能

```
校长：录入教师、学生信息；查看课程、学生作业完成情况；
教师：发布课程；发布课程作业；批阅作业；
学生：课程报名；提交作业；
```
4，主要逻辑

```
课程：一名教师可以发布多个课程，一个课程只有一名教师管理；一名学生可以选择多门课程，但是需要发布课程的教师审核才算选定课程。
作业：一个课程可以发布多次作业，作业发布后学生可以提交作业，教师可以批阅已提交作业。
成绩：作业成绩情况分4种：优秀、良好、合格、不合格。作业状态分为2种：未提交、已提交
```
5，由以上讨论，可以设计大体的菜单功能

```
校长：人员管理（教师、学生的增、删、改、查），信息查询（课程、作业情况查询）
教师：课程管理（增、删、改、查），作业管理（查询、打分），学生管理（选课批准）
学生：课程管理（选课报名），作业管理（查看历史作业、提交作业）
其他：登录、不同角色（校长、教师、学生）登录后菜单功能不同
```
好的，经过1-5的描述，感觉整个系统已大体定型，猫哥基本满意，准备动手。

