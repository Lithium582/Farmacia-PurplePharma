/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 * @T tipo
 * @author Lithium582
**/
import java.util.LinkedList;

public interface INodoAB<T> {

    /**
     * Obtiene el valor de la etiqueta del nodo.
     *
     * @return Etiqueta del nodo.
     */
    public Comparable getEtiqueta();

    /**
     * Obtiene el hijo izquierdo del nodo.
     *
     * @return Hijo Izquierdo del nodo.
     */
    public INodoAB getHijoIzq();

    /**
     * Obtiene el hijo derecho del nodo.
     *
     * @return Hijo derecho del nodo.
     */
    public INodoAB getHijoDer();

    /**
     * Asigna el hijo izquierdo del nodo.
     *
     * @param elemento Nodo a insertar
     */
    public void setHijoIzq(INodoAB<T> elemento);

    /**
     * Asigna el hijo derecho del nodo.
     *
     * @param elemento Nodo a insertar
     */
    public void setHijoDer(INodoAB<T> elemento);

    /**
     * Busca un elemento dentro del arbol con la etiqueta indicada.
     *
     * @param unaEtiqueta del nodo a buscar
     * @return Elemento encontrado. En caso de no encontrarlo, retorna nulo.
     */
    public INodoAB buscar(Comparable unaEtiqueta);

 

    /**
     * Inserta un elemento dentro del arbol.
     *
     * @param elemento Elemento a insertar.
     * @return Exito de la operaci�n.
     */
    public boolean insertar(INodoAB elemento);

  
    /**
     * Imprime en inorden el arbol separado por guiones.
     *
     * @return String conteniendo el InOrden
     */
    public String inOrden();

     /**
     * pone las etiquetas del recorrido en inorden en una linkedlist.
     *
     * @param unaLista
     */
    public void inOrden(LinkedList<Comparable> unaLista);

    /**
     * Retorna los datos contenidos en el elemento.
     *
     * @return
     */
    public T getDatos();
   
    /**
     * Elimina un elemento dada una etiqueta.
     * @param unaEtiqueta
     * @return 
     */
    public INodoAB<T> eliminar(Comparable unaEtiqueta);
	
    /**
     * Retorna el nodo lexicográficamente anterior
     * @param unaClave
     * @return 
     */
    public INodoAB lexicoGraficamenteAnterior(Comparable unaClave);

}
