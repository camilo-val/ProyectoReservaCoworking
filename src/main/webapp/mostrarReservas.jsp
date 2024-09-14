<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlet.ListaReservasServlet"%>
<%@page import="java.util.List"%>
<%@page import="model.ReservaModel"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Reservas</title>
    </head>
    <body>
        <div class="container mt-5">           
            <h2 class="mb-4">Lista de Reservas</h2>
            <% String mensaje = (String) request.getSession().getAttribute("mensaje");
            if(mensaje != null && mensaje.equalsIgnoreCase("Registro Eiliminado exitosamente")){
            %>
                <div id="myAlert" class="alert alert-success alert-dismissible fade show" role="alert">
                    <%=mensaje%>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                 <%
                    }else if( mensaje != null && mensaje.equalsIgnoreCase("No se han registrado reservas")){
                 %>   
                <div id="myAlert" class="alert alert-success alert-dismissible fade show" role="alert">
                    <%=mensaje%>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <ul class="list-group">
                    <% 
                        }
                        request.getSession().removeAttribute("mensaje");
                        List<ReservaModel> listareservas = (List) request.getSession().getAttribute("reservas");
                        int cont = 1;
                        for (ReservaModel reservas : listareservas) {
                    %>  
                        <li class="list-group-item">
                            <p>Nombre: <%=reservas.getNombre()%></p>
                            <p>Espacio: <%= reservas.getEspacio() != null ? reservas.getEspacio().getEspacio() : "No asignado" %></p>
                            <p>Fecha: <%=reservas.getFecha()%> Hora: <%=reservas.getHora()%></p>
                            <p>Duración: <%=reservas.getDuracion()%> horas</p>
                            <form action="EliminarReservaServlet" method="POST">
                                <input disable="dasable" type="hidden" value="<%=reservas.getIdrserva()%>" name="id_reserva">
                                <button type="submit" class="btn btn-danger">Eliminar</button>
                            </form>
                        </li>
                    </ul>
                <% 
                    cont = cont + 1;
                    }

                %>
                <div class="text-center mt-3">
                    <a href="./index.jsp" class="btn btn-secondary">Volver</a>
                </div>
        </div>

    </body>
</html>
