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
 * @author alast
 */
public final class Exit {
    private Exit(){
        
    }
    /*
    *Termina la ejecución del programa MIDOS
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
