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
public final class Ren {
    private Ren(){
        
    }
    public static void rename(List<String> parts,boolean isValidParam, String noValidCommand){
        
        // si viene con espacios
        //ej. ren progra hola mundo
        //     1    2      3    4
        //y todos los nombres son válidos
        if(parts.size() > 3){
            Singleton.getInstance().error.printError("space", "" ,0);
            return;
        }
        // si es diferente de 3 y no posee parametros no validos
        //ej. ren hola
        if(parts.size() != 3 && isValidParam && noValidCommand.isEmpty()){
            Singleton.getInstance().error.printError("sintaxis", "" ,0);
            return;
        }
        //si es diferente de 3 y posee parametros no validos
        // ej. ren hola $mundo ($mundo se desecha) y se agrega a noValidCommand
        if(parts.size() != 3 && !isValidParam && !noValidCommand.isEmpty()){
            Singleton.getInstance().error.printError("errorNameDir", noValidCommand ,0);
            return;
        }
        if(!isValidParam && !noValidCommand.isEmpty()){
            Singleton.getInstance().error.printError(noValidCommand, noValidCommand, 0);
            return;
        }
        //si no existe la carpeta o archivo a la que se le va a cambiar el nombre
        if(!Singleton.getInstance().helper.siblingExists(parts, 1)){
            Singleton.getInstance().error.printError("childNoExists", Arbol.getRutaActual() + "\\" + parts.get(1), 0);
            return;
        }
       //si viene con el nombre del directorio malo
        if(!noValidCommand.isEmpty()){
            MD.DirectoryNameIsValid(noValidCommand, "DIR");
            return;
        }
        if(parts.size() == 3){
            //si la carpeta tiene el mismo nombre de la carpeta padre
        /*if(Singleton.getInstance().helper.ChildHasSameNameAsParent(parts, 2)){
            return;
        }*/
        //si ya existe la carpeta en el mismo nivel del árbol
        if(Singleton.getInstance().helper.siblingExists(parts, 2)){
            Singleton.getInstance().error.printError("directoryExists", Arbol.getRutaActual()
                        + "\\" + parts.get(2), 0);
            return;
        }
        //si la carpeta que se quiere renombrar va entrar en conflicto con ->
        //el mismo nombre de la carpeta o archivo de un hijo
        /*if(Singleton.getInstance().helper.parentWillHaveSameNameAsChildren(parts, 2)){
            Singleton.getInstance().error.printError("sameNameAsChild", "", 0);
            return;
        }*/
             //busca y obtiene la carpeta para cambiarle el nombre
            Object c = Arbol.getCarpetaActual(Singleton.getInstance().helper.getCarpetaActual().getNombre(), parts.get(1));
            //si no se encontró la carpeta
            if(c == null){
                Singleton.getInstance().error.printError("noRouteFound",Arbol.getRutaActual() + "\\" + parts.get(1) ,0);
                return;
            }
            if(c instanceof Carpeta){
                //si el nombre no es válido
                if(!MD.DirectoryNameIsValid(parts.get(2),"DIR")){
                    return;
                }
            }
            else{
                if(!MD.DirectoryNameIsValid(parts.get(2),"FIL")){
                    return;
                }
            }
            Singleton.getInstance().helper.getCarpetaActual().removeOrChangeNameChild(parts.get(1),"RENAME", parts.get(2));
        }
    }
}
