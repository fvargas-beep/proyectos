package bitacoras;

import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Bitacora_XML extends Bitacora{
  
  public Bitacora_XML(){
    this.documento = crearXML();
  }
  
  public Document crearXML(){
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;
    try {
      builder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(Bitacora_XML.class.getName()).log(Level.SEVERE, null, ex);
    }
    Document document = builder.newDocument();
    Element root = document.createElement("bitácora_XML");
    document.appendChild(root);
    return document;
  }
  
  @Override
  public void agregarOperacion(Document document, String pDescripcion){
    Node root = document.getFirstChild();
    Element operacion = document.createElement("operacion");
    root.appendChild(operacion);
    
    Element id = document.createElement("id");
    id.setTextContent(Integer.toString(identificador));
    operacion.appendChild(id);
    identificador++;
    
    Element fecha = document.createElement("fecha");
    fecha.setTextContent(getFecha());
    operacion.appendChild(fecha);
    
    Element descripcion = document.createElement("descripcion");
    descripcion.setTextContent(pDescripcion);
    operacion.appendChild(descripcion);
  }
  
  @Override
  protected void agregarOperacion(Document document, String pDescripcion, String iId, String pFecha){
    Node root = document.getFirstChild();
    Element operacion = document.createElement("operacion");
    root.appendChild(operacion);
    
    Element id = document.createElement("id");
    id.setTextContent(iId);
    operacion.appendChild(id);
    
    Element fecha = document.createElement("fecha");
    fecha.setTextContent(pFecha);
    operacion.appendChild(fecha);
    
    Element descripcion = document.createElement("descripcion");
    descripcion.setTextContent(pDescripcion);
    operacion.appendChild(descripcion);
  }
  
  private String imprimir(Document doc) {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = null;
    try {
      transformer = transformerFactory.newTransformer();
    } catch (TransformerConfigurationException ex) {
      Logger.getLogger(Bitacora_XML.class.getName()).log(Level.SEVERE, null, ex);
    }
    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    StreamResult result = new StreamResult(new StringWriter());
    DOMSource source = new DOMSource(doc);
    try {
      transformer.transform(source, result);
    } catch (TransformerException ex) {
      Logger.getLogger(Bitacora_XML.class.getName()).log(Level.SEVERE, null, ex);
    }
    return result.getWriter().toString();
  }
  
  @Override
  public Document operacionHoy(){
    Document doc = crearXML();
    documento.getDocumentElement().normalize();
    NodeList nodeList = documento.getElementsByTagName("operacion");
    for(int i = 0; i < nodeList.getLength(); i++){
      Node node = nodeList.item(i);
      if(node.getNodeType() == Node.ELEMENT_NODE){
        Element eElement = (Element) node;
        if(isToday(toDate(eElement.getElementsByTagName("fecha").item(0).getTextContent()))){
          String id = eElement.getElementsByTagName("id").item(0).getTextContent();
          String fecha = eElement.getElementsByTagName("fecha").item(0).getTextContent();
          String descripcion = eElement.getElementsByTagName("descripcion").item(0).getTextContent();
          agregarOperacion(doc, descripcion, id, fecha);
        }
      }
    }
    return doc;
  }
  
  @Override
  public Document operacionEntre(String rango1, String rango2){
    Document doc = crearXML();
    documento.getDocumentElement().normalize();
    NodeList nodeList = documento.getElementsByTagName("operacion");
    for(int i = 0; i < nodeList.getLength(); i++){
      Node node = nodeList.item(i);
      if(node.getNodeType() == Node.ELEMENT_NODE){
        Element eElement = (Element) node;
        if(isBetween(toTime(rango1), toTime(rango2), dateTimetoTime(eElement.getElementsByTagName("fecha").item(0).getTextContent()))){
          String id = eElement.getElementsByTagName("id").item(0).getTextContent();
          String fecha = eElement.getElementsByTagName("fecha").item(0).getTextContent();
          String descripcion = eElement.getElementsByTagName("descripcion").item(0).getTextContent();
          agregarOperacion(doc, descripcion, id, fecha);
        }
      }
    }
    return doc;
  }
  
  @Override
  public String imprimirHTML(Document documento){
    return imprimir(documento).replace("</", "<br>&lt;/").replace("<", "&lt").replace(">", "&gt;<br>");
  }

  private static LocalDate toDate(String pFecha){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return LocalDateTime.parse(pFecha, formatter).toLocalDate();
  }
  
  private static boolean isToday(LocalDate pTiempo){
    return pTiempo.equals(LocalDate.now());
  }
  
  private static LocalTime toTime(String pHora){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    return LocalTime.parse(pHora, formatter);
  }
  
  private LocalTime dateTimetoTime(String pHora){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return LocalDateTime.parse(pHora, formatter).toLocalTime();
  }
  
  private boolean isBetween(LocalTime rango1 , LocalTime rango2, LocalTime pTiempo){
    return pTiempo.isAfter(rango1) && pTiempo.isBefore(rango2);
  }
  
}