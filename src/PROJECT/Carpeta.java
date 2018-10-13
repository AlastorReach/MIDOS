package PROJECT;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author alast
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
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadCarpetas() {
        return hijos.size();
    }

    public void setCantidadCarpetas(int cantidadHijos) {
        this.cantidadHijos = hijos.size();
    }

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
    
    public void removeFolder(String nombre) {
            for(int i = 0; i< hijos.size();i++){
                if(hijos.get(i) instanceof Carpeta){
                    if(((Carpeta)hijos.get(i)).getNombre().equalsIgnoreCase(nombre)){
                        hijos.remove(i);
                    }
                }
            }
    }
}
