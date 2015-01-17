package com.tangzq;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //runHqlDBInit();
        runMysqlInit();
    }

    /**
     * 使用内存数据库测试
     */
    private static void runHqlDBInit(){
        Flyway flyway = new Flyway();
        //设置内存数据库URL,用户名密码
        flyway.setDataSource("jdbc:h2:file:target/foobar", "sa", null);
        //Flyway默认会到classpath:db.migration查找sql脚本文件
        flyway.migrate();
    }

    /**
     * Mysql数据库版本要5.1或以上版本，否则会报错：
     * Exception in thread "main" org.flywaydb.core.api.FlywayException: Unable to check whether schema `flywaydemo` is empty
     * Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown table 'events' in information_schema
     * 导致上面异常就是数据库版本问题，使用5.1以上就没问题了，官方参考：http://flywaydb.org/documentation/database/mysql.html
     */
    private static void runMysqlInit(){
        //数据源
        BasicDataSource basicDataSource=new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/flywaydemo?useUnicode=true&characterEncoding=UTF-8");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("");


        Flyway flyway=new Flyway();

        flyway.setDataSource(basicDataSource);
        //flyway.baseline();
        // 设置接受flyway进行版本管理的多个数据库
        flyway.setSchemas("flywaydemo");
        // 设置接受flyway进行版本管理的多个数据库
        flyway.setTable("schema_version"); // 设置存放flyway metadata数据的表名
        // 指定flyway扫描sql脚本的路径，java升级脚本的目录路径或包路径 ，多个路径用逗号隔开
        flyway.setLocations("classpath:db.migration.mysql"); // 设置flyway扫描sql升级脚本、
        flyway.setEncoding("UTF-8"); // 设置sql脚本文件的编码

        flyway.migrate();
    }
}
