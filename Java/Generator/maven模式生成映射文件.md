### 具体步骤：
##### 1. 创建maven项目  

##### 2. 配置pom.xml文件引入相关的jar包：mysql-connector-jar、mybatis-generator-core  
``` xml
<dependencies>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>8.0.13</version>
	</dependency>
	<dependency>
		<groupId>org.mybatis.generator</groupId>
		<artifactId>mybatis-generator-core</artifactId>
		<version>1.3.7</version>
	</dependency>
</dependencies>
```
##### 3. 配置pom文件的build、plugin  
``` xml
<build>
		<finalName>mavenGenerator</finalName>
		<plugins>
			<plugin>
				<groupId>org.mybatis.generator</groupId>
				<artifactId>mybatis-generator-maven-plugin</artifactId>
				<version>1.3.7</version>
				<dependencies>
					<dependency>
						<groupId>org.mybatis.generator</groupId>
						<artifactId>mybatis-generator-core</artifactId>
						<version>1.3.7</version>
					</dependency>
					<dependency>
						<groupId>mysql</groupId>
						<artifactId>mysql-connector-java</artifactId>
						<version>8.0.13</version>
					</dependency>
				</dependencies>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
					<overwrite>true</overwrite>
					<configurationFile>${basedir}/src/test/java/generator.xml</configurationFile>
				</configuration>
			</plugin>
		</plugins>
	</build>
```
##### 4. 创建generator.xml文件并配置内容  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration 
PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" 
"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
	<!--是否去除自动生成的注释 true:是；false：否 -->
	<context id="DB2Tables" targetRuntime="MyBatis3">		
		<commentGenerator>
			<property name="suppressAllComments" value="true" />
		</commentGenerator>

		<!-- 数据库连接 -->
		<jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://192.168.3.10:3306/test_db" userId="root" password="ChinaNet910111"></jdbcConnection>

		<!--默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer true，把JDBC DECIMAL 和  NUMERIC 类型解析为java.math.BigDecimal -->
		<javaTypeResolver>
			<property name="forceBigDecimals" value="false" />
		</javaTypeResolver>

		<!--生成model模型，对应的包路径，以及文件存放路径(targetProject)，targetProject可以指定具体的路径 也可以使用“MAVEN”来自动生成，这样生成的代码会在target/generatord-source目录下 -->
		<javaModelGenerator targetPackage="com.entity" targetProject="src/test/java">
			<property name="enableSubPackages" value="true" />
			<!-- 从数据库返回的值被清理前后的空格 -->
			<property name="trimStrings" value="true" />
		</javaModelGenerator>

		<!--对应的mapper.xml文件 -->
		<sqlMapGenerator targetPackage="com.mapping" targetProject="src/test/java">
			<property name="enableSubPackages" value="true" />
		</sqlMapGenerator>

		<!-- 对应的mapper接口类文件 -->
		<javaClientGenerator type="XMLMAPPER" targetPackage="com.dao" targetProject="src/test/java">
			<property name="enableSubPackages" value="true" />
		</javaClientGenerator>

		<!-- 生成那些表，tableName对应数据库表，domainObjectName对应实体类名 -->
		<table tableName="student" domainObjectName="Student" enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false"></table>
	</context>
</generatorConfiguration>
```
##### 5. 右键maven项目：run as -> 5 Maven Build 接着在Goals中输入mybatis-generator:generate 最后再点击 run 运行

##### 6. 查看文档生成情况(如果控制台有success的字样表明已经成功，下面则刷新一下目录结构即可看到文件)  

### 完整的pom.xml
``` xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>test.mavenGenerator</groupId>
	<artifactId>mavenGenerator</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>
	<name>mavenGenerator Maven Webapp</name>
	<url>http://maven.apache.org</url>
	<dependencies>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.13</version>
		</dependency>
		<dependency>
			<groupId>org.mybatis.generator</groupId>
			<artifactId>mybatis-generator-core</artifactId>
			<version>1.3.7</version>
		</dependency>
	</dependencies>
	<build>
		<finalName>mavenGenerator</finalName>
		<plugins>
			<plugin>
				<groupId>org.mybatis.generator</groupId>
				<artifactId>mybatis-generator-maven-plugin</artifactId>
				<version>1.3.7</version>
				<dependencies>
					<dependency>
						<groupId>org.mybatis.generator</groupId>
						<artifactId>mybatis-generator-core</artifactId>
						<version>1.3.7</version>
					</dependency>
					<dependency>
						<groupId>mysql</groupId>
						<artifactId>mysql-connector-java</artifactId>
						<version>8.0.13</version>
					</dependency>
				</dependencies>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
					<overwrite>true</overwrite>
					<configurationFile>${basedir}/src/test/java/generator.xml</configurationFile>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>
``` 



