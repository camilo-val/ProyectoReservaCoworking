
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ReservaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    int idrserva;
    String nombre;
    LocalDate fecha; 
    @ManyToOne(fetch = FetchType.LAZY)
    SalasModel espacio;
    LocalTime hora;
    byte duracion;
    
    public int getIdrserva() {
        return idrserva;
    }

    public void setIdrserva(int idrserva) {
        this.idrserva = idrserva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public SalasModel getEspacio() {
        return espacio;
    }

    public void setEspacio(SalasModel espacio) {
        this.espacio = espacio;
    }


    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(byte duracion) {
        this.duracion = duracion;
    }
    
}
