/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Singleton;
import java.text.SimpleDateFormat;

/**
 *
 * @author alast
 */
public final class Date {
    private Date(){
        
    }
    /*
    *Muestra la fecha actual al usuario
    */
    public static void showDate(){
        SimpleDateFormat format = Singleton.getInstance().helper.createDateFormat("dd/MM/yyyy");
        String date = format.format(new java.util.Date());
        System.out.println("La fecha actual es: " + date);
    }
}
