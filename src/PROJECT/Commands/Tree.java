package PROJECT.Commands;

import PROJECT.Arbol;
import PROJECT.Carpeta;
import PROJECT.Singleton;
import java.util.List;

/**
 *
 * @author Josué Mora González
 */
public final class Tree {

    private Tree() {

    }

    //muestra el árbol de carpetas
    public static void Tree(Carpeta carpetaActual, String tab, List<String> parts, String noValidCommand) {

        if (parts.size() > 1 || !noValidCommand.isEmpty()) {
            Singleton.getInstance().error.printError("singleCommand", "", 0);
            return;
        }
        //obtiene la carpeta raíz
        Carpeta carpetaRaiz = Arbol.GetFirstLevel();

        System.out.println("Listado de rutas de carpetas para el volumen " + carpetaRaiz.getNombre());
        System.out.println("El número de serie del volumen es : JosueMoraGonzalez-113260029");
        //si la carpeta raiz no tiene subdirectorios
        if (carpetaRaiz.getCantidadCarpetas2() == 0) {
            Singleton.getInstance().error.printError("noDirectories", "", 0);
            return;
        }
        System.out.println(carpetaRaiz.getNombre() + ".");
        imprimirRecursion(carpetaRaiz, tab);

    }

    //función recursiva que imprime las carpetas en forma de árbol
    public static void imprimirRecursion(Carpeta c, String tab) {
        try {
            String vertical = "";
            System.out.println(tab
                    + Singleton.getInstance().helper.getAscii() + "───────" + c);

            //si tiene hijos se agrega un nuevo tab
            if (c.getCantidadCarpetas2() > 0) {
                //si no es la carpeta raiz
                if (c.getPadre() != null) {
                    //si la carpeta padre tiene hijos y el hijo que se está recorriendo no es el último
                    //se agrega vertical
                    //en otras palabras, si la carpeta actual tiene hermanos
                    if (c.getPadre().getCantidadCarpetas2() > 0 && c
                            != c.getPadre().getCarpetaInterna(c.getPadre().getCantidadCarpetas2() - 1)) {
                        vertical = "│";//si no es el ultimo hermano se agrega │
                        Singleton.getInstance().helper.setVertical(vertical);
                    } //si no vertical es vacío
                    else {
                        vertical = "";
                        Singleton.getInstance().helper.setVertical(vertical);
                    }
                }
                tab += vertical + "\t";
                //recorre las carpetas hijas de la carpeta actual
                for (int i = 0; i < c.getCantidadCarpetas2(); i++) {
                    //si es la ultima carpeta hija pone la curva a la linea
                    if (i == c.getCantidadCarpetas2() - 1) {
                        Singleton.getInstance().helper.setAscii("└");
                    } else {//de lo contrario sigue hacia abajo porque hay más hijos
                        Singleton.getInstance().helper.setAscii("├");
                    }
                    imprimirRecursion((Carpeta) c.getCarpetaInterna(i), tab);
                }
            }
            //si el hijo de la carpeta actual no tiene hijos se recorre el siguiente hermano.
            vertical = "";
            Singleton.getInstance().helper.setVertical(vertical);
            // al salir de los hijos y entrar a un hermano se elimina un tab
            tab = tab.replace(Singleton.getInstance().helper.getVertical() + "\t", "");
            Singleton.getInstance().helper.SetTab(tab);
            //aqui avanza al siguiente indice el ciclo for
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
