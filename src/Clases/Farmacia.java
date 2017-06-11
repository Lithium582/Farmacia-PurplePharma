/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import Interfaces.*;
import java.util.*;
import java.text.*;

/**
 *
 * @author Lithium582
 */
public class Farmacia implements IFarmacia {
    
    // <editor-fold defaultstate="extended" desc="Atributos">
        private final String nombre;
        private String direccion;
        private String telefono;
        //Productos en STOCK
        private final Lista<IArbol<IArticulo>> listaArticulos;
        //Ventas realizadas
        private final Lista<IArbol<IMovimiento>> listaVentas;
        //Compras realizadas
        private final Lista<IArbol<IMovimiento>> listaCompras;
    // </editor-fold>
    
    // <editor-fold defaultstate="extended" desc="Constructores">
    
    /**
     * Constructor de Farmacia
     */
    public Farmacia(){
        this.nombre = "Purple Pharma";
        this.direccion = "Avenida Siempreviva 1234";
        this.telefono = "666-333-666";
        this.listaArticulos = new Lista<IArbol<IArticulo>>();
        this.listaVentas = new Lista<IArbol<IMovimiento>>();
        this.listaCompras = new Lista<IArbol<IMovimiento>>();
    }
    
    /**
     * Constructor de Farmacia
     * @param pNombre Nombre de la Farmacia
     * @param pDireccion Direccion de la Farnacia
     * @param pTelefono Teléfono de la Farnacia
    **/
    public Farmacia(String pNombre, String pDireccion, String pTelefono){
        this.nombre = pNombre;
        this.direccion = pDireccion;
        this.telefono = pTelefono;
        this.listaArticulos = new Lista<IArbol<IArticulo>>();
        this.listaVentas = new Lista<IArbol<IMovimiento>>();
        this.listaCompras = new Lista<IArbol<IMovimiento>>();
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="extended" desc="Propiedades">
    @Override
    public String getDireccion() {
        return this.direccion;
    }

    @Override
    public void setDireccion(String value) {
        this.direccion = value;
    }

    @Override
    public String getTelefono() {
        return this.telefono;
    }

    @Override
    public void setTelefono(String value) {
        this.telefono = value;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public Lista<IArbol<IArticulo>> getArticulos() {
        return this.listaArticulos;
    }

    @Override
    public Lista<IArbol<IMovimiento>> getVentas() {
        return this.listaVentas;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="extended" desc="Métodos y Funciones">
    @Override
    public Boolean cargarArticulos(String rutaArchivo) {
        Integer cantErroneos = 0;
        String[] elementos = ManejadorArchivosGenerico.leerArchivo(rutaArchivo);
        
        for (int i = 1; i < elementos.length; i++){
            String[] linea = elementos[i].split(";");
            
            if (linea.length == 9){
                try{
                    Comparable id = Integer.parseInt(linea[0].trim());
                    Date fecha_Creacion = FormatoFecha(linea[1].trim());
                    Date fecha_Actualizacion = FormatoFecha(linea[2].trim());
                    double precio =  Double.parseDouble((linea[3].trim()));
                    String nombre = linea[4].trim();
                    String descripcion = linea[5].trim();
                    Boolean estado = VerificarEstado(linea[6].trim());
                    boolean refrigerado = VerificarBooleano(linea[7].trim());
                    boolean receta = VerificarBooleano(linea[8].trim());
                    String areaAplicacion = "";
                    
                    int res = i%3;
                    int res2 = i%2;
                    int res3 = i%5;
                    
                    if (res3 == 0){
                        areaAplicacion = "MG";
                    }else if (res == 0){
                        areaAplicacion = "CAR";
                    }else if(res2 == 0){
                        areaAplicacion = "END";
                    }else{
                        areaAplicacion = "-1";
                    }
                    
                    
                    IArticulo a = new Articulo(id,fecha_Creacion,fecha_Actualizacion,precio,nombre,descripcion,estado,refrigerado,receta);
                    
                    this.InsertarArticulo(a,areaAplicacion);
                }
                catch(Exception ex){
                    cantErroneos++;
                }
            }else{
                cantErroneos++;
            }
        }
        
        if (cantErroneos > 0){
            System.out.println("Se han omitido " + cantErroneos + " registros incorrectos");
        }
        
        if (elementos.length == 0){
            return false;
        }
        
        return true;
    }
    
    @Override
    public Boolean cargarStock(String rutaArchivo) {
        Integer cantErroneos = 0;
        String[] elementos = ManejadorArchivosGenerico.leerArchivo(rutaArchivo);
        
        for (int i = 1; i < elementos.length; i++){
            String[] linea = elementos[i].split(";");
            
            if (linea.length == 2){
                Integer idArticulo = Integer.parseInt(linea[0].trim());
                Integer stock = Integer.parseInt(linea[1].trim());
                IArticulo objArticulo = BuscarXID(idArticulo);
                
                if(objArticulo != null){
                    Integer stockActual = objArticulo.getStock();
                    objArticulo.setStock(stockActual + stock);
                }
                else{
                    cantErroneos++;
                }
                
            }else{
                cantErroneos++;
            }
        }
        
        if (cantErroneos > 0){
            System.out.println("Se han omitido " + cantErroneos + " registros incorrectos");
        }
        
        if (elementos.length == 0){
            return false;
        }
        
        return true;
    }

    @Override
    public IArticulo BuscarXID(Comparable pId) {
       if (listaArticulos.esVacia()) {
            return null;
       }
       else {
            INodoLista<IArbol<IArticulo>> nodoActual = listaArticulos.getPrimero();
            
            while(nodoActual != null){
                INodoArbol<IArticulo> nodoArbol = nodoActual.getObjeto().buscar(pId);
                
                if(nodoArbol != null){
                    IArticulo prodBuscado = nodoArbol.getDatos();
                    
                    return prodBuscado;
                }
                nodoActual = nodoActual.getSiguiente();
            }
       }
       
       return null;
    }

    //No implementado
    @Override
    public String buscarXDescripcion(String pDescripcion) {
        String cadenaRetorno = "";
        if (listaArticulos.esVacia()) {
            return "";
        } else {
            Lista<IArticulo> listaRetorno = new Lista<IArticulo>();
            
            INodoLista<IArbol<IArticulo>> aux = listaArticulos.getPrimero();
            while (aux != null) {
                String nom = aux.getObjeto().getDescripcion().toLowerCase();
                if (nom.contains(pDescripcion.toLowerCase())) {
                    cadenaRetorno += aux.getObjeto().toString() + "\n";
                }
                aux = aux.getSiguiente();
            }
        }
        return cadenaRetorno;
    }
    
    //No implementado
    @Override
    public String buscarXNombre(String pNombre) {
        String cadenaRetorno = "";
        if (listaArticulos.esVacia()) {
            return "";
        } else {
            Lista<IArticulo> listaRetorno = new Lista<IArticulo>();
            
            INodoLista<IArticulo> aux = listaArticulos.getPrimero();
            while (aux != null) {
                String nom = aux.getObjeto().getNombre().toLowerCase();
                if (nom.contains(pNombre.toLowerCase())) {
                    cadenaRetorno += aux.getObjeto().toString() + "\n";
                }
                aux = aux.getSiguiente();
            }
        }
        return cadenaRetorno;
    }

    @Override
    public Boolean InsertarArticulo(IArticulo pArticulo, Comparable pArea) {
        INodoLista<IArbol<IArticulo>> nodoBuscado = this.listaArticulos.Buscar(pArea);
        
        if (nodoBuscado == null){
            //Significa que el área no existe
            //Generamos el árbol y luego insertamos ese árbol a la lista
            INodoArbol objNodoArbol = new NodoArbol(pArticulo.getID(), pArticulo);
            IArbol objArbol = new Arbol(objNodoArbol);
            
            INodoLista<IArbol<IArticulo>> objLista = new NodoLista<IArbol<IArticulo>>(objArbol, pArea);
            
            this.listaArticulos.Insertar(objLista);
            return true;
        } else{
            INodoArbol<IArticulo> nodoArbolLista = nodoBuscado.getObjeto().buscar(pArticulo.getID());
            
            if (nodoArbolLista == null){
                INodoArbol<IArticulo> nuevoArticulo = new NodoArbol<IArticulo>(pArticulo.getID(),pArticulo);
                nodoBuscado.getObjeto().insertar(nuevoArticulo);
                return true;
            }
            else{
                IArticulo objEncontrado = nodoArbolLista.getDatos();
                
                objEncontrado.setDescripcion(pArticulo.getDescripcion());
                objEncontrado.setEstado(pArticulo.getEstado());
                objEncontrado.setFechaActualizacion(Calendar.getInstance().getTime());
                objEncontrado.setNombre(pArticulo.getNombre());
                objEncontrado.setPrecio(pArticulo.getPrecio());
                objEncontrado.setReceta(pArticulo.getReceta());
                objEncontrado.setRefrigerado(pArticulo.getRefrigerado());
                objEncontrado.setStock(pArticulo.getStock());
            }
            
            return true;
        }
    }

    @Override
    public Boolean EliminarArticulo(Comparable pId) {
        INodoLista<IArbol<IArticulo>> aux = listaArticulos.getPrimero();
        while (aux != null) {
            INodoArbol<IArticulo> nodoBuscado = aux.getObjeto().buscar(pId);
            
            if (nodoBuscado == null) {
                aux = aux.getSiguiente();
            }
            else{
                if (nodoBuscado.getDatos().getStock() > 0){
                    System.out.println("No se puede eliminar un artículo con stock");
                    return false;
                }
                else{
                    return listaArticulos.Borrar(pId);
                }
            }
        }
        
        System.out.println("Artículo inexistente");
        return false;
    }

    @Override
    public Boolean GuardarVenta(IMovimiento pVenta) {
        NodoLista<IMovimiento> pNodo = new NodoLista<IMovimiento>(pVenta, pVenta.getID());
        
        listaVentas.Insertar(pNodo);
        
        return true;
    }

    @Override
    public Boolean ReintegroVenta(Integer pIdVenta) {
        INodoLista<IMovimiento> nodoVenta = listaVentas.Buscar(pIdVenta);
        if (nodoVenta == null){
            return false;
        }
        
        IMovimiento objVenta = nodoVenta.getObjeto();
        
        if (objVenta == null){
            return false;
        }
        else{
            INodoLista<IArticulo> nodoArticulo = listaArticulos.Buscar(objVenta.GetIdArticulo());
            
            if (nodoArticulo == null){
                return false;
            }
            
            IArticulo objArticulo = nodoArticulo.getObjeto();
            Integer stockActual = objArticulo.getStock();
            Integer stockVendido = objVenta.GetCantidad();
            objArticulo.setStock(stockActual + stockVendido);
            
            listaVentas.Borrar(pIdVenta);
            
        }
        
        return true;
    }

    @Override
    public String retornarArticulos() {
        return listaArticulos.Print();
    }

    @Override
    public String retornarArticulos(String pSeparador) {
        if(listaArticulos != null){
            return listaArticulos.Print(pSeparador);
        }
        
        return "Lista aún no inicializado";
    }
    
    @Override
    public String retornarVentas() {
        return listaVentas.Print();
    }

    @Override
    public String retornarVentas(String pSeparador) {
        if(listaVentas != null){
            return listaVentas.Print(pSeparador);
        }
        
        return "Lista aún no inicializado";
    }

    @Override
    public String ListadoVenta(Date pFechaComienzo, Date pFechaFin) {
        String cadenaRetorno = "";
        if (listaVentas.esVacia()) {
            return "";
        } else {
            
            INodoLista<IMovimiento> aux = listaVentas.getPrimero();
            while (aux != null) {
                Date fechaVenta = aux.getObjeto().GetFecha();
                Long longFComienzo = pFechaComienzo.getTime();
                Long longFFin = pFechaFin.getTime();
                Long fVenta = fechaVenta.getTime();
                
                if (longFComienzo <= fVenta && longFFin >= fVenta) {
                    cadenaRetorno += aux.getObjeto().toString() + "\n";
                }
                aux = aux.getSiguiente();
            }
        }
        return cadenaRetorno;
    }
    
    /**
        * Método auxiliar que, dada una string con formato de fecha, la convierte
        * en un dato de tipo Date.
        * @param pFecha Fecha a castear.
        * @return Fecha en formato Date.
    **/
    public Date FormatoFecha (String pFecha) throws ParseException{
           try {
               SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
               Date date = dt.parse(pFecha);
               return date;
           } catch (ParseException e)
               {
                   throw e;
                   //System.err.println("Error de parsing: " + e.getMessage());
               }
       }

       /**
        * Metodo auxiliar que dada una string verifica que sea "Activo" o "Inactivo"
        * y le asigna valores booleanos correspondientes,
        * donde activo = true e inactivo = false.
        * @param str String a verificar.
        * @return Valor booleano correspondiente.
        */
       private Boolean VerificarEstado(String value) throws Exception{
           value = value.trim();
           try {
               if (value.toUpperCase().equals("ACTIVO")){
                   return true; 
               }
               else if (value.toLowerCase().equals("inactivo")){
                   return false;
               }
               else if (value.equals("")){
                   return null;
               }
               else {
                   throw new Exception("El valor " + value + " no es valido. Debe ser Activo/Inactivo.");
               }
           } catch (Exception ex) {
               throw ex;
               //System.out.println(ex.getMessage());
           }
       }

       /**
        * Metodo auxiliar que verifica si una string es "true" or "false",
        * y le asigna valores booleanos en consecuencia.
        * @param str String a verificar.
        * @return Valor booleano correspondiente.
        */
       private Boolean VerificarBooleano(String value) throws Exception{
           try {
               if ("True".equals(value) || "true".equals(value)){
                   return true;
               }
               if ("False".equals(value) || "false".equals(value)){
                   return false;
               }
               else {
                   throw new Exception("El valor " + value + " no es valido");
               }
           }
           catch (Exception ex){
               throw ex;
               //System.out.println(ex.getMessage());
           }
       }
    
    //</editor-fold>
 
}
