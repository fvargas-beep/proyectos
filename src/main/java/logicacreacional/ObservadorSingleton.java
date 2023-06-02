package logicacreacional;

import bitacoras.Observador;

public class ObservadorSingleton {
  private static final Observador instance = new Observador();
  
  private ObservadorSingleton(){}
  
  public static Observador getInstance(){
    return instance;
  }
}
