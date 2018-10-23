/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Singleton;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Josué Mora González
 */
public final class Exit {
    private Exit(){
        
    }
    /*
    *Termina la ejecución del programa MIDOS
    Luego va y guarda en los archivos de texto, la información correspondiente a memoria y directorios
    */
     public static boolean exit(BufferedReader br, String input) throws IOException{
        System.out.println("¿Está seguro de salir de MIDOS?");
        input = br.readLine();
        switch(input.toLowerCase()){
            case "s":
            case "si": Singleton.getInstance().helper.SaveAndExit(); return false;
            default: return true;
        }
    }
     
}
