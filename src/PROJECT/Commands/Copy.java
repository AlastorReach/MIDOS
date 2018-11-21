package PROJECT.Commands;

import PROJECT.Arbol;
import PROJECT.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josué Mora González
 */
public final class Copy {

    private Copy() {

    }

    public static void Copy(List<String> parts, boolean isValid, String NoValidCommand) throws IOException {
        //si viene con el nombre del directorio malo
        if (!NoValidCommand.isEmpty()) {
            MD.DirectoryNameIsValid(NoValidCommand, "FIL");
            return;
        }
        //si vienen espacios entre el nombre del archivo
        if (parts.size() > 3) {
            Singleton.getInstance().error.printError("spaceFile", "", 0);
            return;
        }
        //si la sintaxis es incorrecta
        if (parts.size() != 3) {
            Singleton.getInstance().error.printError("sintaxis", "", 0);
            return;
        }
        if (parts.size() == 3) {
            if (parts.get(1).toUpperCase().equals("CON")) {
                if (MD.DirectoryNameIsValid(parts.get(2), "FIL")) {
                    //si ya hay 8 hijos del directorio
                    if (Singleton.getInstance().helper.getCarpetaActual().getCantidadCarpetas() >= 8) {
                        Singleton.getInstance().error.printError("capacity", "", 0);
                        return;
                    }
                    /*//si el archivo tiene el mismo nombre de la carpeta padre
                if(Singleton.getInstance().helper.ChildHasSameNameAsParent(parts, 2)){
                    return;
                }*/
                    //si ya existe la carpeta en el mismo nivel del árbol
                    if (Singleton.getInstance().helper.siblingExists(parts, 2)) {
                        Singleton.getInstance().error.printError("directoryExists", Arbol.getRutaActual()
                                + "\\" + parts.get(2), 0);
                        return;
                    }
                    //si no hay suficiente memoria disponible
                    if (!Singleton.getInstance().helper.removeMemory(Singleton.getInstance().helper.getFreeMemory(), "FILE")) {
                        Singleton.getInstance().error.printError("noMemory", Integer.toString(Singleton.getInstance().helper.
                                getFreeMemory()), 0);
                        return;
                    }

                    //si la sintaxis es correcta
                    //si hay memoria disponible
                    //si no existe el archivo en el mismo nivel
                    //si el nombre del archivo es válido
                    //si el segundo parametro es @CON
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    List<String> lines = new ArrayList<>();
                    String input = "";
                    String eof = "^Z";
                    String lastRowWithowEOF = "";
                    try {
                        //lee los datos del archivo mientras el último caracter no sea Ctrl Z o ^Z
                        do {
                            input = br.readLine();
                            lines.add(input);

                        } while (!input.endsWith(String.valueOf((char) 0x1a)) && !input.toUpperCase().endsWith(eof));
                    } catch (NullPointerException e) {
                        //si el usuario no digitó nada elimina el ultimo indice de la lista, ya que viene null
                        lines.remove(lines.size() - 1);
                    }
                    if (!lines.isEmpty()) {
                        //si la ultima linea termina en Z
                        if (lines.get(lines.size() - 1).substring(lines.get(lines.size() - 1).length() - 1)
                                .equalsIgnoreCase("Z")) {
                            //se eliminan los ultimos dos caracteres (^Z)
                            lastRowWithowEOF = lines.get(lines.size() - 1).substring(0, lines.get(lines.size() - 1).length() - 2);
                        } else {
                            //String tt = lines.get(lines.size() -1).replace(String.valueOf((char)0x1a),"");
                            lastRowWithowEOF = lines.get(lines.size() - 1).substring(0, lines.get(lines.size() - 1).length() - 1);
                        }
                        //establece el untimo valor de la lista sin el EOF
                        lines.set(lines.size() - 1, lastRowWithowEOF);
                    }
                    Singleton.getInstance().helper.createNewFile(parts, 2, lines);
                }
            } //si el segundo parametro no es "CON"
            else {
                Singleton.getInstance().error.printError("sintaxis", "", 0);
            }
        }
    }
}
