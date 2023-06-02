package classes_DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {
  private static java.sql.Connection con;

  public Connection getCon(){
    try { 
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://proyectodiseno.cpvg6ryk2bv2.us-east-2.rds.amazonaws.com/sys?useSSL=false", "admin", "123456789");
    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
    }
    return con;
  }
}