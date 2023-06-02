package servlets;

import classes_DAO.CalificacionDAO;
import classes_DAO.CategoriaDAO;
import classes_DAO.ItemDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicacreacional.CalificacionDAOSingleton;
import logicacreacional.CategoriaDAOSingleton;
import logicacreacional.ItemDAOSingleton;
import logicadenegocios.Analisis_Sentimientos;

public class analisis extends HttpServlet {

  private static final long serialVersionUID = 1L;
  ItemDAO itemDAO = ItemDAOSingleton.getInstance();
  CategoriaDAO categoriaDAO = CategoriaDAOSingleton.getInstance();
  CalificacionDAO calificacionDAO = CalificacionDAOSingleton.getInstance();

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
      case "Analizar Sentimientos de esta categoria":
        analizarSentimientosCategoria(request, response);
        break;
      case "Consultar preguntas de esta categoria":
        consultarPreguntasCategoria(request, response);
        break;
      case "Analizar Sentimientos de esta Pregunta":
        analizarSentimientosPregunta(request, response);
        break;
      case "Consultar Comentarios de esta Pregunta":
        consultarComentariosPregunta(request, response);
        break;
      case "Analizar Sentimientos de este Comentario":
        analizarSentimientosComentario(request, response);
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

  public void analizarSentimientosCategoria(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    Analisis_Sentimientos analisis = new Analisis_Sentimientos();
    HttpSession session = request.getSession();
    session.setAttribute("sentimientos", analisis.analizarSentimientosLista(calificacionDAO.getComentarios(request.getParameter("categoria"))));
    response.sendRedirect("analizarSentimientos.jsp");
  }
  
  public void consultarPreguntasCategoria(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.setAttribute("preguntas", itemDAO.obtenerItems(request.getParameter("categoria")));
    response.sendRedirect("analizarSentimientos.jsp");
  }
  
  public void analizarSentimientosPregunta(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    Analisis_Sentimientos analisis = new Analisis_Sentimientos();
    HttpSession session = request.getSession();
    session.setAttribute("sentimientos", analisis.analizarSentimientosLista(calificacionDAO.getComentarios(request.getParameter("pregunta"))));
    response.sendRedirect("analizarSentimientos.jsp");
  }
  
  public void consultarComentariosPregunta(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.setAttribute("comentarios", calificacionDAO.obtenerCalificaciones(request.getParameter("pregunta")));
    response.sendRedirect("analizarSentimientos.jsp");
  }
  
  public void analizarSentimientosComentario(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();
    Analisis_Sentimientos analisis = new Analisis_Sentimientos();
    session.setAttribute("comentarioSeleccionado", analisis.analizarSentimiento(calificacionDAO.getComentario(request.getParameter("comentario"))));
    response.sendRedirect("analizarSentimientos.jsp");
  }
}
