
package model;

import java.util.List;
import persistence.ControladorPersisntece;
import persistence.exceptions.NonexistentEntityException;

public class Controller {
    private ControladorPersisntece controladorPersisntece = new ControladorPersisntece();
    
    public void crearReserva(ReservaModel reservaModel) {
        controladorPersisntece.crearReserva(reservaModel);
    }
    public void crearSalas(List<SalasModel> salas) {
        controladorPersisntece.crearSalas(salas);
    }

    public int contarSala() {
        return controladorPersisntece.contarSalas();
    }

    public List<ReservaModel> mostrarReservas() {
        return controladorPersisntece.mostrarReservas();
    }

    public void eliminarReserva(int idreserva) throws NonexistentEntityException {
        controladorPersisntece.eliminarReserva(idreserva);
    }
}
