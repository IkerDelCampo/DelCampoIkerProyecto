
package com.ceep.tiendajuegos.negocio;
/**
 * Importamos el paquete de excepciones creadas por nosotros para poder mandar
 * la excepción
 * Importaremos el paquete dominio que nos permite crear el objeto
 * Importaremos paquete util para poder usar la colección List.
 */
import com.ceep.tiendajuegos.dominio.*;
import com.ceep.tiendajuegos.excepciones.*;
import java.sql.SQLException;
import java.util.*;

/**
 * En esta clase de tipo interfaz definimos los metodos que despues implementaresmos 
 * en la clase CatalogoJuegosImp con todos los lanzamientos de excepciones
 * en cada uno de ellos esto devido a aque esta clase no va a tener una interacción
 * directa con el usuario por lo que mandar cualquier tipo de informacion por pantalla
 * y demás no sería una buena practica, mejor hacemos un throws de la excepción 
 * y que se ocupe la capa superior.
 * @author Rey
 */
public interface ICatalogoJuegos {
    /**
     * iniciarCatalogo: retornará un string para decirnos que completó la acción.
     * de crear el Catalogo
     * @param nombreCatalogo
     * @return
     * @throws AccesoDatosFicheroEx 
     */
    String iniciarCatalogo(String nombreCatalogo) throws AccesoDatosFicheroEx;
    /**
     * agregarJuego: retornará un string para decirnos que añadió el juego al fichero.
     * @param nombreFichero
     * @param juego
     * @param anexar
     * @return
     * @throws EscrituraDatosFicheroEx 
     */
    String agregarJuego(String nombreFichero, Juego juego, boolean anexar) throws EscrituraDatosFicheroEx;
    /**
     * listarJuegos: retornará el List de la capa de datos es decir que solo
     * se encargara de canal de comunicación evitando que la interfaz acceda 
     * directamente a la capa de datos.
     * @param nombreFichero
     * @return
     * @throws LecturaDatosFicheroEx 
     */
    List<Juego> listarJuegos(String nombreFichero) throws LecturaDatosFicheroEx;
    /**
     * localizarJuego: retornará el int que se trata de la fila en la que se 
     * encuentra la busqueda de la capa de datos hará de canal de comunicación 
     * evitando que la interfaz acceda directamente a la capa de datos.
     * @param nombreFichero
     * @param busquedaNombre
     * @return
     * @throws LecturaDatosFicheroEx 
     */
    int localizarJuego(String nombreFichero, String busquedaNombre) throws LecturaDatosFicheroEx;
    /**
     * borrarJuego: devolverá un String aclarando que el Juego fue borrado.
     * @param nombreFichero
     * @param nombreContenido
     * @return
     * @throws AccesoDatosFicheroEx 
     */
    String borrarJuego(String nombreFichero, String nombreContenido) throws AccesoDatosFicheroEx;
    /**
     * borrarCatalogo: devolverá un String aclarando que el Catalogo fue borrado.
     * @param nombreFichero
     * @return
     * @throws AccesoDatosFicheroEx 
     */
    String borrarCatalogo(String nombreFichero) throws AccesoDatosFicheroEx;
    
    //SQL
    /**
     * seleccionarTodoSQL: devolverá un List con los objetos obtenidos de la tabla
     * @return
     * @throws SQLException 
     */
    List<EmpresaDTO> seleccionarTodoSQL() throws SQLException;
    /**
     * insertarSQL: devolverá un int con el numero de instert realizados
     * @param empresa
     * @return
     * @throws SQLException 
     */
    int insertarSQL(EmpresaDTO empresa) throws SQLException;
    /**
     * actualizarSQL: devolverá un int con el numero de update realizados
     * @param empresa
     * @return
     * @throws SQLException 
     */
    int actualizarSQL(EmpresaDTO empresa) throws SQLException;
    /**
     * eliminarSQL: devolverá un int con el numero de delete realizados
     * @param empresa
     * @return
     * @throws SQLException 
     */
    int eliminarSQL(EmpresaDTO empresa) throws SQLException;
}
