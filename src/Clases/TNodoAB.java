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

public class TNodoAB<T> implements INodoAB<T> {

    // <editor-fold defaultstate="extended" desc="Atributos">
    private Integer etiqueta;
    private INodoAB hijoIzq;
    private INodoAB hijoDer;
    private T datos;
    // </editor-fold>

    // <editor-fold defaultstate="extended" desc="Atributos">
    /**
     * @param unaEtiqueta
     * @param unosDatos
     */
    public TNodoAB(Integer unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;
    }
// </editor-fold>
    
    public INodoAB getHijoIzq() {
        return hijoIzq;
    }

    public INodoAB getHijoDer() {
        return hijoDer;
    }

    /**
     * @param unElemento
     * @return
     */
    public boolean insertar(INodoAB unElemento) {
        if (unElemento.getEtiqueta().compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().insertar(unElemento);
            } else {
                hijoIzq = unElemento;
                return true;
            }
        } else if (unElemento.getEtiqueta().compareTo(etiqueta) > 0) {
            if (hijoDer != null) {
                return getHijoDer().insertar(unElemento);
            } else {
                hijoDer = unElemento;
                return true;
            }
        } else {
            // ya existe un elemento con la misma etiqueta.-
            return false;
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    public INodoAB buscar(Comparable unaEtiqueta) {

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

    @Override
    public void inOrden(LinkedList<Comparable> unaLista) {
        if (hijoIzq != null) {
            hijoIzq.inOrden(unaLista);

        }
        unaLista.add(this.getEtiqueta());
        if (hijoDer != null) {
            hijoDer.inOrden(unaLista);
        }

    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    /**
     * @return
     */
    public String imprimir() {
        return (etiqueta.toString());
    }

    @Override
    public T getDatos() {
        return datos;
    }

    @Override
    public void setHijoIzq(INodoAB<T> elemento) {
        this.hijoIzq = elemento;

    }

    @Override
    public void setHijoDer(INodoAB elemento) {
        this.hijoDer = elemento;
    }

  

   @Override
    public INodoAB eliminar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(this.getEtiqueta()) < 0) {
            if (this.hijoIzq != null) {
                this.hijoIzq = hijoIzq.eliminar(unaEtiqueta);
            }
            return this;
        }

        if (unaEtiqueta.compareTo(this.getEtiqueta()) > 0) {
            if (this.hijoDer != null) {
                this.hijoDer = hijoDer.eliminar(unaEtiqueta);

            }
            return this;
        }

        return quitaElNodo();
    }
 private INodoAB quitaElNodo() {
        if (hijoIzq == null) {    // solo tiene un hijo o es hoja
            return hijoDer;
        }

        if (hijoDer == null) { // solo tiene un hijo o es hoja
            return hijoIzq;
        }
// tiene los dos hijos, buscamos el lexicograficamente anterior
        INodoAB elHijo = hijoIzq;
        INodoAB elPadre = this;

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

    public INodoAB lexicoGraficamenteAnterior(Comparable unaClave){
        INodoAB res1 = null;
        INodoAB res2 = null;
        INodoAB retorno = null;
        
        if (this.hijoIzq != null){
            res1 = this.hijoIzq.lexicoGraficamenteAnterior(unaClave);
        }
        
        if (this.hijoDer != null){
            res2 = this.hijoDer.lexicoGraficamenteAnterior(unaClave);
        }

        if(res2 != null && res2.getEtiqueta().compareTo(unaClave) < 0){
            retorno = res2;
        }
        
        if((retorno == null) && (res1 != null) && (res1.getEtiqueta().compareTo(unaClave) < 0)){
            retorno = res1;
        }
        
        if (retorno == null){
            retorno = this;
        }else if(retorno != null && retorno.getEtiqueta().compareTo(this.getEtiqueta()) < 0 
                && this.getEtiqueta().compareTo(unaClave) < 0){
            retorno = this;
        }
        
        return retorno;
    }
 
}
