package PROJECT.Commands;

import PROJECT.Arbol;
import PROJECT.Carpeta;
import PROJECT.Singleton;
import java.util.List;

/**
 *
 * @author Josué Mora González
 */
public final class MD {

    private MD() {

    }

    //crea una nueva carpeta hijo de la carpeta actual
    public static void createDirectory(List<String> parts,
            Carpeta carpetaActual, String noValidCommand) {
        //si viene con el nombre del directorio malo
        if (!noValidCommand.isEmpty()) {
            MD.DirectoryNameIsOk(noValidCommand, "DIR");
            return;
        }
        //si vienen espacios entre el nombre del directorio
        if (parts.size() > 2) {
            Singleton.getInstance().error.printError("space", "", 0);
            return;
        }
        //si viene vacio el nombre de la carpeta
        if (parts.size() == 1) {
            Singleton.getInstance().error.printError("sintaxis", "", 0);
            return;
        }

        //si el nombre no es válido
        if (!DirectoryNameIsValid(parts.get(1), "DIR")) {
            return;
        }
        //si ya hay 8 hijos del directorio
        if (carpetaActual.getCantidadCarpetas() >= 8) {
            Singleton.getInstance().error.printError("capacity", "", 0);
            return;
        }
        /*si la carpeta tiene el mismo nombre de la carpeta padre
        if(Singleton.getInstance().helper.ChildHasSameNameAsParent(parts, 1)){
            return;
        }*/
        //si ya existe la carpeta en el mismo nivel del árbol
        if (Singleton.getInstance().helper.siblingExists(parts, 1)) {
            Singleton.getInstance().error.printError("directoryExists", Arbol.getRutaActual()
                    + "\\" + parts.get(1), 0);
            return;
        }
        //si no hay suficiente memoria disponible
        if (!Singleton.getInstance().helper.removeMemory(Singleton.getInstance().helper.getFreeMemory(), "DIR")) {
            Singleton.getInstance().error.printError("noMemory", Integer.toString(Singleton.getInstance().helper.getFreeMemory()), 0);
            return;
        }
        //si todo esta bien guarda la carpeta
        //crea una nueva carpeta
        //la ruta de la nueva carpeta es la ruta actual más su nombre, establece la carpeta actual como su padre
        Singleton.getInstance().helper.createNewDir(parts, 1);

    }

    public static boolean DirectoryNameIsValid(String name, String type) {
        if (!DirectoryNameIsOk(name, type)) {
            return false;
        }
        return DirectoryLengthIsOk(name, type);
    }

    private static boolean DirectoryNameIsOk(String name, String type) {
        if (name.matches("[a-zA-Z][a-zA-Z0-9_]*") != true) {

            switch (type.toUpperCase()) {
                case "DIR":
                    Singleton.getInstance().error.printError("errorNameDir", name, 0);
                    return false;
                case "FIL":
                    Singleton.getInstance().error.printError("errorNameFile", name, 0);
                    return false;
            }
        }
        return true;
    }

    private static boolean DirectoryLengthIsOk(String name, String type) {
        if (name.length() <= 8) {
            return true;
        }
        switch (type.toUpperCase()) {
            case "DIR":
                Singleton.getInstance().error.printError("lengthDir", "", 0);
                return false;
            case "FIL":
                Singleton.getInstance().error.printError("lengthFile", "", 0);
                return false;
        }
        return false;
    }
}
