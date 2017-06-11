/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.LinkedList;
import Interfaces.*;

/**
 *
 * @param <T> Tipo del nodo
 * @author Lithium582
 */

public class NodoArbol<T> implements INodoArbol<T> {

    // <editor-fold defaultstate="extended" desc="Atributos">
    private final Comparable etiqueta;
    private INodoArbol hijoIzq;
    private INodoArbol hijoDer;
    private final T datos;
    // </editor-fold>

    // <editor-fold defaultstate="extended" desc="Propiedades">
    @Override
    public INodoArbol getHijoIzq() {
        return hijoIzq;
    }

    @Override
    public INodoArbol getHijoDer() {
        return hijoDer;
    }
    
    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }
    
        @Override
    public T getDatos() {
        return datos;
    }

    @Override
    public void setHijoIzq(INodoArbol<T> elemento) {
        this.hijoIzq = elemento;
    }

    @Override
    public void setHijoDer(INodoArbol elemento) {
        this.hijoDer = elemento;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="extended" desc="Constructor">
    /**
     * @param unaEtiqueta
     * @param unosDatos
     */
    public NodoArbol(Comparable unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;
    }
    // </editor-fold>

    // <editor-fold defaultstate="extended" desc="Métodos y Funciones">
    /**
     * @param unElemento
     * @return
     */
    @Override
    public boolean insertar(INodoArbol unElemento) {
        if (unElemento.getEtiqueta().compareTo(this.etiqueta) < 0) {
            if (this.hijoIzq != null) {
                return this.hijoIzq.insertar(unElemento);
            } else {
                this.hijoIzq = unElemento;
                return true;
            }
        } else if (unElemento.getEtiqueta().compareTo(this.etiqueta) > 0) {
            if (this.hijoDer != null) {
                return getHijoDer().insertar(unElemento);
            } else {
                this.hijoDer = unElemento;
                return true;
            }
        } else {
            // Ya existe un elemento con esa etiqueta
            return false;
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @Override
    public INodoArbol buscar(Comparable unaEtiqueta) {

        if (unaEtiqueta.equals(etiqueta)) {
            return this;
        } else if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().buscar(unaEtiqueta);
            } else {
                return null;
            }
        } else if (hijoDer != null) {
            return getHijoDer().buscar(unaEtiqueta);
        } else {
            return null;
        }
    }

    /**
     * @return recorrida en inorden del subArbol que cuelga del elemento actual
     */
    @Override
    public String inOrden() {
        StringBuilder tempStr = new StringBuilder();
        if (hijoIzq != null) {
            tempStr.append(getHijoIzq().inOrden());
            tempStr.append("-");
        }
        tempStr.append(imprimir());
        if (hijoDer != null) {
            tempStr.append("-");
            tempStr.append(getHijoDer().inOrden());
        }

        return tempStr.toString();
    }
    
    public String imprimir() {
        return (etiqueta.toString());
    }
    
   @Override
    public INodoArbol<T> eliminar(Comparable pEtiqueta) {
        if (pEtiqueta.compareTo(this.getEtiqueta()) < 0) {
            if (this.hijoIzq != null) {
                this.hijoIzq = hijoIzq.eliminar(pEtiqueta);
            }
            return this;
        }

        if (pEtiqueta.compareTo(this.getEtiqueta()) > 0) {
            if (this.hijoDer != null) {
                this.hijoDer = hijoDer.eliminar(pEtiqueta);

            }
            return this;
        }

        return quitaElNodo();
    }
    
    private INodoArbol quitaElNodo() {
        if (hijoIzq == null) {    // solo tiene un hijo o es hoja
            return hijoDer;
        }

        if (hijoDer == null) { // solo tiene un hijo o es hoja
            return hijoIzq;
        }
// tiene los dos hijos, buscamos el lexicograficamente anterior
        INodoArbol elHijo = hijoIzq;
        INodoArbol elPadre = this;

        while (elHijo.getHijoDer() != null) {
            elPadre = elHijo;
            elHijo = elHijo.getHijoDer();
        }

        if (elPadre != this) {
            elPadre.setHijoDer(elHijo.getHijoIzq());
            elHijo.setHijoIzq(hijoIzq);
        }

        elHijo.setHijoDer(hijoDer);
        setHijoIzq(null);  // para que no queden referencias y ayudar al collector
        setHijoDer(null);
        return elHijo;
    }

 @Override
    public INodoArbol lexicoGraficamenteAnterior(Comparable pClave){
        INodoArbol res1 = null;
        INodoArbol res2 = null;
        INodoArbol retorno = null;
        
        if (this.hijoIzq != null){
            res1 = this.hijoIzq.lexicoGraficamenteAnterior(pClave);
        }
        
        if (this.hijoDer != null){
            res2 = this.hijoDer.lexicoGraficamenteAnterior(pClave);
        }

        if(res2 != null && res2.getEtiqueta().compareTo(pClave) < 0){
            retorno = res2;
        }
        
        if((retorno == null) && (res1 != null) && (res1.getEtiqueta().compareTo(pClave) < 0)){
            retorno = res1;
        }
        
        if (retorno == null){
            retorno = this;
        }else if(retorno != null && retorno.getEtiqueta().compareTo(this.getEtiqueta()) < 0 
                && this.getEtiqueta().compareTo(pClave) < 0){
            retorno = this;
        }
        
        return retorno;
    }
    
    @Override
    public String posOrden() {
        StringBuilder tempStr = new StringBuilder();
        if (hijoIzq != null) {
            tempStr.append(getHijoIzq().inOrden());
            tempStr.append("-");
        }
        if (hijoDer != null) {
            tempStr.append("-");
            tempStr.append(getHijoDer().inOrden());
        }
        tempStr.append(imprimir());
        
        return tempStr.toString();
    }

    @Override
    public String preOrden() {
        StringBuilder tempStr = new StringBuilder();
        tempStr.append(imprimir());
        if (hijoIzq != null) {
            tempStr.append(getHijoIzq().inOrden());
            tempStr.append("-");
        }
        if (hijoDer != null) {
            tempStr.append("-");
            tempStr.append(getHijoDer().inOrden());
        }
        
        return tempStr.toString();
    }

    @Override
    public int getAltura() {
        int der = -1;
        int izq = -1;
        if (this.hijoIzq!= null) {
            izq = this.hijoIzq.getAltura();
        }
        if (this.hijoDer!= null) {
            izq = this.hijoDer.getAltura();
        }
        return (1 + Math.max(der, izq));
    }

    @Override
    public int getTamano() {
        int der = -1;
        int izq = -1;
        if (this.hijoIzq!= null) {
            izq = this.hijoIzq.getTamano();
        }
        if (this.hijoDer!= null) {
            izq = this.hijoDer.getTamano();
        }
        return (1 + + der + izq);
    }

    @Override
    public int getNivel(Comparable pEtiqueta) {
        int retorno = 0;
        
        if (pEtiqueta.compareTo(this.etiqueta) == 0) {
            retorno = 0;
        } else if (pEtiqueta.compareTo(this.etiqueta) < 0) {
            if (this.hijoIzq == null) {
                retorno = -1;
            } else {
                retorno = this.hijoIzq.getNivel(pEtiqueta);
            }
        } else if (this.hijoDer == null) {
            retorno = -1;
        } else {
            retorno = this.hijoDer.getNivel(pEtiqueta);
        }
        
        retorno += 1;
        return retorno;
    }

    @Override
    public int getHojas() {
        int retorno = 0;
        if (this.hijoIzq != null){
            retorno += this.hijoIzq.getHojas();
        }
        if (this.hijoDer != null){
            retorno += this.hijoDer.getHojas();
        }
        if (this.hijoDer == null && this.hijoDer == null){
            retorno = 1;
        }
        return retorno;
    }
    
    // </editor-fold>
 
}
