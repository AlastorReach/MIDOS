/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Singleton;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Josué Mora González
 */
public final class Date {
    private Date(){
        
    }
    /*
    *Muestra la fecha actual al usuario
    */
    public static void showDate(List<String> parts, String noValidCommand){
        if(parts.size() > 1 || !noValidCommand.isEmpty()){
            Singleton.getInstance().error.printError("singleCommand", "", 0);
            return;
        }
        SimpleDateFormat format = Singleton.getInstance().helper.createDateFormat("dd/MM/yyyy");
        String date = format.format(new java.util.Date());
        System.out.println("La fecha actual es: " + date);
    }
}
