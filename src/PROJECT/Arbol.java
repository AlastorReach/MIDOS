/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT;

import java.io.IOException;

/**
 *
 * @author Josué Mora González
 */
public class Arbol {
    
    private static Carpeta carpetaActual;
    private static String rutaActual;

    public static String getRutaActual() {
        return rutaActual;
    }

    public static void setRutaActual(String rutaActual) {
        Arbol.rutaActual = rutaActual;
    }
    public Carpeta getCarpeta(){
        return carpetaActual;
    }
    
    //se ejecuta al iniciar el programa, carga los datos del txt MIDOSTRE en la carpeta
    //si la carpeta luego de eso aun es nula se crea la carpeta raíz MIDOS (M)
    void archivoLeerOCrear() throws IOException, ClassNotFoundException{
        carpetaActual = Singleton.getInstance().helper.loadTxtFile2();
        if(carpetaActual == null){
            //si es nulo resetea el valor de la memoria a 256K
            Singleton.getInstance().helper.SetFreeMemory(0, "reset");
            carpetaActual = new Carpeta("","MIDOS", null);
        }
    }
    //Se utiliza para el comando CD y RD, busca la carpeta a la cual se quiere ingresar o borrar
    //DEvuelve un objeto que puede ser una carpeta, un archivo o nulo
    public static Object getCarpetaActual(String ruta, String nombre){
        for(int i = 0; i< carpetaActual.getCantidadCarpetas(); i++){
            if(carpetaActual.getHijoInterno(i) instanceof Carpeta){
                Carpeta c = (Carpeta)carpetaActual.getHijoInterno(i);
                if(c.getNombre().equalsIgnoreCase(nombre) 
                        && c.getPadre().getNombre().equalsIgnoreCase(ruta)){
                    return c;
                }
            }
            else if(carpetaActual.getHijoInterno(i) instanceof Archivo){
                Archivo a = (Archivo)carpetaActual.getHijoInterno(i);
                if(a.getNombre().equalsIgnoreCase(nombre) 
                        && a.getPadre().getNombre().equalsIgnoreCase(ruta)){
                    return a;
                }
            }
        }
        return null;
    }
    
    public static void SetCarpetaActual(Carpeta c){
        carpetaActual = c;
    }
    
    //establece la carpeta raiz como la carpeta actual
    //retorna la carpeta que no tiene padre ( la carpeta raiz )
    public static Carpeta GetFirstLevel(){
        Carpeta c = carpetaActual;
        boolean encontrado = false;
        while(!encontrado){
            if(c.getPadre() == null){
                return c;
            }
            else{
                c = c.getPadre();
            }
        }
        return c;
    }
    //devuelve true si la carpeta actual tiene hijos
    public static boolean hasChildren(Carpeta c){
        return c.getCantidadCarpetas() > 0;
    }
    
    //elimina la carpeta que se pide en el comando RD
    public static void removeFolder(Carpeta c, String nombre){
        for(int i = 0; i< carpetaActual.getCantidadCarpetas(); i++){
            if(carpetaActual.getHijoInterno(i) instanceof Carpeta){
                Carpeta b = (Carpeta)carpetaActual.getHijoInterno(i);
                if(b.getNombre().equalsIgnoreCase(nombre)){
                    carpetaActual.getHijoInterno(i);
                }
            }
        }
    }
    
    
}
