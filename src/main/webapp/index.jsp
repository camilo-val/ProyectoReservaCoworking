<%@page import="servlet.ReservaServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlet.ReservaServlet.*"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Agendar</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Bienvenido</h1>
        <form action="ReservaServlet" method="POST">
            <label for="nombre">Nombre</label>
            <input type="text" placeholder="Nombre" id="nombre" name="nombre" required=”required”>
            <label for="fecha_reserva">Fecha de reserva</label>
            <input type="date" placeholder="Fecha de reserva" id="fecha_reserva" name="fecha_reserva"  required=”required”>
            <label for="espacio_trabajo">Espacio de trabajo</label>
            <select required=”required” name="espacio_trabajo">
                <option>-------Seleccione la sala de reserva--------</option>
                <option value="1">Sala de ventas</option>
                <option value="2">Sala de normal</option>
                <option value="3">Sala de normal</option>
            </select>
            <label for="duracion_reserva">Duración de la reserva</label>
            <input type="time" placeholder="Duración de la reserva" id="duracion_reserva" name="duracion_reserva" required=”required”>
            <button type="submit">Enviar</button>
        </form>
        <form action="ListaReservasServlet" method="GET">
            <button type="submit">Mostrar Usuarios</button>
        </form>
    </body>
</html>
