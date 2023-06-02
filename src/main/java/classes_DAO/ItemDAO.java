package classes_DAO;

import classes.Item;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemDAO{
  public void agregarItemDB(Item item){
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();
      stmt.executeUpdate("use sys;");
      stmt.executeUpdate("INSERT INTO Item(categoria_id, pregunta, respuesta, fuente_respuesta, ejemplo, fuente_ejemplo) VALUES('"+item.categoria+"','"+item.pregunta+"','"+item.respuesta+"','"+item.fuente_respuesta+"','"+item.ejemplo+"','"+item.fuente_ejemplo+"')");
    } catch (SQLException ex) {
      Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Item obtenerItem(String pId){
    Item item = new Item();
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();
      stmt.executeUpdate("use sys;");
      ResultSet rs = stmt.executeQuery("SELECT * FROM Item WHERE id = '"+pId+"'");
      while(rs.next()){
        item.setId(rs.getInt("id"));
        item.setCategoria(rs.getString("categoria_id"));
        item.setPregunta(rs.getString("pregunta"));
        item.setRespuesta(rs.getString("respuesta"));
        item.setFuente_respuesta(rs.getString("fuente_respuesta"));
        item.setEjemplo(rs.getString("ejemplo"));
        item.setFuente_ejemplo(rs.getString("fuente_ejemplo"));
      }
      stmt.close();
    } catch (SQLException ex) {
      Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return item;
  }
  
  public ArrayList<String> getComentarios(String item_id) throws IOException{
    ArrayList<String> comentarios = new ArrayList<>();
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT comentario FROM Calificacion_Respuesta INNER JOIN Item on Item.id = '"+item_id+"'");
      while(rs.next()){
        comentarios.add(rs.getString("comentario"));
      }
      
      rs = stmt.executeQuery("SELECT comentario FROM Calificacion_Ejemplo INNER JOIN Item on Item.id = '"+item_id+"'");
      while(rs.next()){
        comentarios.add(rs.getString("comentario"));
      }
      stmt.close();
    } catch (SQLException ex) {
      Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return comentarios;
  }
  
  public ArrayList<Item> obtenerItems(String categoria_id){
    ArrayList<Item> items = new ArrayList<>();
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT id, categoria_id, pregunta, respuesta, fuente_respuesta, ejemplo,fuente_ejemplo FROM Item INNER JOIN Categoria ON Item.categoria_id = Categoria.codigo WHERE Item.categoria_id = '"+categoria_id+"'");
      while(rs.next()){
        int id = rs.getInt("id");
        String categoria = rs.getString("categoria_id");
        String pregunta = rs.getString("pregunta");
        String respuesta = rs.getString("respuesta");
        String fuente_respuesta = rs.getString("fuente_respuesta");
        String ejemplo = rs.getString("ejemplo");
        String fuente_ejemplo = rs.getString("fuente_ejemplo");

        Item item = new Item(categoria, pregunta, respuesta, fuente_respuesta, ejemplo, fuente_ejemplo);
        item.setId(id);
        items.add(item);
      }
      stmt.close();
    } catch (SQLException ex) {
      Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return items;
  }
  
  public static String obtenerInfoItem(int pId){
    String texto = "";
    try{
      ConexionDB conexion = new ConexionDB();
      Connection con = conexion.getCon();
      Statement stmt = con.createStatement();

      texto += "\n\nITEM";
      ResultSet rs = stmt.executeQuery("SELECT * FROM Item WHERE id = "+pId);
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
      rs = stmt.executeQuery("SELECT * FROM Calificacion_Respuesta INNER JOIN Item on Item.id = "+pId);
      while(rs.next()){
        texto += "\n\nID: " + rs.getInt("id");
        texto += "\nItem ID: " + rs.getInt("item_id");
        texto += "\nEstrellas: " + rs.getInt("estrellas");
        texto += "\nComentario: " + rs.getString("comentario");
      }
      
      texto += "\n\nCALIFICACIONES DE EJEMPLOS";
      rs = stmt.executeQuery("SELECT * FROM Calificacion_Ejemplo INNER JOIN Item on Item.id = "+pId);
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
