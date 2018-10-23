package PROJECT.Commands;

import PROJECT.Singleton;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Josué Mora González
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
        SimpleDateFormat format = Singleton.getInstance().helper.createDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String time = format.format(cal.getTime());
        System.out.println("La hora actual es: " + time);
    }
}
