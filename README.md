注意代码格式化：IDEA
快捷键 windows：ctrl+alt+l   mac：option+command+l（win+alt+l）

此项目为demo项目:支持springboot mysql redis

学习springboot连接mysql，以后可能不再更新，当模板用

1、创建项目的步骤：

创建项目:
        
    打开Intellij IDEA，选择new Proejct，在左侧选择Spring Initializr，点next,选择需要添加的功能，一路next直至finish，打开项目后，点击右下角的Enable Auto-import，

建立模块

    现在开始创建多模块,右键左侧导航目录中你的项目，选择Add->Module->Maven,不需要使用模板，直接起好名字一直next就可以了,这种的就是普通的模块，比如说你的Model层，Dao层之类的，我们先创建模块，最后添加依赖关系
    创建好其他模块后，我们还要创建一个入口模块，和创建普通模块一样，名字叫做application,创建完后，修改pom.xml，入口模块要依赖于你的其他模块的，因此，要加入其他模块的依赖,同时该模块也是项目运行的入口，因此将最开始创建项目的pom.xml中的build部分迁移到本模块的pom.xml,同时将src中java下的包复制到application模块的java目录下中
2、项目启动：
    
    运行：HrProsApplicaton.java
    浏览器输入：localhost:8082


3、git常用命令：

     查看分支：git branch
     新建分支：git checkout -b newname
     合并分支最好分两步(切换到主分支后merge，如需要则删掉没用的分支)：
 
        git merge branch-name
        git branch -d branch-name

4、提交自己的操作：(.代表所有操作，可使用其他的，自己去查)

        1）git add .
        2) git commit .
        3) git push
  
  
5、使用缓存

    （一）添加redis
        1、pom添加必要的redis、cache、common依赖
        2、properties添加redis相关配置
        3、启动类编写相关启动bean
        4、dao层使用相关注解进行缓存的使用，资料链接：https://blog.csdn.net/qq_37465368/article/details/81385395
    （二）添加redisson
        1、添加redisson依赖包
        2、添加redisson相关bean
        3、service层可直接编写redisson操作缓存
        
        注意点:使用redisson需要配置自己本地的redis配置文件redis.config  将requirepass foobared注释打开，（foobared即为密码）
            然后重启redis：关闭redis-cli -h 127.0.0.1 -p 6379 shutdown
                        启动：1）、指定配置文件 $: ./redis-server /usr/local/redis.conf &
                             2）、不指定配置：$: ./redis-server &
                             不指定配置文件启动时采用默认配置，无密码
                             redis通过属性requirepass 设置访问密码，但没有设置该属性时，客户端向服务端发送AUTH请求，服务端就好返回异常：ERR Client sent AUTH, but
                             no password is set
        4、properties配置上密码 ： spring.redis.password=foobared 