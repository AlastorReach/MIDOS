/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT;

import java.io.IOException;

/**
 *
 * @author Josué Mora González
 * @version 1.0
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
       try{
        Singleton.getInstance().helper.init();  
       }
       catch(Exception e){
           System.out.println("Error de ejecución: " + e.getMessage());
           System.in.read();
       }
    }
    
}

