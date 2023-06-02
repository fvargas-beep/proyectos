package bitacoras;

import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;

public class Bitacora_CSV{
  Document documento;
  public Bitacora_CSV(){
    this.documento = (Document) crearCSV();
  }
  
  public static Object crearCSV(){
    FileWriter fileWriter = null;
    File file = new File("bitacora.cvs");
    try {
      fileWriter = new FileWriter(file);
      CSVWriter writer = new CSVWriter(fileWriter);
      String[] test = {"Identificador", "Fecha", "Descripcion"};
      writer.writeNext(test);
      writer.close();
    } catch (IOException ex) {
      Logger.getLogger(Bitacora_CSV.class.getName()).log(Level.SEVERE, null, ex);
    }
    return file;
  }
  
  public static void main(String[] args){
    test();
  }
  
  public static void test(){
    try {
      File file = (File) crearCSV();
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      String line = "";
      String[] tempArr;
      while((line = br.readLine()) != null) {
         tempArr = line.split(",");
         for(String tempStr : tempArr) {
            System.out.print(tempStr + " ");
         }
         System.out.println();
      }
      br.close();
      } catch(IOException ioe) {
         ioe.printStackTrace();
      }
  }
  

  public void agregarOperacion() {
    File file = (File) this.documento;
    FileReader reader = null;
    try {
      reader = new FileReader(file);
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Bitacora_CSV.class.getName()).log(Level.SEVERE, null, ex);
    }
    BufferedReader buffer = new BufferedReader(reader);
    String[] temporal;
    String linea = "";
    String hola = null;
    try {
      while((linea = buffer.readLine()) != null){
        temporal = linea.split(",");
        for(String x : temporal){
          hola+= x;
        }
      }
      System.out.println(hola);
    } catch (IOException ex) {
      Logger.getLogger(Bitacora_CSV.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  
}
