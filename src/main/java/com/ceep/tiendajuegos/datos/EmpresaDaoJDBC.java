
package com.ceep.tiendajuegos.datos;
/**
 * Necesitaremos Importar todos los paquetes necesario y requeridos para poder 
 * menejar las excepciones, establecer la conexiones y los List.
 */
import static com.ceep.tiendajuegos.datos.Conexion.close;
import static com.ceep.tiendajuegos.datos.Conexion.getConnection;
import com.ceep.tiendajuegos.dominio.EmpresaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * En esta clase crearemos toda la funcionalidad e implementaremos toda la 
 * logiga de los Procedimientos y Funciones de la interfaz de la que extiende
 * para realizar las CRUD necesarios para la BBDD
 * @author Rey
 */
public class EmpresaDaoJDBC implements EmpresaDAO{
    private static final String SQL_SELECT = "SELECT * FROM empresas";
    private static final String SQL_INSERT = "INSERT INTO empresas (nombreEmpresa,nombreFundador,locationOficinas,ingresosAnuales) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE empresas SET nombreEmpresa=?,nombreFundador=?,locationOficinas=?,ingresosAnuales=? WHERE idEmpresa=?";
    private static final String SQL_DELETE = "DELETE FROM empresas WHERE idEmpresa=?";
    //private static final String SQL_CREATETABLE = "CREATE TABLE patata (nombre VARCHAR(30))";
    
    /**
     * LOs constructores y conexionTransaccional: nos permite conexión desde 
     * uera de los propios metodos.
     */
    private Connection conexionTransaccional;
    public EmpresaDaoJDBC(){
    }
    public EmpresaDaoJDBC(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    /**
     * seleccionarTodo: Función que nos devuleve un List con el contenido solicitado 
     * en el SQL_SELECT
     * 1.-Declaramos conn para la conexión con  la bbdd
     * 2.-Declaramos stmt para poder realizar la colsulta
     * 3.-Declaramos rs para almacenar el resultado de la consulta y poder hacer
     * coas con los datos
     * 4.-conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();
     * Con esa linea sabremos si la conexión probiene del metodo o de fuera de este
     * 5.-El while lo usamos para recorrer cada columna y leer el contenido de ellas
     * despues alamacenarlo como objeto tipo EmpresaDTO en el List empresas
     * 6.- el finally se ejecuta siempre y nos permite cerrar las conexiones.
     * @return
     * @throws SQLException 
     */
    @Override
    public List<EmpresaDTO> seleccionarTodo() throws SQLException {
        //1.- Creamos nuestros objetos a null
        Connection conn = null;//permite hacer un get de mi conexion
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //Cada linea lo combertiresmos en un objeto
        List<EmpresaDTO> empresas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();//me establece la conexion
            stmt = conn.prepareStatement(SQL_SELECT);//Metemos al sentencia de sql_select
            rs = stmt.executeQuery();
            while(rs.next()){
                //Leemos la info linea a linea y tienen que coincidir los datos
                int idEmpresa = rs.getInt("idEmpresa");
                String nombreEmpresa = rs.getString("nombreEmpresa");
                String nombreFundador = rs.getString("nombreFundador");
                String locationOficinas = rs.getString("locationOficinas");
                double ingresosAnuales = rs.getDouble("ingresosAnuales");
                empresas.add(new EmpresaDTO(idEmpresa, nombreEmpresa, nombreFundador, locationOficinas, ingresosAnuales));
            }
        } finally{//Siempre se ejecuta
            close(rs);
            close(stmt);
            if (this.conexionTransaccional == null){
                close(conn);
            }
        }
        return empresas;
    }
    
    /**
     * insertar: Función que devuelve un entero con el numero de inserciones realizadas
     * 1.-Declaramos conn para la conexión con  la bbdd
     * 2.-Declaramos stmt para poder realizar la colsulta
     * 3.-Declaramos rs para almacenar el resultado de la consulta y poder hacer
     * coas con los datos
     * 4.-conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();
     * Con esa linea sabremos si la conexión probiene del metodo o de fuera de este
     * 5.-stmt = conn.prepareStatement(SQL_INSERT) formula la inserción 
     * 6.-stmt.setString o el dato que sea cambia las ? por el valor que le asignemos
     * 7.-registros = stmt.executeUpdate() añade 1 a registros por cada inserción
     * realizada
     * 8.- el finally se ejecuta siempre y nos permite cerrar las conexiones.
     * @param empresa
     * @return
     * @throws SQLException 
     */
    @Override
    public int insertar(EmpresaDTO empresa) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();//me establece la conexion
            stmt = conn.prepareStatement(SQL_INSERT);
            //pasamos el valor a laos interrogantes el 1 significa el primer ?
            stmt.setString(1, empresa.getNombreEmpresa());
            stmt.setString(2, empresa.getNombreFundador());
            stmt.setString(3, empresa.getLocationOficinas());
            stmt.setDouble(4, empresa.getIngresosAnuales());
            //stmt.setString(5, empresa.getTelefono());
            registros = stmt.executeUpdate();
        } finally {
            close(stmt);
            if (this.conexionTransaccional == null){
                close(conn);
            }
        }
        return registros;
    }
    
    /**
     * actualizar: Función que devuelve un entero con el numero de Actualizaciones realizadas
     * 1.-Declaramos conn para la conexión con  la bbdd
     * 2.-Declaramos stmt para poder realizar la colsulta
     * 3.-Declaramos rs para almacenar el resultado de la consulta y poder hacer
     * coas con los datos
     * 4.-conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();
     * Con esa linea sabremos si la conexión probiene del metodo o de fuera de este
     * 5.-stmt = conn.prepareStatement(SQL_UPDATE) formula la actualización
     * 6.-stmt.setString o el dato que sea cambia las ? por el valor que le asignemos
     * 7.-registros = stmt.executeUpdate() añade 1 a registros por cada inserción
     * realizada
     * 8.- el finally se ejecuta siempre y nos permite cerrar las conexiones.
     * @param empresa
     * @return
     * @throws SQLException 
     */
    @Override
    public int actualizar(EmpresaDTO empresa) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();//me establece la conexion
            stmt = conn.prepareStatement(SQL_UPDATE);
            //Pasamos valores a ?
            stmt.setString(1, empresa.getNombreEmpresa());
            stmt.setString(2, empresa.getNombreFundador());
            stmt.setString(3, empresa.getLocationOficinas());
            stmt.setDouble(4, empresa.getIngresosAnuales());
            stmt.setInt(5, empresa.getIdEmpresa());
            registros = stmt.executeUpdate();
        } finally {
            close(stmt);
            if (this.conexionTransaccional == null){
                close(conn);
            }
        }
        return registros;
    }
    
    /**
     * eliminar: Función que devuelve un entero con el numero de Actualizaciones realizadas
     * 1.-Declaramos conn para la conexión con  la bbdd
     * 2.-Declaramos stmt para poder realizar la colsulta
     * 3.-Declaramos rs para almacenar el resultado de la consulta y poder hacer
     * coas con los datos
     * 4.-conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();
     * Con esa linea sabremos si la conexión probiene del metodo o de fuera de este
     * 5.-stmt = conn.prepareStatement(SQL_DELETE) formula la actualización
     * 6.-stmt.setInt cambia las ? por el valor que le asignemos
     * 7.-registros = stmt.executeUpdate() añade 1 a registros por cada inserción
     * realizada
     * 8.- el finally se ejecuta siempre y nos permite cerrar las conexiones.
     * @param empresa
     * @return
     * @throws SQLException 
     */
    @Override
    public int eliminar(EmpresaDTO empresa) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();//me establece la conexion
            stmt = conn.prepareStatement(SQL_DELETE);
            //Pasamos valores a ?
            stmt.setInt(1, empresa.getIdEmpresa());
            registros = stmt.executeUpdate();
        } finally {
            close(stmt);
            if (this.conexionTransaccional == null){
                close(conn);
            }
        }
        return registros;
    }
    
}
