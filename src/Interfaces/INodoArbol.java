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

public interface INodoArbol<T> {

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
    public INodoArbol getHijoIzq();

    /**
     * Obtiene el hijo derecho del nodo.
     *
     * @return Hijo derecho del nodo.
     */
    public INodoArbol getHijoDer();

    /**
     * Asigna el hijo izquierdo del nodo.
     *
     * @param pElemento Nodo a insertar
     */
    public void setHijoIzq(INodoArbol<T> pElemento);

    /**
     * Asigna el hijo derecho del nodo.
     *
     * @param pElemento Nodo a insertar
     */
    public void setHijoDer(INodoArbol<T> pElemento);

    /**
     * Busca un elemento dentro del arbol con la etiqueta indicada.
     *
     * @param pEtiqueta del nodo a buscar
     * @return Elemento encontrado. En caso de no encontrarlo, retorna nulo.
     */
    public INodoArbol buscar(Comparable pEtiqueta);

    /**
     * Busca un elemento por el atributo recibido por parámetro
     *
     * @param pEtiqueta ID del elemento que se quiere buscar
     * @param pAttr Nombre del atributo de la clase por el cual debe buscar
     * @return Nodo con la etiqueta buscada o null si no existe
     */
    public String buscarXAtributo(String pAttr, String pStringBuscado);
    
    /**
     * Inserta un elemento dentro del arbol.
     *
     * @param pNodo Elemento a insertar.
     * @return Exito de la operaci�n.
     */
    public boolean insertar(INodoArbol pNodo);
  
    /**
     * Retorna todas las etiquetas del árbol en inOrden
     *
     * @return String conteniendo las etiquetas del árbol en inOrden
     */
    public String inOrden();
    
    /**
     * Retorna todas las etiquetas del árbol en posOrden
     *
     * @return String conteniendo las etiquetas del árbol en posOrden
     */
    public String posOrden();
    
    /**
     * Retorna todas las etiquetas del árbol en preOrden
     *
     * @return String conteniendo las etiquetas del árbol en preOrden
     */
    public String preOrden();

    /**
     * Retorna la altura del árbol
     * 
     * @return altura total del árbol
     */
    public int getAltura();
    
    /**
     * Retorna el tamaño del árbol
     * 
     * @return tamaño total del árbol
     */
    public int getTamano();
    
    /**
     * Retorna el nivel donde se encuentra la etiqueta
     * 
     * @param pEtiqueta etiqueta del nodo cuyo nivel quiere saberse
     * @return nivel donde se encuentra la etiqueta en el árbol
     */
    public int getNivel(Comparable pEtiqueta);
    
    /**
     * Retorna la cantidad de hojas del árbol
     * 
     * @return cantidad de hojas del árbol
     */
    public int getHojas();
    
    /**
     * Retorna los datos contenidos en el elemento.
     *
     * @return
     */
    public T getDatos();
   
    /**
     * Elimina un elemento dada una etiqueta.
     * @param pEtiqueta
     * @return 
     */
    public INodoArbol<T> eliminar(Comparable pEtiqueta);
    
    /**
     * Retorna el nodo lexicográficamente anterior
     * @param pClave
     * @return 
     */
    public INodoArbol lexicoGraficamenteAnterior(Comparable pClave);

}
