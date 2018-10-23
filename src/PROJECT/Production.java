/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alast
 */
public final class Production {
    public Production(){
        create();
    }
    static List<String> productions;
    public static void create(){
        productions  = new ArrayList();
        productions.add("COPY CON [a-zA-Z0-9][a-zA-Z0-9_]*");
        productions.add("MD [a-zA-Z][a-zA-Z0-9_]*");
        productions.add("CD [a-zA-Z][a-zA-Z0-9_]*");
        productions.add("CD /");
        productions.add("CD/");
        productions.add("CD \\\\");
        productions.add("CD\\\\");
        productions.add("TIME");
        productions.add("DATE");
        productions.add("CLS");
        productions.add("DIR");
        productions.add("EXIT");
        productions.add("PROMPT \\$P \\$G");
        productions.add("PROMPT \\$G  \\$P");
        productions.add("PROMPT \\$P");
        productions.add("PROMPT \\$G");
        productions.add("PROMPT");
        productions.add("RD [a-zA-Z][a-zA-Z0-9_]*");
        productions.add("TREE");
        productions.add("VER");   
    }
    
    public boolean validate(String wholeCommand){
        for(int i = 0; i < productions.size(); i++){
            if(wholeCommand.toUpperCase().matches(productions.get(i))){
                return true;
            }
        }
        return false;
    }
}
