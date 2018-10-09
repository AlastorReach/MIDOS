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
        errorList.put("errorName","001 El nombre del directorio no es válido: ");
        errorList.put("length","002 El nombre del directorio permite como máximo un largo de 8 caracteres.");
        errorList.put("noCommand","003 no se reconoce como un comando interno o externo.");
        errorList.put("singleCommand","004 El comando no utiliza parámetros.");
        errorList.put("directoryExists","005 Ya existe el subdirectorio o el archivo "); 
        errorList.put("noMemory","006 Memoria insuficiente, disponible: ");
        errorList.put("sintaxis","007 La sintaxis del comando no es correcta.");
        errorList.put("capacity","008 Capacidad de creación de directorios al máximo.");
        errorList.put("sameNameAsParent","009 El nombre del directorio coincide con el directorio que lo contiene.");
        errorList.put("noRouteFound","010 El sistema no puede encontrar la ruta especificada: ");
        errorList.put("isFile","011 No se puede accesar archivos, sólo directorios.");
        errorList.put("noEmpty","012 El directorio no está vacío.");
        errorList.put("noValid","013 Identificador no válido.");
        errorList.put("space","014 Nombre de carpeta no puede tener espacios en blanco.");
        
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
