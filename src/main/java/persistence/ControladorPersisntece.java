package persistence;

import model.ReservaModel;
import model.SalasModel;
import java.util.List;
import persistence.exceptions.NonexistentEntityException;

public class ControladorPersisntece {
    ReservaModelJpaController controller = new ReservaModelJpaController();
    SalasModelJpaController salasModelJpaController = new SalasModelJpaController();
    public void crearReserva(ReservaModel reservaModel) {
        controller.create(reservaModel);
    }

    public void crearSalas(List<SalasModel> salasModel) {
        for (int i = 0; i < salasModel.size(); i++) {
            salasModelJpaController.create(salasModel.get(i));
        }
    }

    public int contarSalas() {
        return salasModelJpaController.getSalasModelCount();
    }

    public List<ReservaModel> mostrarReservas() {
        return controller.findReservaModelEntities();
    }

    public void eliminarReserva(int idreserva) throws NonexistentEntityException {
        controller.destroy(idreserva);
    }
    
}
