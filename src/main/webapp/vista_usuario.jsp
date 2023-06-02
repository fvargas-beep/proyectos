<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <script>
        function googleTranslateElementInit() {
          new google.translate.TranslateElement({pageLanguage: 'es', includedLanguages: 'en,de,fr,es'}, 'google_translate_element');
        }
    </script>
    <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body{
                background-color: #233142;
                max-height: 100vh;
                min-height: 100vh;
            }
            #items{
                background-color: #f95959;
                position: absolute;
                left: 10%;
                top: 20%;
                height: 60%;
                width: 40%;
                border-radius: 10px;
            }
            #items_container{
                position: absolute;
                height: 70%;
                width: 88%;
                left: 5%;
                top: 25%;
                overflow: scroll;
                border-style: solid;
                border-color: #92a8d1;
            }
            #pregunta_seleccionada_container{
                background-color: #f95959;
                position: absolute;
                right: 10%;
                top: 15%;
                width: 30%;
                max-height: 40%;
                height: 40%;
                border-radius: 10px;
                overflow: scroll;
            }
            #comentario_container{
                background-color: #f95959;
                position: absolute;
                right: 10%;
                width: 30%;
                height: 20%;
                bottom: 15%;
                border-radius: 10px;
            }
            #boton_analizar_sentimientos{
                position: absolute;
                left: 15%;
                bottom: 15%;
            }
        </style>
        <title>Vista Usuario</title>
    </head>
    <body>
        <div id="google_translate_element"></div>
        <div id="items">
            <form action="../usuario" method="post">
                <br>
                <label for="categoria">Seleccione una categoria</label>
                <select name="categoria">
                    <c:forEach items="${categorias}" var="categoria">
                        <option value="${categoria.getID()}">${categoria.getNombre()}</option>
                    </c:forEach>
                </select>
                <input type="submit" id="consultarPregunta" name="boton" value="Consultar">
                
                <div id="items_container">
                    <c:forEach items="${preguntas}" var="pregunta">
                        <label for="${pregunta.idToString()}">
                            <input type="radio" name="pregunta" value="${pregunta.idToString()}">${pregunta.toStringRespuesta()}<br>${pregunta.idToString()}
                        </label>
                    </c:forEach>
                </div>
                <input type="submit" id="consultarPregunta" name="boton" value="Consultar Pregunta">
            </form>
        </div>
        
        <div id="pregunta_seleccionada_container">
            
            ${preguntaConsultada.getID()}<br>
            ${preguntaConsultada.toStringCategoria()}<br>
            ${preguntaConsultada.toStringPregunta()}<br>
            ${preguntaConsultada.toStringRespuesta()}<br>
            ${preguntaConsultada.toStringFuenteRespuesta()}<br>
            ${preguntaConsultada.toStringEjemplo()}<br>
            ${preguntaConsultada.toStringFuenteEjemplo()}<br>
        </div>
        
        <div id="comentario_container">
            <form action="../usuario" method="post">
                <input type="hidden" name="item_id" value="${item_id}">
                Agregar Calificación<br>
                <textarea name="comentario" placeholder="Comentario" style="resize: none"></textarea><br>
                <label for="Respuesta-ChatGPT">
                    <input type="radio" name="tipo_comentario" value="Respuesta" required>Respuesta-ChatGPT
                </label><br>
                <label for="Ejemplo-ChatGPT">
                    <input type="radio" name="tipo_comentario" value="Ejemplo" required>Ejemplo-ChatGPT
                </label><br>
                Estrellas:<input type="number" name="estrellas" min="0" max="5" required><br>
                <input type="submit" name="boton" value="Agregar Calificacion">
            </form>
        </div>
                
        <form action="../usuario" method="post">
            <input id="boton_analizar_sentimientos" type="submit" name="boton" value="Analisis de Sentimientos"/>
        </form>
                
        <form action="../usuario" method="post">
            <input type="hidden" name="email" value="${email}">
            <input id="boton_eviar_pdf" type="submit" name="boton" value="Enviar PDF"/>
            <input type="submit" name="boton" value="Atras"/>
        </form>
                
        <label>${email}</label>
    </body>
</html>


