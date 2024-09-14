<%@page import="servlet.ReservaServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Agendar</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Bootstrap Example</title>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <h1>Bienvenido</h1>
                    <form action="ReservaServlet" method="POST">
                        <div class="mb-3 ">
                            <label class="form-label fs-4" for="nombre">Nombre</label>
                            <input class="form-control" type="text" placeholder="Nombre" id="nombre" name="nombre" required>
                        </div>
            
                        <div class="mb-3 ">
                            <label class="form-label fs-4" for="fecha_reserva">Fecha de reserva</label>
                            <input class="form-control" type="date" placeholder="Fecha de reserva" id="fecha_reserva" name="fecha_reserva"  required>
                        </div>
            
                        <div class="mb-3 ">
                            <label class="form-label fs-4" for="espacio_trabajo">Espacio de trabajo</label>
                            <select class="form-select form-select mb-3" required name="espacio_trabajo">
                                <option value="0">-------Seleccione la sala de reserva--------</option>
                                <option value="1">Sala de ventas</option>
                                <option value="2">Sala de normal</option>
                                <option value="3">Sala de normal</option>
                            </select>
                        </div>
            
                        <div class="mb-3 ">
                            <label class="form-label fs-4" for="duracion_reserva">Duración de la reserva</label>
                            <input class="form-control" type="time" placeholder="Duración de la reserva" id="duracion_reserva" name="duracion_reserva" required>
                        </div>
                        <%
                            String mensaje = (String )request.getSession().getAttribute("mensaje");
                            if (mensaje != null && mensaje.equalsIgnoreCase("Se ha registrado la reserva de forma exitosa")) {
                        %>
                            <div id="myAlert" class="alert alert-success alert-dismissible fade show" role="alert">
                                <%=mensaje%>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        <%
                            }else if(mensaje != null && mensaje.contains("invalid")){
                        %>          
                            <div id="myAlert" class="alert alert-danger alert-dismissible fade show" role="alert">
                                <%=mensaje%>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        <%
                            }
                            request.getSession().removeAttribute("mensaje");
                        %>
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary fs-4" type="submit">Enviar</button>
                            </div>

                    </form>
                    <form action="ListaReservasServlet" method="GET">
                        <div class="d-grid gap-2">
                            <button class="btn btn-secondary mt-3 fs-4" type="submit">Mostrar Usuarios</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
