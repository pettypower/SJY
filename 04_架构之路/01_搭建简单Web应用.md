# 架构之路-搭建简单Web应用环境

> 撰稿人：邹强  
> 基本简介：搭建一个简单的Web环境  
> 作成时间：2019-7-23  
> 修改时间：2019-7-23

### 1. 创建IDEA项目

1. Create New Project --> 弹出 New Project 框
2. 选择 Maven --> Next
3. GroupId = org.smart4j; ArtifactId = chapter1; Version = 1.0.0 --> Next
4. ProjectName = chapter1; ProjectLocation= "项目路径" --> Finish

### 2. 调整Maven配置 pom.xml

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.smart4j</groupId>
    <artifactId>chapter1</artifactId>
    <version>1.0.0</version>
    <packaging>war</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- Servlet -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>
        <!-- JSP -->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
            <scope>provided</scope>
        </dependency>
        <!-- JSTL -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
            <scope>runtime</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Compile -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.3</version>
                <configuration>
                    <source>1.6</source>
                    <target>1.6</target>
                </configuration>
            </plugin>
            <!-- Test -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.18.1</version>
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>
            <!-- Tomcat -->
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <path>/${project.artifactId}</path>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

```

### 3. 添加web.xml(转为Web项目)

添加文件/main/webapp/WEB-INF/web.xml

```xml

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">
</web-app>

```

### 4. 添加Servlet类

1. 创建包org.smart4j.chapter1
2. 在上述包下，创建HelloServlet.java类

```java

package org.smart4j.chapter1;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        req.setAttribute("currentTime", currentTime);
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req,resp);
    }
}

```

### 5. 添加JSP页面

/webapp/WEB-INF/jsp/hello.jsp

```jsp

<%@ page pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Hello</title>
    </head>
    <body>
        <h1>Hello!</h1>
        <h2>当前时间：${currentTime}</h2>
    </body>
</html>

```

### 6. 让Web用户跑起来

#### 6.1 在IDEA中配置Tomcat

> 1. Run --> Edit Configurations
> 2. 点击"+", 选择Tomcat-->Local
> 3. 输入Name，例: tomcat; 取消After launch选项
> 4. 单击Application server下拉框右侧的Config按钮，配置本地Tomcat路径
> 5. 切换Deployment选项卡，单击右边"+",选择"Artifact..."--> chapter1:war exploded
> 6. 在Application context 中输入 /chapter1
> 7. 回到Server选项卡，在On frame deactivation下拉框中选择"Update resources"选项

最后，单击IDEA工具栏中的Run或Debug，即可启动Tomcat并部署Web应用  
注：此方法可实现热部署（需要Ctrl+F9手动编译）

#### 6.2 在IDEA中使用Maven插件

- 在 pom.xml中添加配置

```xml

<!-- Tomcat -->
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
    <path>/${project.artifactId}</path>
    </configuration>
</plugin>

```

- 打开IDEA中Maven面板，双击 tomcat7:run命令启动Web服务

注：此方法可热更新JSP代码，但不能热部署Java代码，也不可以设置断点

#### 6.3 在IDEA中使用Maven插件，并以Debug方式运行

> 1. Run --> Edit Configurations
> 2. 点击"+", 选择Maven
> 3. Name = tomcat(可自定义)
> 4. Command line = tomcat7:run
> 5. 点击Run，或Debug即可启动Tomcat进行部署应用

注：JSP改动可立即生效，也可以实现类的热部署

### 7. 其它注意事项

1. tomcat无法启动，提示：Can't load IA 64-bit .dll on a AMD 64-bit platform
    > 可能是jdk是32位（tomcat是64位）导致
    > 处理方案：
    > 1. 下载64位jdk, JAVA_HOME换成新地址
    > 2. IDEA打开项目
    > 3. File --> project Structure
    > 4. Project Setting/Project--> ProjectSDK: 换成新的SDK路径

2. tomcat日志中文乱码怎么办？
    > 1. 打开 Run/Debug Configuration
    > 2. 选中服务器tomcat-->选项卡：Start/Connection
    > 3. 勾选Pass environment variable
    > 4. 输入[Name]JAVA_TOOL_OPTIONS [Value]-Dfile.encoding=UTF-8
    > 5. 切换到Server选项卡--> VM options: -Dfile.encoding=UTF-8
    > 6. 上述操作后重启服务器，仍存在问题的话，进入idea的安装目录, 进入bin目录下
    > 7. 找到idea.exe.vmoptions，idea64.exe.vmoptions这两个文件
    > 8. 在最后一行加入-Dfile.encoding=UTF-8
