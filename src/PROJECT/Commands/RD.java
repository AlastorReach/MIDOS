/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Arbol;
import PROJECT.Archivo;
import PROJECT.Carpeta;
import PROJECT.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alast
 */
public final class RD {
    private RD(){
        
    }
    public static void RD(List<String> parts, int counter, List<String>directories, String actualPath){
        //si el comando no posee parámetro de búsqueda
        if(parts.size() == 1){
            Singleton.getInstance().error.printError("sintaxis", "", 0);
            return;
        }
        RemoveDirectoryOrFile(parts.get(1), counter, directories,actualPath);
    }
    private static void RemoveDirectoryOrFile(String dir, int counter, List<String>directories, String actualPath){
        counter = 0;
        boolean notFound = true;
        String pathToBeSaved = actualPath + "\\" + dir;
         String dirOrFile = "";
         String dirOrFileName = "";
        List<String>dirs = new ArrayList();
        for(int i = 0; i < directories.size(); i++){
            int iniPath = directories.get(i).indexOf("<PATH>") + 6;
            int endPath = directories.get(i).indexOf("</PATH>");
            int iniName = directories.get(i).indexOf("<NAME>") + 6;
            int endName = directories.get(i).indexOf("</NAME>");
            dirOrFile = directories.get(i).substring(iniPath, endPath);
            dirOrFileName = directories.get(i).substring(iniName, endName);
            //si la ruta actual concuerda con la ruta que se recorre en la lista guardada
            if((actualPath +"\\").equalsIgnoreCase(dirOrFile)){
                //si el directorio o archivo que se quiere borrar existe en la lista guardada
               if((actualPath +"\\" + dir).equals(dirOrFile + dirOrFileName)){
                   //si no tiene directorios o archivos hijos
                    if(Singleton.getInstance().helper.directoryCount(dirOrFile + dirOrFileName) == 0){ 
                        directories.remove(i);
                        Singleton.getInstance().helper.SetFreeMemory(8,"add");
                        notFound = false;
                    }
                    //si el directorio que se quiere eliminar tiene hijos
                    else{
                        notFound = false;
                        Singleton.getInstance().error.printError("noEmpty", "" ,0);
                    }
               }
            }
        }
        if(notFound){
                Singleton.getInstance().error.printError("noRouteFound", pathToBeSaved ,0);
        }

    }
    
    public static void RD2(List<String> parts){
        if(parts.size() == 1){
            Singleton.getInstance().error.printError("sintaxis", "", 0);
            return;
        }
        RemoveDirectoryOrFile2(parts);
        
    }
    private static void RemoveDirectoryOrFile2(List<String>parts){
        Object c = Arbol.getCarpetaActual(Arbol.getRutaActual() + "\\", parts.get(1));
        Carpeta carpetaActual = Singleton.getInstance().helper.getCarpetaActual();
        if(c == null){
            System.err.println("No existe la ruta");
            return;
        }
        if(c instanceof Archivo){
            System.err.println("No se puede accesar a un archivo");
            return;
        }
        if(((Carpeta) c).getCantidadCarpetas() > 0){
            Singleton.getInstance().error.printError("noEmpty", "", 0);
            return;
        }
        carpetaActual.removeFolder(parts.get(1));
        Singleton.getInstance().helper.SetCarpetaActual(carpetaActual);
        Singleton.getInstance().helper.SetFreeMemory(8,"add");
    }
}
