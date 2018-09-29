/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT;

/**
 *
 * @author Josué Mora González cédula 113260029
 * Clase Singleton para mantener una sola instancia de las siguienets clases
 */
public class Singleton {
   private static Singleton instance = null;
   public ErrorList error = new ErrorList();
   public Helpers helper = new Helpers();
   public Tokens tokens = new Tokens();
   protected Singleton() {
   }
   public static Singleton getInstance() {
      if(instance == null) {
         instance = new Singleton();
      }
      return instance;
   }
}
