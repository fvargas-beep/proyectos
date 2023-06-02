package classes;

public class Calificacion {
  private final int item_id;
  private final int estrellas;
  private final String comentario;

  public Calificacion(int pItem_id, int pEstrellas, String pComentario){
    this.item_id = pItem_id;
    this.estrellas = pEstrellas;
    this.comentario = pComentario;
  }
  
  public int getItem_id(){
    return this.item_id;
  }
  
  public int getEstrellas(){
    return this.estrellas;
  }
  
  public String getComentario(){
    return this.comentario;
  }
  
  public String estrellasToString(){
    return "Estrellas: " + this.estrellas;
  }
  
  public String comentarioToString(){
    return "Comentario: " + this.comentario;
  }
}
