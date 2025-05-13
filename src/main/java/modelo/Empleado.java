/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 *
 * @author Dell Inspiron 16
 */

@Entity
@Table(name = "empleado")
public class Empleado extends Persona {
    
    public enum RolEmpleado{
        Cajero,
        Supervisor,
        Administrativo
    }
    
    @Enumerated(EnumType.STRING)
    private RolEmpleado rol;

    private LocalDate fechaIngreso;

    private boolean activo;
    
    
    public Empleado() {
        
    }

    public Empleado(RolEmpleado rol, LocalDate fechaIngreso, boolean activo, String nombre, String apellido, String correo, String telefono, LocalDate fecha_nacimiento, String cedula) {
        super(nombre, apellido, correo, telefono, fecha_nacimiento, cedula);
        this.rol = rol;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
    }
    
    // Getters and Setters
    public RolEmpleado getRol() {
        return rol;
    }

    public void setRol(RolEmpleado rol) {
        this.rol = rol;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
