package PROJECT.Commands;

import PROJECT.Archivo;
import PROJECT.Carpeta;
import PROJECT.Singleton;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Josué Mora González
 */
public final class Dir {

    private Dir() {

    }

    //imprime el directorio actual
    public static void DIR2(List<String> parts, String noValidCommand) {
        
        //el comando es simple, no permite DIR parametro
        if (parts.size() > 1 || !noValidCommand.isEmpty()) {
            Singleton.getInstance().error.printError("singleCommand", "", 0);
            return;
        }
        //imprime el nombbre de la carpera actual donde está el usuario
        System.out.println("Directorio de " + Singleton.getInstance().helper.getCarpetaActual());
        System.out.println();
        
        int fileCount = 0;
        int folderCount = 0;
        //se crea una copia de los hijos de la carpeta actual
        List<Object> lista = new LinkedList(Singleton.getInstance().helper.getCarpetaActual().getHijos());
        //se ordena los hijos de la lista de forma alfabetica
        ordenamientoInsercion(lista);
        //por cada hijo de la carpeta actual, imprime su nombre
        for (int i = 0; i < Singleton.getInstance().helper.getCarpetaActual().getCantidadCarpetas(); i++) {
            if (lista.get(i) instanceof Carpeta) { //si el hijo es una carpeta imprime su nombre
                System.out.println("<DIR> " + lista.get(i));
                folderCount++;
            } //por cada hijo de la carpeta actual, si el hijo es un archivo imprime su nombre
            else if (lista.get(i) instanceof Archivo) {
                System.out.println("<FILE> " + lista.get(i));
                fileCount++;
            }
        }
        //muestra el conteo de los archivos y carpetas totales y luego el total de memoria libre
        System.out.println(fileCount + " archivos " + fileCount * 4 + " bytes.");
        System.out.println(folderCount + " directorios " + folderCount * 8 + " bytes.");
        System.out.println(Singleton.getInstance().helper.getFreeMemory() + " bytes libres.");
    }

    public static void ordenamientoInsercion(List<Object> lista) {
        int in;

        for (int i = 1; i < lista.size(); i++) {
            Object aux = lista.get(i);
            in = i;    //inicia el desplazamiento en i
            //mientras lista[n-1] > alfebaticamente que lista[n]
            //lista[n-1] = lista[n]
            while (in > 0 && lista.get(in - 1).toString().toUpperCase().compareTo(aux.toString().toUpperCase()) > 0) {
                lista.set(in, lista.get(in - 1));
                //desplaza el elemento hacia la derecha
                --in;
            }
            lista.set(in, aux);
        }
    }
}
