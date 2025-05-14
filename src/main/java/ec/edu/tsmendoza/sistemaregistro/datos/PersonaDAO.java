/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.tsmendoza.sistemaregistro.datos;

import modelo.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import modelo.Cliente;
import modelo.Empleado;
import util.persistenceUtil;

/**
 *
 * @author Dell Inspiron 16
 */
public class PersonaDAO {

//    private final ConexionDB conex;
//    public PersonaDAO() {
//        conex = new ConexionDB();
//    }
//    
//    public void agregarPersona(Persona persona) throws SQLException{
//        System.out.println("ingresando conexion");
//        String sql = "insert into usuario (nombre,apellido,correo, telefono, fecha_nacimiento, cedula) values (?,?,?,?,?,?);";
//        Connection conn = ConexionDB.AbrirConexion();
//        System.out.println("paso conexion");
//        try (PreparedStatement start = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
//            start.setString(1,persona.getNombre());
//            start.setString(2,persona.getApellido());
//            start.setString(3, persona.getCorreo());
//            start.setString(4, persona.getTelefono());
//            start.setString(5, String.valueOf(persona.getFecha_nacimiento()));
//            start.setString(6, persona.getCedula());
//            
//            int filasAfectadas = start.executeUpdate();
//            if(filasAfectadas > 0){
//                try(ResultSet generateKey = start.getGeneratedKeys()){
//                    if(generateKey.next()){
//                        int idGenerado = generateKey.getInt(1);
//                        System.out.println("Registro exitoso con id mas idgenerado");
//                    }else{
//                        System.out.println("No se genero ningun id");
//                    }
//                }
//            }else{
//                System.out.println("No se pudo insertar el registro");
//            }
//        }catch(SQLDataException EX){
//            System.out.println("Error al agregar a Persona" + EX.getMessage());
//        }finally{
//            ConexionDB.CerrarConexion(conn);
//        }
//    }
    public void AgregarPersona(Persona personaAgregar) {

        EntityManager em = persistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(personaAgregar);
            em.getTransaction().commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error de sesion de trabajo" + ex.getMessage());

        } finally {
            em.close();
        }
    }

    public int VerificarAgregarPersona(Persona personaAgregar) {
        int result = 0;
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = persistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Persona personaExiste = em.createQuery(
                    "SELECT p FROM Persona p WHERE p.cedula = :numId", Persona.class)
                    .setParameter("numId", personaAgregar.getCedula())
                    .getSingleResult();

            if (personaExiste != null) {
                System.out.println("YA EXISTE LA PERSONA");
                em.close();
                return result;
            }
        } catch (NoResultException ex) {
            // Se inicia la transicion
            em.getTransaction().begin();
            // Se inserta la persona
            em.persist(personaAgregar);
            // Confirmar y guardar los cambios
            em.getTransaction().commit();
            result = 1;
        } catch (Exception ex) {
            // Revertir todo, no guardar nada
            em.getTransaction().rollback();
            System.err.println("Error de sesion de trabajo: " + ex.getMessage());
            result = 2;
        } finally {
            em.close();
        }
        return result;
    }

    public List<Persona> obtenerPersona() {
        EntityManager em = persistenceUtil.getEntityManagerFactory().createEntityManager();
        List<Persona> personas = new ArrayList<>();

        try {
            em.getTransaction().begin();
            personas = em.createQuery("SELECT c FROM Cliente c", Persona.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error al obtener personas: " + ex.getMessage());
        } finally {
            em.close();
        }

        return personas;
    }

    // Metodo que permite actulizar la persona
    public boolean ActualizarPersona(int id, Persona personaActualizar) {
        EntityManager em = persistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Persona existente = em.find(Persona.class, id);
            if (existente == null) {
                return false;
            }

            em.getTransaction().begin();
            existente.setNombre(personaActualizar.getNombre());
            existente.setApellido(personaActualizar.getApellido());
            existente.setCedula(personaActualizar.getCedula());
            existente.setCorreo(personaActualizar.getCorreo());
            existente.setFecha_nacimiento(personaActualizar.getFecha_nacimiento());
            existente.setEdad(personaActualizar.getEdad());

            // em.merge(personaActualizar);
            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    // Eliminar persona
    // Si retorna true se elimino el registro, false no se pudo eliminar
    public boolean EliminarPersona(int numId) {
        EntityManager em = persistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Persona persona = em.find(Persona.class, numId);

            if (persona == null) {
                return false;
            }

            em.getTransaction().begin();
            em.remove(persona);
            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public int RegistrarPersona(Persona personaAgregar) {
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = persistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Long count = em.createQuery(
                    "SELECT COUNT(c) FROM Cliente c WHERE c.cedula = :ced", Long.class)
                    .setParameter("ced", personaAgregar.getCedula())
                    .getSingleResult();

            // Existe la persona, porque el contador dio un resultado
            if (count > 0) {
                return 0;
            }

            // Se inicia la transicion
            em.getTransaction().begin();
            // Se inserta la persona
            em.persist(personaAgregar);
            // Confirmar y guardar los cambios
            em.getTransaction().commit();
            return 1;
        } catch (Exception ex) {
            // Revertir todo, no guardar nada
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return 2;

        } finally {
            em.close();
        }
    }

    public Persona BuscarPersonaPorCedula(String cedula) {
        EntityManager em = persistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cliente c WHERE c.cedula = :ced", Persona.class)
                    .setParameter("ced", cedula)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Persona> ListarPersonasRegistradas() {
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = persistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Devuelve el listado de la busqueda
            return em.createQuery("SELECT c FROM Cliente c", Persona.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Empleado> obtenerEmpleados() {
        EntityManager em = persistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Empleado e", Empleado.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public boolean ActualizarEmpleado(int id, Empleado empleadoActualizar) {
        EntityManager em = persistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Empleado existente = em.find(Empleado.class, id);
            if (existente == null) {
                return false;
            }

            em.getTransaction().begin();

            // Campos heredados de Persona
            existente.setNombre(empleadoActualizar.getNombre());
            existente.setApellido(empleadoActualizar.getApellido());
            existente.setCedula(empleadoActualizar.getCedula());
            existente.setCorreo(empleadoActualizar.getCorreo());
            existente.setFecha_nacimiento(empleadoActualizar.getFecha_nacimiento());
            existente.setEdad(empleadoActualizar.getEdad());

            // Campos específicos de Empleado
            existente.setRol(empleadoActualizar.getRol());
            existente.setFechaIngreso(empleadoActualizar.getFechaIngreso());
            existente.setActivo(empleadoActualizar.isActivo());
            existente.setTelefono(empleadoActualizar.getTelefono());

            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace(); // para depurar errores
            return false;
        } finally {
            em.close();
        }
    }

    public List<Cliente> obtenerTodosLosClientes() {
        EntityManager em = persistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cliente c", Cliente.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    public boolean ActualizarCliente(int id, Cliente clienteActualizar) {
        EntityManager em = persistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Cliente existente = em.find(Cliente.class, id);
            if (existente == null) {
                return false;
            }

            em.getTransaction().begin();

            // Campos heredados de Persona
            existente.setNombre(clienteActualizar.getNombre());
            existente.setApellido(clienteActualizar.getApellido());
            existente.setCedula(clienteActualizar.getCedula());
            existente.setCorreo(clienteActualizar.getCorreo());
            existente.setFecha_nacimiento(clienteActualizar.getFecha_nacimiento());
            existente.setEdad(clienteActualizar.getEdad());

            // Campos propios del Cliente (si los tiene)
            existente.setDireccion(clienteActualizar.getDireccion());
            existente.setTelefono(clienteActualizar.getTelefono());

            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

}
