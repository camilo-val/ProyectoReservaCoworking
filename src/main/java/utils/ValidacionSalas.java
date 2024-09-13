/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import model.Controller;
import model.SalasModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camilo
 */
public class ValidacionSalas {
    Controller controller = new Controller();
    public void crearSalas(){
        List<SalasModel> salas = new ArrayList<>();
        SalasModel salasModel = new SalasModel(1,"escritorio");
        salas.add(salasModel);
        salasModel = new SalasModel(2,"sala de reuniones");
        salas.add(salasModel);
        salasModel = new SalasModel(3,"oficina privada");
        salas.add(salasModel);
        controller.crearSalas(salas);
    }
        public boolean estaVacio(){
        controller.contarSala();
        return controller.contarSala()>=1;
    }
}
