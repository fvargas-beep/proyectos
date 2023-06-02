package servlets;

import bitacoras.Observador;
import classes.*;
import classes_DAO.CategoriaDAO;
import classes_DAO.ItemDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.TransformerException;
import logicacreacional.CategoriaDAOSingleton;
import logicacreacional.ItemDAOSingleton;
import logicacreacional.ObservadorSingleton;
import logicadenegocios.ChatGPT;

@WebServlet(name = "admin", urlPatterns = {"/admin"})
public class admin extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private ItemDAO itemDAO = ItemDAOSingleton.getInstance();
  private CategoriaDAO categoriaDAO = CategoriaDAOSingleton.getInstance();
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
            throws ServletException, IOException, TransformerException {
      response.setContentType("text/html;charset=UTF-8");
      String tipo = request.getParameter("boton");
      switch (tipo) {
        case "Agregar Categoria":
          agregarCategoria(request, response);
          break;
        case "Respuesta - ChatGPT":
          respuesta_chatGPT(request, response);
          break;
        case "Ejemplo - ChatGPT":
          ejemplo_chatGPT(request, response);
          break;
        case "Agregar":
          agregarItem(request, response);
            break;
        case "Consultar":
          consultarItems(request, response);
          break;
        case "Consultar Pregunta":
          consultarPregunta(request, response);
          break;
        case "Bitacora XML":
          bitacoraXML(request, response);
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
    try {
      processRequest(request, response);
    } catch (TransformerException ex) {
      Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
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
    } catch (TransformerException ex) {
      Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Categoria> obtenerCategorias()
            throws ServletException, IOException{
      return categoriaDAO.obtenerCategorias();
    }
            
    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
      HttpSession session = request.getSession();
      String nombre = request.getParameter("categoria_nombre");
      String descrpcion = request.getParameter("categoria_descripcion");
      //observador.notifyAllObservers("categoria agregada");
      Categoria categoria = new Categoria(nombre, descrpcion);
      categoriaDAO.registrarCategoriaDB(categoria);
      session.setAttribute("categorias", categoriaDAO.obtenerCategorias());
      response.sendRedirect("vista_admin.jsp");
    }
    
    private void respuesta_chatGPT(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
      HttpSession session = request.getSession();
      String pregunta = request.getParameter("item_pregunta");
      String ejemplo = request.getParameter("item_ejemplo");
      ChatGPT prompt = new ChatGPT(pregunta);
      String respuesta = prompt.conexion();
      //observador.notifyAllObservers("respuesta brindada por chatGPT");
      session.setAttribute("respuesta", respuesta);
      session.setAttribute("pregunta", pregunta);
      session.setAttribute("ejemplo", ejemplo);
      session.setAttribute("categorias", categoriaDAO.obtenerCategorias());
      response.sendRedirect("vista_admin.jsp");
    }
    
    private void ejemplo_chatGPT(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
      HttpSession session = request.getSession();
      String pregunta = request.getParameter("item_pregunta");
      String respuesta = request.getParameter("item_respuesta");
      ChatGPT prompt = new ChatGPT(pregunta);
      String ejemplo = prompt.ejemplo();
      //observador.notifyAllObservers("ejemplo brindado por chatGPT");
      session.setAttribute("respuesta", respuesta);
      session.setAttribute("pregunta", pregunta);
      session.setAttribute("ejemplo", ejemplo);
      session.setAttribute("categorias", categoriaDAO.obtenerCategorias());
      response.sendRedirect("vista_admin.jsp");
    }
    
    private void agregarItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
      HttpSession session = request.getSession();
      String categoria = request.getParameter("categoria");
      String pregunta = request.getParameter("item_pregunta");
      String respuesta = request.getParameter("item_respuesta");
      String ejemplo = request.getParameter("item_ejemplo");
      String fuenteRespuesta;
      String fuenteEjemplo;

      if(request.getParameter("ejemplo_GPT") == null){
          fuenteRespuesta = "Manual";
      } else{
          fuenteRespuesta = "ChatGPT";
      }

      if(request.getParameter("respuesta_GPT") == null){
          fuenteEjemplo = "Manual";
      } else{
          fuenteEjemplo = "ChatGPT";
      }
      //observador.notifyAllObservers("item agregado");
      Item item = new Item(categoria, pregunta, respuesta, fuenteRespuesta, ejemplo, fuenteEjemplo);
      itemDAO.agregarItemDB(item);
      session.setAttribute("categorias", categoriaDAO.obtenerCategorias());
      session.setAttribute("categorias1", categoriaDAO.obtenerCategorias());
      response.sendRedirect("vista_admin.jsp");
    }
    
    
    public void consultarItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{     
      HttpSession session = request.getSession();
      session.setAttribute("categorias", categoriaDAO.obtenerCategorias());
      session.setAttribute("itemss", itemDAO.obtenerItems(request.getParameter("categoria1")));
      //observador.notifyAllObservers("items consultados");
      response.sendRedirect("vista_admin.jsp");
    }
    
    public void consultarPregunta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
      HttpSession session = request.getSession();
      session.setAttribute("preguntaConsultada", itemDAO.obtenerItem(request.getParameter("items")));
      session.setAttribute("categorias", categoriaDAO.obtenerCategorias());
      session.setAttribute("categorias1", obtenerCategorias());
      //observador.notifyAllObservers("pregunta consultada");
      response.sendRedirect("vista_admin.jsp");
    }
    
    public void bitacoraXML(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TransformerException{
      //HttpSession session = request.getSession();
      //observador.notifyAllObservers("ver bitacora xml");
      //Bitacora bitacoraXML = observador.get("Bitacora_XML");
      //session.setAttribute("bitacora", bitacoraXML.imprimirHTML(bitacoraXML.documento));
      response.sendRedirect("bitacoraXML.jsp");
    }
}