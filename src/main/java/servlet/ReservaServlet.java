
package servlet;

import model.Controller;
import model.ReservaModel;
import model.SalasModel;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        int sala = Integer.parseInt(request.getParameter("espacio_trabajo"));
        String nombre = request.getParameter("nombre");
        LocalDate fecha;
        LocalTime hora;
        int[] fechaFormateada = formatoFecha(request.getParameter("fecha_reserva").replaceAll("-", "")),
        horaFormateada = formatoHora(request.getParameter("duracion_reserva").replaceAll(":", ""));
        hora = LocalTime.of(horaFormateada[0],horaFormateada[1]);
        fecha = LocalDate.of(fechaFormateada[0],fechaFormateada[1],fechaFormateada[2]);
        Controller controller = new Controller();
        controller.crearReserva(nuevaReserva(nombre, sala, fecha, hora));
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
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
