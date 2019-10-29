**1.下载eclipse的Mybatis-Generator插件：**   
  [help]->[eclipse-marketplace] 进入插件安装界面之后搜索 mybatis-generator进行安装  
  
**2.下载数据库驱动包：mysql、oracle等：**  

**3.在项目当中创建：mybatis generator configuration file**  

**4.配置generator.xml文件：**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration 
PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" 
"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
  <!--引入mysql-connector-java-8.0.13.jar位置 -->
  <classPathEntry location="E:/WorkSpace/eclipseGenerator/generator/mysql-connector-java-8.0.13.jar" />

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
    <javaModelGenerator targetPackage="com.entity" targetProject="eclipseGenerator/src/main/resources">
      <property name="enableSubPackages" value="true" />
      <!-- 从数据库返回的值被清理前后的空格  -->
      <property name="trimStrings" value="true" />
    </javaModelGenerator>

    <!--对应的mapper.xml文件  -->
    <sqlMapGenerator targetPackage="com.mapping" targetProject="eclipseGenerator/src/main/resources">
      <property name="enableSubPackages" value="true" />
    </sqlMapGenerator>

    <!-- 对应的Mapper接口类文件 -->
    <javaClientGenerator type="XMLMAPPER" targetPackage="com.dao" targetProject="eclipseGenerator/src/main/resources">
      <property name="enableSubPackages" value="true" />
    </javaClientGenerator>

    <!-- 生成那些表，tableName对应数据库表，domainObjectName对应实体类名 -->
    <table tableName="student" domainObjectName="Student" enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false"></table>
  </context>
</generatorConfiguration>
```
**5.单机generator.xml右键 run as -> run mybatis generator 执行生成操作**  

**注意事项：**  
generator.xml里面依赖的jar包需要通过绝对路径的方式引用  
