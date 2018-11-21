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
        
        TokensList.add(CD());
        TokensList.add(CD2());
        TokensList.add(CD3());
        TokensList.add(CD4());
        TokensList.add(COPY());
        TokensList.add(TYPE());
        TokensList.add(DEL());
        TokensList.add(CLS());
        TokensList.add(RD());
        TokensList.add(REN());
        TokensList.add(VER());
        TokensList.add(DIR());
        TokensList.add(DATE());
        TokensList.add(TIME());
        TokensList.add(EXIT());
        TokensList.add(MD());
        TokensList.add(TREE());
        TokensList.add(PROMPT());
        TokensList.add(SPACE());
        TokensList.add(IDENTIFICADOR());
        TokensList.add(PROMPTP());
        TokensList.add(PROMPTPG());
        TokensList.add(PROMPTGP());
        TokensList.add(POINTS());
        TokensList.add(SLASH());
        TokensList.add(BACKSLASH());

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
    private HashMap PROMPTP(){
        HashMap space = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        space.put("TOKEN","[\\$][pg]{1}");
        space.put("ID",8);
        space.put("TIPO","PROMPTPARAM");
        space.put("SINGULAR",true);
        return space;
    }
    private HashMap IDENTIFICADOR(){
        HashMap space = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        space.put("TOKEN","[a-zA-Z][a-zA-Z0-9_]*");
        space.put("ID",9);
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
    
    private HashMap PROMPT(){
        HashMap p = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        p.put("TOKEN","PROMPT");
        p.put("ID",8);
        p.put("TIPO","ESPECIFICO");
        p.put("SINGULAR",false);
        return p;
    }
    private HashMap DIR(){
        HashMap dir = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        dir.put("TOKEN","DIR");
        dir.put("ID",10);
        dir.put("TIPO","ESPECIFICO");
        dir.put("SINGULAR",false);
        return dir;
    }
    private HashMap CD(){
        HashMap dir = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        dir.put("TOKEN","CD");
        dir.put("ID",11);
        dir.put("TIPO","ESPECIFICO");
        dir.put("SINGULAR",false);
        return dir;
    }
    private HashMap CD2(){
        HashMap dir = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        dir.put("TOKEN","CD..");
        dir.put("ID",11);
        dir.put("TIPO","ESPECIFICO");
        dir.put("SINGULAR",true);
        return dir;
    }
    private HashMap CD3(){
        HashMap dir = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        dir.put("TOKEN","CD/");
        dir.put("ID",11);
        dir.put("TIPO","ESPECIFICO");
        dir.put("SINGULAR",true);
        return dir;
    }
    private HashMap CD4(){
        HashMap dir = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        dir.put("TOKEN","CD\\\\");
        dir.put("ID",11);
        dir.put("TIPO","ESPECIFICO");
        dir.put("SINGULAR",true);
        return dir;
    }
    private HashMap POINTS(){
        HashMap point = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        point.put("TOKEN","..");
        point.put("ID",12);
        point.put("TIPO","SECUNDARIO");
        point.put("SINGULAR",true);
        return point;
    }
    private HashMap BACKSLASH(){
        HashMap s = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        s.put("TOKEN","[\\\\]");
        s.put("ID",13);
        s.put("TIPO","SECUNDARIO");
        s.put("SINGULAR",true);
        return s;
    }
     private HashMap SLASH(){
        HashMap s = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        s.put("TOKEN","/");
        s.put("ID",15);
        s.put("TIPO","SECUNDARIO");
        s.put("SINGULAR",true);
        return s;
    }
    private HashMap RD(){
        HashMap s = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        s.put("TOKEN","RD");
        s.put("ID",14);
        s.put("TIPO","ESPECIFICO");
        s.put("SINGULAR",false);
        return s;
    }
    private HashMap TREE(){
        HashMap t = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        t.put("TOKEN","TREE");
        t.put("ID",16);
        t.put("TIPO","ESPECIFICO");
        t.put("SINGULAR",true);
        return t;
    }
    private HashMap COPY(){
        HashMap c = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        c.put("TOKEN","COPY");
        c.put("ID",17);
        c.put("TIPO","ESPECIFICO");
        c.put("SINGULAR",false);
        return c;
    }
    private HashMap TYPE(){
        HashMap t = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        t.put("TOKEN","TYPE");
        t.put("ID",18);
        t.put("TIPO","ESPECIFICO");
        t.put("SINGULAR",false);
        return t;
    }
     private HashMap DEL(){
        HashMap d = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        d.put("TOKEN","DEL");
        d.put("ID",19);
        d.put("TIPO","ESPECIFICO");
        d.put("SINGULAR",false);
        return d;
    }
     private HashMap REN(){
        HashMap r = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        r.put("TOKEN","REN");
        r.put("ID",19);
        r.put("TIPO","ESPECIFICO");
        r.put("SINGULAR",false);
        return r;
    }
     private HashMap PROMPTPG(){
        HashMap p = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        p.put("TOKEN","\\$P\\$G");
        p.put("ID",20);
        p.put("TIPO","PROMPTPARAM");
        p.put("SINGULAR",true);
        return p;
    }
     private HashMap PROMPTGP(){
        HashMap p = new HashMap(); 
        //LinkedList Cls = new LinkedList();
        p.put("TOKEN","\\$G\\$P");
        p.put("ID",20);
        p.put("TIPO","PROMPTPARAM");
        p.put("SINGULAR",true);
        return p;
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
            if(tobeMatched.matches(TokensList.get(i).get("TOKEN").toString()) 
                    && TokensList.get(i).get("TIPO") == "PROMPTPARAM"){
                return "PROMPTPARAM";
            }
            if(tobeMatched.matches(TokensList.get(i).get("TOKEN").toString()) 
                    && TokensList.get(i).get("TIPO") == "ESPECIFICO"){
                return "ESPECIFICO";
            }
            if(tobeMatched.matches(TokensList.get(i).get("TOKEN").toString()) 
                    && TokensList.get(i).get("TIPO") == "ESPACIO"){
                return "ESPACIO";
            }
            if(tobeMatched.matches(TokensList.get(i).get("TOKEN").toString()) 
                    && TokensList.get(i).get("TIPO") == "IDENTIFICADOR"){
                return "IDENTIFICADOR";
            }
            if(tobeMatched.matches(TokensList.get(i).get("TOKEN").toString()) 
                    && TokensList.get(i).get("TIPO") == "SECUNDARIO"){
                return "SECUNDARIO";
            }
        }
        return "NOMATCH";
    }
}
