package PROJECT;

import java.io.IOException;

/**
 *
 * @author Josué Mora González
 * @version 1.0
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            Singleton.getInstance().helper.init();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error de ejecución: " + e.getMessage());
            System.in.read();
        }
    }

}
