<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body{
                background-color: #233142;
            }
            #registrar_item_container{
                background-color: #f95959;
                border-radius: 10px;
                position: absolute;
                left: 10%;
                bottom: 10%;
                width: 500px;
                height: 300px;
                text-align: center;
            }
            #item_pregunta{
                position: absolute;
                left: 5%;
                top: 15%;
                width: 90%;
                height: 15%;
                resize: none;
                border-radius: 10px;
            }
            #item_respuesta{
                position: absolute;
                left: 5%;
                top: 35%;
                width: 90%;
                height: 20%;
                resize: none;
                border-radius: 10px;
            }
            #item_ejemplo{
                position: absolute;
                left: 5%;
                top: 60%;
                width: 90%;
                height: 20%;
                resize: none;
                border-radius: 10px;
            }
            #boton_ejemplo{
                position: absolute;
                bottom: 5%;
                left: 10%;
            }
            #ejemplo_GPT{
                position: absolute;
                bottom: 5%;
                left: 35%;
            }
            #boton_respuesta{
                position: absolute;
                bottom: 5%;
                right: 10%;
            }
            #respuesta_GPT{
                position: absolute;
                bottom: 5%;
                right: 37%;
            }
            #submit2{
                position: absolute;
                bottom: 5%;
                left: 43%;
            }
            #registrar_categorias_container{
                background-color: #f95959;
                position: absolute;
                left: 17%;
                top: 10%;
                height: 150px;
                width: 300px;
                border-radius: 10px;
                text-align: center;
            }
            #categoria_nombre{
                position: absolute;
                left: 5%;
                top: 20%;
                width: 90%;
                height: 20%;
                resize: none;
                border-radius: 10px;
            }
            #categoria_descripcion{
                position: absolute;
                left: 5%;
                top: 45%;
                width: 90%;
                height: 20%;
                resize: none;
                border-radius: 10px;
            }
            #submit1{
                position: absolute;
                bottom: 10%;
                left: 35%;
            }
        </style>
        <title>Vista Administrativa</title>
    </head>
    <body>    

        <div id="registrar_categorias_container">
            <form action="admin" method="post">
                Registrar categoria
                <br>
                <input type="text" id="categoria_nombre" name="categoria_nombre" placeholder="Nombre de la Categoría">
                <br>
                <input type="text" id="categoria_descripcion" name="categoria_descripcion" placeholder="Descripción de la Categoría">
                <br>
                <input type="submit" id="submit1" name="boton" value="Agregar Categoria">
            </form>
        </div>
        
        <div id="registrar_item_container">
            <form action="admin" method="post">
                Registrar Ítem
                <br>
                <label for="categoria">Seleccione una categoria</label>
                <select name="categoria">
                    <c:forEach items="${categorias}" var="categoria">
                        <option value="${categoria.getID()}">${categoria.getNombre()}</option>
                    </c:forEach>
                </select>

                <textarea id="item_pregunta" name="item_pregunta" placeholder="Pregunta del Ítem">${pregunta}</textarea>
                <textarea id="item_respuesta" name="item_respuesta" placeholder="Respuesta del Ítem">${respuesta}</textarea>
                <textarea id="item_ejemplo" name="item_ejemplo" placeholder="Ejemplo del Ítem">${ejemplo}</textarea>
                
                <input type="checkbox" id="ejemplo_GPT" name="ejemplo_GPT" value="ejemplo_GPT">
                <input type="checkbox" id="respuesta_GPT" name="respuesta_GPT" value="respuesta_GPT">
                
                <input type="submit" id="boton_ejemplo" name="boton" value="Ejemplo - ChatGPT">
                <input type="submit" id="boton_respuesta" name="boton" value="Respuesta - ChatGPT">
                <input type="submit" id="submit2" name="boton" value="Agregar">
                <input type="submit" name="boton" value="Bitacora XML">
            </form>
        </div>
                
        
    </body>
</html>