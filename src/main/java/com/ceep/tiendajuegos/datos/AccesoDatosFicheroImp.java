
package com.ceep.tiendajuegos.datos;
/**
 * Importamos el paquete de excepciones creadas por nosotros para poder mandar
 * la excepción
 * Importaremos nuestra clase Juego que nos permite crear el objeto
 * Importaremos List para poder usas esa colección
 * Importaremos el paquete io el cual será necesario para realizar acciones con ficheros
 * Importaremos el paquete text para poder realizar todo lo que requiera de date
 * Importaremos el paquete util para todo lo relacionado con la colección List
 */
import com.ceep.tiendajuegos.dominio.Dificultades;
import com.ceep.tiendajuegos.dominio.Juego;
import com.ceep.tiendajuegos.excepciones.*;
import java.io.*;
import java.text.*;
import java.util.*;

/**
 * En esta clase crearemos toda la funcionalidad e implementaremos toda la 
 * logiga de los Procedimientos y Funciones de la interfaz de la que extiende
 * @author Rey
 */
public class AccesoDatosFicheroImp implements IAccesoDatosFichero{
    /**
     * existeFichero: Creamos el objeto fichero de tipo File con el String que 
     * recibe y con ayuda de una función propia de file nos da verdadero 
     * o falso en caso de que exista o no.  
     * @param nombreFichero
     * @return
     * @throws AccesoDatosFicheroEx 
     */
    @Override
    public boolean existeFichero(String nombreFichero) throws AccesoDatosFicheroEx {
        File fichero = new File(nombreFichero);
        return fichero.exists();
    }
    
    /**
     * crearFichero: Creamos el objeto fichero de tipo File que con el String
     * que recibe;
     * Despues dentro del try para manejar la excepción IOException declaramos
     * escribir un objeto tipo PrintWriter que nos permite crear un archivo con
     * el nombre asignado al objeto file archivo.
     * @param nombreFichero
     * @throws AccesoDatosFicheroEx 
     */
    @Override
    public void crearFichero(String nombreFichero) throws AccesoDatosFicheroEx {
        File archivo = new File(nombreFichero);
        try {
            PrintWriter escribir = new PrintWriter(new FileWriter(archivo));
            escribir.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * listarContenido: Función que nos permite adquirir una lista con los contenidos
     * de cada linea de nuestro Fichero,
     * 1.-Utilizamos el objeto archivo de tipo File para derle nombre con el String
     * obtenido
     * 2.-Despues declaramos un objeto de tipo Juego que será necesario para
     * ingresar cada una de las lineas del fichero en el List
     * 3.-Un array de String de 6 ya que 6 es el número de atributos de nustro objeto
     * 4.-Un List contenidos que almacenara todos los objetos del archivo
     * 5.-formatoFecha será necesario para poder establecer un formato de fecha 
     * y hacer posteriormente el parseo
     * 6.-BufferedReader leer nos permite crear un descriptor para poder ir linea a linea
     * 7.-String lectura almacenará el contenido del BufferedReader leer
     * 8.-Es necesario que tras realizar una accion en este caso de lectura lo cerremos.
     * @param nombreFichero
     * @return
     * @throws LecturaDatosFicheroEx 
     */
    @Override
    public List<Juego> listarContenido(String nombreFichero) throws LecturaDatosFicheroEx {
        File archivo = new File(nombreFichero);
        Juego contenidoN = null;
        String[] contenido = new String[6]; 
        List<Juego> contenidos = new ArrayList<>();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        try {
            BufferedReader leer = new BufferedReader(new FileReader(archivo));
            String lectura = null;
            while((lectura = leer.readLine()) != null){
                contenido = lectura.split(";");
                contenidoN = new Juego(Integer.parseInt(contenido[0]), contenido[1], Double.parseDouble(contenido[2]), Dificultades.valueOf(contenido[3]), formatoFecha.parse(contenido[4]), Integer.parseInt(contenido[5]));
                contenidos.add(contenidoN);
            }
            leer.close();            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();            
        } catch (ParseException ex){
            ex.printStackTrace();
        }
        return contenidos;
    }
    
    /**
     * agregarContenido: Es un procedimiento que nos permite añadir un nuevo 
     * objeto al fichero
     * 1.-Utilizamos el objeto archivo de tipo File para derle nombre con el String
     * obtenido
     * 2.-Declaramos el objeto escribir de tipo PrintWriter con el cual podremos
     * acceder a metodos y funciones propias de la clase que nos permitiran añadir
     * contenido a nuestro fichero
     * 3.-Creamos una variable de tipo String  que contendra todo nuestro objeto
     * separado por ;
     * 4.-Cerramos escribir.
     * @param nombreFichero
     * @param juego
     * @param anexar
     * @throws EscrituraDatosFicheroEx 
     */
    @Override
    public void agregarContenido(String nombreFichero, Juego juego, boolean anexar) throws EscrituraDatosFicheroEx {
        File archivo = new File(nombreFichero);
        try {
            PrintWriter escribir = new PrintWriter(new FileWriter(archivo, anexar));
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String contenidoTxt = juego.getIdJuego() + ";" + juego.getNombre() + ";" + juego.getPrecio() + ";" + juego.getDificultad() + ";" + formatoFecha.format(juego.getFechaLanzamiento()) + ";" + juego.getIdDesarrollador();
            escribir.println(contenidoTxt);
            escribir.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * localizarContenido: Se trata de una función que recibe el nombre del fichero 
     * y el nombre que quieres buscar y devuelve un número entero, que es el numero
     * de fila en la que se encuantra la busqueda en el fichero
     * 1.-Utilizamos el objeto archivo de tipo File para derle nombre con el String
     * obtenido
     * 2.-Crearemos un array de tipo String en el cual meteresmos el contenido
     * de la variable lectura spliteado por ;
     * 3.-Un contador que nos permitira devolver la posicion del contenido
     * 4.-Un string lectura que contendra la linea del puntero
     * 5.-Un BufferedReader leer que nos dará un puntero para poder pasar linea 
     * por linea y leer la información
     * 6.-Un if que en caso de encontar lo que buscamos rompe el bucle y evita 
     * que siga leyendo
     * 7.-Se cierra la lectura
     * @param nombreFichero
     * @param busquedaNombre
     * @return
     * @throws LecturaDatosFicheroEx 
     */
    @Override
    public int localizarContenido(String nombreFichero, String busquedaNombre) throws LecturaDatosFicheroEx {
        File archivo = new File(nombreFichero);
        String[] contenido = new String[6];
        int contador = 0;
        try {
            BufferedReader leer = new BufferedReader(new FileReader(archivo));
            String lectura = null;
            
            while((lectura = leer.readLine()) != null){
                contenido = lectura.split(";"); 
                if (busquedaNombre.equalsIgnoreCase(contenido[1])){ 
                    break;
                }
                contador++;
            }
            leer.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return contador;
    }
    /**
     * borrarContenido: Procedimiento que recibe dos String uno con el nombre 
     * del fichero y otro con el nombre del contenido, que nos permite eliminar
     * por el nombre del contenido
     * 1.-Declaramos dos objetos de tipo File uno será el que contenga el archivo
     * y el otro sera una copia del anterior
     * 2.-Declaramos un Array de String que contenga el String lectura spliteado por ;
     * 3.-Un BufferedReader leer para pasar por cada una de las lineas de nuestro
     * fichero
     * 4.-Un PrintWriter escribir para añadir en archivoBackup todas la lineas menos
     * la que desamos eliminar
     * 5.-String lecturas que contendra la linea del BufferedReader leer
     * 6.-Condicional if que en caso de que no coincida la comparación escribe el
     * String[] contenido en archivoBackup
     * 7.-Tenemos que cerrar tanto leer como escribir
     * 8.-Despues comprobamos que el fichero exista y si es asi lo borramos
     * 7.-Despues al archivo copia le asignamos el nombre del original
     * @param nombreFichero
     * @param nombreContenido
     * @throws AccesoDatosFicheroEx 
     */
    @Override
    public void borrarContenido(String nombreFichero, String nombreContenido) throws AccesoDatosFicheroEx {
        File archivoOriginal = new File(nombreFichero);
        File archivoBackup = new File("temp.txt");
        String[] contenido = new String[6];
        try {
            BufferedReader leer = new BufferedReader(new FileReader(archivoOriginal));
            PrintWriter escribir = new PrintWriter(new FileWriter(archivoBackup));            
            String lectura = null;
            while((lectura = leer.readLine()) != null){
                contenido = lectura.split(";");
                if (!contenido[1].equalsIgnoreCase(nombreContenido)){
                    escribir.println(lectura);
                }                    
            }
            leer.close();
            escribir.close();
            if (existeFichero(nombreFichero)){
                borrarFichero(nombreFichero);
            }
            archivoBackup.renameTo(archivoOriginal);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
    /**
     * borrarFichero: Procedimiento que recibe un String con el nombre del fichero
     * Si lo encuentra lo borra
     * 1.-File archivo con el nombre del fichero
     * 2.-if que llama al metodo existeFichero para saber si existe o no
     * 3.-archivo.delete(); funcion de la clase File que permite eliminar el archivo
     * @param nombreFichero
     * @throws AccesoDatosFicheroEx 
     */
    @Override
    public void borrarFichero(String nombreFichero) throws AccesoDatosFicheroEx {
        File archivo = new File(nombreFichero);
        //archivo.delete();
        /*if (archivo.delete()) { 
            System.out.println("Deleted the file: " + archivo.getName());
        } else {
        System.out.println("Failed to delete the file.");
        } */
        /*if (existeFichero(nombreFichero)){
            archivo.delete();
        }*/
        
        if (archivo.exists()){
            archivo.delete();
        }
        
    }
    
}
