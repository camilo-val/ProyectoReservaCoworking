
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
        HttpSession session = request.getSession();
        int sala = Integer.parseInt(request.getParameter("espacio_trabajo"));
        String nombre = request.getParameter("nombre"),
                fechajsp = request.getParameter("fecha_reserva"),
                horajsp = request.getParameter("duracion_reserva");
        LocalDate fecha;
        LocalTime hora;
        boolean validarcampos = validarCampos(session, request, nombre, String.valueOf(sala), fechajsp, horajsp);
        if (validarcampos) {
            int[] fechaFormateada = formatoFecha(fechajsp.replaceAll("-", "")),
            horaFormateada = formatoHora(horajsp.replaceAll(":", ""));
            hora = LocalTime.of(horaFormateada[0],horaFormateada[1]);
            fecha = LocalDate.of(fechaFormateada[0],fechaFormateada[1],fechaFormateada[2]);
            Controller controller = new Controller();
            controller.crearReserva(nuevaReserva(nombre, sala, fecha, hora));
            session.setAttribute("mensaje", "Se ha registrado la reserva de forma exitosa");
        }else{
            System.out.println(" Mensaje: "+session.getAttribute("mensaje"));
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    public boolean validarCampos(HttpSession session, HttpServletRequest request,String nombre, String espacio, String fecha,String hora) throws IOException{
        System.out.println("Fecha actual jsp: " + fecha);

        String[] valorFecha = fecha.trim().split("-");
        LocalDate fechaActual = LocalDate.now(), fechareserva;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        

        if(nombre.isEmpty() || nombre.isBlank()){
            session.setAttribute("mensaje", "Nombre invalido, el nombre no puede ser vacio");
            return false;
        } 
            
        if(espacio.isEmpty() || espacio.isBlank() ||
            (Integer.parseInt(espacio) >3 || Integer.parseInt(espacio) <1)){
            session.setAttribute("mensaje", "Sala invalida, debe seleccionar una sala valida");
            return false;
        } 
            
        if(fecha.isEmpty() || fecha.isBlank()) {
            session.setAttribute("mensaje", "Fecha invalida");
            return false;
        }
            
        if(validarFormato(valorFecha)){
            int[] fechaFormateada = formatoFecha(fecha.replaceAll("-", ""));
            fechareserva = LocalDate.of(fechaFormateada[0], fechaFormateada[1], fechaFormateada[2]);
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

        if(hora.isEmpty() || hora.isBlank() || !validarFormato(valorFecha)) {
            valorFecha = hora.trim().split(":");
            session.setAttribute("mensaje", "Hola invalida");
            return false;
        }
        return true;
    }
    private static ReservaModel nuevaReserva(String nombre, int espacio, LocalDate fecha,LocalTime hora){
        ReservaModel reservaModel = new ReservaModel();
        reservaModel.setNombre(nombre);
        reservaModel.setEspacio(new SalasModel(espacio,""));
        reservaModel.setFecha(fecha);
        reservaModel.setHora(hora);
        return reservaModel;
    }
    
    private static void crearSala(){
        ValidacionSalas validacionSalas =  new ValidacionSalas();
            if(!validacionSalas.estaVacio()){
                validacionSalas.crearSalas();
            }
    }
}
