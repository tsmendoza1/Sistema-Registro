/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.tsmendoza.sistemaregistro.presentacion;
import ec.edu.tsmendoza.sistemaregistro.negocio.ProductoServicio;
import modelo.Producto;
/**
 *
 * @author Dell Inspiron 16
 */
public class ProductoMain {
    public static void main(String[] args) {
        ProductoServicio servicio = new ProductoServicio();
        Producto p1 = new Producto("002", "mouse", 5.00);
        servicio.AgregarNuevoProducto(p1);
    }
}
