package bitacoras;

import java.util.ArrayList;

public class Observador {
  public ArrayList<Bitacora> observadores = new ArrayList<>();
  
  public void attach(Bitacora pBitacora){
    this.observadores.add(pBitacora);
  }
  
  public Bitacora get(String pNombre){
    Bitacora test = null;
    for(Bitacora bitacora : observadores){
      if(pNombre.equals(bitacora.getClass().getSimpleName()))
        test = bitacora;
    }
    return test;
  }
  
  public void notifyAllObservers(String pDescripcion){
    for(Bitacora bitacora : observadores){
      bitacora.agregarOperacion(bitacora.documento, pDescripcion);
    }
  }

}
