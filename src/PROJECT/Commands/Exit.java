package PROJECT.Commands;

import PROJECT.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Josué Mora González
 */
public final class Exit {

    private Exit() {

    }

    /*
    *Termina la ejecución del programa MIDOS
    Luego va y guarda en los archivos de texto, la información correspondiente a memoria y directorios
     */
    public static boolean exit(BufferedReader br, String input, List<String> parts, String noValidCommand) throws IOException {
        //el comando exit es simple, no lleva parámetros
        if (parts.size() > 1 || !noValidCommand.isEmpty()) {
            Singleton.getInstance().error.printError("singleCommand", "", 0);
            return false;
        }
        System.out.println("¿Está seguro de salir de MIDOS? (Si, si, S, s) para salir:");
        input = br.readLine();
        if (input == null) {
            input = "default";
        }
        switch (input.toLowerCase()) {
            case "s":
            case "si":
                Singleton.getInstance().helper.SaveAndExit();
                return false;
            default:
                return true;
        }
    }

}
