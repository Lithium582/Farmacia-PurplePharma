/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lithium582
 */
public class ArbolTest {
    Arbol<Articulo> objArbol;
    
    public ArbolTest() {
        objArbol = new Arbol();
        objArbol.insertar(new NodoArbol<Articulo>(3,new Articulo()));
        objArbol.insertar(new NodoArbol<Articulo>(4,new Articulo()));
        objArbol.insertar(new NodoArbol<Articulo>(10,new Articulo()));
        objArbol.insertar(new NodoArbol<Articulo>(12,new Articulo()));
        objArbol.insertar(new NodoArbol<Articulo>(16,new Articulo()));
    }

    @Test
    public void testGetRaiz() {
        
    }

    @Test
    public void testInsertar() {
    }

    @Test
    public void testBuscar() {
    }

    @Test
    public void testInOrden() {
        System.out.println("Test InOrden");
        assertEquals("Test Inorden", "3-4-10-12-16",objArbol.inOrden());
    }

    @Test
    public void testEsVacio() {
    }

    @Test
    public void testVaciar() {
    }

    @Test
    public void testEliminar() {
    }

    @Test
    public void testLexicoGraficamenteAnterior() {
    }

    @Test
    public void testPosOrden() {
    }

    @Test
    public void testPreOrden() {
    }

    @Test
    public void testGetAltura() {
    }

    @Test
    public void testGetTamano() {
    }

    @Test
    public void testGetNivel() {
    }

    @Test
    public void testGetHojas() {
    }
    
}
