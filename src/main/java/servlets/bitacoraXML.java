package servlets;

import bitacoras.Bitacora;
import bitacoras.Observador;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicacreacional.ObservadorSingleton;

public class bitacoraXML extends HttpServlet {
  Observador observador = ObservadorSingleton.getInstance();
  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String tipo = request.getParameter("boton");
    
    switch(tipo){
      case "Operaciones de hoy":
        operacionHoy(request, response);
        break;
      case "Operaciones entre estas horas":
        //
        break;
      default:
        break;
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>
  public void operacionHoy(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();
    observador.notifyAllObservers("consulta de operacion de hoy");
    Bitacora bitacoraXML = observador.get("Bitacora_XML");
    session.setAttribute("bitacora", bitacoraXML.operacionHoy());
    response.sendRedirect("bitacoraXML.jsp");
  }
  
  public void operacionEntre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    HttpSession session = request.getSession();
    observador.notifyAllObservers("consulta de operacion entre horas");
    String rango1 = request.getParameter("rango1");
    String rango2 = request.getParameter("rango2");
    Bitacora bitacoraXML = observador.get("Bitacora_XML");
    session.setAttribute("bitacora", bitacoraXML.operacionEntre(rango1, rango2));
    response.sendRedirect("bitacoraXML.jsp");
  }
}
