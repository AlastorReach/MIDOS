package PROJECT.Commands;

import PROJECT.Arbol;
import PROJECT.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josué Mora González
 */
public final class Copy {
    private Copy(){
        
    }
    /*
    *Crea un nuevo archivo y permite escribir en él ( aun no se implementa y no hace su función.)
    */
     public static void Copy(List<String> parts, boolean isValid, String NoValidCommand) throws IOException {
        //si viene con el nombre del directorio malo
        if(!NoValidCommand.isEmpty()){
            MD.DirectoryNameIsValid(NoValidCommand, "FIL");
            return;
        }
        //si vienen espacios entre el nombre del archivo
        if(parts.size() > 3){
            Singleton.getInstance().error.printError("spaceFile", "" ,0);
            return;
        }
        //si la sintaxis es incorrecta
        if(parts.size() != 3){
            Singleton.getInstance().error.printError("sintaxis", "" ,0);
            return;
        }
         if(parts.size() == 3){
            if(parts.get(1).toUpperCase().equals("CON")){
                if(MD.DirectoryNameIsValid(parts.get(2), "FIL")){
                    
                //si el archivo tiene el mismo nombre de la carpeta padre
                if(Singleton.getInstance().helper.ChildHasSameNameAsParent(parts, 2)){
                    return;
                }
                    //si ya existe la carpeta en el mismo nivel del árbol
                    if(Singleton.getInstance().helper.siblingExists(parts, 2)){
                        Singleton.getInstance().error.printError("directoryExists", Arbol.getRutaActual()
                        + "\\" + parts.get(2), 0);
                        return;
                    }
                    //si no hay suficiente memoria disponible
                    if(!Singleton.getInstance().helper.removeMemory(Singleton.getInstance().helper.getFreeMemory(), "FILE")){
                        Singleton.getInstance().error.printError("noMemory",Integer.toString(Singleton.getInstance().helper.
                            getFreeMemory()), 1);
                        return;
                    }
                    
                    //si la sintaxis es correcta
                    //si hay memoria disponible
                    //si no existe el archivo en el mismo nivel
                    //si el nombre del archivo es válido
                    //si el segundo parametro es @CON
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    List<String> lines = new ArrayList<>();
                    String input = "";
                    char c = (char)26;
                    try{
                        do{      
                           input = br.readLine();
                           lines.add(input);

                        }while(!input.endsWith(String.valueOf((char)0x1a)));
                        }
                        catch(NullPointerException e){
                            lines.remove(lines.size()-1);
                        }
                        if(!lines.isEmpty()){
                            String tt = lines.get(lines.size() -1).replace(String.valueOf((char)0x1a),"");
                            lines.set(lines.size() -1, tt);
                        }
                        Singleton.getInstance().helper.createNewFile(parts, 2, lines);
                }
            }
            //si el segundo parametro no es "CON"
            else{
                Singleton.getInstance().error.printError("sintaxis", "", 0);
            }
        }
    }
}
