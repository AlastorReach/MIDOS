/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Josué Mora González - 113260029
 */
public class ErrorList{
    // Diccionario que guarda la lista de errores posibles
    public Map<String, String> errorList = new HashMap<>();
    public ErrorList(){
       fillErrorList();
    }
    /*
    *Llena el diccionario con una lista de errores y sus respectivas llaves
    */
    private void fillErrorList(){
        errorList.put("errorNameDir","001 El nombre del directorio no es válido: ");
        errorList.put("errorNameFile","002 El nombre del archivo no es válido: ");
        errorList.put("lengthDir","003 El nombre del directorio permite como máximo un largo de 8 caracteres.");
        errorList.put("lengthFile","004 El nombre del archivo permite como máximo un largo de 8 caracteres.");
        errorList.put("noCommand","005 no se reconoce como un comando interno o externo.");
        errorList.put("singleCommand","006 El comando no utiliza parámetros.");
        errorList.put("directoryExists","007 Ya existe el subdirectorio o el archivo "); 
        errorList.put("noMemory","008 Memoria insuficiente, disponible: ");
        errorList.put("sintaxis","009 La sintaxis del comando no es correcta.");
        errorList.put("capacity","010 Capacidad de creación de subdirectorios al máximo.");
        errorList.put("sameNameAsParent","011 El nombre del directorio o archivo coincide con el directorio que lo contiene.");
        errorList.put("noRouteFound","012 El sistema no puede encontrar la ruta especificada: ");
        errorList.put("isFile","013 es un archivo, no se puede accesar mediante el comando CD.");
        errorList.put("noEmpty","014 El directorio no está vacío.");
        errorList.put("noValid","015 Identificador no válido.");
        errorList.put("space","016 Nombre de carpeta no puede tener espacios en blanco.");
        errorList.put("notForFiles","017 es un archivo, no se puede eliminar mediante el comando RD.");
        errorList.put("notForDir","018 es un directorio, no se puede eliminar mediante el comando DEL.");
        errorList.put("isDir","019 es un directorio, no se puede accesar mediante el comando TYPE.");
        errorList.put("spaceFile","020 Nombre del archivo no puede tener espacios en blanco.");
        errorList.put("childNoExists","021 No existe el subdirectorio o el archivo ");
        errorList.put("sameNameAsChild","021 El nuevo nombre a utilizar entra en conflicto con el nombre de un hijo");
        errorList.put("noDirectories","022 No existe ninguna subcarpeta");
        
    }
    
    /*Imprime un error en la lista
    *Dos formatos
    *1- Parametro + error
    *2- Error mas parametro
    */
    public void printError(String error, String data, int before){
        if(data.isEmpty()){
        System.err.println("ERROR: " + printErrorNumber(error) + printErrorBody(error) );
        }
        else{
            if(error.equalsIgnoreCase("noMemory")){
                data = data + " K";
            }
            switch(before){
                case 0: System.err.println("ERROR: " + printErrorNumber(error) + printErrorBody(error) + data);break;
                default: System.err.println("ERROR: " + printErrorNumber(error) + " " + "\""+ data +"\"" + printErrorBody(error));
            }
        }
    }
    
    /*
    *Aplica un substring para imprimir el número del error
    */
    private String printErrorNumber(String error){
        return errorList.get(error).substring(0,3);
    }
    /*
    *Aplica un substring para imprimir el cuerpo del error
    */
    private String printErrorBody(String error){
        return errorList.get(error).substring(3);
    }

       
}
