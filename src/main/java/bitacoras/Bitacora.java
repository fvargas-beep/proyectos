package bitacoras;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.w3c.dom.Document;

public abstract class Bitacora {
  protected int identificador = 0000;
  public Document documento;
  
  public abstract void agregarOperacion(Document document, String pDescripcion);
  protected abstract void agregarOperacion(Document document, String pDescripcion, String iId, String pFecha);
  public abstract String imprimirHTML(Document documento);
  public abstract Object operacionHoy();
  public abstract Object operacionEntre(String rango1, String rango2);
  
  
  protected String getFecha(){
    LocalDateTime now = LocalDateTime.now();  
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");  
    String formatDateTime = now.format(format);  
    return formatDateTime;
  }
}
