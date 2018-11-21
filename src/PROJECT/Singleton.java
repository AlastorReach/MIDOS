package PROJECT;

/**
 *
 * @author Josué Mora González cédula 113260029 Clase Singleton para mantener
 * una sola instancia de las siguienets clases
 */
public class Singleton {

    private static Singleton instance = null;
    public ErrorList error;
    public Helpers helper;
    public Tokens tokens;
    //public Production prod;

    protected Singleton() {
        instance = this;
        error = new ErrorList();
        helper = new Helpers();
        tokens = new Tokens();
        //prod = new Production();
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
