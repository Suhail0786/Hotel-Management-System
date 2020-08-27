/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_hotel_project;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class My_Connection {
    
    // In this class we will make or connect with mysql database
    // 1 download the mysql database 
    // 2 Extract file
    // 3 add the connector to your project
    // 4 open xampp
    // 5 create the database
    
    //create a function to connect with mysql
    public Connection createConnection()
    {
        Connection connection =null;
        
        MysqlDataSource mds =new MysqlDataSource();
        
        mds.setServerName("localhost");
        mds.setPortNumber(3306);
        mds.setUser("root");
        mds.setPassword("");
        mds.setDatabaseName("hotel_db");
        
        try {
            connection =mds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(My_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
}
