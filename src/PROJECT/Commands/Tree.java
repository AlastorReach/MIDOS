package PROJECT.Commands;

import PROJECT.Carpeta;
import PROJECT.Singleton;

/**
 *
 * @author Josué Mora González
 */
public final class Tree {
    private Tree(){
        
    }
    //muestra el árbol de carpetas
    public static void Tree(Carpeta carpetaActual, String tab) {
        
        System.out.println("La carpeta actual es " + carpetaActual);
        imprimirRecursion(carpetaActual, tab);

        
    }
    //función recursiva que imprime las carpetas en forma de árbol
    public static void imprimirRecursion(Carpeta c, String tab){
        try{
            boolean isRoot = true;
            if(!c.getNombre().equalsIgnoreCase("MIDOS")){
                System.out.println(tab + Singleton.getInstance().helper.getAscii() + "───────" + c.getNombre());
                isRoot = false;
            }
            //si tiene hijos se agrega un nuevo tab
            if(c.getCantidadCarpetas() != 0){
                if(!isRoot){
                    tab +="│\t";
                }
                
                for(int i = 0; i < c.getCantidadCarpetas(); i++){
                        if(i == c.getCantidadCarpetas() -1){
                            Singleton.getInstance().helper.setAscii("└");
                        }else {
                            Singleton.getInstance().helper.setAscii("├");
                        }
                        imprimirRecursion((Carpeta)c.getHijoInterno(i), tab);
                }
            }
                
            // al salir de los hijos y entrar a un hermano se elimina un tab
            tab = tab.replace("\t", "");
            Singleton.getInstance().helper.SetTab(tab);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
