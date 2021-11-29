
package com.ceep.tiendajuegos.datos;
/**
 * Importamos el paquete de excepciones creadas por nosotros para poder mandar
 * la excepción
 * Importaremos nuestra clase Juego que nos permite crear el objeto
 * Importaremos List para poder usar esa colección.
 */
import com.ceep.tiendajuegos.dominio.Juego;
import com.ceep.tiendajuegos.excepciones.*;
import java.util.List;

/**
 * 1.-En esta clase de tipo interfaz definimos los metodos que despues implementaresmos 
 * en la clase AccesoDatosImpFichero con todos los lanzamientos de excepciones
 * en cada uno de ellos esto devido a aque esta clase no va a tener una interacción
 * directa con el usuario por lo que mandar cualquier tipo de informacion por pantalla
 * y demás no sería una buena practica, mejor hacemos un throws de la excepción 
 * y que se ocupe la futura capa.
 * @author Rey
 */
public interface IAccesoDatosFichero {
    /**
     * existeFichero: Función que nos devolverá un frue o false
     * dependiendo de si el nombre que recibe lo encuentra o no.
     * @param nombreFichero
     * @return
     * @throws AccesoDatosFicheroEx 
     */
    boolean existeFichero(String nombreFichero) throws AccesoDatosFicheroEx;
    /**
     * crearFichero: Procedimiento que recibe un texto y crea un archivo con este
     * @param nombreFichero
     * @throws AccesoDatosFicheroEx 
     */
    void crearFichero(String nombreFichero) throws AccesoDatosFicheroEx;
    /**
     * listarContenido: Función que devolverá una colección de tipo Juego.
     * @param nombreFichero
     * @return
     * @throws LecturaDatosFicheroEx 
     */
    List<Juego> listarContenido(String nombreFichero) throws LecturaDatosFicheroEx;
    /**
     * agregarContenido: Procedimiento que permitirá añadir contenido al fichero.
     * @param juego
     * @param nombreFichero
     * @param anexar
     * @throws EscrituraDatosFicheroEx 
     */
    void agregarContenido(String nombreFichero, Juego juego, boolean anexar) throws EscrituraDatosFicheroEx;
    /**
     * localizarContenido: Función que nos devolverá la localizacion de lo que recibe.
     * @param nombreFichero
     * @param busquedaNombre
     * @return
     * @throws LecturaDatosFicheroEx 
     */
    int localizarContenido(String nombreFichero, String busquedaNombre) throws LecturaDatosFicheroEx;
    /**
     * borrarContenido: Procedimiento que permitirá eliminar contenido del fichero.
     * @param nombreFichero
     * @param nombreContenido
     * @throws AccesoDatosFicheroEx 
     */
    void borrarContenido(String nombreFichero, String nombreContenido) throws AccesoDatosFicheroEx;
    /**
     * borrarFichero: Procedimiento que nos permite eliminar un fichero
     * @param nombreFichero
     * @throws AccesoDatosFicheroEx 
     */
    void borrarFichero(String nombreFichero)throws AccesoDatosFicheroEx;
}
