/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Josué Mora González - 113260029
 * Guarda los tokens en un diccionario de diciconarios
 * Las llaves son {TOKEN, ID, TIPO, SINGULAR}
 * TOKEN debe concordar con algun comando
 * ID no se utiliza, pero esta ahí por si acaso
 * TIPO, se utiliza por ejemplo, para caracterizar un espacio en blanco(se elimina)
 * SINGULAR,  se utiliza para especificar que el comando solo es de una palabra o no
 */
public class Tokens {
    private static final List<HashMap> TokensList =  new ArrayList<>();
    public Tokens(){
        
        TokensList.add(CLS());
        TokensList.add(VER());
        TokensList.add(DATE());
        TokensList.add(TIME());
        TokensList.add(EXIT());
        TokensList.add(MD());
        TokensList.add(SPACE());
    }
    
    private HashMap SPACE(){
        HashMap space = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        space.put("TOKEN","");
        space.put("ID",7);
        space.put("TIPO","ESPACIO");
        space.put("SINGULAR",true);
        return space;
    }
    private HashMap IDENTIFICADOR(){
        HashMap space = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        space.put("TOKEN","[a-zA-Z][a-zA-Z0-9_]*");
        space.put("ID",7);
        space.put("TIPO","IDENTIFICADOR");
        space.put("SINGULAR",true);
        return space;
    }
    private HashMap CLS(){
        HashMap Cls = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        Cls.put("TOKEN","CLS");
        Cls.put("ID",1);
        Cls.put("TIPO","ESPECIFICO");
        Cls.put("SINGULAR",true);
        return Cls;
    }
    
    private HashMap VER(){
        HashMap Ver = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        Ver.put("TOKEN","VER");
        Ver.put("ID",2);
        Ver.put("TIPO","ESPECIFICO");
        Ver.put("SINGULAR",true);
        return Ver;
    }
    
    private HashMap DATE(){
        HashMap Date = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        Date.put("TOKEN","DATE");
        Date.put("ID",3);
        Date.put("TIPO","ESPECIFICO");
        Date.put("SINGULAR",true);
        return Date;
    }
    
    private HashMap TIME(){
        HashMap Time = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        Time.put("TOKEN","TIME");
        Time.put("ID",4);
        Time.put("TIPO","ESPECIFICO");
        Time.put("SINGULAR",true);
        return Time;
    }
    
    private HashMap EXIT(){
        HashMap Exit = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        Exit.put("TOKEN","EXIT");
        Exit.put("ID",5);
        Exit.put("TIPO","ESPECIFICO");
        Exit.put("SINGULAR",true);
        return Exit;
    }
    
    private HashMap MD(){
        HashMap Md = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        Md.put("TOKEN","MD");
        Md.put("ID",6);
        Md.put("TIPO","ESPECIFICO");
        Md.put("SINGULAR",false);
        return Md;
    }
    
    /*
    *Devuelve la lista de tokens
    */
    public List<HashMap> GetList(){
        return Tokens.TokensList;
    }
    
    /*
    *Devuelve un String indicador del tipo del token que se esta evaluando en la clase @Sintax - 23 - analizador();
    */
    public String Match(String tobeMatched){
        for(int i = 0; i<TokensList.size(); i++){
            if(tobeMatched.equalsIgnoreCase(TokensList.get(i).get("TOKEN").toString()) 
                    && TokensList.get(i).get("TIPO") == "ESPECIFICO"){
                return "ESPECIFICO";
            }
            if(tobeMatched.equalsIgnoreCase(TokensList.get(i).get("TOKEN").toString()) 
                    && TokensList.get(i).get("TIPO") == "ESPACIO"){
                return "ESPACIO";
            }
            if(tobeMatched.matches(TokensList.get(i).get("TOKEN").toString()) 
                    && TokensList.get(i).get("TIPO") == "IDENTIFICADOR"){
                return "IDENTIFICADOR";
            }
        }
        return "NOMATCH";
    }
}
