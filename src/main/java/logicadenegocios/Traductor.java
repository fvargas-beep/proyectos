package logicadenegocios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Traductor {
  public String traducirAEspanol(String text) throws IOException {
    String urlStr = "https://script.google.com/macros/s/AKfycbx_vIDDxcVnJ27wM3JTR0vDD_1lfHuOxd_KGQAUMie9Jk5SMzzDVJdPPgr-6WoxMYHF/exec" +
      "?q=" + URLEncoder.encode(text, "UTF-8") +
      "&target=" + "es" +
      "&source=" + "en";
    URL url = new URL(urlStr);
    StringBuilder response = new StringBuilder();
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
    }
    in.close();
    return response.toString();
  }
  
  public String traducirAIngles(String text) throws IOException {
    String urlStr = "https://script.google.com/macros/s/AKfycbx_vIDDxcVnJ27wM3JTR0vDD_1lfHuOxd_KGQAUMie9Jk5SMzzDVJdPPgr-6WoxMYHF/exec" +
      "?q=" + URLEncoder.encode(text, "UTF-8") +
      "&target=" + "en" +
      "&source=" + "es";
    URL url = new URL(urlStr);
    StringBuilder response = new StringBuilder();
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    return response.toString();
  }
  
  public ArrayList<String> traducirAInglesArray(ArrayList<String> comentarios) throws IOException{
    ArrayList<String> comentariosTraducidos = new ArrayList<>();
    for(String comentario : comentarios){
      comentariosTraducidos.add(traducirAIngles(comentario));
    }
    return comentariosTraducidos;
  }
}