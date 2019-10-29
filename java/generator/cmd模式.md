### 通过Windows系统的cmd命令来生成MyBatis的实体关系映射类操作步骤  

##### 1. 安装java的jdk(建议在8以上的版本)  
##### 2. 下载generator的jar包（包名：mybatis-generator-core-1.3.7.jar，具体下载地址：https://github.com/mybatis/generator/releases）  
##### 3. 数据库的驱动jar包：mysql、oracle等（我使用的mysql进行测试，对应的jar包：mysql-connector-java-8.0.13.jar）  
##### 4. 测试数据库（我的如下：）  
``` sql
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Id` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `CreateTime` datetime NOT NULL,
  `UpdateTime` datetime NOT NULL,
  `Remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
```
##### 5.新建generator.xml文件，其内容如下：
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
  <!--引入mysql-connector-java-8.0.13.jar位置 -->
  <classPathEntry location="D:/Program Files/Java\mybatis-generator-core-1.3.7/mysql-connector-java-8.0.13.jar" />

  <!--是否去除自动生成的注释 true:是；false：否 -->
  <context id="DB2Tables" targetRuntime="MyBatis3">
    <commentGenerator>
      <property name="suppressAllComments" value="true" />
    </commentGenerator>

    <!-- 数据库连接 -->
    <jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://192.168.3.10:3306/test_db" userId="root" password="ChinaNet910111">
    </jdbcConnection>

    <!--默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer true，把JDBC DECIMAL 和 NUMERIC 类型解析为java.math.BigDecimal-->
    <javaTypeResolver>
      <property name="forceBigDecimals" value="false" />
    </javaTypeResolver>

    <!--生成model模型，对应的包路径，以及文件存放路径(targetProject)，targetProject可以指定具体的路径    
        也可以使用“MAVEN”来自动生成，这样生成的代码会在target/generatord-source目录下-->
    <javaModelGenerator targetPackage="com.entity" targetProject="D:\Program Files\Java\mybatis-generator-core-1.3.7\src">
      <property name="enableSubPackages" value="true" />
      <!-- 从数据库返回的值被清理前后的空格  -->
      <property name="trimStrings" value="true" />
    </javaModelGenerator>

    <!--对应的mapper.xml文件  -->
    <sqlMapGenerator targetPackage="com.mapping" targetProject="D:\Program Files\Java\mybatis-generator-core-1.3.7\src">
      <property name="enableSubPackages" value="true" />
    </sqlMapGenerator>

    <!-- 对应的Mapper接口类文件 -->
    <javaClientGenerator type="XMLMAPPER" targetPackage="com.dao" targetProject="D:\Program Files\Java\mybatis-generator-core-1.3.7\src">
      <property name="enableSubPackages" value="true" />
    </javaClientGenerator>

    <!-- 生成那些表，tableName对应数据库表，domainObjectName对应实体类名 -->
    <table tableName="student" domainObjectName="Student" enableCountByExample="true" enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false"></table>
  </context>
</generatorConfiguration>
```

##### 6.创建run.bat并使用记事本填入一下的代码（每次生成只需要执行该文件即可）
``` bash
java -jar mybatis-generator-core-1.3.7.jar -configfile generator.xml -overwrite
```





