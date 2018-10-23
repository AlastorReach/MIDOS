/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import java.io.IOException;

/**
 *
 * @author Josué Mora González
 */
public final class CLS {
    private CLS(){
        
    }
    /*
    *Limpia la consola en cmd llamando al mismo cmd: cls
    */
    public static void clrscr(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } 
        catch (IOException | InterruptedException ex) 
        {
            clear(5);
        }
    }
    static String clear(int num){
        String newline = "\n\n\n\n\n\n\n\n\n\n";
        if(num <= 1){
            return "";
        }
        System.out.println(newline);
        return clear(num -1);
    }
}
