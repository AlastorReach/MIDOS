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
        errorList.put("character","001 El nombre del directorio debe empezar con una letra");
        errorList.put("especial","002 El nombre del directorio no permite caracteres especiales");
        errorList.put("errorName","003 El nombre del directorio no es válido: ");
        errorList.put("length","004 El nombre del directorio permite como máximo un largo de 8 caracteres");
        errorList.put("noCommand","005 no se reconoce como un comando interno o externo");
        errorList.put("singleCommand","006 Comando simple");
        errorList.put("directoryExists","007 Ya existe el subdirectorio o el archivo "); 
        errorList.put("noMemory","008 Memoria insuficiente, disponible: ");
        errorList.put("sintaxis","009 La sintaxis del comando no es correcta.");
        errorList.put("capacity","010 Capacidad de creación de directorios al máximo");
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
    public String printErrorNumber(String error){
        return errorList.get(error).substring(0,3);
    }
    /*
    *Aplica un substring para imprimir el cuerpo del error
    */
    public String printErrorBody(String error){
        return errorList.get(error).substring(3);
    }

       
}
