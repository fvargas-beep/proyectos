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
        </style>
        <title>JSP Page</title>
    </head>
    <body>
       <div id="items_container">
           <form action="enviarPDF" method="post">
                <select name="categoria">
                    <c:forEach items="${categorias}" var="categoria">
                        <option value="${categoria.getID()}">${categoria.getNombre()}</option>
                    </c:forEach>
                </select>
                <input type="submit" name="boton" value="Enviar PDF Completo"><br>
                <input type="submit" name="boton" value="Consultar preguntas de esta categoria"><br>
                <input type="submit" name="boton" value="Enviar PDF de esta Categoria">
           
                <label>${email}</label>
                <p>${sentimientos}</p>
                <br>
                <div id="items_container2">
                    <c:forEach items="${preguntas}" var="pregunta">
                        <label for="${pregunta.idToString()}">
                            <input type="radio" name="pregunta" value="${pregunta.idToString()}">${pregunta.toStringRespuesta()}<br>${pregunta.idToString()}
                        </label>
                    </c:forEach>
                    <br>
                    <input type="submit" name="boton" value="Enviar PDF de esta Pregunta">
                </div>
            </form>
        </div>
    </body>
</html>
