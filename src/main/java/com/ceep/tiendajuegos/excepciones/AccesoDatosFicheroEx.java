package com.ceep.tiendajuegos.excepciones;
/**
 * Creamos nuestra propia excepción, que extiende de Exception
 * para poder manejar los posibles errores de accesoa a datos 
 * de los ficheros a lo largo del proyecto
 * @author Rey
 */
public class AccesoDatosFicheroEx extends Exception{
    /**
     * Formaremos un constructor a nuestra excepción al que le pasemos un String
     * que con ayuda del Java.lang.exception y el mensaje pasado podremos escribir
     * con detalle la razón de que aparezca la excepción 
     * @param mensaje 
     */
    public AccesoDatosFicheroEx(String mensaje){
        super(mensaje);
    }
}
