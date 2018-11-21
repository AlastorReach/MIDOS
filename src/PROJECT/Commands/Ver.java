package PROJECT.Commands;

import PROJECT.Singleton;
import java.util.List;

/**
 *
 * @author Josué Mora González
 */
public final class Ver {
    private Ver(){
        
    }
    /*
    *Mustra la versión del porgrama MIDOS y la cantidad de memoria libre
    */
    public static void showVersion(List<String> parts, int freeMemory, String noValidCommand){
        
        if(parts.size() > 1 || !noValidCommand.isEmpty()){
                Singleton.getInstance().error.printError("singleCommand", "", 0);
                return;
        }
        System.out.println("Versión 1.0 Memoria libre: "+freeMemory+" K");
    }
}
