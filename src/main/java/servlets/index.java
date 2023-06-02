package servlets;

import classes_DAO.CategoriaDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import logicacreacional.CategoriaDAOSingleton;

@WebServlet(name = "index", urlPatterns = {"/index"})
public class index extends HttpServlet {

  private final CategoriaDAO categoriaDAO = CategoriaDAOSingleton.getInstance();
  /*Bitacora bitacoraXML = new Bitacora_XML();
  Bitacora bitacoraCSV = new Bitacora_CSV();
  Bitacora bitacoraTXT = new Bitacora_TXT();
  Observador observador = ObservadorSingleton.getInstance();*/
  
  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   * @throws javax.xml.parsers.ParserConfigurationException
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException, ParserConfigurationException {
    response.setContentType("text/html;charset=UTF-8");
    String tipo_usuario = request.getParameter("tipo_usuario");
    HttpSession session = request.getSession();
    //observador.attach(bitacoraXML);
    //observador.attach(bitacoraCSV);
    //observador.attach(bitacoraTXT);
    switch(tipo_usuario){
      case "usuario":
        //observador.notifyAllObservers("se entró al modo usuario");
        //session.setAttribute("categorias", categoriaDAO.obtenerCategorias());
        session.setAttribute("email", request.getParameter("email"));
        response.sendRedirect("vista_usuario.jsp");
        break;
      case "admin":
        //observador.notifyAllObservers("se entró al modo administrador");
        session.setAttribute("categorias", categoriaDAO.obtenerCategorias());
        response.sendRedirect("vista_admin.jsp");
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
      try {
        processRequest(request, response);
      } catch (ParserConfigurationException ex) {
        Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
      }
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
      try {
        processRequest(request, response);
      } catch (ParserConfigurationException ex) {
        Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
      }
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
}
