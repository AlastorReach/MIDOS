/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT.Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alast
 */
public final class Copy {
    private Copy(){
        
    }
    /*
    *Crea un nuevo archivo y permite escribir en él
    */
    public static void createFile(String path, List<String>parts, String input, BufferedReader br) throws IOException{
        if(parts.size() == 3){
            path = ".\\src\\MIDOS\\"+parts.get(2);
            List<String> lines = new ArrayList<>();
            while(!"^Z".equals(input = br.readLine())){
               lines.add(input);
            }
            Path file = Paths.get(path);
            Files.write(file, lines, Charset.forName("UTF-8"));
        }
    }
}
