package com.ceep.tiendajuegos.datos;


import java.sql.*;

/**
 * La Clase conexión se encargará de l establecer la conexión y cerrar los distintos
 * procedimientos para evitar tener que añadir todo ese codigo repetidas veces en
 * los procedimientos y funciones de las demás clases que lo necesiten.
 * @author Rey
 */
public class Conexion {
    private static final  String JDBC_URL = "jdbc:mysql://localhost:3306/desarrolladores?"
            + "useSSL=false&useTimezone=true&serverTimezone=UTC&"
            + "allowPublicKeyRetrieval=true";
    private static final  String JDBC_USER = "root";
    private static final  String JDBC_PASSWORD = "";
    
    /**
     * getConnection: Función que permite establecer la conexion con la base de
     * datos con la ayuda DriverManager.getConnection(Eltipo de bbdd + el host + puerto
     * +nombre de la bbdd, el usuario que utilizamos root, y la contraseña del usuario)
     * @return
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException{
        /**
        *Permite establecer la conexion 
        */
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
    /**
     * close(ResultSet rs): Procedimiento para cerrar el ResultSet
     * @param rs
     * @throws SQLException 
     */
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    /**
     * close(Statement stmt): Procedimiento con el que creamos el stament
     * @param stmt
     * @throws SQLException 
     */
    public static void close(Statement stmt) throws SQLException{
        stmt.close();
    }
    /**
     * close(PreparedStatement stmt): Procedimiento con el que creamos el PreparedStatement
     * @param stmt
     * @throws SQLException 
     */
    public static void close(PreparedStatement stmt) throws SQLException{
        /**
         * Ejecuta las consultas de una forma mas optimizada
         */
        stmt.close();
    }
    /**
     * close(Connection conn): Procedimiento paera cerrar la connexión
     * @param conn
     * @throws SQLException 
     */
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}
