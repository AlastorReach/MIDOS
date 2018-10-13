/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Carpeta;
import PROJECT.Singleton;

/**
 *
 * @author alast
 */
public final class Tree {
    private Tree(){
        
    }
    public static void Tree(Carpeta carpetaActual, String tab) {
        
        System.out.println("La carpeta actual es " + carpetaActual);
        imprimirRecursion(carpetaActual, tab);

        
    }
    public static void imprimirRecursion(Carpeta c, String tab){
        try{
            boolean isRoot = true;
            if(!c.getNombre().equalsIgnoreCase("MIDOS")){
                System.out.println(tab + "├───" + c.getNombre());
                isRoot = false;
            }
            
            for(int i = 0; i < c.getCantidadCarpetas(); i++){
                if(c.getCantidadCarpetas() != 0){
                    if(!isRoot){
                    tab +="\t";
                    }
                    imprimirRecursion((Carpeta)c.getHijoInterno(i), tab);
                }
            }
            tab = tab.replace("\t", "");
            Singleton.getInstance().helper.SetTab(tab);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
