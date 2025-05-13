/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Dell Inspiron 16
 */

@Entity
@Table(name = "cliente")
public class Cliente extends Persona{
    
    private String direccion;
    
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private Fidelidad fidelidad;
    
    
    // Constructor vacio
    public Cliente(){
        
    }

    public Cliente(String direccion, Fidelidad fidelidad, String nombre, String apellido, String correo, String telefono, LocalDate fecha_nacimiento, String cedula, int edad) {
        super(nombre, apellido, correo, telefono, fecha_nacimiento, cedula, edad);
        this.direccion = direccion;
        this.fidelidad = fidelidad;
    }

    public Cliente(int id, String nombre, String apellido, String correo, String telefono, LocalDate fecha_nacimiento, String cedula) {
        super(id, nombre, apellido, correo, telefono, fecha_nacimiento, cedula);
        this.direccion = direccion;
        this.fidelidad = fidelidad;
    }

    public Cliente(String direccion, String nombre, String apellido, String correo, String telefono, LocalDate fecha_nacimiento, String cedula) {
        super(nombre, apellido, correo, telefono, fecha_nacimiento, cedula);
        this.direccion = direccion;
    }
    
    public Cliente(String nombre, String apellido, String correo, String telefono, LocalDate fecha_nacimiento, String cedula) {
        super(nombre, apellido, correo, telefono, fecha_nacimiento, cedula);
        
    }

    // Getters and Setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Fidelidad getFidelidad() {
        return fidelidad;
    }

    public void setFidelidad(Fidelidad fidelidad) {
        this.fidelidad = fidelidad;
    }
    
}
