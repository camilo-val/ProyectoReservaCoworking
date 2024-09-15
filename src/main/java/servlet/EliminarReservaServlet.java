/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Controller;
import model.ReservaModel;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author Camilo
 */
@WebServlet(name = "EliminarReservaServlet", urlPatterns = {"/EliminarReservaServlet"})
public class EliminarReservaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        Controller controller = new Controller();
        HttpSession session = request.getSession();
        List <ReservaModel> reservas= new ArrayList<>();
        //Se captura el id de registro a eliminar
        int idreserva = Integer.parseInt(request.getParameter("id_reserva"));
        try {
            //Se ejecuta el delete
            controller.eliminarReserva(idreserva);
            //Se ejecuta la consulta nuevamente para mostrar los datos actualizados
            reservas.addAll(controller.mostrarReservas());
            //Se devuelve mensaje y se setea la lista para actualizarla en front
            session.setAttribute("reservas", reservas);
            session.setAttribute("mensaje", "Registro Eiliminado exitosamente");
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EliminarReservaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se reenderiza al mostrarReservas pero de esta manera conservamos nuestras variables de sessión
        request.getRequestDispatcher("mostrarReservas.jsp").forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
