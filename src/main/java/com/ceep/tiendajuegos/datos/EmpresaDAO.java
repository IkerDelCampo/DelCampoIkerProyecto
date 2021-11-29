
package com.ceep.tiendajuegos.datos;
/**
 * Importamos el paquete de excepciones SQLException para mangar la excepción y
 * gestionarla más adelante
 * Importaremos nuestra clase EmpresaDTO que nos permite crear el objeto
 * Importaremos List para poder usar esa colección.
 */
import com.ceep.tiendajuegos.dominio.EmpresaDTO;
import java.sql.SQLException;
import java.util.List;
/**
 * En esta clase de tipo interfaz definimos los metodos que despues implementaresmos 
 * en la clase EmpresaDaoJDBC con todos los lanzamientos de excepciones
 * en cada uno de ellos esto devido a aque esta clase no va a tener una interacción
 * directa con el usuario por lo que mandar cualquier tipo de informacion por pantalla
 * y demás no sería una buena practica, mejor hacemos un throws de la excepción 
 * y que se ocupe la futura capa.
 * @author Rey
 */
public interface EmpresaDAO {
    /**
     * seleccionarTodo: Función que nos devuelve un List Con toda el contenido
     * de la tabla.
     * @return
     * @throws SQLException 
     */
    List<EmpresaDTO> seleccionarTodo() throws SQLException;
    /**
     * insertar: Función que nos devuleve un numero entero, con el numero de inserts
     * realizados.
     * @param empresa
     * @return
     * @throws SQLException 
     */
    int insertar(EmpresaDTO empresa) throws SQLException;
    /**
     * actualizar: Función que nos devuleve un numero entero, con el numero de updates
     * realizados.
     * @param empresa
     * @return
     * @throws SQLException 
     */
    int actualizar(EmpresaDTO empresa) throws SQLException;
    /**
     * eliminar: Función que nos devuleve un numero entero, con el numero de deletes
     * realizados.
     * @param empresa
     * @return
     * @throws SQLException 
     */
    int eliminar(EmpresaDTO empresa) throws SQLException;
}
