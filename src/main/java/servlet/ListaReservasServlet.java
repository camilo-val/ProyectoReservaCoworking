package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Controller;
import model.ReservaModel;

@WebServlet(name = "ListaReservasServlet", urlPatterns = {"/ListaReservasServlet"})
public class ListaReservasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //se crea la lista donde se van a retornar las reservas
            List <ReservaModel> reservas= new ArrayList<>();
            //Se ejecuta el select en la base de datos
            Controller controller = new Controller();
            //Se guarda el resultado en la lista
            reservas.addAll(controller.mostrarReservas());
            //Se crea la sessión
            HttpSession session = request.getSession();
            //Fragmento que valida si no hay datos
            if(reservas.isEmpty()){
                //Se setea mensaje de error para indicar al usuario que no hay datos
                session.setAttribute("mensaje", "No se han registrado reservas");
            }
            //se setea la lista para poderla mostrar en el front
            session.setAttribute("reservas", reservas);
            //Se reenderiza al mostrarReservas pero de esta manera conservamos nuestras variables de sessión
            request.getRequestDispatcher("mostrarReservas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
