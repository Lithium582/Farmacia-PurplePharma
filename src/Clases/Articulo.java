/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import Interfaces.*;
import java.util.*;

/**
 *
 * @author VialeSoftUCU
 */

public class Articulo implements IArticulo {
    
    // <editor-fold defaultstate="extended" desc="Atributos">
    private final Comparable<Integer> id;
    private Date fecha_Creacion;
    private Date fecha_Actualizacion;
    private double precio;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private boolean refrigerado;
    private boolean receta;
    private Integer stock;
    private Integer anoVencimiento;
    
    // </editor-fold>

    // <editor-fold defaultstate="extended" desc="Constructores">
    /**
     * Constructor de Artículo
     */
    public Articulo(){
        this.id = 0;
    }
    
    /**
     *
     * @param pID ID
     * @param pFechaCreacion Fecha de creación
     * @param pFechaActualizacion Fecha de actualización
     * @param pPrecio Precio
     * @param pNombre Nombre
     * @param pDescripcion Descripción
     * @param pAnoVencimiento Año de Vencmiento del Artículo
     * @param pEstado Estado
     * @param pRefrigeracion Estado de refrigeración
     * @param pReceta Requiere receta
     */
    
    public Articulo(Comparable pID, Date pFechaCreacion, Date pFechaActualizacion, Double pPrecio, String pNombre, String pDescripcion, Integer pAnoVencimiento, boolean pEstado, boolean pRefrigeracion, boolean pReceta){
        this.id = pID;
        this.fecha_Creacion = pFechaCreacion;
        this.fecha_Actualizacion = pFechaActualizacion;
        this.precio = pPrecio;
        this.nombre = pNombre;
        this.descripcion = pDescripcion;
        this.estado = pEstado;
        this.refrigerado = pRefrigeracion;
        this.receta = pReceta;
        this.stock = 0;
        this.anoVencimiento = pAnoVencimiento;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="extended" desc="Propiedades">
    @Override
    public Comparable getID() {
        return this.id;        
    }

    @Override
    public Date getFechaCreacion() {
        return this.fecha_Creacion;
    }

    @Override
    public void setFechaCreacion(Date value) {
        this.fecha_Creacion = value;
    }

    @Override
    public Date getFechaActualizacion() {
        return this.fecha_Actualizacion;
    }

    @Override
    public void setFechaActualizacion(Date value) {
        this.fecha_Actualizacion = value;
    }

    @Override
    public double getPrecio() {
        return this.precio;
    }

    @Override
    public void setPrecio(double value) {
        this.precio = value;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public void setNombre(String value) {
        this.nombre = value;
    }

    @Override
    public String getDescripcion() {
        return this.descripcion;
    }

    @Override
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    @Override
    public Boolean getEstado() {
        return this.estado;
    }

    @Override
    public void setEstado(Boolean value) {
        this.estado = value;
    }

    @Override
    public boolean getRefrigerado() {
        return this.refrigerado;
    }

    @Override
    public void setRefrigerado(boolean value) {
        this.refrigerado = value;
    }

    @Override
    public boolean getReceta() {
        return this.receta;
    }

    @Override
    public void setReceta(boolean value) {
        this.receta = value;
    }

    @Override
    public int getStock() {
        return this.stock;
    }

    @Override
    public void setStock(Integer value) {
        this.stock = value;
    }
    
    @Override
    public Integer getAnoVencimiento() {
        return this.anoVencimiento;
    }
    
    //</ editor-fold>

    // <editor-fold defaultstate="extended" desc="Funciones y Métodos">
    public String toString() {
        String cadenaRetorno = this.getID().toString();
        cadenaRetorno += " - " + "Nombre: " + this.getNombre();
        cadenaRetorno += " - " + "Descripción: " + this.getDescripcion();
        cadenaRetorno += " - " + "Precio: " + this.getPrecio();
        if (this.getEstado() == null){
            cadenaRetorno += " - " + "Estado: NULL";
        }
        else{
            cadenaRetorno += " - " + "Estado: " + (this.getEstado() == true ? "Activo" : "Inactivo");
        }
        cadenaRetorno += " - " + "Refrigerado: " + (this.getRefrigerado() == true ? "Si" : "No");
        cadenaRetorno += " - " + "Recetado: " + (this.getReceta() == true ? "Si" : "No");
        cadenaRetorno += " - " + "Stock: " + (this.getStock());
        
        return cadenaRetorno;
    }
    // </editor-fold>
}