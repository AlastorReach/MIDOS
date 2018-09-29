/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josué Mora González - 113260029
 */
public class Sintax {
    public Sintax(){
    }
    /*
    *Analizador de sintaxis
    *Remueve espacios en blanco y deja solo las palabras
    */
    public void analizador(List<String> input) throws IOException{
        List<String> commands = new ArrayList();
        int contador = 0;
        do{
            String match = Singleton.getInstance().tokens.Match(input.get(contador));
            switch(match){
                case "ESPECIFICO": commands.add(input.get(contador));input.remove(contador);contador = 0;break;
                case "ESPACIO"   : input.remove(contador);contador = 0;break;
                case "IDENTIFICADOR": commands.add(input.get(contador));input.remove(contador);contador = 0;break;
                default: commands.add(input.get(contador));input.remove(contador);contador = 0;break;
            }
        }while(contador < input.size());

        //luego envia las palabras a este método mendiante una Lista
        //Minimo debe llegar una letra, sino no deberia haber entrado aquí
        Singleton.getInstance().helper.verifying(commands);

    }
}
