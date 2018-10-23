/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Arbol;
import PROJECT.Archivo;
import PROJECT.Carpeta;
import PROJECT.Singleton;
import java.util.List;

/**
 *
 * @author Josué Mora González
 */
public final class CD {
    private CD(){
        
    }
   
    public static void CD2(List<String> parts, Carpeta carpetaActual, String actualPrompt,
            String prompt,String SP, String GP, boolean isValidParam, String noValidCommand ) {
       /********************************ERRORES**************************************************/
        //si viene vacio el nombre de la carpeta
        if(parts.size() == 1 && !isValidParam){
            Singleton.getInstance().error.printError("sintaxis", "" ,0);
            return;
        }
    
        //si vienen espacios entre el nombre del directorio
        if(parts.size() > 2){
            Singleton.getInstance().error.printError("space", "" ,0);
            return;
        }
        /********************************FIN ERRORES**************************************************/
       
        //si el comando es CD .. o CD /  o CD \ o CD NOMBRECARPETA
        if(parts.size() == 2){
            if(parts.get(1).equals("..")){
                goBackOneLevel(actualPrompt, prompt, SP, GP);
                return;
            }
            
            if(parts.get(1).equals("/") || parts.get(1).equals("\\") ){
               returnToRootLevel(actualPrompt, prompt, SP, GP);
               return;
            }
            goForwardOneLevel(parts,actualPrompt, prompt, SP, GP);
            return;
        }
        
        //Si el comando es CD/ o CD\ o CD..
        if(parts.size() == 1){
            if(parts.get(0).equalsIgnoreCase("cd/") || parts.get(0).equalsIgnoreCase("cd\\") ){
               returnToRootLevel(actualPrompt, prompt, SP, GP);
               return;
            }
            
            if(parts.get(0).equalsIgnoreCase("cd..")){
                goBackOneLevel(actualPrompt, prompt, SP, GP);
            }
        }

        
    }
    
    //función que actualiza el prompt del helper (por referencia) mediante set
    private static void SetPrompt(String actualPrompt, String prompt, String SP, String GP){
        switch(actualPrompt){
                        case"$p": prompt = Arbol.getRutaActual() + SP;Singleton.getInstance().helper.SetPrompt(prompt); break;
                        case"$g$p": prompt = GP + Arbol.getRutaActual();Singleton.getInstance().helper.SetPrompt(prompt); break;
                        case"$p$g": prompt = Arbol.getRutaActual() + SP + GP;Singleton.getInstance().helper.SetPrompt(prompt); break;
                        default: break;
                    }
    }
    
    private static void goBackOneLevel(String actualPrompt, String prompt, String SP, String GP){
        //si la carpeta actual es M, simplemente sale y no hace nada
        if(Singleton.getInstance().helper.getCarpetaActual().getNombre().equalsIgnoreCase("M:")){
                    return;
        }
        //establece la carpeta padre de la carpeta actual como la carpeta actual
        //ej. si la ruta actual es M:\a\b\c
        //la carpeta actual sería b (M:\a\b)
        Singleton.getInstance().helper.SetCarpetaActual(Singleton.getInstance().helper.getCarpetaActual().getPadre());
        Arbol.SetCarpetaActual(Singleton.getInstance().helper.getCarpetaActual());
        if(Singleton.getInstance().helper.getCarpetaActual().getNombre().equalsIgnoreCase("M:")){
            Arbol.setRutaActual("M:");
        }
        else{
            //ruta actual es igual la ruta del padre mas el nombre de la carpeta padre
            //ej. M:\a\b\c
            //cd .. (la ruta de la carpeta padre, es decir b, es M:\a, porque b está en M:\a
            //entonces la ruta al hacer cd.. sería M:\a + nombre de la carpeta actual (que sería b)
            //por lo tanto la ruta queda como M:\a\b
            Arbol.setRutaActual(Singleton.getInstance().helper.getCarpetaActual().getRuta()
                   +"\\" + Singleton.getInstance().helper.getCarpetaActual().getNombre());
        }
        try{
        //System.err.println("Carpeta padre es: " +carpetaActual.getPadre().getNombre());
        SetPrompt(actualPrompt,prompt, SP, GP);
        }
        catch(NullPointerException e){
             Arbol.setRutaActual("M:\\");  
        }
    }
    
    private static void returnToRootLevel(String actualPrompt, String prompt, String SP, String GP){
                //si es la carpeta raiz(midos) no hace nada
                if(Singleton.getInstance().helper.getCarpetaActual().getNombre().equalsIgnoreCase("MIDOS")){
                    return;
                }
                //establece la carpeta raiz (MIDOS) como la carpeta actual
               Singleton.getInstance().helper.SetCarpetaActual(Arbol.GetFirstLevel());
               Arbol.SetCarpetaActual(Singleton.getInstance().helper.getCarpetaActual());
               Arbol.setRutaActual("M:");
               SetPrompt(actualPrompt,prompt, SP, GP);
    }
    
    private static void goForwardOneLevel(List<String> parts, String actualPrompt, String prompt, String SP, String GP){
        //si el comando es MD + nombreCarpeta
        //busca la carpeta que se esta pidiendo en CD
        Object c = Arbol.getCarpetaActual(Singleton.getInstance().helper.getCarpetaActual().getNombre(), parts.get(1));
        //si no se encontró la carpeta
        if(c == null){
            Singleton.getInstance().error.printError("noRouteFound",Arbol.getRutaActual() + "\\" + parts.get(1) ,0);
            return;
        }
        // si lo que está buscando es un archivo
        if(c instanceof Archivo){
            Singleton.getInstance().error.printError("isFile", parts.get(1) ,1);
            return;
        }
        //si todo está bien
        //establece la ruta de carpeta a la que se acaba de ingresar como la ruta actual 
        Arbol.setRutaActual(Arbol.getRutaActual()+ "\\" + parts.get(1));
        //establece la carpeta a la que se acaba de ingresar como la carpeta actual 
        Singleton.getInstance().helper.SetCarpetaActual((Carpeta)c);
        Arbol.SetCarpetaActual(Singleton.getInstance().helper.getCarpetaActual());
        //actualiza la ruta del prompt
         SetPrompt(actualPrompt,prompt, SP, GP);
    }
}
