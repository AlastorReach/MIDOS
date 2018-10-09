/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import PROJECT.Singleton;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author alast
 */
public final class Dir {
    private Dir(){
        
    }
    public static void DIR(String actualPath, int counter, List<String>directories, int freeMemory){
        System.out.println("Directorio de " + actualPath);
        String[] folders = Singleton.getInstance().helper.GetDataFromPath(actualPath).toArray(new String[counter]);
        int fileCount = 0;
        int folderCount = 0;
        Arrays.sort(folders, String.CASE_INSENSITIVE_ORDER);
        for(int i = 0; i < folders.length; i++){
            int iniName = folders[i].indexOf("<NAME>") + 6;
            int endName = folders[i].indexOf("</NAME>");
            String dirOrFile = folders[i].substring(iniName, endName);
            if(folders[i].contains("<DIR>")){
                System.out.println("<DIR> " + dirOrFile); 
                folderCount++;
                continue;
            }
            if(directories.get(i).contains("<FILE>")){
                System.out.println("<FILE> " + dirOrFile); 
                fileCount++;
            }
        }
        System.out.println(fileCount + " archivos " + fileCount*4 + " bytes.");
        System.out.println(folderCount + " directorios " + folderCount*8 + " bytes.");
        System.out.println(freeMemory + " bytes libres.");
        counter = 0;
    }
}
