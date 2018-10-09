/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Singleton;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author alast
 */
public final class MD {
    private MD(){
        
    }
    /*
    *Crea nuevos directorios a partir del directorio raiz MIDOS
    */
    public static void createDirectory(List<String> parts, List<String>directories, String actualPath, String actualParent,
            int freeMemory, int counter) throws FileNotFoundException, IOException{
        if(DirectoryNameIsValid(parts, actualParent)){
            List<String> folders = new LinkedList<>(Arrays.asList(parts.get(1).split("\\\\")));
            //si ya hay directorios guardados
            if(directories.size()>0){
               if(Singleton.getInstance().helper.directoryCount(actualPath, counter) <= 7){
                   int index = actualPath.lastIndexOf("\\")+1;
                   String parentName = actualPath.substring(index);
                   if(parentName.equals(parts.get(1))){
                    Singleton.getInstance().error.printError("sameNameAsParent", "" ,0);
                    return;
                   }
                    String pathToBeSaved = actualPath + "\\" + parts.get(1);
                    String result = Singleton.getInstance().helper.PathExists(pathToBeSaved, "MD");
                    //Si no existe el directorio que se va a guardar
                    if(result.equals("NoExist")){
                        saveFolderAndRemoveMemory(folders,"DIR", freeMemory, directories, actualPath);
                    }
                    //Si ya existe el directorio que se va a guardar
                    else{
                        Singleton.getInstance().error.printError("directoryExists", actualPath + "\\" + parts.get(1) ,0);
                    }
                }
               //Si la capacidad de directorios ya está al máximo
               else{
                   Singleton.getInstance().error.printError("capacity", "" ,0);
               }
            }
            //Si aun no hay directorios en la lista de directorios
            else{
                saveFolderAndRemoveMemory(folders,"DIR", freeMemory, directories, actualPath);
            }
        }         
    }
    private static boolean DirectoryNameIsValid(List<String> parts, String actualParent){
        if(!DirectoryNameIsOk(parts, actualParent)){
            return false;
        }
        return DirectoryLengthIsOk(parts);
    }
    /*
    *Revisa el nombre del directorio que se desea crear
    */
    private static boolean DirectoryNameIsOk(List<String> parts, String actualParent){
        String[] folders = null;
        if(parts.size() < 2){
            Singleton.getInstance().error.printError("sintaxis", "", 0);
            return false;
        }
        folders = parts.get(1).split("\\\\"); 
        for(int i = 0; i < folders.length; i++){
            if(folders[i].matches("[a-zA-Z][a-zA-Z0-9_]*") != true){
                Singleton.getInstance().error.printError("errorName", folders[i] ,0);
                return false;
            }
        }
        if(actualParent.equals("M:")){
            if(folders[0].equalsIgnoreCase("midos")){
                Singleton.getInstance().error.printError("sameNameAsParent", "" ,0);
                return false;
            }
        }
        return true;
    }
    /*
    *Revisa que el largo del nombre de l directorio es válido
    */
    private static boolean DirectoryLengthIsOk(List<String> parts){
        String[] directories = parts.get(1).split("\\\\"); 
        if(directories[directories.length -1].length() <= 8){   
            return true;
        }
        Singleton.getInstance().error.printError("length", "" ,0);
        return false;
    }
   
    /*
    *Función que remueve memoria y luego guarda la información de los directorios creados en @directorios
    *Primero utiliza @removeMemory()
    *Si @removememory() da el visto bueno con la memoria, agrega el directorio al @directories
    */
    private static void saveFolderAndRemoveMemory(List<String> folders, String toDelete, int freeMemory, List<String> directories, String actualPath){
        if(Singleton.getInstance().helper.removeMemory(freeMemory, toDelete)){
            directories.add("<DIR><NAME>"+folders.get(0)+"</NAME><PATH>"+ actualPath + "\\" +"</PATH></DIR>");
            }
            else{
                Singleton.getInstance().error.printError("noMemory", "" ,0);
            }
    }
}
