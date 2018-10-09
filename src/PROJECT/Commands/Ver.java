/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Singleton;
import java.util.List;

/**
 *
 * @author alast
 */
public final class Ver {
    private Ver(){
        
    }
    /*
    *Mustra la versión del porgrama MIDOS y la cantidad de memoria libre
    */
    public static void showVersion(List<String> parts, int freeMemory){
        if(parts.size() == 1){
        System.out.println("Versión 1.0 Memoria libre: "+freeMemory+" K");
        }else{
            Singleton.getInstance().error.printError("singleCommand","",0);
        }
    }
}
