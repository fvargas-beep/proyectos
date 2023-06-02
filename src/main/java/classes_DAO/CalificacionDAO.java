package classes_DAO;

import classes.Calificacion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalificacionDAO {
  public void agregarCalificacion(Calificacion calificacion, boolean calificacionRespuesta){
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();
      if(calificacionRespuesta){
        stmt.executeUpdate("INSERT INTO Calificacion_Respuesta(item_id, estrellas, comentario) VALUES('"+calificacion.getItem_id()+"','"+calificacion.getEstrellas()+"','"+calificacion.getComentario()+"')");
      }
      else{
        stmt.executeUpdate("INSERT INTO Calificacion_Ejemplo(item_id, estrellas, comentario) VALUES('"+calificacion.getItem_id()+"','"+calificacion.getEstrellas()+"','"+calificacion.getComentario()+"')");
      }
      stmt.close();
    } catch (SQLException ex) {
      Logger.getLogger(CalificacionDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public ArrayList<Calificacion> obtenerCalificaciones(String pItem_id){
    ArrayList<Calificacion> calificaciones = new ArrayList<>();
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Calificacion_Respuesta WHERE item_id = '"+pItem_id+"'");
      while(rs.next()){
        int id = rs.getInt("id");
        int estrellas = rs.getInt("estrellas");
        String comentario = rs.getString("comentario");
        Calificacion calificacion = new Calificacion(id, estrellas, comentario);
        calificaciones.add(calificacion);
      }
      
      rs = stmt.executeQuery("SELECT * FROM Calificacion_Ejemplo WHERE item_id = '"+pItem_id+"'");
      while(rs.next()){
        int id = rs.getInt("id");
        int estrellas = rs.getInt("estrellas");
        String comentario = rs.getString("comentario");
        Calificacion calificacion = new Calificacion(id, estrellas, comentario);
        calificaciones.add(calificacion);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return calificaciones;
  }
  
  public String getComentario(String pId){
    String test = "";
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT comentario from Calificacion_Respuesta WHERE id = '"+pId+"' UNION SELECT comentario FROM Calificacion_Ejemplo WHERE id = '"+pId+"'");
      while(rs.next()){
        test = rs.getString("comentario");
      }
    } catch (SQLException ex) {
      Logger.getLogger(CalificacionDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return test;
  }
  
  public ArrayList<String> getComentarios(String categoria_id) throws IOException{
    ArrayList<String> comentarios = new ArrayList<>();
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT comentario FROM Calificacion_Respuesta INNER JOIN Item on Calificacion_Respuesta.item_id = Item.id INNER JOIN Categoria on Categoria.codigo = '"+categoria_id+"'");
      while(rs.next()){
        comentarios.add(rs.getString("comentario"));
      }
      
      rs = stmt.executeQuery("SELECT comentario FROM Calificacion_Ejemplo INNER JOIN Item on Calificacion_Ejemplo.item_id = Item.id INNER JOIN Categoria on Categoria.codigo = '"+categoria_id+"'");
      while(rs.next()){
        comentarios.add(rs.getString("comentario"));
      }
      stmt.close();
    } catch (SQLException ex) {
      Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return comentarios;
  }
}
