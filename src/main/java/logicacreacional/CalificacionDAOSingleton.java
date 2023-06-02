package logicacreacional;

import classes_DAO.CalificacionDAO;

public class CalificacionDAOSingleton {
  private static final CalificacionDAO instance = new CalificacionDAO();
  
  private CalificacionDAOSingleton(){}
  
  public static CalificacionDAO getInstance(){
    return instance;
  }
}
