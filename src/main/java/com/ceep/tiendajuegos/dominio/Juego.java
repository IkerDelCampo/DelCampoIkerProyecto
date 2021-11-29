/**
* La Clase Juego la declaramos para poder crear a lo largo de la ejecución de 
* nuestro progama los objetos con los que trabajaremos para poder ingresar,leer
* y eliminar lo que sea necesario.
*/
package com.ceep.tiendajuegos.dominio;
/**
 * Importaremos java.util.Date para poder crear atributos para el objeto de 
 * este tipo de dato.
 */
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 1.- Declaramos los atributos de nustro futuro objeto de forma privada para mayor 
 * seguridad y con el tipo de dato necesario
 * 2.- Declararemos tambien una variable estatica para poder forma el indice autoincremental
 * 3.1- El constructor vacio en parametros contendrá la forma de auto incrementar
 * el indice de nuestros objetos
 * 3.2- El segundo constructor con todos los parametros menos el indice que 
 * queremos auntoincrementar y que hace referencia al contructor vacio
 * 3.3- El ultimos contructor con todo en un principio no lo utiizaremos más que para pruebas
 * 4.- Despues declararemos todos los getter y setter no requeriremos de todos en principio
 * 5.- Por ultimo en esta clase meteremos el metodo toString para posteriormente
 * visualizar los objetos
 * @author Rey
 */
public class Juego {
    //Atributos 1
    private int idJuego; 
    private String nombre;
    private Double precio;
    private Dificultades dificultad;
    private Date fechaLanzamiento;
    private int idDesarrollador;
    
    // 2
    private static int contadorJuegos = 1;
    
    //Constructores 3
    public Juego() {
        this.idJuego = Juego.contadorJuegos++;
    }

    public Juego(String nombre, Double precio, Dificultades dificultad, Date fechaLanzamiento, int idDesarrollador) {
        this();
        this.nombre = nombre;
        this.precio = precio;
        this.dificultad = dificultad;
        this.fechaLanzamiento = fechaLanzamiento;
        this.idDesarrollador = idDesarrollador;
    }
    
    public Juego(int idJuego, String nombre, Double precio, Dificultades dificultad, Date fechaLanzamiento, int idDesarrollador) {
        this.idJuego = idJuego;
        this.nombre = nombre;
        this.precio = precio;
        this.dificultad = dificultad;
        this.fechaLanzamiento = fechaLanzamiento;
        this.idDesarrollador = idDesarrollador;
    }
    
    //Getter y Setter 4
    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Dificultades getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultades dificultad) {
        this.dificultad = dificultad;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(int idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    public static int getContadorJuegos() {
        return contadorJuegos;
    }

    public static void setContadorJuegos(int contadorJuegos) {
        Juego.contadorJuegos = contadorJuegos;
    }
    
    //Visualización 5
    @Override
    public String toString() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return "Juego{" + "idJuego=" + idJuego + ", nombre=" + nombre + ", precio=" + precio + ", dificultad=" + dificultad + ", fechaLanzamiento=" + formatoFecha.format(fechaLanzamiento) + ", idDesarrollador=" + idDesarrollador + '}';
    }
    
    
    
}

