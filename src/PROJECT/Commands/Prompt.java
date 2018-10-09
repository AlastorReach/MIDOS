/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Singleton;
import java.util.List;

/**
 *
 * @author alast
 */
public final class Prompt {
    private Prompt(){
        
    }
    public static void Prompt(List<String> parts, boolean isValidParam, String noValidCommand,
            String prompt, String actualPath, String actualPrompt, String SP, String GP){
        if(!isValidParam){
            Singleton.getInstance().error.printError("noCommand", noValidCommand ,1);
            return;
        }
        String parameters = "";
        for(int i = 0; i < parts.size(); i++){
            if(i > 0){
                parameters+= parts.get(i);
            }
        }
        switch(parameters.toUpperCase()){
            case "$P": PromptP(prompt, actualPath,SP, actualPrompt);break;
            case "$G": PromptG(prompt,GP, actualPrompt);break;
            case "$P$G": PromptPG(prompt, actualPath,GP,SP, actualPrompt);break;
            case "$G$P": PromptGP(prompt, actualPath,GP,SP, actualPrompt);break;
            case "": PromptPG(prompt, actualPath,GP,SP, actualPrompt);break;
            default: Singleton.getInstance().error.printError("noCommand", parameters ,1);;break;
        }
    }
    private static void PromptP(String prompt, String actualPath, String SP, String actualPrompt){
        prompt = actualPath + SP;
        actualPrompt = "$p";
        Singleton.getInstance().helper.SetActualPrompt(actualPrompt);
        Singleton.getInstance().helper.SetPrompt(prompt);
    }
    private static void PromptG(String prompt, String GP, String actualPrompt){
        prompt = GP;
        actualPrompt = "$g";
        Singleton.getInstance().helper.SetActualPrompt(actualPrompt);
        Singleton.getInstance().helper.SetPrompt(prompt);
    }
    private static void PromptPG(String prompt, String actualPath, String GP,String SP, String actualPrompt){
        prompt = actualPath + SP + GP;
        actualPrompt = "$p$g";
        Singleton.getInstance().helper.SetActualPrompt(actualPrompt);
        Singleton.getInstance().helper.SetPrompt(prompt);
    }
    private static void PromptGP(String prompt, String actualPath, String GP,String SP, String actualPrompt){
        prompt = GP + actualPath + SP ;
        actualPrompt = "$g$p";
        Singleton.getInstance().helper.SetActualPrompt(actualPrompt);
        Singleton.getInstance().helper.SetPrompt(prompt);
    }
}
