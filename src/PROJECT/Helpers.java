package PROJECT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import PROJECT.Commands.*;

/**
 *
 * @author Josue Mora González 113260029
 */
public class Helpers {
    //consta//
    private static final String SP = "\\";
    private static final String GP = ">";
    //
    //atributos
    private static int counter = 0;
    private int freeMemory = 256;
    public void SetFreeMemory(int m){
        this.freeMemory = freeMemory + m;
    }
    private int childCounter = 0;
    private String actualPrompt = "$p$g";
    public void SetActualPrompt(String p){
        this.actualPrompt = p;
    }
    private String actualPath = "M:";
    public void SetActualPath(String p){
        this.actualPath = p;
    }
    private String prompt = actualPath + SP + GP;
    public void SetPrompt(String p){
        this.prompt = p;
    }
    private String actualParent = "M:";
    public void SetActualParent(String p){
        this.actualParent = p;
    }
    private List<String> directories = new LinkedList<>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String input = "";
    private static List<String> parts;
    private static String path  = "";
    private static File files;
    Path fileTree = Paths.get("C:\\MIDOSTRE.txt");
    Path fileFree = Paths.get("C:\\MIDOSFRE.txt");
    //end atributos
    
    /**
     * 
     * @throws IOException 
     * Inicia MIDOS
     */ 
    public void init() throws IOException{
        try{
            initMemoryData();
            Sintax sintax = new Sintax();
            System.out.print("MINGOSOFT ® MIDOS\n" +"© Copyright MINGOSOFT CORPORATION 2018\n" +
                "Versión 1.0 Memoria libre: "+String.valueOf(freeMemory)+" K\n" +
                "Autor: Josué Mora González - 113260029\n");
            while(true){
                System.out.print(prompt);
                input = br.readLine();
                parts = new LinkedList<>(Arrays.asList(input.split(" ")));
                //trimParts(parts);
                if(!"".equals(input)){
                    sintax.analizador(parts);
                }
            }
        }catch(IOException e){
            System.out.println("Ocurrió la siguiente excepción " + e.getMessage()); 
            System.in.read();
        }
    }

    /*
    *Verifica qué comando digita el usuario y manda una función
    */
    public void verifying( List<String> parts, boolean isValidParam, String noValidCommand) throws IOException{
        if(parts.size() > 0){
            switch(parts.get(0).toUpperCase()){
                case "CLS":     CLS.clrscr();break;
                case "EXIT":    Exit.exit(br,input);break;
                case "VER":     Ver.showVersion(parts, freeMemory);break;
                case "DATE":    Date.showDate(parts, noValidCommand);break;
                case "MD":      MD.createDirectory(parts, directories,actualPath, actualParent,freeMemory, counter, noValidCommand);break;
                case "TIME":    Time.showTime(parts, noValidCommand);break;
                case "DIR":     Dir.DIR(actualPath, counter, directories, freeMemory);break;
                case "CD": 
                case "CD..":
                case "CD/":
                case "CD\\":    CD.CD(parts, actualPath,actualPrompt, prompt, SP, GP, actualParent, noValidCommand);break;
                case "RD":      RD.RD(parts, counter, directories, actualPath);break;
                case "PROMPT": Prompt.Prompt(parts, isValidParam, noValidCommand, prompt, actualPath, actualPrompt, SP, GP);break;
                default: Singleton.getInstance().error.printError("noCommand", parts.get(0), 1);
            }
        }
        else{
            Singleton.getInstance().error.printError("noCommand", noValidCommand, 1);
        }
    }
    
    public static SimpleDateFormat createDateFormat(String dateFormat){
       SimpleDateFormat format = new SimpleDateFormat(dateFormat);
       return format;
    }
 
    /*
    *Escribe en los dos archivos, FileTree y FileFree, una vez el usuario elige salir del programa
    *en FileFree guarda la información de la variable @freeMemory
    *en FileTree guarda la información de los directorios creados y guardados en el array @directories
    */
    public void SaveAndExit() throws IOException{
        try{
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileFree.toString()))) {
                writer.write(Integer.toString(freeMemory));
                writer.close();
            }
            Files.write(fileTree, directories, Charset.forName("UTF-8"));
            System.exit(0);
        }
        catch(IOException e){
            System.err.println("Ocurrio la siguiente excepción: " + e.getMessage());
        }
    }
    /*
    *Función que controla dos funciones
    *@removeFileMemory elimina 4 bytes si es permitido al crearse un archivo txt
    *@removeDirMemory elimina 8 bytes si es permitido al crearse un nuevo directorio
    */
    public boolean removeMemory(int memory,String file){
        switch(file){
        case "FILE": return removeFileMemory(memory);
        case "DIR" : return removeDirMemory(memory);
        default:return false;
        }
    }
    /*Remueve 4 k de memoria disponible
    *retorna true si el proceso fue exitoso
    */
    private boolean removeFileMemory(int memory) {
        if(memory -4 >= 0){
            freeMemory -= 4;
            return true;
        }
        return false;
    }
    /*Remueve 8 k de memoria disponible
    *retorna true si el proceso fue exitoso
    */
    private boolean removeDirMemory(int memory) {
        if(memory -8 >= 0){
            freeMemory -= 8;
            return true;
        }
        return false;
    }
    /*
    *Función que inicializa la lectura de los archivos @MIDOSFRE y @MIDOSTRE
    * se utiliza en el @init()
    */
    private void initMemoryData() throws IOException{
        initFreeMemoryData();
        InitDirectoryTreeData();  
    }
    /*
    *Función que carga los datos del archivo txt @MIDOSFREE
    *Los guarda en un array temporal
    * Si el array en nulo, se inicia la memoria por defecto = 256 k
    *De lo contrario establece el valor de memoria en el txt a la variable @freeMemory
    */
    private void initFreeMemoryData() throws FileNotFoundException, IOException{
        List<String> temp = loadTxtFile(fileFree);
        if(temp != null){
            freeMemory = Integer.parseInt(temp.get(0));
        } 
        else{
            freeMemory = 256;
        }
    }
    /*
    *Carga los datos de los directorios guardados en @MIDOSTRE.txt
    *Si los datos son nulos, setea la memoria @freeMemory en 256 k, debido a que no posee info de los directorios
    *También crea una nueva instancia de @directories
    */
    private void InitDirectoryTreeData() throws IOException{
        try{
            directories = loadTxtFile(fileTree);
            if(directories == null){
                //try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileFree.toString()))) {
                    freeMemory = 256;
                   // writer.write(Integer.toString(freeMemory));
                //}
                directories = new ArrayList();
            }
            else{
                if(directories.isEmpty()){
                    freeMemory = 256;
                }
            }
        }
        catch(IOException e){
            System.out.println("Ocurrió la siguiente excepción " + e.getMessage()); 
            System.in.read();
        }
    }
    /*
    *Carga los datos de un archivo de texto
    *Parametro: nombre o ruta del archivo de texto que se desea cargar
    *retorna una lista de String si fue exitoso
    *Caso contrario retorna null
    */
    private List<String> loadTxtFile(Path txtFile) throws IOException{
        try{
        File f = new File(txtFile.toString());
        List<String> lines;
        if(f.exists()){
            lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(f.toString())); 
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        }
            return null ;
        }
        catch(IOException e){
            System.out.println("Ocurrió la siguiente excepción " + e.getMessage()); 
            System.in.read();
        }
        return null;
    }

    private String SubString(List<String> l, int i, String initial, String end){
        int indexIni = l.get(i).indexOf(initial);
        int indexFin = l.get(i).indexOf(end);
        return l.get(i).substring(indexIni +6, indexFin);
    }
    public String PathExists(String path, String command){
        for(int i = 0; i < directories.size(); i++){
            int dirIndex = directories.get(i).indexOf("<DIR>");
            int iniName = directories.get(i).indexOf("<NAME>") + 6;
            int endName = directories.get(i).indexOf("</NAME>");
            int iniPath = directories.get(i).indexOf("<PATH>") + 6;
            int endPath = directories.get(i).indexOf("</PATH>");
            String name = directories.get(i).substring(iniName, endName);
            String paths = directories.get(i).substring(iniPath, endPath);
            String tobeCompared = paths + name;
            if(tobeCompared.equalsIgnoreCase(path) && dirIndex != -1){
                return "isOk";
            }
            else if(tobeCompared.equalsIgnoreCase(path) && dirIndex == -1){
                if(command.equals("CD")){
                    Singleton.getInstance().error.printError("isFile", "" ,0);
                }
                return "isFile";
            }
            
        }
        if(command.equals("CD")){
            Singleton.getInstance().error.printError("noRouteFound", path ,0);
        }
        return "NoExist";
    }
    public List<String> GetDataFromPath(String path){
        counter = 0;
        List<String>dirs = new ArrayList();
        for(int i = 0; i < directories.size(); i++){
            int iniPath = directories.get(i).indexOf("<PATH>") + 6;
            int endPath = directories.get(i).indexOf("</PATH>");
            String dirOrFile = directories.get(i).substring(iniPath, endPath);
                if((path +"\\").equalsIgnoreCase(dirOrFile)){
                    dirs.add(directories.get(i));
                    counter++;
                }
        }
        return dirs;
    }
    
    public int directoryCount(String path){
            List<String> folders = GetDataFromPath(path);
            return folders.size();    
    }
    
}
