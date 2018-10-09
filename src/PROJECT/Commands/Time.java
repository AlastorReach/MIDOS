/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import static PROJECT.Helpers.createDateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author alast
 */
public final class Time {
    private Time(){
        
    }
     /*
    *Muestra la hora actual al usuario
    */
    public static void showTime(){
        SimpleDateFormat format = createDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String time = format.format(cal.getTime());
        System.out.println("La hora actual es: " + time);
    }
}
