/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Arbol;
import PROJECT.Carpeta;
import static PROJECT.Commands.MD.DirectoryNameIsValid;
import PROJECT.Singleton;
import java.util.List;

/**
 *
 * @author alast
 */
public final class Del {
    private Del(){
        
    }
    
    public static void borrarArchivo(List<String> parts, boolean isValidParam, String noValidCommand){
        //si no viene el nombre del directorio a eliminar
        if(parts.size() == 1 && isValidParam){
            Singleton.getInstance().error.printError("sintaxis", "", 0);
            return;
        }
        if(parts.size() == 1 && !isValidParam){
            Singleton.getInstance().error.printError("errorNameFile", noValidCommand, 0);
            return;
        }
        if(!isValidParam){
            Singleton.getInstance().error.printError("errorNameFile", noValidCommand, 0);
            return;
        }
         //si vienen espacios entre el nombre del directorio
        if(parts.size() > 2){
            Singleton.getInstance().error.printError("space", "" ,0);
            return;
        }
        if(!DirectoryNameIsValid(parts.get(1),"FIL")){
            return;
        }
        Object c = Arbol.getCarpetaActual(Singleton.getInstance().helper.getCarpetaActual().getNombre(), parts.get(1));
        //si el directorio que se quiere eliminar no existe
        if(c == null){
            Singleton.getInstance().error.printError("noRouteFound", Arbol.getRutaActual() + "\\" + parts.get(1) ,0);
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
