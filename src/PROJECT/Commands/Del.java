/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Arbol;
import PROJECT.Carpeta;
import PROJECT.Singleton;
import java.util.List;

/**
 *
 * @author alast
 */
public final class Del {
    private Del(){
        
    }
    
    public static void borrarArchivo(List<String> parts){
        //si no viene el nombre del directorio a eliminar
        if(parts.size() == 1){
            Singleton.getInstance().error.printError("sintaxis", "", 0);
            return;
        }
         //si vienen espacios entre el nombre del directorio
        if(parts.size() > 2){
            Singleton.getInstance().error.printError("space", "" ,0);
            return;
        }
        
        Object c = Arbol.getCarpetaActual(Singleton.getInstance().helper.getCarpetaActual().getNombre(), parts.get(1));
        //si el directorio que se quiere eliminar no existe
        if(c == null){
            Singleton.getInstance().error.printError("noRouteFound", "" ,0);
            return;
        }
        //si el directorio que se quiere eliminar es una carpeta
        if(c instanceof Carpeta){
            Singleton.getInstance().error.printError("notForDir", ((Carpeta) c).getNombre() ,1);
            return;
        }
        //si todo está bien elimina el directorio
        Singleton.getInstance().helper.getCarpetaActual().removeOrChangeNameChild(parts.get(1),"REMOVE","");
        //libera 4 KB de memoria disponible
        Singleton.getInstance().helper.SetFreeMemory(4,"add");
    }
}
