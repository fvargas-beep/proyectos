package classes;

public interface Interface_Item {
  public abstract void setCategoria(String pCategoria);
  public abstract void setPregunta(String pPregunta);
  public abstract void setRespuesta(String pRespuesta);
  public abstract void setFuente_respuesta(String pFuente_respuesta);
  public abstract void setEjemplo(String pEjemplo);
  public abstract void setFuente_ejemplo(String pFuente_ejemplo);
  public abstract void agregarCalificacion(Calificacion calificacion);
  public abstract String idToString();
  public abstract String getID();
  public abstract String toStringCategoria();
  public abstract String toStringPregunta();
  public abstract String toStringRespuesta();
  public abstract String toStringFuenteRespuesta();
  public abstract String toStringEjemplo();
  public abstract String toStringFuenteEjemplo();
}
