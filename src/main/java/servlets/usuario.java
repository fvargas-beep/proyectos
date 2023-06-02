package servlets;

import classes.Calificacion;
import classes.Categoria;
import classes_DAO.CalificacionDAO;
import classes_DAO.CategoriaDAO;
import classes_DAO.ItemDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicacreacional.CalificacionDAOSingleton;
import logicacreacional.CategoriaDAOSingleton;
import logicacreacional.ItemDAOSingleton;

@WebServlet(name = "usuario", urlPatterns = {"/usuario"})
public class usuario extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private ItemDAO itemDAO = ItemDAOSingleton.getInstance();
  private CategoriaDAO categoriaDAO = CategoriaDAOSingleton.getInstance();
  private CalificacionDAO calificacionDAO = CalificacionDAOSingleton.getInstance();
  //Bitacora bitacoraXML = new Bitacora_XML();
  //Bitacora bitacoraCSV = new Bitacora_CSV();
  //Bitacora bitacoraTXT = new Bitacora_TXT();
  //Observador observador = ObservadorSingleton.getInstance();
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
    switch (tipo){
      case "Consultar Pregunta":
        consultarPregunta(request, response);
        break;
      case "Consultar":
        consultarItems(request, response);
        break;
      case "Agregar Calificacion":
        agregarCalificacion(request, response);
        break;
      case "Analisis de Sentimientos":
        analizarSentimientos(request, response);
        break;
      case "Enviar PDF":
        enviarPDF(request, response);
        break;
      case "Atras":
        atras(request, response);
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
    
  public ArrayList<Categoria> obtenerCategorias(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
    return categoriaDAO.obtenerCategorias();
  }
    
  public void consultarItems(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
    //bitacoraXML.agregarOperacion(bitacoraXML.documento, "preguntas de una categoria consultadas");
    HttpSession session = request.getSession();
    session.setAttribute("categorias", obtenerCategorias(request, response));
    session.setAttribute("preguntas", itemDAO.obtenerItems(request.getParameter("categoria")));
    session.setAttribute("email", request.getParameter("email"));
    response.sendRedirect("vista_usuario.jsp");
  }

  public void consultarPregunta(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
    HttpSession session = request.getSession();
    //bitacoraXML.agregarOperacion(bitacoraXML.documento, "pregunta consultada");
    session.setAttribute("preguntaConsultada", itemDAO.obtenerItem(request.getParameter("pregunta")));
    session.setAttribute("categorias", obtenerCategorias(request, response));
    session.setAttribute("item_id", request.getParameter("pregunta"));
    session.setAttribute("email", request.getParameter("email"));
    response.sendRedirect("vista_usuario.jsp");
  }

  public void agregarCalificacion(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
    HttpSession session = request.getSession();
    int item_id = Integer.parseInt(request.getParameter("item_id"));
    int estrellas = Integer.parseInt(request.getParameter("estrellas"));
    String comentario = request.getParameter("comentario");
    Calificacion calificacion = new Calificacion(item_id, estrellas, comentario);
    boolean calificacionRespuesta = false;
    if("Respuesta".equals(request.getParameter("tipo_comentario"))){
      calificacionRespuesta = true;
    }
    calificacionDAO.agregarCalificacion(calificacion, calificacionRespuesta);
    session.setAttribute("email", request.getParameter("email"));
    //bitacoraXML.agregarOperacion(bitacoraXML.documento, "calificacion agregada");
    response.sendRedirect("vista_usuario.jsp");
  }
  
  public void analizarSentimientos(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
    HttpSession session = request.getSession();
    session.setAttribute("categorias", obtenerCategorias(request, response));
    session.setAttribute("email", request.getParameter("email"));
    response.sendRedirect("analizarSentimientos.jsp");
  }
  
  public void enviarPDF (HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
    HttpSession session = request.getSession();
    session.setAttribute("categorias", obtenerCategorias(request, response));
    session.setAttribute("email", request.getParameter("email"));
    response.sendRedirect("enviarPDF.jsp");
  }
  
  public void atras(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
    response.sendRedirect("index.html");
  }
}
