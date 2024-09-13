/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Camilo
 */
@Entity
public class SalasModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    int idespacio;
    String espacio;

    public SalasModel(int idespacio, String espacio) {
        this.idespacio = idespacio;
        this.espacio = espacio;
    }

    public SalasModel() {

    }

    public int getIdespacio() {
        return idespacio;
    }

    public void setIdespacio(int idespacio) {
        this.idespacio = idespacio;
    }

    public String getEspacio() {
        return espacio;
    }

    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }
}
