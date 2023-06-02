package logicacreacional;

import classes_DAO.CategoriaDAO;

public class CategoriaDAOSingleton {
  private static final CategoriaDAO instance = new CategoriaDAO();
  
  private CategoriaDAOSingleton(){}
  
  public static CategoriaDAO getInstance(){
    return instance;
  }
}
