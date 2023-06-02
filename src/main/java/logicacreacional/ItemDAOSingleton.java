package logicacreacional;

import classes_DAO.ItemDAO;

public class ItemDAOSingleton {
  private static final ItemDAO instance = new ItemDAO();
  
  private ItemDAOSingleton(){}
  
  public static ItemDAO getInstance(){
    return instance;
  }
}
