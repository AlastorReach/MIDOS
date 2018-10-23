/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josué Mora González
 */
public class Archivo implements Serializable
{
    private String nombre;
    private String ruta;
    private List<String> contenido;
    private Carpeta padre;

    public Carpeta getPadre() {
        return padre;
    }

    public void setPadre(Carpeta padre) {
        this.padre = padre;
    }

    public Archivo(String ruta,String nombre, Carpeta padre, List<String> contenido) {
        this.ruta = ruta;
        this.nombre = nombre;
        this.padre = padre;
        this.contenido = new ArrayList<>(contenido);
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

    public List<String> getContenido() {
        return contenido;
    }

    public void setContenido(List<String> contenido) {
        this.contenido = contenido;
    }
}
