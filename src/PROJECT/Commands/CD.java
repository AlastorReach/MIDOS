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
 * @author alast
 */
public final class CD {
    private CD(){
        
    }
    public static void CD(List<String> parts, String actualPath, String actualPrompt, String prompt, String SP, String GP,
            String actualParent, String noValidCommand){
        //si hay al menos un comando no válido
        if(!noValidCommand.isEmpty()){
            Singleton.getInstance().error.printError("noValid", "", 0);
            return;
        }
        //si el nombre de la carpeta contiene espacios
        if(parts.size() > 2){
            Singleton.getInstance().error.printError("space", "", 0);
            return;
        }
        //si posee un espacio y el nombre de la carpeta como parámetro
        if(parts.size() > 1){
            //si el parametro es un nombre de carpeta
            if(!parts.get(1).equals("..") && !parts.get(1).equals("\\") && !parts.get(1).equals("/") ){
                String pathToBeSaved = actualPath + "\\" + parts.get(1);
                String result = Singleton.getInstance().helper.PathExists(pathToBeSaved, "CD");
                if(result.equals("isOk")){
                    actualParent = actualPath;
                    Singleton.getInstance().helper.SetActualParent(actualParent);
                    actualPath = actualPath + "\\"  + parts.get(1);
                    Singleton.getInstance().helper.SetActualPath(actualPath);
                }
                //si es un comando secundario como parámetro
            }else if(parts.get(1).equals("..")){
                int index = actualPath.lastIndexOf("\\");
                actualPath = actualPath.substring(0,index);
                Singleton.getInstance().helper.SetActualPath(actualPath);
                actualParent = actualPath;
                Singleton.getInstance().helper.SetActualParent(actualParent);
            }
            else if(parts.get(1).equals("\\") || parts.get(1).equals("/")){
                actualPath = "M:";
                actualParent = actualPath;
                Singleton.getInstance().helper.SetActualParent(actualParent);
                Singleton.getInstance().helper.SetActualPath(actualPath);
            }
            //si el comando tiene el parámetro pegado sin espacios
        }else{
            //si el comando es cd..
           if(parts.get(0).equalsIgnoreCase("cd..")){
                int index = actualPath.lastIndexOf("\\");
                actualPath = actualPath.substring(0,index);
                Singleton.getInstance().helper.SetActualPath(actualPath);
                actualParent = actualPath;
                Singleton.getInstance().helper.SetActualParent(actualParent);
            }
           //si el comando es cd\ o cd/
            else if(parts.get(0).equalsIgnoreCase("cd\\") || parts.get(0).equalsIgnoreCase("cd/")){
                actualPath = "M:";
                Singleton.getInstance().helper.SetActualPath(actualPath);
                actualParent = actualPath;
                Singleton.getInstance().helper.SetActualParent(actualParent);
            }
        }
        //actualiza la ruta actual del actual prompt
        switch(actualPrompt){
                        case"$p": prompt = actualPath + SP;Singleton.getInstance().helper.SetPrompt(prompt); break;
                        case"$g$p": prompt = GP + actualPath;Singleton.getInstance().helper.SetPrompt(prompt); break;
                        case"$p$g": prompt = actualPath + SP + GP;Singleton.getInstance().helper.SetPrompt(prompt); break;
                        default: break;
                    }
    }
    
    public static void CD2(List<String> parts, Carpeta carpetaActual, String actualPrompt,
            String prompt,String SP, String GP ) {
        if(parts.get(1).equals("..")){
            if(Singleton.getInstance().helper.getCarpetaActual().getNombre().equalsIgnoreCase("MIDOS")){
                return;
            }
            carpetaActual = carpetaActual.getPadre();
            Arbol.SetCarpetaActual(carpetaActual);
            if(carpetaActual.getNombre().equalsIgnoreCase("MIDOS")){
                Arbol.setRutaActual(carpetaActual.getRuta() + "M:");
            }
            else{
                Arbol.setRutaActual(carpetaActual.getRuta() + carpetaActual.getNombre());
            }
            try{
            //System.err.println("Carpeta padre es: " +carpetaActual.getPadre().getNombre());
            SetPrompt(actualPrompt,prompt, SP, GP);
            }
            catch(NullPointerException e){
                 Arbol.setRutaActual("M:\\");  
            }
            Singleton.getInstance().helper.SetCarpetaActual(carpetaActual);
            return;
        }
        if(parts.get(1).equals("/")){
            if(carpetaActual.getNombre().equalsIgnoreCase("MIDOS")){
                return;
            }
           carpetaActual = Arbol.GetFirstLevel();
           Arbol.SetCarpetaActual(carpetaActual);
           Arbol.setRutaActual("M:\\");
           return;
        }
        Object c = Arbol.getCarpetaActual(Arbol.getRutaActual() + "\\", parts.get(1));
        if(c == null){
            Singleton.getInstance().error.printError("noRouteFound", "" ,0);
            return;
        }
        if(c instanceof Archivo){
            Singleton.getInstance().error.printError("isFile", "" ,0);
            return;
        }
        Arbol.setRutaActual(Arbol.getRutaActual() + "\\" + parts.get(1));
        carpetaActual = (Carpeta)c;
        Arbol.SetCarpetaActual(carpetaActual);
        Singleton.getInstance().helper.SetCarpetaActual(carpetaActual);
        try{
        System.err.println("Carpeta padre es: " +carpetaActual.getPadre().getNombre());
        }
        catch(NullPointerException e){
            System.err.println("Ocurrio la siguiente excepción: " + e.getMessage());
        }
        //Arbol.SetCarpetaActual((Carpeta)c);
         SetPrompt(actualPrompt,prompt, SP, GP);
    }
    
    private static void SetPrompt(String actualPrompt, String prompt, String SP, String GP){
        switch(actualPrompt){
                        case"$p": prompt = Arbol.getRutaActual() + SP;Singleton.getInstance().helper.SetPrompt(prompt); break;
                        case"$g$p": prompt = GP + Arbol.getRutaActual();Singleton.getInstance().helper.SetPrompt(prompt); break;
                        case"$p$g": prompt = Arbol.getRutaActual() + SP + GP;Singleton.getInstance().helper.SetPrompt(prompt); break;
                        default: break;
                    }
    }
}
