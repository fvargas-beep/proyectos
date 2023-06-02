package classes_DAO;

import classes.Categoria;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaDAO {
  public void registrarCategoriaDB(Categoria categoria){
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();
      stmt.executeUpdate("INSERT INTO Categoria(nombre, descrpcion) VALUES('"+categoria.getNombre()+"','"+categoria.getDescripcion()+"')");
    } catch (SQLException ex) {
      Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public ArrayList<Categoria> obtenerCategorias(){
    ArrayList<Categoria> categorias = new ArrayList<>();
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Categoria");  
      while(rs.next()){
        Categoria categoria = new Categoria(rs.getString("nombre"), rs.getString("descrpcion"));
        categoria.setId(rs.getInt("codigo"));
        categorias.add(categoria);
    }
    } catch (SQLException ex) {
      Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return categorias;
  }
  
  public String obtenerInfo(){
    String texto = "";
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Categoria");
      texto += "CATEGORIAS";
      while(rs.next()){
        texto += "\n\nCódigo: " + rs.getInt("codigo");
        texto += "\nNombre: " + rs.getString("nombre");
        texto += "\nDescripción: " + rs.getString("descrpcion");
      }
      
      texto += "\n\nITEMS";
      rs = stmt.executeQuery("SELECT * FROM Item");
      while(rs.next()){
        texto += "\n\nID: " + rs.getInt("id");
        texto += "\nCategoria : " + rs.getInt("categoria_id");
        texto += "\nPregunta: " + rs.getString("pregunta");
        texto += "\nRespuesta: " + rs.getString("respuesta");
        texto += "\nFuente de la Respuesta : " + rs.getString("fuente_respuesta");
        texto += "\nEjemplo: " + rs.getString("ejemplo");
        texto += "\nFuente del ejemplo: " + rs.getString("fuente_ejemplo");
      }
      
      texto += "\n\nCALIFICACIONES DE RESPUESTAS";
      rs = stmt.executeQuery("SELECT * FROM Calificacion_Respuesta");
      while(rs.next()){
        texto += "\n\nID: " + rs.getInt("id");
        texto += "\nItem ID: " + rs.getInt("item_id");
        texto += "\nEstrellas: " + rs.getInt("estrellas");
        texto += "\nComentario: " + rs.getString("comentario");
      }
      
      texto += "\n\nCALIFICACIONES DE EJEMPLOS";
      rs = stmt.executeQuery("SELECT * FROM Calificacion_Ejemplo");
      while(rs.next()){
        texto += "\n\nID: " + rs.getInt("id");
        texto += "\nItem ID: " + rs.getInt("item_id");
        texto += "\nEstrellas: " + rs.getInt("estrellas");
        texto += "\nComentario: " + rs.getString("comentario");
      }
    } catch (SQLException ex) {
      Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return texto;
  }
  
  public String obtenerInfoCategoria(int categoria_id){
    String texto = "";
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();
      
      ResultSet rs = stmt.executeQuery("SELECT * FROM Categoria WHERE codigo = '"+categoria_id+"'");
      texto += "CATEGORIA";
      while(rs.next()){
        texto += "\n\nCódigo: " + rs.getInt("codigo");
        texto += "\nNombre: " + rs.getString("nombre");
        texto += "\nDescripción: " + rs.getString("descrpcion");
      }
      
      texto += "\n\nITEMS";
      rs = stmt.executeQuery("SELECT * FROM Item WHERE categoria_id = '"+categoria_id+"'");
      while(rs.next()){
        texto += "\n\nID: " + rs.getInt("id");
        texto += "\nCategoria : " + rs.getInt("categoria_id");
        texto += "\nPregunta: " + rs.getString("pregunta");
        texto += "\nRespuesta: " + rs.getString("respuesta");
        texto += "\nFuente de la Respuesta : " + rs.getString("fuente_respuesta");
        texto += "\nEjemplo: " + rs.getString("ejemplo");
        texto += "\nFuente del ejemplo: " + rs.getString("fuente_ejemplo");
      }
      
      texto += "\n\nCALIFICACIONES DE RESPUESTAS";
      rs = stmt.executeQuery("SELECT * FROM Calificacion_Respuesta INNER JOIN Item on Calificacion_Respuesta.item_id = Item.id INNER JOIN Categoria on Categoria.codigo = '"+categoria_id+"'");
      while(rs.next()){
        texto += "\n\nID: " + rs.getInt("id");
        texto += "\nItem ID: " + rs.getInt("item_id");
        texto += "\nEstrellas: " + rs.getInt("estrellas");
        texto += "\nComentario: " + rs.getString("comentario");
      }
      
      texto += "\n\nCALIFICACIONES DE EJEMPLOS";
      rs = stmt.executeQuery("SELECT * FROM Calificacion_Ejemplo INNER JOIN Item on Calificacion_Ejemplo.item_id = Item.id INNER JOIN Categoria on Categoria.codigo = '"+categoria_id+"'");
      while(rs.next()){
        texto += "\n\nID: " + rs.getInt("id");
        texto += "\nItem ID: " + rs.getInt("item_id");
        texto += "\nEstrellas: " + rs.getInt("estrellas");
        texto += "\nComentario: " + rs.getString("comentario");
      }
    } catch (SQLException ex) {
      Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return texto;
  }
}
