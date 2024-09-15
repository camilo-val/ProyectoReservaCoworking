
package servlet;

import model.Controller;
import model.ReservaModel;
import model.SalasModel;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static utils.FormatoFecha.*;
import utils.ValidacionSalas;


@WebServlet(name = "ReservaServlet", urlPatterns = {"/ReservaServlet"})
public class ReservaServlet extends HttpServlet { 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        crearSala();
        //Se crea la session para poder setear la lista
        HttpSession session = request.getSession();
        //Variables para capturar los campos del jsp
        int sala = Integer.parseInt(request.getParameter("espacio_trabajo"));
        byte duracion = Byte.parseByte(request.getParameter("duracion_reserva"));        
        String nombre = request.getParameter("nombre"),
                fechajsp = request.getParameter("fecha_reserva"),
                horajsp = request.getParameter("inicio_reserva");
        
        //Variables en formato tiempo y fecha para poder insertar en la bd ya que
        //este es el formato en la entidad
        LocalDate fecha;
        LocalTime hora;
        
        //Variable que ejecuta la validación de campos
        boolean validarcampos = validarCampos(session, request, nombre, String.valueOf(sala), fechajsp, horajsp, String.valueOf(duracion));
        
        //Condicional para ejecutar el insert en la tabla reserva
        if (validarcampos) {
            //arreglo que captura la fecha, 1 posicion = año, 2 = día, 3 mes
            int[] fechaFormateada = formatoFecha(fechajsp.replaceAll("-", "")),
            horaFormateada = formatoHora(horajsp.replaceAll(":", ""));
            //Se asigna la hora en la variable de tipo LocalTime
            hora = LocalTime.of(horaFormateada[0],horaFormateada[1]);
            //Se asigna la fecha en la variable de tipo LocalDate
            fecha = LocalDate.of(fechaFormateada[0],fechaFormateada[1],fechaFormateada[2]);
            //Se instancia el controlador para ejecutar las sentencias sql
            Controller controller = new Controller();
            //Se crea el registro en BD
            controller.crearReserva(nuevaReserva(nombre, sala, fecha, hora,duracion));
            //Se mapea el mensaje de confirmación
            session.setAttribute("mensaje", "Se ha registrado la reserva de forma exitosa");
        }
        //Se reenderiza al index pero de esta manera conservamos nuestras variables de sessión
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    //Metodo de validación de errores por campo
    public boolean validarCampos(HttpSession session, HttpServletRequest request,String nombre, String espacio, String fecha,String hora,String duracion) throws IOException{
        System.out.println("Fecha actual jsp: " + fecha);

        String[] valorFecha = fecha.trim().split("-");
        LocalDate fechaActual = LocalDate.now(), fechareserva;
        
        //Condicional que valida que el campo no vacio
        if(nombre.isEmpty() || nombre.isBlank()){
            //Mapeo de mensaje de error si el campo esta vacio
            session.setAttribute("mensaje", "Nombre invalido, el nombre no puede ser vacio");
            return false;
        } 
        
        //Condicional que valida que el campo no vacio
        if(espacio.isEmpty() || espacio.isBlank() ||
            (Integer.parseInt(espacio) >3 || Integer.parseInt(espacio) <1)){
            //Mapeo de mensaje de error si el campo esta vacio
            session.setAttribute("mensaje", "Sala invalida, debe seleccionar una sala valida");
            return false;
        } 
         //Condicional que valida que el campo no vacio   
        if(fecha.isEmpty() || fecha.isBlank()) {
            session.setAttribute("mensaje", "Fecha invalida");
            return false;
        }
         //Condicional que valida que la fecha no tenga un formato incorrecto
        if(validarFormato(valorFecha)){
            int[] fechaFormateada = formatoFecha(fecha.replaceAll("-", ""));
            fechareserva = LocalDate.of(fechaFormateada[0], fechaFormateada[1], fechaFormateada[2]);
            //Condicional que valida que la fecha sea igual al dia actual o posterior
             if (fechareserva.isBefore(fechaActual)) {
                 System.out.println("Fecha actual: " + fechaActual + " : " + fechareserva);
                 session.setAttribute("mensaje", "Fecha invalida, La fecha debe ser el día actual o posterior");
                 return false;
             }
        }
        else{
            session.setAttribute("mensaje", "Fecha invalida");
            return false;
        }
        //Condicional que valida que el campo no vacio
        if(hora.isEmpty() || hora.isBlank() || !validarFormato(valorFecha)) {
            valorFecha = hora.trim().split(":");
            session.setAttribute("mensaje", "Hola invalida");
            return false;
        }
        //Condicional que valida que el campo no vacio
        if(duracion.isEmpty() || duracion.isBlank()){
            session.setAttribute("mensaje", "Nombre invalido, el nombre no puede ser vacio");
            return false;
        } 
        //Condicional que valida que el campo duración no permita numeros menores a 1 y mayores a 24
        if(Integer.parseInt(duracion)<0 ||Integer.parseInt(duracion)>25){
            session.setAttribute("mensaje", "Tiempo de duración invalido, debe ser en un rango de 24 horas");
            return false;
        }
        //Si las validaciones son exitosas retorna verdadero
        return true;
    }
    
    //Metodo para crear el objeto reserva
    private static ReservaModel nuevaReserva(String nombre, int espacio, LocalDate fecha,LocalTime hora,byte duracion){
        ReservaModel reservaModel = new ReservaModel();
        reservaModel.setNombre(nombre);
        reservaModel.setEspacio(new SalasModel(espacio,""));
        reservaModel.setFecha(fecha);
        reservaModel.setHora(hora);
        reservaModel.setDuracion(duracion);
        return reservaModel;
    }
    
    //Metodo que inserta los datos en la tabla sala para permitir la inserción en la bd
    private static void crearSala(){
        ValidacionSalas validacionSalas =  new ValidacionSalas();
            if(!validacionSalas.estaVacio()){
                validacionSalas.crearSalas();
            }
    }
}
