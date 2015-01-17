package com.tangzq;

import org.flywaydb.core.Flyway;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        runHqlDBInit();
        //runMysqlInit();
    }

    private static void runHqlDBInit(){
        // Create the Flyway instance
        Flyway flyway = new Flyway();
        // Point it to the database
        flyway.setDataSource("jdbc:h2:file:target/foobar", "sa", null);
        // Start the migration
        flyway.migrate();
    }

    //TODO,这个方法有错误，需要进一步验证
    private static void runMysqlInit(){

        Flyway flyway=new Flyway();
        flyway.setDataSource("jdbc:mysql://localhost:3306/flywaydemo","root","");
        flyway.migrate();
    }
}
