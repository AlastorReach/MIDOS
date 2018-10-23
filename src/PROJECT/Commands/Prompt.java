/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Arbol;
import PROJECT.Singleton;
import java.util.List;

/**
 *
 * @author Josué Mora González
 */
public final class Prompt {
    private Prompt(){
        
    }
    public static void Prompt(List<String> parts, boolean isValidParam, String noValidCommand,
            String prompt, String actualPath, String actualPrompt, String SP, String GP){
        //si el comando no es valido
        if(!isValidParam){
            Singleton.getInstance().error.printError("noCommand", noValidCommand ,1);
            return;
        }
        //une todos los parámetros y los deja sin espacios
        //ejemplo $p $g queda como $p$g
        String parameters = "";
        for(int i = 0; i < parts.size(); i++){
            if(i > 0){
                parameters+= parts.get(i);
            }
        }
        //dependiendo de los parámetros cambia el prompt
        //si el parametro viene vacio es decir solo prompt establece el de por defecto : case ""
        switch(parameters.toUpperCase()){
            case "$P": PromptP(prompt, Arbol.getRutaActual(),SP, actualPrompt);break;
            case "$G": PromptG(prompt,GP, actualPrompt);break;
            case "$P$G": PromptPG(prompt, Arbol.getRutaActual(),GP,SP, actualPrompt);break;
            case "$G$P": PromptGP(prompt, Arbol.getRutaActual(),GP,SP, actualPrompt);break;
            case "": PromptPG(prompt, Arbol.getRutaActual(),GP,SP, actualPrompt);break;
            default: Singleton.getInstance().error.printError("noCommand", parameters ,1);;break;
        }
    }
    //establece el prompt a esta forma: M:\nombreCarpeta
    private static void PromptP(String prompt, String actualPath, String SP, String actualPrompt){
        prompt = actualPath + SP;
        actualPrompt = "$p";
        Singleton.getInstance().helper.SetActualPrompt(actualPrompt);
        Singleton.getInstance().helper.SetPrompt(prompt);
    }
    //establece el prompt a esta forma: >
    private static void PromptG(String prompt, String GP, String actualPrompt){
        prompt = GP;
        actualPrompt = "$g";
        Singleton.getInstance().helper.SetActualPrompt(actualPrompt);
        Singleton.getInstance().helper.SetPrompt(prompt);
    }
    //establece el prompt a esta forma: M:\>nombreCarpeta
    private static void PromptPG(String prompt, String actualPath, String GP,String SP, String actualPrompt){
        prompt = actualPath + SP + GP;
        actualPrompt = "$p$g";
        Singleton.getInstance().helper.SetActualPrompt(actualPrompt);
        Singleton.getInstance().helper.SetPrompt(prompt);
    }
    //establece el prompt a esta forma: <M:\nombreCarpeta
    private static void PromptGP(String prompt, String actualPath, String GP,String SP, String actualPrompt){
        prompt = GP + actualPath + SP ;
        actualPrompt = "$g$p";
        Singleton.getInstance().helper.SetActualPrompt(actualPrompt);
        Singleton.getInstance().helper.SetPrompt(prompt);
    }
}
