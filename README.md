# HelloMySQL
网易云课堂java web微专业数据库开发

## 云课堂昵称
偶是小菜鸟

============================

## 总体说明
> 本项目为网易云课堂java web微专业数据库开发的课件部分.

本项目一共包含五个部分，分别为JDBC、数据库连接池、SQL注入与防范、事务以及MyBatis.

目录
=================

* [1. JDBC](#1-jdbc)
	* [1.1 JDBC重要意义](#11-jdbc重要意义)
	* [1.2 JDBC优势](#12-jdbc优势)
	* [1.3 JDBC体系架构](#13-jdbc体系架构)
	* [1.4 JDBC安装](#14-jdbc安装)
	* [1.5 JDBC API](#15-jdbc-api)
		* [1.5.1 Driver & DriverManager](#151-driver--drivermanager)
		* [1.5.2 Connection](#152-connection)
		* [1.5.3 Statement](#153-statement)
		* [1.5.4 ResultSet](#154-resultset)
		* [1.5.5 SQLException](#155-sqlexception)
	* [1.6 JDBC URL](#16-jdbc-url)
		* [1.6.1 介绍](#161-介绍)
		* [1.6.2 常用JDBC URL](#162-常用jdbc-url)
	* [1.7 构建步骤](#17-构建步骤)
	* [1.8 JDBC高级功能](#18-jdbc高级功能)
* [2. 数据库连接池](#2-数据库连接池)
	* []()
* [3. SQL注入与防范](#3-sql注入与防范)
* [4. 事务](#4-事务)
* [5. MyBatis](#5-mybatis)

## 1. JDBC

### 1.1 JDBC重要意义
&emsp;&emsp;应用程序通过调用统一接口可以访问任意数据库。JDBC屏蔽了客户端与服务器端交互协议的实现细节，只要能熟练的使用JDBC提供的标准接口，无需关心底层数据库的实现方式。  
&emsp;&emsp;对于Java应用程序，JDBC就是一个普通的架包，在应用程序中引用架包提供的类和方法，通过操作Java对象的方式，就可以获取数据库中的数据。  
&emsp;&emsp;对数据库厂商来说，JDBC就是一套接口规范，每个数据库厂商都必须实现JDBC定义的接口，确保用户通过JDBC正确的访问数据库。

### 1.2 JDBC优势
1. 简单。掌握一套接口就可以实现对任意数据库的访问。
2. 便捷。提高了程序开发的效率，压缩了开发的时间，让Java Web的开发变得更加快捷。
3. 移植性。有了统一的接口和规范，使得Java Web程序面向不同的数据库时，具备跨平台的可移植性。
4. 框架。JDBC仅仅提供了基本的接口和功能，满足最基本的功能需求。基于JDBC功能之上，可以定制更加强大的框架。

### 1.3 JDBC体系架构
<p align="center">
<img src="/img/JDBC/JDBC体系架构.png" alt="JDBC体系架构图">
</p>

1. JDBC API层。负责与Java Web进行通信。
2. JDBC Driver API数据库驱动层。负责与数据库建立连接。一般来说，下层的Driver都是由数据库厂商来提供的，负责和实现与自己提供的数据库的通信。
	
### 1.4 JDBC安装
安装方式有两种：
1. 数据库官网下载。从数据库官网下载JDBC驱动，下载下来的是一个JAR包，然后加入到Java Web项目中。
2. Maven管理。通过Maven配置JDBC驱动。

### 1.5 JDBC API
<p align="center">
<img src="/img/JDBC/JDBC-API.png" alt="JDBC API">
</p>

#### 1.5.1 Driver & DriverManager
* Driver是一个接口，定义了各个驱动程序都必须实现的功能，是驱动程序的抽象。通过操作Driver接口即可以实现对各个驱动程序的操作。
* DriverManager是Java的管理类，用户通过Class.forName的方式就可以向DriverManager注册一个驱动程序，然后通过DriverManager的getConnection方法就可以调用该驱动程序，建立到后端数据库的物理链接。

#### 1.5.2 Connection
代表Java应用程序对后端数据库的一条物理链接，基于这条链接可以执行一些SQL语句。
* 常用方法
```Java
Statment stmt = conn.createStatement();
```

#### 1.5.3 Statement
Statement对象是一个SQL的容器，容器中可以执行诸如insert、delete、update、select也就是增删改查等操作。

容器可以承载放入的一些SQL语句：
1. 通过Statement的executeQuery方法可以执行一个数据库查询，得到数据库查询结果的一个集合，集合是以一个ResultSet对象来表示；
2. 通过Statement对象执行更新、删除语句，这时候调用execute和executeUpdate方法，它返回的是一个int值的对象，它代表的是执行的语句影响了多少条数据库记录。
* 常用方法
```Java
ResultSet rs = stmt.executeQuery("select userName form user");
```

#### 1.5.4 ResultSet
ResultSet代表了一个SQL的查询结果。关系型数据库的本质是一个二元表，所以ResultSet对象实际也是一个由行和列所组成的二元表。

ResultSet对象的内部存在一个指针，用来指向当前的一个行记录，默认该指针指向第一行记录。

移动指针的方法：
* .next()		将指针指向下一行
* .previous()	将指针指向上一行
* .absolute()	将指针指向某一行
* .beforeFirst()将指针指向第一行的最开始部分。通过调用.beforeFirst().next()获取第一行记录
* .afterLast()	将指针指向最后一条记录的下一条记录

获取列结果：
* .getString(ColumnName/Index)
* .getInt(ColumnName/Index)
* .getObject(ColumnName/Index)

每个方法都有两种方式：获取列名和获取列序号（从0开始排序）。建议采用列名的方式获取结果，因为更加直观。

#### 1.5.5 SQLException
通过SQLException来表示异常。在应用程序的处理过程中，要通过捕获SQLException来进行相应的异常处理。

### 1.6 JDBC URL
#### 1.6.1 介绍
JDBC URL是后端数据库的唯一标识符，应用程序通过该标识符即可唯一的确定后端的某个数据库实例。它是由三个部分构成的：
1. 协议：这是固定和统一的，为jdbc；
2. 子协议：getConnection方法就是通过URL中子协议部分确定调用对应的驱动程序，来建立到后端数据库的物理链接。以MySQL数据库为例，就是mysql；
3. 子名称：由三个部分组成：主机、端口、数据库.

以下是一个JDBC URL（后文例子中称之为<strong>DB_URL</strong>）的示例：
<pre>
jdbc:mysql://<a href="#db_url" title="IP"><strong>10.164.172.20</strong></a>:<a href="#db_url" title="端口"><strong>3306</strong></a>/<a href="#db_url" title="数据库"><strong>cloud_study</strong></a>
</pre>
* 协议：jdbc
* 子协议：mysql
* 子名称：10.164.172.20:3306/cloud_study
	* 主机IP：10.164.172.20
	* 端口：3306
	* 数据库：cloud_study
	
#### 1.6.2 常用JDBC URL
1. MySQL
```Java
jdbc:mysql://<ip>:<port>/database
```
2. ORACLE
```Java
jdbc:oracle:thin@<ip>:<port>/database
```
3. SQL Server
```Java
jdbc:microsoft:sqlserver://<ip>:<port>;DatabaseName=database
```

### 1.7 构建步骤
构建一个完整的Java Web程序至少应该包含以下五个步骤：
1. 装载驱动程序
2. 建立数据库连接
3. 执行SQL语句
4. 获取执行结果
5. 清理环境

### 1.8 JDBC高级功能
#### 1.8.1 游标
游标提供一种客户端能够部分读取服务器端结果集的功能支持，允许分批读取SQL查询的结果。
* 如何使用游标
1. DB_URL中新增一个参数
<pre>
jdbc:mysql://&lt;ip&gt;:&lt;port&gt;/&lt;database&gt;<a href="#db_url"><strong>?useCursorFetch=true</strong></a>
</pre>

2. 使用`PreparedStatement`接口  
&emsp;&emsp;`PreparedStatement`继承自`Statement`接口，可以使用`PreparedStatement`接口来替代`Statement`接口，`PreparedStatement`接口相比`Statement`接口要求程序员在生成`PreparedStatement`的时候就要传入SQL语句，这个SQL语句是一个`参数格式化`的SQL，也就是说，SQL的`WHERE`过滤条件的参数都是通过`?`的形式来表示的，后续是通过`PreparedStatement`的`setString`和`setInt`方法来设置这些参数，然后进行执行。  
&emsp;&emsp;`PreparedStatement`有个`setFetchSize`接口，这个接口可以实现游标的功能。通过`setFetchSize`，就可以设置客户端JDBC每次从服务器端取回的记录的数量。

#### 1.8.2 

#### 1.8.3 

#### 1.8.4 


## 2. 数据库连接池


## 3. SQL注入与防范


## 4. 事务


## 5. MyBatis

