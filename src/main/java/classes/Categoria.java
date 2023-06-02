package classes;
import java.util.ArrayList;

public class Categoria implements Interface_Elemento{
  private int id;
  private final String nombre;
  private final String descipcion;
  private final ArrayList<Item> items;

  public Categoria(String pNombre, String pDescripcion){
    this.nombre = pNombre;
    this.descipcion = pDescripcion;
    this.items = new ArrayList<>();
  }

  @Override
  public void setId(int pId){
    this.id = pId;
  }

  public void agregarItem(Item item){
    this.items.add(item);
  }

  public String getNombre(){
    return this.nombre;
  }
  
  public String getDescripcion(){
    return this.descipcion;
  }

  public int getID(){
    return this.id;
  }
}
