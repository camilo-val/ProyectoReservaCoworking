<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlet.ListaReservasServlet"%>
<%@page import="java.util.List"%>
<%@page import="model.ReservaModel"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Reservas</h1>
         <ul>
            <% 
                List<ReservaModel> listareservas = (List) request.getSession().getAttribute("reservas");
                int cont = 1;
                for (ReservaModel reservas : listareservas) {
            %>  
                
                <li>
                    <p><%=reservas.getNombre()%></p>
                    <p>Espacio: <%= reservas.getEspacio() != null ? reservas.getEspacio().getEspacio() : "No asignado" %></p>
                    <p><%=reservas.getFecha()%></p>
                    <p><%=reservas.getHora()%></p>
                </li>
                <form action="EliminarReservaServlet" method="POST">
                    <input disable="dasable" type="hidden" value="<%=reservas.getIdrserva()%>" name="id_reserva">
                    <button type="submit">Eliminar</button>
                </form>
            
        <% 
            cont = cont + 1;
            }

        %>
        </ul>

    </body>
</html>
