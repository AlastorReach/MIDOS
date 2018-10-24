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
            String vertical = "";
            boolean isRoot = true;
            int contador = 1;
            if(!c.getNombre().equalsIgnoreCase("MIDOS")){
                if(c instanceof Carpeta){
                    System.out.println(tab + 
                            Singleton.getInstance().helper.getAscii() + "───────" + c.getNombre());
                    isRoot = false;
                }
            }
            //si tiene hijos se agrega un nuevo tab
            if(c.getCantidadCarpetas2() > 0){
                if(!isRoot){
                    if(Singleton.getInstance().helper.getContador() > 1){
                        vertical = "│";
                        Singleton.getInstance().helper.setVertical(vertical);
                    }
                    tab += vertical + "\t";
                }
                for(int i = 0; i < c.getCantidadCarpetas2(); i++){
                        contador ++;
                        Singleton.getInstance().helper.setContador(contador);
                        if(i == c.getCantidadCarpetas2() - 1){
                            Singleton.getInstance().helper.setAscii("└");
                        }else {
                            Singleton.getInstance().helper.setAscii("├");
                        }
                        if(c instanceof Carpeta){
                            imprimirRecursion((Carpeta)c.getCarpetaInterna(i), tab);
                        }
                }
            }
                vertical = "";
                Singleton.getInstance().helper.setVertical(vertical);
            // al salir de los hijos y entrar a un hermano se elimina un tab
            tab = tab.replace(vertical + "\t", "");
            Singleton.getInstance().helper.SetTab(tab);
            Singleton.getInstance().helper.setContador(0);
            
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
