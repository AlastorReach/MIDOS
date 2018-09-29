/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Josue Mora González 113260029
 */
public class Helpers {
    //atributos
    private static int freeMemory = 256;
    private int childCounter = 0;
    private List<String> directories = new LinkedList();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String input = "";
    private static List<String> parts;
    private static String path  = "";
    private static File files;
    Path fileTree = Paths.get("C:\\MIDOS\\MIDOSTRE.txt");
    Path fileFree = Paths.get("C:\\MIDOS\\MIDOSFRE.txt");
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
                System.out.print("M:\\>");
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
    
    static String clear(int num){
        String newline = "\n\n\n\n\n\n\n\n\n\n";
        if(num <= 1){
            return "";
        }
        System.out.println(newline);
        return clear(num -1);
    }
    /*
    *Limpia la consola en cmd llamando al mismo cmd: cls
    */
    public static void clrscr(){
    try {
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    } 
    catch (IOException | InterruptedException ex) 
    {
        clear(5);
    }
}
     
    /*
    *Limpia la consola
    */
    public void clearConsole() {
        clear(5);
    }
    
    /*
    *Verifica qué comando digita el usuario y manda una función
    */
    public void verifying( List<String> parts) throws IOException{
        switch(parts.get(0).toUpperCase()){
            case "CLS": clrscr();break;
            case "EXIT": exit(br);break;
            case "VER": showVersion(parts);break;
            case "DATE": showDate();break;
            case "MD": createDirectory(parts);break;
            case "COPY": createFile(path);break;
            case "TIME": showTime();break;
            default: Singleton.getInstance().error.printError("noCommand", parts.get(0), 1);
        }
    }
    
    /*
    *Termina la ejecución del programa MIDOS
    */
    private boolean exit(BufferedReader br) throws IOException{
        System.out.println("¿Está seguro de salir de MIDOS?");
        input = br.readLine();
        switch(input.toLowerCase()){
            case "s":
            case "si": SaveAndExit(); return false;
            default: return true;
        }
    }
    /*
    *Mustra la versión del porgrama MIDOS y la cantidad de memoria libre
    */
    private void showVersion(List<String> parts){
        if(parts.size() == 1){
        System.out.println("Versión 1.0 Memoria libre: "+freeMemory+" K");
        }else{
            Singleton.getInstance().error.printError("singleCommand","",0);
        }
    }
    /*
    *Muestra la fecha actual al usuario
    */
    private static void showDate(){
        SimpleDateFormat format = createDateFormat("dd/MM/yyyy");
        String date = format.format(new Date());
        System.out.println("La fecha actual es: " + date);
    }
    /*
    *Muestra la hora actual al usuario
    */
    private static void showTime(){
        SimpleDateFormat format = createDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String time = format.format(cal.getTime());
        System.out.println("La hora actual es: " + time);
    }
    /*
    *Crea nuevos directorios a partir del directorio raiz MIDOS
    */
    private void createDirectory(List<String> parts) throws FileNotFoundException, IOException{
        if(DirectoryNameIsValid(parts)){
            List<String> folders = new LinkedList<>(Arrays.asList(parts.get(1).split("\\\\")));
            //trimParts(folders);
            String tab = "";
            List<String> Parents;
            boolean isRepeated = false;
            //si ya hay directorios existentes en la lista
            if(directories.size()>0){
               if(directoryCount(parts) <= 7){
                Parents = new ArrayList<>();
                //recorre las carpetas que quiere crear el usuario
                for(int i = 0;i <folders.size(); i++){ 
                    //por cada carpeta recorre la lista para ver si ya existe
                    for(int j = 0; j<directories.size();j++){//búsqueda
                        isRepeated = false;
                        String folder = tab + "["+folders.get(i)+ "]";
                        //si la carpeta existe en la lista, entonces no la guarda en la lista
                            if(folder.equals(directories.get(j))){
                                if(i > 0){
                                Parents.add(tab + "["+folders.get(i-1)+ "]");
                                }
                                Singleton.getInstance().error.printError("directoryExists", folders.get(i) ,0);
                                isRepeated = true;
                                break;
                            }
                    }
                    //si recorrió toda la lista y la carpeta no existe
                    if(!isRepeated){
                        if(Parents.isEmpty()){
                             saveFolderAndRemoveMemory(folders, tab,i, false,"DIR");
                        }
                        else{
                            String lastParent = Parents.get(Parents.size()-1);
                            int index =  directories.indexOf(lastParent)+1;
                             saveFolderAndRemoveMemory(folders, tab,i, false,"DIR");
                        }
                        
                      
                    }
                    tab += "\t";
                }
               }//si la capacidad de hijos en cada directorio ya es de 8
               else{
                   Singleton.getInstance().error.printError("capacity", "", 0);
               }
            }
            else{
                for(int i = 0;i <folders.size(); i++){
                    saveFolderAndRemoveMemory(folders, tab,i, true,"DIR");
                }
            }
            //Files.write(fileTree, directories, Charset.forName("UTF-8"));
        }
            
}
    /*
    *Crea un nuevo archivo y permite escribir en él
    */
    private void createFile(String path) throws IOException{
        split();
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
    private void split(){
        parts = Arrays.asList(input.split(" "));
    }
    private static SimpleDateFormat createDateFormat(String dateFormat){
       SimpleDateFormat format = new SimpleDateFormat(dateFormat);
       return format;
    }
    
    private static boolean DirectoryNameIsValid(List<String> parts){
        if(!DirectoryNameIsOk(parts)){
            return false;
        }
        return DirectoryLengthIsOk(parts);
    }
    /*
    *Revisa el nombre del directorio que se desea crear
    */
    private static boolean DirectoryNameIsOk(List<String> parts){
        String[] folders = null;
        if(parts.size() < 2){
            Singleton.getInstance().error.printError("sintaxis", "", 0);
            return false;
        }
        folders = parts.get(1).split("\\\\"); 
        for(int i = 0; i < folders.length; i++){
            if(folders[i].matches("[a-zA-Z][a-zA-Z0-9_]*") != true){
                Singleton.getInstance().error.printError("errorName", folders[i] ,0);
                return false;
            }
        }
        return true;
    }
    /*
    *Revisa que el largo del nombre de l directorio es válido
    */
    private static boolean DirectoryLengthIsOk(List<String> parts){
        String[] directories = parts.get(1).split("\\\\"); 
        if(directories[directories.length -1].length() <= 8){   
            return true;
        }
        Singleton.getInstance().error.printError("length", "" ,0);
        return false;
    }
    /*
    *Antes de usar un archivo, este método revisa si el archivo existe
    *retorna true si el archivo existe
    */
    private boolean FileExists(Path filePath){
        File ft = new File(filePath.toString());
        return ft.exists();
    }
    /*
    *Escribe en los dos archivos, FileTree y FileFree, una vez el usuario elige salir del programa
    *en FileFree guarda la información de la variable @freeMemory
    *en FileTree guarda la información de los directorios creados y guardados en el array @directories
    */
    private void SaveAndExit() throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileFree.toString()))) {
            writer.write(Integer.toString(freeMemory));
            writer.close();
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
    *Función que remueve memoria y luego guarda la información de los directorios creados en @directorios
    *Primero utiliza @removeMemory()
    *Si @removememory() da el visto bueno con la memoria, agrega el directorio al @directories
    */
    private void saveFolderAndRemoveMemory(List<String> folders,String tab, int i, boolean incrementTab, String toDelete){
        if(removeMemory(freeMemory, toDelete)){
            directories.add(tab+"["+folders.get(i)+"]");
                if(incrementTab){
                    tab += "\t"; //la idea del tab es ir creando el arbol de directorios en el archivo de texto
                }               //pero esta en proceso, aun no funciona como debe ser
            }
            else{
                Singleton.getInstance().error.printError("noMemory", "" ,0);
            }
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
    
    /*
    *Cuenta los directorios que ya existen
    * la idea es que cuente los subdirectorios, pero como ahorita solo hay un subdirectorio...
    */
    private int directoryCount(List<String> parts){
        int contador = 0;
        for(int i = 0; i < directories.size();i++){
            if(parts.get(0).matches("[\\[a-zA-Z][a-zA-Z0-9_\\]]*")){
                contador++;
            }
        }
        return contador;
    }


        
}
