package PROJECT.Commands;

import PROJECT.Arbol;
import PROJECT.Archivo;
import PROJECT.Carpeta;
import PROJECT.Singleton;
import java.util.List;

/**
 *
 * @author JosuéMora González
 */
public final class RD {

    private RD() {

    }

    //elimina un directorio
    public static void RD2(List<String> parts, boolean isValidParam, String noValidCommand) {

        //si el nomnbre del directorio no es válido
        if (parts.size() == 1 && !isValidParam) {
            Singleton.getInstance().error.printError("errorNameDir", noValidCommand, 0);
            return;
        }
        //si no viene el nombre del directorio a eliminar
        if (parts.size() == 1) {
            Singleton.getInstance().error.printError("sintaxis", "", 0);
            return;
        }
        //si vienen espacios entre el nombre del directorio
        if (parts.size() > 2) {
            Singleton.getInstance().error.printError("space", "", 0);
            return;
        }
        //si viene algún nombre que no se permite
        if (parts.size() == 2 && !isValidParam && !noValidCommand.isEmpty()) {
            Singleton.getInstance().error.printError("errorNameDir", noValidCommand, 0);
            return;
        }
        RemoveDirectoryOrFile2(parts);

    }

    //remueve el directorio si los comandos son validos
    private static void RemoveDirectoryOrFile2(List<String> parts) {
        //obtiene el directorio o archivo que se quiere eliminar
        Object c = Arbol.getCarpetaActual(Singleton.getInstance().helper.getCarpetaActual().getNombre(), parts.get(1));
        
        //si el directorio que se quiere eliminar no existe
        if (c == null) {
            Singleton.getInstance().error.printError("noRouteFound", Arbol.getRutaActual() + "\\" + parts.get(1), 0);
            return;
        }
        //si el directorio que se quiere eliminar es un archivo
        if (c instanceof Archivo) {
            Singleton.getInstance().error.printError("notForFiles", ((Archivo) c).getNombre(), 1);
            return;
        }
        //Si el directorio que se quiere eliminar tiene hijos
        if (((Carpeta) c).getCantidadCarpetas() > 0) {
            Singleton.getInstance().error.printError("noEmpty", "", 0);
            return;
        }
        //si todo está bien elimina el directorio
        Carpeta carpetaActual = Singleton.getInstance().helper.getCarpetaActual();
        carpetaActual.removeOrChangeNameChild(parts.get(1), "REMOVE", "");
        Singleton.getInstance().helper.SetCarpetaActual(carpetaActual);
        //libera 8 KB de memoria disponible
        Singleton.getInstance().helper.SetFreeMemory(8, "add");
    }
}
