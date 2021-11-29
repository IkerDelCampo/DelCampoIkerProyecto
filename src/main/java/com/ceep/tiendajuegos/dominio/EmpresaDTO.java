
package com.ceep.tiendajuegos.dominio;

/**
 * La Clase EmpresaDTO la declaramos para poder crear a lo largo de la ejecución de 
 * nuestro progama los objetos con los que trabajaremos para poder realizar las
 * operaciones CRUD
 * 1.- Declaramos los atributos de nustro futuro objeto de forma privada para mayor 
 * seguridad y con el tipo de dato necesario
 * 3.2- El segundo constructor con todos los parametros menos el indice que 
 * queremos auntoincrementar 
 * 3.3- El ultimos contructor con todo en un principio no lo utiizaremos más que para pruebas
 * 4.- Despues declararemos todos los getter y setter no requeriremos de todos en principio
 * 5.- Por ultimo en esta clase meteremos el metodo toString para posteriormente
 * visualizar los objetos
 * @author Rey
 */
public class EmpresaDTO {
    //Atributos
    private int idEmpresa;
    private String nombreEmpresa;
    private String nombreFundador;
    private String locationOficinas;
    private double ingresosAnuales;
    
    //Constructores
    public EmpresaDTO() {
        
    }

    public EmpresaDTO(String nombreEmpresa, String nombreFundador, String locationOficinas, double ingresosAnuales) {
        this.nombreEmpresa = nombreEmpresa;
        this.nombreFundador = nombreFundador;
        this.locationOficinas = locationOficinas;
        this.ingresosAnuales = ingresosAnuales;
    }

    public EmpresaDTO(int idEmpresa, String nombreEmpresa, String nombreFundador, String locationOficinas, double ingresosAnuales) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.nombreFundador = nombreFundador;
        this.locationOficinas = locationOficinas;
        this.ingresosAnuales = ingresosAnuales;
    }
    
    //getter and setter
    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreFundador() {
        return nombreFundador;
    }

    public void setNombreFundador(String nombreFundador) {
        this.nombreFundador = nombreFundador;
    }

    public String getLocationOficinas() {
        return locationOficinas;
    }

    public void setLocationOficinas(String locationOficinas) {
        this.locationOficinas = locationOficinas;
    }

    public double getIngresosAnuales() {
        return ingresosAnuales;
    }

    public void setIngresosAnuales(double ingresosAnuales) {
        this.ingresosAnuales = ingresosAnuales;
    }
    
    //Visualizar toString

    @Override
    public String toString() {
        return "EmpresaDTO{" + "idEmpresa=" + idEmpresa + ", nombreEmpresa=" + nombreEmpresa + ", nombreFundador=" + nombreFundador + ", locationOficinas=" + locationOficinas + ", ingresosAnuales=" + ingresosAnuales + '}';
    }
    
    
}


