package com.ceep.tiendajuegos.excepciones;
/**
 * Creamos nuestra propia excepción, que extiende de AccesoDatosFicheroEx
 * para poder manejar los posibles errores de escritura de datos 
 * de los ficheros a lo largo del proyecto
 * @author Rey
 */
public class EscrituraDatosFicheroEx extends AccesoDatosFicheroEx{
    /**
     * Formaremos un constructor a nuestra excepción al que le pasemos un String
     * que con ayuda del Java.lang.exception y el mensaje pasado podremos escribir
     * con detalle la razón de que aparezca la excepción 
     * @param mensaje 
     */
    public EscrituraDatosFicheroEx(String mensaje){
        super(mensaje);
    }
}
