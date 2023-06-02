package bitacoras;

import org.w3c.dom.Document;

public class Bitacora_TXT extends Bitacora{

  @Override
  public void agregarOperacion(Document document, String pDescripcion) {
    
  }

  @Override
  protected void agregarOperacion(Document document, String pDescripcion, String iId, String pFecha) {
    
  }

  @Override
  public Document operacionHoy() {
    return documento;
  }

  @Override
  public Document operacionEntre(String rango1, String rango2) {
    return documento;
  }

  @Override
  public String imprimirHTML(Document dcmnt) {
    return "rfe";
  }
  
}
