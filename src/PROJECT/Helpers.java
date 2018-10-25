package PROJECT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import PROJECT.Commands.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    
    /*Carpeta actual*/
    static int totalDirectorios;
    static Carpeta carpetaActual;
    public void SetCarpetaActual(Carpeta c){
        carpetaActual = c;
    }
    public Carpeta getCarpetaActual(){
        return carpetaActual;
    }
    /*Fin de Carpeta actual*/
    
    private static String tab = "";
    public void SetTab(String t){
        tab = t;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    private int contador = 1;
    private static String ascii = "├";
    public String getAscii(){
        return ascii;
    }
    public void setAscii(String a){
       ascii = a;
    }
    private static String vertical = "";
    public void setVertical(String v){
        vertical = v;
    }
    public String getVertical(){
        return vertical;
    }
    /*Propiedad que maneja la memoria*/
    private int freeMemory = 256;
    public void SetFreeMemory(int m, String type){
        switch(type){
            case "add": this.freeMemory = freeMemory + m;break;
            case "remove": this.freeMemory = freeMemory - m;break;
            case "reset": this.freeMemory = 256 ;break;
        }
        
    }
    public int getFreeMemory(){
        return freeMemory;
    }
    /*Fin de Propiedad que maneja la memoria*/
    
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
    public void init() throws IOException, ClassNotFoundException{
        try{
            Arbol.setRutaActual(actualPath);
            initMemoryData();
            Sintax sintax = new Sintax();
            System.out.print("MINGOSOFT ® MIDOS\n" +"© Copyright MINGOSOFT CORPORATION 2018\n" +
                "Versión 1.0 Memoria libre: "+String.valueOf(freeMemory)+" K\n" +
                "Autor: Josué Mora González - 113260029\n");
            while(true){
                System.out.print(prompt);
                input = br.readLine();
                if(input != null){
                    if(!input.isEmpty()){
                        parts = new LinkedList<>(Arrays.asList(input.split(" ")));
                    }
                }
                //trimParts(parts);
                if(!"".equals(input) && input != null){
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
                case "CLS":     CLS.clrscr(parts, noValidCommand);break;
                case "REN":     Ren.rename(parts, isValidParam, noValidCommand);break;
                case "TYPE":    Type.PrintContent(parts, isValidParam, noValidCommand);break;
                case "DEL":     Del.borrarArchivo(parts,isValidParam, noValidCommand );break;
                case "EXIT":    Exit.exit(br,input);break;
                case "VER":     Ver.showVersion(parts, freeMemory);break;
                case "DATE":    Date.showDate(parts, noValidCommand);break;
                case "MD":      //MD.createDirectory(parts, directories,actualPath, actualParent,freeMemory, counter, noValidCommand);
                                MD.createDirectory(parts,carpetaActual, noValidCommand);
                                 break;
                case "TIME":    Time.showTime(parts, noValidCommand);break;
                case "DIR":     //Dir.DIR(actualPath, counter, directories, freeMemory);break;
                                Dir.DIR2();break;
                case "CD": 
                case "CD..":
                case "CD/":
                case "CD\\":   // CD.CD(parts, actualPath,actualPrompt, prompt, SP, GP, actualParent, noValidCommand);break;
                                CD.CD2(parts, carpetaActual, actualPrompt,prompt,SP,GP, isValidParam, noValidCommand);
                                break;
                case "RD":      //RD.RD(parts, counter, directories, actualPath);break;
                                RD.RD2(parts);break;
                case "PROMPT":  Prompt.Prompt(parts, isValidParam, noValidCommand, prompt, actualPath, actualPrompt, SP, GP);break;
                case "TREE":    Tree.Tree(carpetaActual, tab);break;
                case "COPY":    Copy.Copy(parts, isValidParam, noValidCommand);break;
                default: Singleton.getInstance().error.printError("noCommand", parts.get(0), 1);
            }
        }
        else{
            Singleton.getInstance().error.printError("noCommand", noValidCommand, 1);
        }
    }
    
    public SimpleDateFormat createDateFormat(String dateFormat){
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
            guardarDirectorioEnArchivo();
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
    public boolean removeDirMemory(int memory) {
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
    private void initMemoryData() throws IOException, ClassNotFoundException{
        Arbol arbol = new Arbol();
        arbol.archivoLeerOCrear();
        carpetaActual = arbol.getCarpeta(); 
        initFreeMemoryData();
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
            contarDirectorios(carpetaActual);
            freeMemory = 256 - totalDirectorios; 
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
    
    //carga los datos del archivo de texto a la carpeta raiz
    //la carpeta raiz tiene los datos de las demas carpetas
    public Carpeta loadTxtFile2() throws IOException, ClassNotFoundException{
        try{
            Carpeta c;
            Path fileTree2 = Paths.get("C:\\MIDOSTRE.txt");
            File f = new File(fileTree2.toString());
            if(f.exists()){
                ObjectInputStream file = new ObjectInputStream(new FileInputStream("C:\\MIDOSTRE.txt"));
                c = (Carpeta) file.readObject();
                file.close();
                return c;
            }
            return null;
        }
        catch(IOException e){
            System.out.println("Ocurrió la siguiente excepción " + e.getMessage()); 
            System.in.read();
        }
        return null;
    }


  
    //guarda la carpeta raiz en el archivo de texto
    //luego cuando se lee el archivo se llena la carpeta actual, la raiz con los datos del archivo de texto
    public static void guardarDirectorioEnArchivo()
    {
        try {
            carpetaActual = Arbol.GetFirstLevel();
            //Se escribe el objeto en archivo
            try (
            //Se crea un Stream para guardar archivo
                    ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("C:\\MIDOSTRE.txt"))
                ) {
                file.writeObject(carpetaActual);
                //se cierra archivo
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    //cuenta la cantidad de directorios y archivos de tal manera que si se elimina el archivo de memoria
    //recupera el estado de acuerdo a la cantidad de carpetas y archivos existentes
     public static void contarDirectorios(Carpeta c){
        try{
            //si tiene hijos se agrega un nuevo tab
            if(c.getCantidadCarpetas() != 0){
                for(int i = 0; i < c.getCantidadCarpetas(); i++){
                    if(c.getHijoInterno(i) instanceof Carpeta){
                        totalDirectorios+=8;
                    }
                    else{
                        totalDirectorios+=4;
                    }
                        contarDirectorios((Carpeta)c.getHijoInterno(i));
                }
            }
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
     
     public boolean ChildHasSameNameAsParent(List<String> parts, int indice){
        if(carpetaActual.getNombre().equalsIgnoreCase("M:")){
            if(parts.get(indice).equalsIgnoreCase("MIDOS")){
                Singleton.getInstance().error.printError("sameNameAsParent", "", 0);
                return true;
            }
        }
        //si la carpeta tiene el mismo nombre de la carpeta padre
        if(parts.get(indice).equalsIgnoreCase(carpetaActual.getNombre())){
            Singleton.getInstance().error.printError("sameNameAsParent", "", 0);
            return true;
        }
         return false;
     }
     
     public boolean parentWillHaveSameNameAsChildren(List<String> parts, int indice){
         Carpeta hijo = null;
            for(int i = 0; i < carpetaActual.getCantidadCarpetas(); i++){
                if(parts.get(1).equalsIgnoreCase(carpetaActual.getHijoInterno(i).toString())){
                    if(carpetaActual.getHijoInterno(i) instanceof Carpeta){
                        hijo = (Carpeta)carpetaActual.getHijoInterno(i);
                    }
                }
            }
            if(hijo != null){
                for(int i = 0; i < hijo.getCantidadCarpetas(); i++){
                    Object o = hijo.getHijoInterno(i);
                    if(parts.get(indice).equalsIgnoreCase(o.toString()))
                    {
                        return true;
                    }
                }
            }
            return false;
     }
     
     public boolean siblingExists(List<String>parts, int indice){
        for(int i = 0; i < carpetaActual.getCantidadCarpetas(); i++){
            if(parts.get(indice).equalsIgnoreCase(carpetaActual.getHijoInterno(i).toString())){
                return true;
            }
        }
        return false;
     }
     
     public void createNewDir(List<String> parts, int indice){
            Carpeta carpeta = new Carpeta(Arbol.getRutaActual() 
                    , parts.get(indice),carpetaActual);
            carpetaActual.setHijoInterno(carpeta);
            carpetaActual.setCantidadCarpetas();
     }

    public void createNewFile(List<String> parts, int indice, List<String> lines) {
        Archivo archivo = new Archivo(Singleton.getInstance().helper.getCarpetaActual().getNombre() 
                                + "\\",parts.get(2),Singleton.getInstance().helper.getCarpetaActual(),lines );
        carpetaActual.setHijoInterno(archivo);
        carpetaActual.setCantidadCarpetas();
    }
    
    public void ordenamientoInsercion(List<Carpeta> lista) {
        int in;

        for (int i = 1 ; i < lista.size() ; i++) {
            Carpeta aux = lista.get(i);
            in = i;    //inicia el desplazamiento en i
            
            while (in > 0 && lista.get(in - 1).toString().toUpperCase().compareTo(aux.toString().toUpperCase()) > 0) {
                lista.set(in, lista.get(in - 1));
                //desplaza el elemento hacia la derecha
                --in;
            }
            lista.set(in, aux);
        }
    }
    
}
