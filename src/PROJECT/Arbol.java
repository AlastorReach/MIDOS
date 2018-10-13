/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author alast
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
    
    void archivoLeerOCrear() throws IOException, ClassNotFoundException{
        carpetaActual = Singleton.getInstance().helper.loadTxtFile2();
        if(carpetaActual == null){
            Singleton.getInstance().helper.SetFreeMemory(0, "reset");
            carpetaActual = new Carpeta("","MIDOS", null);
        }
    }
    public static Object getCarpetaActual(String ruta, String nombre){
        for(int i = 0; i< carpetaActual.getCantidadCarpetas(); i++){
            if(carpetaActual.getHijoInterno(i) instanceof Carpeta){
                Carpeta c = (Carpeta)carpetaActual.getHijoInterno(i);
                if(c.getNombre().equalsIgnoreCase(nombre) 
                        && c.getRuta().equalsIgnoreCase(ruta)){
                    return c;
                }
            }
            else if(carpetaActual.getHijoInterno(i) instanceof Archivo){
                Archivo a = (Archivo)carpetaActual.getHijoInterno(i);
                if(a.getNombre().equalsIgnoreCase(nombre) 
                        && a.getRuta().equalsIgnoreCase(ruta)){
                    return a;
                }
            }
        }
        return null;
    }
    
    public static void SetCarpetaActual(Carpeta c){
        carpetaActual = c;
    }
    
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
    public static boolean hasChildren(Carpeta c){
        return c.getCantidadCarpetas() > 0;
    }
    
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
