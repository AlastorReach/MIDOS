/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Arbol;
import PROJECT.Archivo;
import PROJECT.Singleton;
import java.util.List;

/**
 *
 * @author alast
 */
public final class Type {
    private Type(){
        
    }
    
    public static void PrintContent(List<String> parts, boolean isValidParam, String noValidCommand){
       // si solo se digita type
        if(parts.size() == 1 && isValidParam){
            Singleton.getInstance().error.printError("sintaxis", "" ,0);
            return;
        }
        if(!isValidParam){
           MD.DirectoryNameIsValid(noValidCommand, "FIL");
            return;
       }
        //si vienen espacios entre el nombre del directorio
        if(parts.size() > 2){
            Singleton.getInstance().error.printError("spaceFile", "" ,0);
            return;
        }
        boolean exist = false;
        //recorre los hijos de la carpeta actual
        //si el nombre que se indica es igual al nombre de un hijo
        //luego si es un archivo
        //luego si el contenido no está vacío
        for(int i = 0; i < Singleton.getInstance().helper.getCarpetaActual().getCantidadCarpetas(); i++){
            if(parts.get(1).equalsIgnoreCase(Singleton.getInstance().helper.getCarpetaActual().getHijoInterno(i).toString())){
                exist = true;
                if(Singleton.getInstance().helper.getCarpetaActual().getHijoInterno(i) instanceof Archivo){
                   Archivo a = (Archivo)Singleton.getInstance().helper.getCarpetaActual().getHijoInterno(i);
                   if(!a.getContenido().isEmpty()){
                        for(int j = 0; j < a.getContenido().size(); j++){
                             System.out.println(a.getContenido().get(j));
                        }
                        return;
                   }
                }
                //si es un directorio
                else{
                    Singleton.getInstance().error.printError("isDir", parts.get(1), 1);
                    return;
                }
            }
        }
        //si no existe
        if(!exist){
                Singleton.getInstance().error.printError("noRouteFound", Arbol.getRutaActual() + "\\" + parts.get(1), 0);
        }
    }
}
