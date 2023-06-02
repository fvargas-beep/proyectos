package classes;

import java.util.ArrayList;

public class Item implements Interface_Elemento, Interface_Item{
  public int id;
  public String categoria;
  public String pregunta;
  public String respuesta;
  public String fuente_respuesta;
  public String ejemplo;
  public String fuente_ejemplo;
  public ArrayList<Calificacion> calificaciones;

  public Item(){}

  public Item(String pCategoria, String pPregunta, String pRespuesta, String pFuente_respuesta, String pEjemplo, String pFuente_ejemplo){
    this.categoria = pCategoria;
    this.pregunta = pPregunta;
    this.respuesta = pRespuesta;
    this.fuente_respuesta = pFuente_respuesta;
    this.ejemplo = pEjemplo;
    this.fuente_ejemplo = pFuente_ejemplo;
    this.calificaciones = new ArrayList<>();
  }

  @Override
  public void setId(int pId){
    this.id = pId;
  }

  @Override
  public void setCategoria(String pCategoria){
    this.categoria = pCategoria;
  }

  @Override
  public void setPregunta(String pPregunta){
    this.pregunta = pPregunta;
  }

  @Override
  public void setRespuesta(String pRespuesta){
    this.respuesta = pRespuesta;
  }

  @Override
  public void setFuente_respuesta(String pFuente_respuesta){
    this.fuente_respuesta = pFuente_respuesta;
  }

  @Override
  public void setEjemplo(String pEjemplo){
    this.ejemplo = pEjemplo;
  }

  @Override
  public void setFuente_ejemplo(String pFuente_ejemplo){
    this.fuente_ejemplo = pFuente_ejemplo;
  }

  @Override
  public void agregarCalificacion(Calificacion calificacion){
    this.calificaciones.add(calificacion);
  }

  @Override
  public String idToString(){
    return String.valueOf(this.id);
  }

  @Override
  public String getID(){
    String msg;
    msg = "\nID: " + this.id;
    return msg;
  }

  @Override
  public String toStringCategoria(){
    String msg;
    msg = "\nCategoria: " + this.categoria;
    return msg;
  }

  @Override
  public String toStringPregunta(){
    String msg;
    msg = "\nPregunta: " + this.pregunta;
    return msg;
  }

  @Override
  public String toStringRespuesta(){
    String msg;
    msg = "\nRespuesta: " + this.respuesta;
    return msg;
  }

  @Override
  public String toStringFuenteRespuesta(){
    String msg;
    msg = "\nFuente de la respuesta: " + this.fuente_respuesta;
    return msg;
  }

  @Override
  public String toStringEjemplo(){
    String msg;
    msg = "\nEjemplo: " + this.ejemplo;
    return msg;
  }

  @Override
  public String toStringFuenteEjemplo(){
    String msg;
    msg = "\nFuente del ejemplo: " + this.fuente_ejemplo;
    return msg;
  }
}
