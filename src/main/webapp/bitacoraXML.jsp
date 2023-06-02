<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${bitacora}</h1>
        
        <form action="bitacoraXML" method="post">
            <input type="time" name="rango1" required>Rango1<br>
            <input type="time" name="rango2" required>Rango2<br>
            <input type="submit" name="boton" value="Operaciones entre estas horas"><br>
            <input type="submit" name="boton" value="Operaciones de hoy">
        </form>
    </body>
</html>
