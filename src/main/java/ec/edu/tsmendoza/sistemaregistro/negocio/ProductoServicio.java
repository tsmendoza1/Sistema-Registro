/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.tsmendoza.sistemaregistro.negocio;

import ec.edu.tsmendoza.sistemaregistro.datos.ProductoDAO;
import java.util.List;
import modelo.Persona;
import modelo.Producto;
/**
 *
 * @author Dell Inspiron 16
 */
public class ProductoServicio {
    private final ProductoDAO productoDao;

    public ProductoServicio() {
        this.productoDao = new ProductoDAO();
    }
    
    public int AgregarNuevoProducto(Producto producto){
        return productoDao.RegistrarProducto(producto);
    }
    
    public List<Producto> ObtenerProducto() {
        return productoDao.obtenerProductos();
    }
    
    public boolean EliminarProductoPorId(int numId) {
        return productoDao.EliminarProducto(numId);
    }

    public boolean ActualizarProducto(int id, Producto producto) {
        return productoDao.ActualizarProducto(id, producto);
    }
}
