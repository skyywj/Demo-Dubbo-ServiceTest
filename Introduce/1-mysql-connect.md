springboot连接mysql数据库

1、在application.properties中配置url ，psw，username

2、建立dao层，使用JdbcTemplate连接并对数据库进行增删改查

    ps：无需其他多余配置，数据库连接都是自动进行连接的

3、JdbcTemplate依赖

                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-jdbc</artifactId>
                </dependency>
