
package com.ceep.tiendajuegos.negocio;

import com.ceep.tiendajuegos.datos.*;
import com.ceep.tiendajuegos.dominio.*;
import com.ceep.tiendajuegos.excepciones.*;
import java.sql.SQLException;
import java.util.*;

/**
 * En esta clase crearemos toda la funcionalidad e implementaremos toda la 
 * logiga de los Procedimientos y Funciones de la interfaz de la que extiende.
 * @author Rey
 */
public class CatalogoJuegosImp implements ICatalogoJuegos{
    
    private final IAccesoDatosFichero datos;
    private final EmpresaDAO datos2;
    /**
     * Tanto el atributo IAccesoDatosFichero datos como el Constructor en esta 
     * clase serán necesario para poder hacer la "conexion" entre esta capa y la
     * inferior.
     */
    public CatalogoJuegosImp(){
        this.datos = new AccesoDatosFicheroImp();
        this.datos2 = new EmpresaDaoJDBC();
    }
    
    /**
     * iniciarCatalogo: Función que recibe un String y retorna otro estring además
     * de que ejecuta el metodo crearFichero de la capa inferior.
     * @param nombreCatalogo
     * @return
     * @throws AccesoDatosFicheroEx 
     */
    @Override
    public String iniciarCatalogo(String nombreCatalogo) throws AccesoDatosFicheroEx {
        datos.crearFichero(nombreCatalogo);
        return "El Catalogo " + nombreCatalogo + " se ha creado";
    }
    
    /**
     * agregarJuego: Función que recibe un String y retorna otro estring además
     * de que ejecuta el metodo agregarContenido de la capa inferior.
     * @param nombreFichero
     * @param juego
     * @param anexar
     * @return
     * @throws EscrituraDatosFicheroEx 
     */
    @Override
    public String agregarJuego(String nombreFichero, Juego juego, boolean anexar) throws EscrituraDatosFicheroEx {
        datos.agregarContenido(nombreFichero, juego, anexar);
        return "El Juego ha sido añadido con exito al Catalogo " + nombreFichero;
    }
    
    /**
     * listarJuegos: Función que recibe un String y retorna un List con los contenidos
     * además de que ejecuta el metodo listarContenido de la capa inferior.
     * @param nombreFichero
     * @return
     * @throws LecturaDatosFicheroEx 
     */
    @Override
    public List<Juego> listarJuegos(String nombreFichero) throws LecturaDatosFicheroEx {
        return datos.listarContenido(nombreFichero);
    }
    
    /**
     * localizarJuego: Función que recibe dos String y retorna un int con la fila
     * donde se encunetra el segundo String
     * @param nombreFichero
     * @param busquedaNombre
     * @return
     * @throws LecturaDatosFicheroEx 
     */
    @Override
    public int localizarJuego(String nombreFichero, String busquedaNombre) throws LecturaDatosFicheroEx {
        return datos.localizarContenido(nombreFichero, busquedaNombre);
    }
    
    /**
     * borrarJuego: Función que recibe un String y retorna otro estring además
     * de que ejecuta el metodo borrarContenido de la capa inferior
     * @param nombreFichero
     * @param nombreContenido
     * @return
     * @throws AccesoDatosFicheroEx 
     */
    @Override
    public String borrarJuego(String nombreFichero, String nombreContenido) throws AccesoDatosFicheroEx {
        datos.borrarContenido(nombreFichero, nombreContenido);
        return "El Juego ha sido borrado con exito en el Catalogo " + nombreFichero;
    }
    
    /**
     * borrarCatalogo: Función que recibe un String y retorna otro estring además
     * de que ejecuta el metodo borrarFichero de la capa inferior
     * @param nombreFichero
     * @return
     * @throws AccesoDatosFicheroEx 
     */
    @Override
    public String borrarCatalogo(String nombreFichero) throws AccesoDatosFicheroEx {
        datos.borrarFichero(nombreFichero);
        return "El Catalogo " + nombreFichero + " se eliminó con exito";
    }
    
    //SQL
    /**
     * seleccionarTodoSQL: Función que retorna un List Con el contenido del metodo
     * seleccionarTodo de la capa inferior
     * @return
     * @throws SQLException 
     */
    @Override
    public List<EmpresaDTO> seleccionarTodoSQL() throws SQLException {
        return datos2.seleccionarTodo();
    }
    
    /**
     * insertarSQL: Función que retorna un int de la función insertar de la capa inferior
     * @param empresa
     * @return
     * @throws SQLException 
     */
    @Override
    public int insertarSQL(EmpresaDTO empresa) throws SQLException {
        return datos2.insertar(empresa);
    }
    
    /**
     * actualizarSQL: Función que retorna un int de la función actualizar de la capa inferior
     * @param empresa
     * @return
     * @throws SQLException 
     */
    @Override
    public int actualizarSQL(EmpresaDTO empresa) throws SQLException {
        return datos2.actualizar(empresa);
    }
    
    /**
     * eliminarSQL: Función que retorna un int de la función eliminar de la capa inferior
     * @param empresa
     * @return
     * @throws SQLException 
     */
    @Override
    public int eliminarSQL(EmpresaDTO empresa) throws SQLException {
        return datos2.eliminar(empresa);
    }
    
}
