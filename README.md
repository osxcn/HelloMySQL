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
		* [1.8.1 游标](#181-游标)
		* [1.8.2 流方式](#182-流方式)
		* [1.8.3 批处理](#183-批处理)
		* [1.8.4 字符集设置](#184-字符集设置)
* [2. 数据库连接池](#2-数据库连接池)
	* [2.1 连接池存在的重要原因](#21-连接池存在的重要原因)
	* [2.2 连接池](#22-连接池)
		* [2.2.1 什么是连接池？](#221-什么是连接池)
		* [2.2.2 使用连接池](#222-使用连接池)
		* [2.2.3 高级配置](#223-高级配置)
* [3. SQL注入与防范](#3-sql注入与防范)
	* [3.1 数据库注入](#31-数据库注入)
	* [3.2 问题根源](#32-问题根源)
	* [3.3 解决方案](#33-解决方案)
	* [3.4 其他注意事项](#34-其他注意事项)
* [4. 事务](#4-事务)
	* [4.1 事务原理与开发](#41-事务原理与开发)
		* [4.1.1 事务特性](#411-事务特性)
			* [4.1.1.1 原子性](#4111-原子性)
			* [4.1.1.2 一致性](#4112-一致性)
			* [4.1.1.3 隔离性](#4113-隔离性)
			* [4.1.1.4 持久性](#4114-持久性)
		* [4.1.2 什么是事务？](#412-什么是事务)
		* [4.1.3 JDBC事务控制](#413-jdbc事务控制)
		* [4.1.4 检查点](#414-检查点)
		* [4.1.5 事务并发执行](#415-事务并发执行)
			* [4.1.5.1 脏读](#4151-脏读)
			* [4.1.5.2 不可重复读](#4152-不可重复读)
			* [4.1.5.3 幻读](#4153-幻读)
		* [4.1.6 事务隔离级别](#416-事务隔离级别)
		* [4.1.7 设置隔离级别](#417-设置隔离级别)
	* [4.2 死锁分析与解决](#42-死锁分析与解决)
		* [4.2.1 事务并发执行](#421-事务并发执行)
		* [4.2.2 死锁产生的必要条件](#422-死锁产生的必要条件)
		* [4.2.3 MySQL中的锁](#423-mysql中的锁)
		* [4.2.4 加锁方式](#424-加锁方式)
		* [4.2.5 哪些SQL需要持有锁](#425-哪些sql需要持有锁)
		* [4.2.6 SQL加锁分析](#426-sql加锁分析)
		* [4.2.7 分析死锁的常用方法](#427-分析死锁的常用方法)
* [5. MyBatis](#5-mybatis)
	* []()

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

[构建实例：JDBC基础-HelloJDBC](/src/main/java/com/micro/profession/jdbc/practice/HelloJDBC.java)

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

应用场景：查询时返回记录过多。

[构建实例：JDBC进阶-游标-HelloJDBC_cursor](/src/main/java/com/micro/profession/jdbc/practice/HelloJDBC_cursor.java)

#### 1.8.2 流方式
流方式就是将大字段的内容以二进制的方式按照区间进行划分，划分为多个区间，每次读取一个区间中的内容，在处理结束后再读取下一个区间。

应用场景：读取大字段数据。

[构建实例：JDBC进阶-流方式-LiuFang](/src/main/java/com/micro/profession/jdbc/practice/LiuFang.java)

#### 1.8.3 批处理
批处理就是通过发送一次SQL可以插入多条数据，即将多条SQL一次性进行发送。

批处理使用涉及到`Statement`的三个函数：
* addBatch()		把SQL打包成一个执行单元(Batch)
* executeBatch()	执行SQL
* clearBatch()		清空Batch中的SQL语句，准备下次执行

注：`PreparedStatement`同样可以使用这三个函数

应用场景：大量数据插入操作。

[构建实例：JDBC进阶-批处理-BatchTest](/src/main/java/com/micro/profession/jdbc/practice/BatchTest.java)

#### 1.8.4 字符集设置
1. 获取数据库编码设置
```mysql
show variables like '%character%';
```
2. 编码级别
* 实例级别：character_set_server
* 数据库级别：character_set_database
* 表级别：DEFAULT CHARSET=utf8
* 列级别：CHARACTER SET utf8
`
编码级别优先顺序：Server < Database < Table < Column
`
3. JDBC设置

`DB_URL` = `DB_URL` + `characterEncoding=utf8`

注：为了保证中文不出错，无论是数据库的还是JDBC的，建议设置为`utf8`

## 2. 数据库连接池
### 2.1 连接池存在的重要原因
1. **连接复用**
	1.  建立连接  
	通过`DriverManager`的`getConnection`方法可以建立一条Java应用程序到后端数据库的物理链接。虽然对我们来说，仅仅是一个方法的调用，但是在JDBC的数据库驱动中完成了大量客户端与服务器端的交互，这里以MySQL为例：  
	<p align="center">
	<img src="/img/JDBC/getConnection交互.png" alt="getConnection交互">
	</p>
	&emsp;&emsp;该方法首先会在客户端（也就是应用程序所在的服务器）发起一个到服务器端的TCP请求，然后服务器端随机生成一个密码种子返回给客户端，客户端利用这个密码种子和自己保存的数据库密码按照约定的加密算法可以计算得到一个加密的密码，然后再将这个加密的密码发送给MySQL服务器端进行验证，MySQL服务器端通过验证以后，返回给客户端确认该连接建立成功。一个`getConnection`方法需要四次客户端与服务器端的网络传输，由于跨机器的网络传输有较大的时间开销，所以`getConnection`也需要花费较多的时间，建立连接时间开销大。

	2. 多线程数据库访问  
	&emsp;&emsp;在实际业务场景中，当用户要访问Java程序时，会启动一个线程去处理用户的请求。在处理过程中，如果涉及到要访问后端数据库，需要创建一个`Connection`对象，建立到后端数据库的物理连接，在SQL执行完成以后，随着`Close`方法调用，数据库连接会被销毁。业务处理完成后，线程也会被释放。  
	在用户再一次发起请求时，又会被分配一个新的线程去处理。同样，涉及到数据库访问时，又会去创建一个新的数据库连接。连接执行完成之后，又被销毁。这样一来，每次用户请求，都需要创建一个数据库连接，每次都需要花费大量的时间在建立连接上，用户的响应时间会变的很慢。

	3. 连接复用  
	&emsp;&emsp;每个线程在使用数据库连接后并不是立即销毁，而是把它交给下一个需要访问数据库的线程，多个线程共用后端相同的物理连接，实现连接的复用，这就是为什么需要连接池的最重要的一个原因。
	<p align="center">
	<img src="/img/JDBC/连接复用.png" alt="连接复用">
	</p>
	&emsp;&emsp;以连接池的形式来管理数据库连接，每个需要访问数据库的线程每次从连接池中租借数据库连接，使用完毕后，归还给连接池，这样就可以实现连接的重复使用，避免了每次访问数据库都要去创建数据库连接，从创建改变为租借。
	
2. **限制连接**

&emsp;&emsp;数据库服务器端在处理数据库请求时，会在服务器端分配一定的资源，比如说内存，用来保存数据库查询的结果。在请求处理结束后，这些资源也会被释放。服务器端的资源是有限的，不能无限制的分配，当同时有多个数据库请求访问数据库的时候，服务器端能处理的连接数是有限的。当超过最大可分配的资源时，就会出现服务器宕机的故障。为了限制并发访问的连接数，数据库服务器端一般会设置最大的并发连接数。如果超过最大连接数，就会抛`too many connections`的异常。
<p align="center">
<img src="/img/JDBC/限制连接.png" alt="限制连接">
</p>
&emsp;&emsp;虽然服务器端做了必要的保护限制，但是对于应用程序，一方面服务器端直接抛SQL异常，对Java程序的处理不够友好，Java程序必须要捕获这些SQL异常进行异常处理；另一方面，不应该仅仅依靠服务器端的最大连接限制，应该在数据库访问客户端的时候，就应该实现这种限流，数据库连接必须有序、可控的被线程使用，一旦出现异常，也多了一种保护手段。
&emsp;&emsp;所以需要在客户端的Java程序中，就实现业务线程排队获取数据库连接，限制同时获得数据库连接的线程数，起到一个限流对后端数据库保护的作用。同时，连接数过多也会对后端数据库的性能造成严重的影响，因为连接数的增多，后端数据库就会存在更多的锁的冲突与检测，加大数据库服务器端的资源的消耗，所以应该保证应用程序有序、可控的获得数据库连接。

### 2.2 连接池
#### 2.2.1 什么是连接池？  
&emsp;&emsp;连接池本质上是一组Java架包，介于Java应用程序和JDBC数据库物理连接之间，负责帮助应用程序来管理JDBC连接。通过连接池暴露的接口，业务程序可以获取数据库连接，使用完毕以后可以将数据库连接归还到连接池，供下一个线程使用。
<p align="center">
<img src="/img/JDBC/连接池.png" alt="连接池">
</p>
&emsp;&emsp;连接池对JDBC连接实行有效的管理。在连接池中，JDBC连接不足时，会自动创建连接；在空闲的连接较多时，也会自动的销毁连接。在由多个线程同时获得数据库连接时，连接池还提供了排队等待的功能，能够保证应用程序有序的获得数据库连接。

#### 2.2.2 使用连接池
&emsp;&emsp;以DBCP连接池为例：  
&emsp;&emsp;DBCP连接池是Apache的一个开源的Java连接池项目，是Tomcat使用的连接池组件。DBCP连接池包括三个Java架包，分别是：  
* commons-dbcp.jar
* commons-pool.jar
* commons-logging.jar

1. 创建连接池对象  
&emsp;&emsp;DBCP使用`BasicDataSource`对象来表示一个连接池，所以首先要创建一个`BasicDataSource`对象。因为连接池只是JDBC连接的一个管理单位，它的底层数据库访问依然是通过JDBC来实现的，所以必须告诉DBCP必要的信息才能让DBCP帮助我们自动的创建连接。这些信息与我们创建一个JDBC连接是一致的，首先要包括一个`DB_URL`、数据库的名称、数据库的用户名和密码。
<p align="center">
<img src="/img/JDBC/连接池BasicDataSource.png" alt="连接池BasicDataSource">
</p>

创建BasicDataSource：
```Java
public static BasicDataSource ds = null;

public final static String DRIVER_NAME = "com.mysql.dbc.Driver";
public final static String USER_NAME = "root";
public final static String PASSWORD = "root";
public final static String DB_URL = "jdbc:mysql://localhost/cloud_study";

public static void dbpoolInit() {
    // 创建BasicDataSource对象
    ds = new BasicDataSource();
    // 给BasicDataSource对象传入数据库驱动、数据库URL、数据库用户名和密码
    ds.setUrl(DB_URL);
    ds.setDriverClassName(DRIVER_NAME);
    ds.setUsername(USER_NAME);
    ds.setPassword(PASSWORD);
}
```

2. 获取数据库连接  
在创建完`BasicDataSource`对象后，应用程序拥有了一个数据库连接池，然后就可以从连接池中获取数据库连接。通过`BasicDataSource`对象提供的`getConnection`方法，可以从连接池中租借一个数据库连接，然后通过这个连接访问后端的数据库。

3. 释放数据库连接  
使用完毕后，需要将连接归还给连接池供下一个线程使用。同样，调用`Connection`的`close`方法进行释放。

注：使用JDBC时，`Connection`的`close`方法实际上是销毁了连接；这里同样调用了`Connection`的`close`方法，却是将连接归还给了连接池，原因是在DBCP的实现中，将`Connection`的`close`方法进行了重写，将之前的销毁连接的逻辑改为了将数据库连接归还给数据库连接池的逻辑，所以虽然同样是调用`close`方法，但是实现的是不一样的。

[构建实例：数据库连接池-DBPoolTest](/src/main/java/com/micro/profession/jdbc/practice/DBPoolTest.java)

#### 2.2.3 高级配置
在实际开发过程中，会去设置一些数据库连接的参数来帮助我们优化连接池，提高数据库访问的性能。  
BasicDataSource参数：
* .setInitialSize()
* .setMaxTotal()
* .setMaxWaitMillis()
* .setMaxIdle()
* .setMinIdle()

1. setInitialSize  
&emsp;&emsp;当我们的应用第一次访问数据库的时候，往往会出现很慢的情况，这是因为连接池中没有数据库连接，需要去创建连接，这个过程是需要花费大量时间的。当连接创建完成后，后续的访问就不再需要重新创建连接，所以速度就变得很快了。  
&emsp;&emsp;如何提高第一次访问数据库的速度呢？  
&emsp;&emsp;可以在应用程序启动的时候，就向连接池中预置一定数量的数据库连接，来保证应用程序第一次访问的时候连接池中就有一定数量的连接。这样一来，第一次访问就不会变得很慢。可以通过`InitialSize`参数来设置连接池创建时预置的连接数，一般设置为`预期业务平均访问量`是比较合适的。
2. setMaxTotal  
&emsp;&emsp;当连接池没有空闲的连接，又有线程需要去访问数据库的时候，此时连接池会去创建一个新的数据库连接。但是如果此时的连接数已经达到了`MaxTotal`设定的最大值，则连接池就不会为线程新建一个数据库连接，而是强制该线程进入一个等待队列进行等待，直到有其他线程归还数据库连接时再分配。`MaxTotal`实际是去设置了客户端的一个最大连接数，起到一个限流保护数据库的作用。
3.setMaxWaitMillis  
进入队列的线程不可能无限制的等待，可以通过设置一个叫做`MaxWaitMillis`的参数来设置一个最大的等待时间。如果超过该时间，则应用程序会得到一个`SQLException`异常。
4. setMaxIdle  
当应用程序的线程使用完连接后，将连接归还给连接池。如果此时的连接池空闲连接数超过了`MaxIdle`设置的值后，则连接池会自动的销毁这个数据库连接。这样做的目的就是可以减少后端数据库的连接数来减少不必要的资源损耗。
5. setMinIdle  
如果连接池的空闲连接低于`MinIdle`设定的值后，连接池也会自动的触发去创建数据库连接，来保证连接池有足够的连接可以被租借。一般来说，为了避免连接池频繁的创建和销毁数据库连接，建议将`MinIdle`和`MaxIdle`设定为一样的值。

**DBCP定期检查**

BasicDataSource定期检查参数：
* .setTestWhileIdle(True)
* .setMinEvictableIdleTimeMillis()
* .setTimeBetweenEvictionRunsMills()

&emsp;&emsp;为了实现定期检查的功能，数据库服务端为了释放空闲等待的资源，默认会自动关闭空闲时间超过一定阈值的数据库连接。以MySQL数据库为例：  
MySQL数据库默认的服务器端会自动关闭空闲时间超过8小时的数据库连接。服务器端关闭连接以后，客户端的连接池却不知道连接已经被服务器端关闭，当应用程序的线程向连接池租借连接的时候，连接池有可能将这个失效的数据库连接租借给应用程序。当线程使用该连接的时候就会抛出一个`SQLException`的异常。  
&emsp;&emsp;为了避免上述情况的发生，尽量保证连接池中的连接都是有效的，可以定期的对连接池中的连接的空闲时间进行一个检查，在服务器端关闭连接之前，就保证把这个连接销毁掉，重新补充新的连接，来保证应用程序从连接池中租借的连接都是有效的。`TestWhileIdle`参数可以开启该功能。`MinEvictableIdleTimeMillis`来表示销毁连接的最小空闲时间，也就是说，只有当空闲时间超过该值的时候，会被连接池自动的销毁。`TimeBetweenEvictionRunsMills`表示检查运行时间的间隔。

注：建议将`MinEvictableIdleTimeMillis`的值一定要小于服务器端自动关闭连接的阈值时间，也就是说一定要小于8小时，这样才能有效的检测空闲时间超过该值的一个空闲连接，然后去主动的关闭它，补充新的连接。

[构建实例：数据库连接池-DBPoolDbcpImpl](/src/main/java/com/micro/profession/jdbc/practice/DBPoolDbcpImpl.java)

## 3. SQL注入与防范
### 3.1 数据库注入
&emsp;&emsp;在Web应用架构下，用户无法直接访问数据库，必须发送HTTP请求到Java应用服务器，然后由Java应用服务器来访问后端的数据库，所以恶意用户想要获取数据库中的核心价值数据就绕不开Java应用程序，唯一的途径就是利用业务程序的一个漏洞伪装自己的请求，欺骗业务程序，达到最终获取到数据库数据的目的。  
以下为根据之前JDBC内容所编写的根据用户名和密码获取用户信息的一段应用程序：
```Java
User user = null;
String sql = "select * from user where userName = '" + userName 
			+ "' and password = '" + password + "'";
rs = stmt.executeQuery(sql);
while (rs.next()) {
	user = new User();
	user.setUserName(rs.getString("userName"));
	user.setCardNum(rs.getString("cardNum"));
}
return user;
```
&emsp;&emsp;在这段程序中，程序会根据用户名和密码去查询后端数据库的User表，看是否有跟用户名和密码匹配的用户，如果数据库返回的记录不为空，这个应用程序也就会返回一个不为空的User对象，将用户的信息返回给调用者。
这段代码在Web应用中经常用于用户登录的场景。

| 用户名 | 密码 |
| :----: | :----: |
| ZhangSan | 123456 |

```mysql
select * from user where userName = 'ZhangSan' and password = '123456';
```
&emsp;&emsp;用户在表单中输入用户名和密码，然后发送给Java应用程序所在的业务服务器，然后Java应用程序利用SQL语句去检索数据库，根据用户名和密码去匹配相应的数据库记录，如果能找到响应的用户，这条数据库记录返回就不为空，Java应用程序就获得了用户的相关信息，也就确认了这个用户为合法用户。如果User对象为空的话，则会用户认证失败，这说明用户在表单中提交的用户名和密码不正确。这是理想的用户登录的场景，看似天衣无缝，非常完美，但是事实并非如此。

| 用户名 | 密码 |
| :----: | :----: |
| ZhangSan';-- | 111 |

&emsp;&emsp;使用如上表所示的用户名和密码，该用户名和密码实际并不存在于数据库，但是依旧登录成功了，看似严格的用户登录程序认证失效了，这是为什么呢？  
问题出在访问后端数据库的SQL语句上，如下所示的发送到后端数据库的SQL语句：

```mysql
select * from user where userName = 'ZhangSan';--' and password = '123456';
```
&emsp;&emsp;原先设计的SQL语句是包括UserName和password这两个检索条件的，但是实际上Java应用程序发送给后端数据库的SQL语句，已经不再是原先设计的场景的SQL语句的语义。实际上，由于用户名中的分号，导致原先的一条SQL语句变成了两条SQL语句，并且在第一条语句中去掉了SQL的检索条件。同时，SQL语句的后半部分，第二条SQL语句，因为两个`-`的注释符导致被数据库认为是注释的内容，自动被忽略掉了。最终，数据库检索的仅仅是用户名为`ZhangSan`的数据库记录，不再附有密码的检索条件，利用Java应用程序动态拼接SQL的漏洞，破坏了原先Java程序设定的SQL语义，欺骗了业务服务器，恶意获取了数据库中的数据。这样，他也获得了User对象，返回给了数据库，但是检索条件只是userName，并没有对password进行检索，这就导致了应用程序出现了漏洞，他在不知道密码的情况下也可以实现合法用户的认证登录。

&emsp;&emsp;`SQL注入`就是用户输入表单或者URL参数中输入SQL命令达到欺骗Java应用程序的目的，破坏原有SQL的语义，发送恶意的SQL语句到数据库，导致数据库信息遭到泄露的一个Java应用程序的漏洞。

### 3.2 问题根源
&emsp;&emsp;`SQL注入`漏洞的根源在于SQL语句本身是动态拼接而成的，在用户注入参数前，SQL本身的语义是不确定的，用户输入的参数如果带有SQL命令或者特殊字符，可能会导致原有的SQL的语义发生改变，原先设定SQL的语义是根据用户名密码作为WHERE的两个过滤条件，而实际执行的时候SQL语义只检索了用户名，密码被注释掉了。

### 3.3 解决方案
&emsp;&emsp;参数化SQL的实现方式：首先，确定SQL的语义；随后，传入SQL的参数，能够保证SQL传入的参数不改变原先SQL的语义。这里的实现方式是利用`Connection`的`.preparedStatement`方法来创建一个`preparedStatement`对象来实现的。
&emsp;&emsp;`preparedStatement`对象实现了`Statement`接口定义的所有方法，但是相对于`Statement`，它最大的优势在于提供了参数化SQL的实现方式。
```mysql
Select * from user where userName = ? AND password = ?
```
&emsp;&emsp;调用`Connection`的`preparedStatement`方法传入一个格式化的SQL，格式化SQL与平时写的SQL不同的地方在于：所有外部需要输入的参数都使用一个`?`来代替，这样就生成了一个`preparedStatement`对象，SQL语义伴随着对象也就确定了。这里的`?`号替代了参数，实现了一个占位符的功能。这条语句（`preparedStatement`函数）确定了SQL的语义。  
&emsp;&emsp;然后开始向SQL传入参数：按照格式化SQL注入参数从做到右的顺序，根据参数的类型，如果是整形的话，用`setInt`；字符型的话，用`setString`；如果是布尔类型的话，用`setBoolean`。参数有两个，第一个是序号，也就是参数从左到右的出现顺序，在上述所说的SQL中，userName在前，password在后，所以userName是1，password是2。第二个参数就是要输入的值了，比如UserName的值为`ZhangSan`，那么就写成`setString(1,'ZhangSan')`,同理password写成`setString(2,'123456')`。这样完成后，SQL里传入了参数，并且这个参数能保证不改变SQL语义，就可以防止SQL的注入了。`preparedStatement`是最基础也是最常用的预防SQL注入的方法。

[构建实例：SQL注入与防范-Login](/src/main/java/com/micro/profession/jdbc/practice/Login.java)

### 3.4 其他注意事项
* 严格的数据库权限管理
	* 仅给予Web应用访问数据库的最小权限；
	* 避免Drop table等权限；

| 权限 | 是否给予用户 |
| :--- | :--------- |
| Select | Y |
| Update | Y |
| Delete | 谨慎 |
| Insert | Y |
| All | No |

* 封装数据库错误
	* 禁止直接将后端数据库异常信息暴露给用户！
	* 对后端异常信息进行必要的封装，避免用户直接查看到后端异常！

<p align="center">
<img src="/img/JDBC/后端异常.png" alt="后端异常">
</p>

* 机密信息禁止明文存储
	* 涉密信息需要加密处理
	* mysql可以使用AES_ENCRYPT/AES_DECRYPT加密和解密

## 4. 事务
### 4.1 事务原理与开发
#### 4.1.1 事务特性
事务特性以银行转账为例:  
业务逻辑为：开始交易->张三账户扣除100元->李四账户增加100元->结束交易
##### 4.1.1.1 原子性  
张三扣除100元和李四增加100元，这两个过程必须作为一个整体来完成，要么全部执行，要么一个都不执行，不可分割。  
	整个交易必须是一个整体，要么全做，要不都不做！
##### 4.1.1.2 一致性  
在整个交易中，交易前和交易后，钱的总量是不变的。交易前张三有100元，李四0元，交易后张三把钱给了李四，张三0元，李四100元。整个交易前后钱都是一致的状态，称之为一致性。
##### 4.1.1.3 隔离性  
在张三和李四交易的同时，此时赵五需要给张三转账200元。假设两个交易在并发的执行：
<p align="center">
<img src="/img/JDBC/隔离性.png" alt="隔离性">
</p>
首先，交易一种，银行系统读取张三的账户，余额为100元。假如此时交易二同时在发生，银行系统也是读取了张三账户的余额，为100元。然后交易一中，因为张三给李四转账100元，张三的账户被扣除了100元，银行系统更新张三账户的余额为0元。此时交易二，赵五给张三转账200元，张三的账户因为之前读取了100元，此时加上200元，实际变为了300元。如果此时银行更新账户为300元，则银行实际上亏本了。  
实际上，张三原来有100元，赵五给了200元，张三给李四转了100元，实际的账户余额应为200元。因为两个交易是并发执行的，没有进行必要的隔离，导致出现了银行系统的错误。
所谓隔离性，就是指两个交易同时发生时，在一个交易执行之前，不能受到另一个交易的影响。

##### 4.1.1.4 持久性
两个人去银行交易转账，如果说交易结束之后，过了一段时间，因为银行机器坏了，两个人的交易不生效了，这个是没办法接受的。  
持久性：一旦交易结束，无论出现任何情况，交易都应该是永久生效的。

#### 4.1.2 什么是事务？
`事务（Transaction）`是并发控制的基本单位，指作为单个逻辑工作单元执行的一系列操作，而这些逻辑工作单元需要满足`ACID`特性。  
* 原子性：atomicity
* 一致性：consistency
* 隔离性：isolation
* 持久性：durability  
如果一个业务场景，有这四个特性的需求，就可以使用事务来实现。

#### 4.1.3 JDBC事务控制
如何实现事务控制？JDBC的`Connection`对象提供了三个方法可以帮助实现 一个事务逻辑：
* setAutoCommit() 开启事务  
将这个方法设为false，则这个`Connection`对象后续所有的执行的SQL语句都会被当做JDBC的一个事务来执行。如果设置为true，则表示`Connection`对象后续的每一个SQL都作为一个单独的语句执行，都是以非事务的方式来执行的。  
	注：默认情况下，都是以非事务的方式执行的，除非开始事务，将`setAutoCommit`设置为`false`。
* commit() 提交事务 
开启事务模式之后，`Connection`对象后续执行的所有SQL都将会作为一个事务来执行，直到调用`Connection`的`commit`方法。`commit`方法表示这个事务被提交，也就是说事务结束，整个事务执行的SQL语句的结果都将生效。
* rollback() 回滚事务  
如果当事务执行的过程中出现问题，需要回滚这个事务的时候，可以调用`Connection`对象的`rollback`方法。回滚的意思就是虽然执行了事务中的语句，但是可以回滚到事务开始之前的一个状态。

[构建实例：事务-未使用事务-TransactionTestInit](/src/main/java/com/micro/profession/jdbc/practice/Transaction/TransactionTestInit.java)

[构建实例：事务-使用事务-TransactionTest](/src/main/java/com/micro/profession/jdbc/practice/Transaction/TransactionTest.java)

#### 4.1.4 检查点
JDBC提供了断点处理和控制的功能。
* setSavePoint() 保存断点
* rollback(SavePoint savePoint) 回滚到断点

[构建实例：事务-回滚断点-TransactionTestSavePoint](/src/main/java/com/micro/profession/jdbc/practice/Transaction/TransactionTestSavePoint.java)

#### 4.1.5 事务并发执行
在事务的四个特性中，原子性、一致性、持久性都不难理解，但是对于隔离性，涉及到一个并发控制，理解起来较为困难。以下通过进一步的涉及隔离性的例子来帮助理解日常开发过程中经常涉及到的几个概念，依旧以银行转账为例：
##### 4.1.5.1 脏读  
例子：张三转账100元给李四，同时张三存银行200元  
<p align="center">
<img src="/img/JDBC/脏读.png" alt="脏读">
</p>
张三转账100元给李四:T1中，银行系统读取了张三的账户为100元，更新张三账户的余额为0元；

此时张三在银行又进行了存钱交易，这时候正好开始，同时也读取了张三账户余额，此时读取到的账户余额为0元，然后更新张三账户为200元；

如果此时T1张三转账给李四的交易失败了，交易进行了回滚，本来应该回滚到交易前的状态；但是此时由于余额为0元的状态被张三存钱的事务给读取了，对其他的事务产生了影响。本来最后应该有300元的张三，最后只有200元。

> 脏读：读取一个事务未提交的更新！

##### 4.1.5.2 不可重复读
例子：T1张三读取两次账户余额，T2张三存了200元
<p align="center">
<img src="/img/JDBC/不可重复读.png" alt="不可重复读">
</p>
T1中，张三读取了余额为100，然后在T2存钱后T1再次读取余额，变为300元，也就是说同一个事务两次读取同一个记录，结果不一样。

> 不可重复读：同一个事务中两次读取相同的记录，结果不一样！

##### 4.1.5.3 幻读
例子：T1读取所有的用户，包含张三和李四两个用户，T2新增用户赵五
<p align="center">
<img src="/img/JDBC/幻读.png" alt="幻读">
</p>

T1读取了所有的用户，包含张三和李四两个用户，但此时T2又新增了一个赵五的用户，然后T1再次读取用户列表的时候，发现读取的行记录的个数与之前读取的结果不一致。
> 幻读：两次读取的结果包含的行记录不一样！

> 幻读与不可重复读的区别是：幻读是读到的记录条数不一样，不可重复读是读到的值不一样。

#### 4.1.6 事务隔离级别
* 读未提交（read uncommitted）  
该级别允许出现脏读的情况
* 读提交（read committed）  
不允许出现脏读，但是允许出现不可重复读
* 重复读（repeatable read）  
不允许出现不可重复读，但是允许出现幻读
* 串行化（serializable）  
不允许出现幻读，但作为事务最高隔离级别，并发控制最为严格，所有的事务都是串行执行的，会导致数据库的性能变得极差。

> MySQL默认事务级别为repeatable read。

> 事务隔离级别越高，数据库性能越差，编程的难度越低。

#### 4.1.7 设置隔离级别
JDBC中，可以通过`Connection`的`getTransactionIsolation`和`setTransactionIsolation`来获得和设置事务的隔离级别。

### 4.2 死锁分析与解决
#### 4.2.1 事务并发执行
数据库记录：

| ID | UserName | Account | Corp |
| :-- | :------ | :------ | :--- |
| 1  | ZhangSan | 100     | Ali  |
| 2  | LiSi     | 0       | Ali  |

事务一： 张三需要给李四转账100元钱  
事务二： 将张三和李四的单位改为Netease

* 事务持锁  
两事务并发执行：  
首先：事务一更新记录一的Account数据，持有记录一的行锁；事务二更新记录二的Corp记录，持有记录二的行锁；此时两事务各自持有一个行锁  
接着：事务一要更新记录二的Account数据，需要持有李四的行锁，但是李四的行锁被事务二占据，所以事务一只能等待事务二完成之后释放李四这行的行锁才能记录执行；而事务二此时需要更新张三的Corp数据，也需要持有张三这行的行锁，但是这个行锁被事务一占据，同样，事务二需要等待事务一执行完成之后才能继续执行。  
这样就发生了事务一和事务二相互等待导致两个事务都无法执行下去的现象。

> 死锁： 指`两个`或者`两个以上`的事务在执行过程中，因`争夺锁资源`而造成的一种`互相等待`的现象。

#### 4.2.2 死锁产生的必要条件
1. 互斥（无法避免）  
* 定义：并发执行的事务为了进行必要的隔离保证执行正确，在事务结束前，需要对修改的数据库记录持锁，保证多个事务对相同数据库记录串行修改。
* 对于大型并发系统无法避免。

2. 请求和保持（无法避免）  
* 定义：一个事务需要申请多个资源，并且已经持有一个锁资源，在等待另一个锁资源
* 死锁仅发生在请求两个或者连个以上的锁对象
* 由于应用需要，难以消除

3. 不剥夺
* 定义：已经获得锁资源的事务，在未执行前，不能被其他事务强制剥夺，只能等本事务使用完时，由事务自己释放。
* 一般用于已经出现死锁时，通过破坏该条件达到解除死锁的目的

4. 环路等待
<p align="center">
<img src="/img/JDBC/环路等待.png" alt="环路等待">
</p>

* 定义：发生死锁时，必然存在一个事务——锁的环形链。
* 按照统一顺序获得锁，可以破坏该条件。
* 通过分析死锁事务之间的锁竞争关系，调整SQL的顺序，达到消除死锁的目的。

#### 4.2.3 MySQL中的锁
<p align="center">
<img src="/img/JDBC/MySQL中的锁.png" alt="MySQL中的锁">
</p>

X：排他锁：跟其他任何锁都是冲突的  
S：共享锁：多个事务可以共享一把锁，多个锁可以兼容

#### 4.2.4 加锁方式
1. 外部加锁
* 由应用程序添加，锁依赖关系系比较容易分析
* 共享锁（S）：select * from table lock in share mode
* 排他锁（X）：select * from table for update

2. 内部加锁
* 为了实现ACID特性，由数据库系统内部自动添加
* 加锁规则繁琐，与SQL执行计划、事务隔离级别、表索引结构有关

#### 4.2.5 哪些SQL需要持有锁
1. 快照读
* Innodb实现了多版本控制（MVCC），支持不加锁快照度
* select * from table where ……
* 能够保证同一个Select结果集是一致的
* 不能保证同一个事务内部，Select语句和其他语句的数据一致性，如果业务需要，需通过外部显式加锁。

2. 当前读
* Select * from table lock in share mode
* Select * from table for update
* Update form table set ……
* Insert into ……
* Delete from table ……

#### 4.2.6 SQL加锁分析
| ID | UserName | Account | Corp |
| :-- | :------ | :------ | :--- |
| 1  | ZhangSan | 100     | Ali  |
| 2  | LiSi     | 0       | Ali  |

```mysql
Update user set account = 0 where id = 1
```
这个Update操作就是直接在记录上加排他锁，此时如果是Select操作，是不会被阻塞的。因为是快照读，但是如果是`Select for update`或者`Select for share mode`的方式，都是会被阻塞的。  
```mysql
Select UserName from user where id=1 in share mode
```
当然，如果执行`Select for share mode`这条语句的话，是对行记录加了一个共享锁。此时如果其他事务要执行`Select for share mode`的话，对同一行记录还是可以执行的，不会被阻塞。但是如果外部其他事务要执行一个`Select for update`的话，则一定会被阻塞。

#### 4.2.7 分析死锁的常用方法
MySQL数据库会自动的检测死锁并强制回滚代价最小的事务，这个不需要开发人员关心。死锁的解除，MySQL会自动的帮助我们去做，但是我们需要在死锁解除之后去分析死锁产生的SQL语句，避免死锁再次产生。  
捕获死锁的命令：
```mysql
show engine innodb status
```
执行完这个命令后，会有一大段命令出现。其中有一个部分是包括死锁的，里面会列出发生死锁时，所等待的两个SQL语句，然后也会列出系统强制回滚的是哪个事务。知道了这些SQL语句，可以分析SQL语句的加锁方式，来调整SQL语句的顺序，或者改变SQL语句来保证按序获取锁资源，这样就可以有效避免死锁的产生。

## 5. MyBatis

