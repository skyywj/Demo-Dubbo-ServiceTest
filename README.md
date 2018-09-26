此项目为测试体验项目

学习：springboot、grpc协议、protobuf、git、mvn

创建项目的步骤：
创建项目:
        
    打开Intellij IDEA，选择new Proejct，在左侧选择Spring Initializr，点next,选择需要添加的功能，一路next直至finish，打开项目后，点击右下角的Enable Auto-import，

建立模块

    现在开始创建多模块,右键左侧导航目录中你的项目，选择Add->Module->Maven,不需要使用模板，直接起好名字一直next就可以了,这种的就是普通的模块，比如说你的Model层，Dao层之类的，我们先创建模块，最后添加依赖关系
    创建好其他模块后，我们还要创建一个入口模块，和创建普通模块一样，名字叫做application,创建完后，修改pom.xml，入口模块要依赖于你的其他模块的，因此，要加入其他模块的依赖,同时该模块也是项目运行的入口，因此将最开始创建项目的pom.xml中的build部分迁移到本模块的pom.xml,同时将src中java下的包复制到application模块的java目录下中

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>blog</artifactId>
        <groupId>lee</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>application</artifactId>
    <dependencies>
        <dependency>
            <groupId>lee</groupId>
            <artifactId>hello</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

git常用命令：

     查看分支：git branch
     新建分支：git checkout -b newname
     合并分支最好分两步(切换到主分支后merge，如需要则删掉没用的分支)：
 
        git merge branch-name
        git branch -d branch-name

提交自己的操作：(.代表所有操作，可使用其他的，自己去查)

        1）git add .
        2) git commit .
        3) git push
  
  
