/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import static PROJECT.Helpers.createDateFormat;
import PROJECT.Singleton;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
    public static void showTime(List<String> parts, String noValidCommand){
        if(parts.size() > 1 || !noValidCommand.isEmpty()){
            Singleton.getInstance().error.printError("singleCommand", "", 0);
            return;
        }
        SimpleDateFormat format = createDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String time = format.format(cal.getTime());
        System.out.println("La hora actual es: " + time);
    }
}
