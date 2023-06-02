<%-- 
    Document   : analizarSentimientos
    Created on : May 17, 2023, 3:31:56 PM
    Author     : felipecorralesvargas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body{
                background-color: #233142;
                max-height: 100vh;
                min-height: 100vh;
            }
            #items_container2{
                background-color: #f95959;
                position: absolute;
            }
        </style>
        <title>Análisis de Sentimientos</title>
    </head>
    <body>
        <div id="items_container">
           <form action="analisis" method="post">
                <select name="categoria">
                    <c:forEach items="${categorias}" var="categoria">
                        <option value="${categoria.getID()}">${categoria.getNombre()}</option>
                    </c:forEach>
                </select>
               <input type="submit" name="boton" value="Consultar preguntas de esta categoria"><br>
               <input type="submit" name="boton" value="Analizar Sentimientos de esta categoria"><br>
           
           
                <p>${sentimientos}</p>
                <br>
                <div id="items_container2">
                    <c:forEach items="${preguntas}" var="pregunta">
                        <label for="${pregunta.idToString()}">
                            <input type="radio" name="pregunta" value="${pregunta.idToString()}">${pregunta.toStringRespuesta()}<br>${pregunta.idToString()}
                        </label>
                    </c:forEach>
                    <br>
                    <input type="submit" name="boton" value="Analizar Sentimientos de esta Pregunta">
                    <br>
                    <input type="submit" name="boton" value="Consultar Comentarios de esta Pregunta">
                    <br>
                    <c:forEach items="${comentarios}" var="comentario">
                        <label>
                            <input type="radio" name="comentario" value="${comentario.getItem_id()}">${comentario.estrellasToString()}<br>${comentario.comentarioToString()}<br>
                        </label>
                    </c:forEach>
                    
                    <input type="submit" name="boton" value="Analizar Sentimientos de este Comentario">
                    <label>${comentarioSeleccionado}</label>
                </div>
            </form>
        </div>
    </body>
</html>
