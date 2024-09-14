<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlet.ListaReservasServlet"%>
<%@page import="java.util.List"%>
<%@page import="model.ReservaModel"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>Reservas</title>
    </head>
    <body>
        <div class="container mt-5">           
            <h2 class="mb-4">Lista de Reservas</h2>
                <ul class="list-group">
                <% 
                    List<ReservaModel> listareservas = (List) request.getSession().getAttribute("reservas");
                    int cont = 1;
                    for (ReservaModel reservas : listareservas) {
                %>  
                    
                    <li class="list-group-item">
                        <p>Nombre: <%=reservas.getNombre()%></p>
                        <p>Espacio: <%= reservas.getEspacio() != null ? reservas.getEspacio().getEspacio() : "No asignado" %></p>
                        <p>Fecha: <%=reservas.getFecha()%> Hora: <%=reservas.getHora()%></p>
                        <p>Duración: </p>
                        <form action="EliminarReservaServlet" method="POST">
                            <input disable="dasable" type="hidden" value="<%=reservas.getIdrserva()%>" name="id_reserva">
                            <button type="submit" class="btn btn-danger">Eliminar</button>
                        </form>
                    </li>

                <% 
                    cont = cont + 1;
                    }

                %>
            </ul>
        </div>

    </body>
</html>
