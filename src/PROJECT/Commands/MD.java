/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Arbol;
import PROJECT.Carpeta;
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
    /*y
    *Crea nuevos directorios a partir del directorio raiz MIDOS
    */
    public static void createDirectory(List<String> parts, List<String>directories, String actualPath, String actualParent,
            int freeMemory, int counter, String noValidCommand) throws FileNotFoundException, IOException{
        if(!noValidCommand.isEmpty()){
            DirectoryNameIsOk(parts, actualParent, noValidCommand);
            return;
        }
        if(parts.size() > 2){
            Singleton.getInstance().error.printError("space", "" ,0);
            return;
        }
        if(DirectoryNameIsValid(parts, actualParent, noValidCommand)){
            List<String> folders = new LinkedList<>(Arrays.asList(parts.get(1).split(" ")));
            //si ya hay directorios guardados
            if(directories.size()>0){
               if(Singleton.getInstance().helper.directoryCount(actualPath) <= 7){
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
    private static boolean DirectoryNameIsValid(List<String> parts, String actualParent, String noValidCommand){
        if(!DirectoryNameIsOk(parts, actualParent, noValidCommand)){
            return false;
        }
        return DirectoryLengthIsOk(parts);
    }
    /*
    *Revisa el nombre del directorio que se desea crear
    */
    private static boolean DirectoryNameIsOk(List<String> parts, String actualParent, String noValidCommand){
        if(!noValidCommand.isEmpty()){
            if(noValidCommand.matches("[a-zA-Z][a-zA-Z0-9_]*") != true){
                Singleton.getInstance().error.printError("errorName", noValidCommand ,0);
                return false;
            }
        }
        String[] folders = null;
        if(parts.size() != 2){
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
    private static void saveFolderAndRemoveMemory(List<String> folders, String toDelete, 
            int freeMemory, List<String> directories, String actualPath){
        if(Singleton.getInstance().helper.removeMemory(freeMemory, toDelete)){
            directories.add("<DIR><NAME>"+folders.get(0)+"</NAME><PATH>"+ actualPath + "\\" +"</PATH></DIR>");
            }
            else{
                Singleton.getInstance().error.printError("noMemory", "" ,0);
            }
    }
    
   
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void createDirectory2(List<String> parts, 
            Carpeta carpetaActual, String noValidCommand) {
        if(!noValidCommand.isEmpty()){
            DirectoryNameIsOk2(noValidCommand);
            return;
        }
        if(parts.size() > 2){
            Singleton.getInstance().error.printError("space", "" ,0);
            return;
        }
        if(!DirectoryNameIsValid2(parts.get(1))){
            return;
        }
        if(carpetaActual == null){
            return;
        }
        if(carpetaActual.getCantidadCarpetas()>=8){
            Singleton.getInstance().error.printError("capacity", "", 0);
            return;
        }
        if(parts.get(1).equalsIgnoreCase(carpetaActual.getNombre())){
            Singleton.getInstance().error.printError("sameNameAsParent", "", 0);
            return;
        }
        for(int i = 0; i < carpetaActual.getCantidadCarpetas(); i++){
            if(parts.get(1).equalsIgnoreCase(carpetaActual.getHijoInterno(i).toString())){
                Singleton.getInstance().error.printError("directoryExists", "", 0);
                return;
            }
        }
        Carpeta carpeta = new Carpeta(Arbol.getRutaActual() + "\\", parts.get(1),carpetaActual);
            int contador = carpetaActual.getCantidadCarpetas();
            carpetaActual.setHijoInterno(carpeta);
            carpetaActual.setCantidadCarpetas(contador + 1);
            Singleton.getInstance().helper.SetCarpetaActual(carpetaActual);
            Singleton.getInstance().helper.SetFreeMemory(8, "remove");
            
    }
    
    private static boolean DirectoryNameIsValid2(String name){
        if(!DirectoryNameIsOk2(name)){
            return false;
        }
        return DirectoryLengthIsOk2(name);
    }
    
    private static boolean DirectoryNameIsOk2(String name){
        if(name.matches("[a-zA-Z][a-zA-Z0-9_]*") != true){
                Singleton.getInstance().error.printError("errorName", name ,0);
                return false;
            }
        return true;
    }
    
    private static boolean DirectoryLengthIsOk2(String name){
        if(name.length() <= 8){   
            return true;
        }
        Singleton.getInstance().error.printError("length", "" ,0);
        return false;
    }
}
