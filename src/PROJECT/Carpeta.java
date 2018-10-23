package PROJECT;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Josué Mora González
 */
public class Carpeta implements Serializable
{

    private String ruta;
    private String nombre;
    private Carpeta padre;
    private int cantidadHijos;
    private List<Object> hijos = new LinkedList();

    public Carpeta getPadre() {
        return padre;
    }
    public List<Object> getHijos() {
        return hijos;
    }

    public void setPadre(Carpeta padre) {
        this.padre = padre;
    }

    public Carpeta(String ruta, String nombre, Carpeta padre) {
        this.ruta = ruta;
        this.nombre = nombre;
        this.padre = padre;
    }
    
    @Override
    public String toString() {
        return nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre() {
        if(nombre.equals("MIDOS")){
            return "M:";
        }
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadCarpetas() {
        return hijos.size();
    }

    public void setCantidadCarpetas() {
        this.cantidadHijos = hijos.size();
    }

    //obtiene el hijo de la carpeta segun el índice
    public Object getHijoInterno(int indice) {
       if(hijos.get(indice) instanceof Carpeta){
           return (Carpeta)hijos.get(indice);
       }
       if(hijos.get(indice) instanceof Archivo){
           return hijos.get(indice);
       }
       System.err.println("El sistema no puede encontrar la ruta especificada");
        return null;
    }

    public void setHijoInterno(Object carpetaInterna) {
            hijos.add(carpetaInterna);
            cantidadHijos++;
    }
    
    public void removeOrChangeNameChild(String nombre, String command, String replace) {
            for(int i = 0; i< hijos.size();i++){
                if(hijos.get(i) instanceof Carpeta){
                    if(((Carpeta)hijos.get(i)).getNombre().equalsIgnoreCase(nombre)){
                        switch(command.toUpperCase()){
                            case "REMOVE":hijos.remove(i);return;
                            case "RENAME": Carpeta c = (Carpeta)hijos.get(i);
                            c.setNombre(replace);
                            hijos.set(i, c);
                            return;
                        }
                        
                    }
                }
                else if(hijos.get(i) instanceof Archivo){
                    if(((Archivo)hijos.get(i)).getNombre().equalsIgnoreCase(nombre)){
                        switch(command.toUpperCase()){
                            case "REMOVE":hijos.remove(i);return;
                            case "RENAME": Archivo a = (Archivo)hijos.get(i);
                            a.setNombre(replace);
                            hijos.set(i, a);
                            return;
                        }
                    }
                }
            }
    }
}
