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
public final class CD {
    private CD(){
        
    }
    public static void CD(List<String> parts, String actualPath, String actualPrompt, String prompt, String SP, String GP,
            String actualParent, String noValidCommand){
        if(!noValidCommand.isEmpty()){
            Singleton.getInstance().error.printError("noValid", "", 0);
        }
        if(parts.size() > 1){
            if(!parts.get(1).equals("..") && !parts.get(1).equals("\\") && !parts.get(1).equals("/") ){
                String pathToBeSaved = actualPath + "\\" + parts.get(1);
                String result = Singleton.getInstance().helper.PathExists(pathToBeSaved, "CD");
                if(result.equals("isOk")){
                    actualParent = actualPath;
                    Singleton.getInstance().helper.SetActualParent(actualParent);
                    actualPath = actualPath + "\\"  + parts.get(1);
                    Singleton.getInstance().helper.SetActualPath(actualPath);
                }
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
        }else{
           if(parts.get(0).equalsIgnoreCase("cd..")){
                int index = actualPath.lastIndexOf("\\");
                actualPath = actualPath.substring(0,index);
                Singleton.getInstance().helper.SetActualPath(actualPath);
                actualParent = actualPath;
                Singleton.getInstance().helper.SetActualParent(actualParent);
            }
            else if(parts.get(0).equalsIgnoreCase("cd\\") || parts.get(0).equalsIgnoreCase("cd/")){
                actualPath = "M:";
                Singleton.getInstance().helper.SetActualPath(actualPath);
                actualParent = actualPath;
                Singleton.getInstance().helper.SetActualParent(actualParent);
            }
        }
        switch(actualPrompt){
                        case"$p": prompt = actualPath + SP;Singleton.getInstance().helper.SetPrompt(prompt); break;
                        case"$g$p": prompt = GP + actualPath;Singleton.getInstance().helper.SetPrompt(prompt); break;
                        case"$p$g": prompt = actualPath + SP + GP;Singleton.getInstance().helper.SetPrompt(prompt); break;
                        default: break;
                    }
    }
}
