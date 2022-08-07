/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
/**
 *
 * @author Admin
 */
public class Conexion {
    private static Connection connection = null;
    
    public static Connection conexion1 (){
      try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/proyectosconstruccion","root","123456");
            return connection;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
}
}
