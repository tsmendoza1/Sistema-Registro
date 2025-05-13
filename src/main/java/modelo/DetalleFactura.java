/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

/**
 *
 * @author Dell Inspiron 16
 */
@Entity
@Table(name = "detalle_factura")

public class DetalleFactura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int cantidad;
    
    @Column(nullable = false)
    private double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;

    
    public DetalleFactura() {
    
    }
    
    
    public DetalleFactura(int cantidad, Double precioUnitario, Producto producto) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.producto = producto;
    }
    
    
    public DetalleFactura(int id, int cantidad, Double precioUnitario, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.producto = producto;
    }

    public DetalleFactura(int id, int cantidad, double precioUnitario, Producto producto, Factura factura) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.producto = producto;
        this.factura = factura;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
}
