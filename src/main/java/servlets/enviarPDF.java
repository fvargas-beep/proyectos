/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import classes.Categoria;
import classes_DAO.CalificacionDAO;
import classes_DAO.CategoriaDAO;
import classes_DAO.ItemDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicacreacional.CalificacionDAOSingleton;
import logicacreacional.CategoriaDAOSingleton;
import logicacreacional.ItemDAOSingleton;
import logicadenegocios.CrearPDF;
import logicadenegocios.EnviarCorreo;

/**
 *
 * @author felipecorralesvargas
 */
public class enviarPDF extends HttpServlet {
  
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
      case "Enviar PDF Completo":
        enviarPdfCompleto(request, response);
        break;
      case "Consultar preguntas de esta categoria":
        consultarPregunta(request, response);
        break;
      case "Enviar PDF de esta Categoria":
        enviarPdfCategoria(request, response);
        break;
      case "Enviar PDF de esta Pregunta":
        enviarPdfPregunta(request, response);
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
  
  public void enviarPdfCompleto(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.setAttribute("categorias", obtenerCategorias(request, response));
    
    CrearPDF crear = new CrearPDF();
    //crear.generarPDF(categoriaDAO.obtenerInfo(), "Informacion Completa.pdf");
    //enviar.enviarMail("felipaocorrales2310@gmail.com", "Informacion Completa.pdf");
    
    //EnviarCorreo enviar = new EnviarCorreo();
    //EnviarCorreo.enviarMail1();
    response.sendRedirect("enviarPDF.jsp");
  }
  
  public void consultarPregunta(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.setAttribute("preguntas", itemDAO.obtenerItems(request.getParameter("categoria")));
    session.setAttribute("categorias", obtenerCategorias(request, response));
    response.sendRedirect("enviarPDF.jsp");
  }
  
  public void enviarPdfCategoria(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.setAttribute("categorias", obtenerCategorias(request, response));
    EnviarCorreo enviar = new EnviarCorreo();
    CrearPDF crear = new CrearPDF();
    request.getParameter("categoria");
    response.sendRedirect("enviarPDF.jsp");
  }
  
  public void enviarPdfPregunta(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.setAttribute("categorias", obtenerCategorias(request, response));
    response.sendRedirect("enviarPDF.jsp");
  }
}
