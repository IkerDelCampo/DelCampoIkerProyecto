
package com.ceep.tiendajuegos.tienda;

import static com.ceep.tiendajuegos.datos.Conexion.getConnection;
import com.ceep.tiendajuegos.dominio.*;
import com.ceep.tiendajuegos.excepciones.*;
import com.ceep.tiendajuegos.negocio.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase se trata de la interfaz, la clase con la que el usuario va a tener
 * una interacción directa y en la que deberemos gestionar todas las excepciones
 * que fuimos mandando de una capa a otra, ya que en esta si sería correcto algo
 * como mostar un mensaje por pantalla para que sepa el usuario que es lo que ocurrió
 * @author Rey
 */
public class TiendaDeJuegos {
    /**
     * Precisaremos del escaner para que el usuario pueda elegir las opciones e 
     * ingresar datos por teclado en caso de que sea necesario
     */
    static Scanner teclado = new Scanner(System.in);
    /**
     * El main solo crear un objeto de tipo catalogo para poder acceder a sus metodos
     * LLama al menú
     * Y le proporciona al metodo opciones el objeto
     * @param args 
     */
    public static void main(String[] args) {
        ICatalogoJuegos C1 = new CatalogoJuegosImp();
        menu();
        opciones(C1);
    }
    /**
     * El menuTansolo muestra un cimulo de sout para que conozcas las opciones
     */
    public static void menu() {
        System.out.println("");
        System.out.println("\t\tMENÚ");
        System.out.println("1.- Crear un Catalogo:");
        System.out.println("2.- Añadir juego a un catalogo:");
        System.out.println("3.- Listar juegos de un catalogo:");
        System.out.println("4.- Localizar juego en una fila del catalogo:");
        System.out.println("5.- Borrar juego de un catalogo:");
        System.out.println("6.- Borrar un catalogo:");
        System.out.println("7.- Mostrar toda la tabla Empresas:");
        System.out.println("8.- Insertar datos en la tabla Empresas:");
        System.out.println("9.- Actualizar datos en la tabla Empresas:");
        System.out.println("10.- Eliminar datos en la tabla Empresas:");
        System.out.println("0.- Salir:");
    }
    /**
     * pedirOpcion: es una funcion que nos devulve un numero que pasará a ser la
     * opción elegida, esta función se asegura de que el dato ingresdo sea un numero
     * @return 
     */
    private static int pedirOpcion(){
        int opcion;
        while (true) {
            try {
                //me pide un número y si este no es un número "error" me lo pide de nuevo 
                opcion = Integer.parseInt(teclado.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Número no valido intentalo de nuevo");
            }
        }
        return opcion;
    }
    public static void opciones(ICatalogoJuegos datosCruciales) {
        int numero = pedirOpcion();
        while (numero != 0) {
            switch (numero) {
                /**
                 * case 1: Solicita un nombre para el archivo y te lo crea con 
                 * la extensión especificada.
                 */
                case 1:
                    System.out.println("Introduzca el nombre del Catalogo con la extensión");
                    System.out.println("Ejemplo: catalogo.txt");
                    try {
                        System.out.println(datosCruciales.iniciarCatalogo(teclado.nextLine()));
                    } catch (AccesoDatosFicheroEx e) {
                        System.out.println("Lo sentimos el Catalogo no se ha podido crear");
                    }
                    menu();
                    break;
                    
                /**
                 * case 2: Te permite agregar informción a un fichero en concreto
                 * con la extensión, los datos tendrán que ser introducidos uno a uno
                 * y este se añadirá a la primera fila vacia que encuentre.
                 */
                case 2:
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println("Introduzca el nombre del archivo con la extensión");
                    System.out.println("Ejemplo: catalogo.txt");
                    String nombreArchivo = teclado.nextLine();
                    System.out.println("Introduzca el nuevo Contenido en el archivo");
                    System.out.println("Ejemplo: Tetris 0 MuyDificil 21/11/2001 2");
                    System.out.println("Despues de ingresar uno, por favor pulse Enter");
                    System.out.println("Introduzca el nombre del juego");
                    String Atributo1 = teclado.nextLine();
                    System.out.println("Introduzca el precio del juego");
                    String Atributo2 = teclado.nextLine();
                    System.out.println("Introduzca la  dificultad {MuyFacil,Facil,Normal,Dificil,MuyDificil}\n¡Importante: la dificultad tiene que escribirla tal y como se le indica!");
                    String Atributo3 = teclado.nextLine();
                    System.out.println("Introduzca la fecha de salida Fromato: dd/MM/yyyy");
                    String Atributo4 = teclado.nextLine();
                    System.out.println("Introduzca el id del desarrollador");
                    String Atributo5 = teclado.nextLine();
                    try {
                        Juego juegoAdd = new Juego(Atributo1, Double.parseDouble(Atributo2), Dificultades.valueOf(Atributo3), formatoFecha.parse(Atributo4), Integer.parseInt(Atributo5));
                        try {
                            System.out.println(datosCruciales.agregarJuego(nombreArchivo, juegoAdd, true));
                        } catch (EscrituraDatosFicheroEx e) {
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(TiendaDeJuegos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    menu();
                    break;
                /**
                 * case 3: Nos permite Listar todo el contenido del Fichero que 
                 * seleccionemos si el fichero no existe no mostrará nada.
                 */    
                case 3:
                    System.out.println("Introduzca el nombre del catalogo con la extensión");
                    System.out.println("Ejemplo: catalogo.txt");
                    try {
                        List<Juego> listaJuegos = datosCruciales.listarJuegos(teclado.nextLine());// NO FUNCIONA
                        for (int i = 0; i < listaJuegos.size(); i++) {
                            System.out.println(listaJuegos.get(i));
                        }
                    } catch (LecturaDatosFicheroEx e) {
                        System.out.println("Lo sentimos no se ha podido listar el catalogo");
                    }
                    menu();
                    break;
                /**
                 * case 4: Identifica la fila empezando por cero en la que se 
                 * encurntra el contenido con el nombre especificado.
                 */    
                case 4:
                    System.out.println("Introduzca el nombre del catalogo con la extensión");
                    System.out.println("Ejemplo: catalogo.txt");
                    nombreArchivo = teclado.nextLine();
                    System.out.println("Introduzca el nombre del juego a buscar en "+nombreArchivo);
                    System.out.println("Ejemplo: Tetris");
                    String busquedaNombre = teclado.nextLine();
                    try {
                        System.out.println(datosCruciales.localizarJuego(nombreArchivo, busquedaNombre));
                    } catch (LecturaDatosFicheroEx e) {
                        System.out.println("El Juego " + busquedaNombre + "no se encontró");
                    }
                    menu();
                    break;
                /**
                 * case 5: Nos permite borrar un juego(contenido) especificando 
                 * el nombre del fichero y el nombre del juego.
                 */    
                case 5:
                    System.out.println("Introduzca el nombre del catalogo con la extensión");
                    System.out.println("Ejemplo: catalogo.txt");
                    nombreArchivo = teclado.nextLine();
                    System.out.println("Introduzca el nombre del Juego que desea borrar");
                    System.out.println("Ejemplo: Tetris");
                    String nombreContenido = teclado.nextLine();
                    try {
                        System.out.println(datosCruciales.borrarJuego(nombreArchivo, nombreContenido));
                    } catch (AccesoDatosFicheroEx e) {
                        System.out.println("No se pudo eliminar el Juego");
                    }
                    menu();
                    break;
                /**
                 * case 6: Especificando el nombre del archivo con la extensión
                 * si tiene borrará el fichero 
                 */
                case 6:
                    System.out.println("Introduzca el nombre del catalogo con la extensión");
                    System.out.println("Ejemplo: catalogo.txt");
                    nombreArchivo = teclado.nextLine();
                    try {
                        System.out.println(datosCruciales.borrarCatalogo(nombreArchivo));// NO FUNCIONA
                    } catch (AccesoDatosFicheroEx e) {
                        System.out.println("Error al borrar el catalogo");
                    }
                    menu();
                    break;
                /**
                 * case 7: Establece una conexión con la cual si en algún momento
                 * salta un error nos revertirán las acciones realizadas en caso
                 * de funcionar nos mostrará una lista con el contenido de la tabla sql.
                 */
                case 7:
                    Connection conexion = null;
                    try {
                        conexion = getConnection();
                        if (conexion.getAutoCommit()){
                            //Aqui preguntamos si esta el autocomit a true y si lo esta lo ponemos a false
                            conexion.setAutoCommit(false);
                        }
                        List<EmpresaDTO> empresas = datosCruciales.seleccionarTodoSQL();

                        empresas.forEach(empresa->{
                            System.out.println("Empresas =" + empresa);
                        });
                        //System.out.println("KKK" + personaDao.seleccionar()); //tambien lo muestra pero todo en una linea
                        conexion.commit();
                    } catch (SQLException eX) {
                        eX.printStackTrace(System.out);
                        System.out.println("Entramos en el rollback");
                        try {
                            //Revierte el estado, causado por un error en alguna consulta y devulvela al estado en el que estaba
                            conexion.rollback();
                        } catch (SQLException e) {
                            e.printStackTrace(System.out);
                        }
                    }
                    menu();
                    break;
                /**
                 * case 8: Establece una conexión con la cual si en algún momento
                 * salta un error nos revertirán las acciones realizadas en caso
                 * de funcionar nos añadirá información en la tabla.
                 */
                case 8:
                    conexion = null;
                    try {
                        conexion = getConnection();
                        if (conexion.getAutoCommit()){
                            //Aqui preguntamos si esta el autocomit a true y si lo esta lo ponemos a false
                            conexion.setAutoCommit(false);
                        }
                        System.out.println("Introduzca el nombre de la Empresa");
                        Atributo1 = teclado.nextLine();
                        System.out.println("Introduzca el nombre del Fundador");
                        Atributo2 = teclado.nextLine();
                        System.out.println("Introduzca la location de las Oficinas");
                        Atributo3 = teclado.nextLine();
                        System.out.println("Introduzca los ingresos Anuales");
                        double AtributoD = Double.parseDouble(teclado.nextLine());
                        EmpresaDTO empresaAdd = new EmpresaDTO(Atributo1, Atributo2, Atributo3, AtributoD);
                        datosCruciales.insertarSQL(empresaAdd);
                        conexion.commit();
                    } catch (SQLException eX) {
                        eX.printStackTrace(System.out);
                        System.out.println("Entramos en el rollback");
                        try {
                            //Revierte el estado, causado por un error en alguna consulta y devulvela al estado en el que estaba
                            conexion.rollback();
                        } catch (SQLException e) {
                            e.printStackTrace(System.out);
                        }
                    }
                    menu();
                    break;
                /**
                 * case 9: Establece una conexión con la cual si en algún momento
                 * salta un error nos revertirán las acciones realizadas en caso
                 * de funcionar nos actualizará la información en la tabla.
                 */
                case 9:
                    conexion = null;
                    try {
                        conexion = getConnection();
                        if (conexion.getAutoCommit()){
                            //Aqui preguntamos si esta el autocomit a true y si lo esta lo ponemos a false
                            conexion.setAutoCommit(false);
                        }
                        System.out.println("Introduzca el id de Empresa");
                        int AtributoI = Integer.parseInt(teclado.nextLine());
                        System.out.println("Introduzca el nombre de la Empresa");
                        Atributo1 = teclado.nextLine();
                        System.out.println("Introduzca el nombre del Fundador");
                        Atributo2 = teclado.nextLine();
                        System.out.println("Introduzca la location de las Oficinas");
                        Atributo3 = teclado.nextLine();
                        System.out.println("Introduzca los ingresos Anuales");
                        double AtributoD = Double.parseDouble(teclado.nextLine());
                        EmpresaDTO empresaUpdate = new EmpresaDTO(AtributoI,Atributo1, Atributo2, Atributo3, AtributoD);
                        datosCruciales.actualizarSQL(empresaUpdate);
                        conexion.commit();
                    } catch (SQLException eX) {
                        eX.printStackTrace(System.out);
                        System.out.println("Entramos en el rollback");
                        try {
                            //Revierte el estado, causado por un error en alguna consulta y devulvela al estado en el que estaba
                            conexion.rollback();
                        } catch (SQLException e) {
                            e.printStackTrace(System.out);
                        }
                    }
                    menu();
                    break;
                /**
                 * case 10: Establece una conexión con la cual si en algún momento
                 * salta un error nos revertirán las acciones realizadas en caso
                 * de funcionar nos eliminará información de la tabla.
                 */
                case 10:
                    conexion = null;
                    try {
                        conexion = getConnection();
                        if (conexion.getAutoCommit()){
                            //Aqui preguntamos si esta el autocomit a true y si lo esta lo ponemos a false
                            conexion.setAutoCommit(false);
                        }
                        System.out.println("Introduzca el id de Empresa");
                        int AtributoI = Integer.parseInt(teclado.nextLine());
                        System.out.println("Introduzca el nombre de la Empresa");
                        Atributo1 = teclado.nextLine();
                        System.out.println("Introduzca el nombre del Fundador");
                        Atributo2 = teclado.nextLine();
                        System.out.println("Introduzca la location de las Oficinas");
                        Atributo3 = teclado.nextLine();
                        System.out.println("Introduzca los ingresos Anuales");
                        double AtributoD = Double.parseDouble(teclado.nextLine());
                        EmpresaDTO empresaDelete = new EmpresaDTO(AtributoI,Atributo1, Atributo2, Atributo3, AtributoD);
                        datosCruciales.eliminarSQL(empresaDelete);
                        conexion.commit();
                    } catch (SQLException eX) {
                        eX.printStackTrace(System.out);
                        System.out.println("Entramos en el rollback");
                        try {
                            //Revierte el estado, causado por un error en alguna consulta y devulvela al estado en el que estaba
                            conexion.rollback();
                        } catch (SQLException e) {
                            e.printStackTrace(System.out);
                        }
                    }
                    menu();
                    break;
                case 0:
                    System.out.println("Muchas gracias por utilizar la aplicación");
                    break;
                default: System.out.println("opción incorrecta selecciona una del 0 al 10");
            }
            numero = pedirOpcion();
        }
    }
}
