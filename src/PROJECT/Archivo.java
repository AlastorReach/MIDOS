/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROJECT;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author alast
 */
public class Archivo implements Serializable
{
     private String nombre;
    private String ruta;
    private Carpeta carpeta;
    private List<String> contenido;

    public Archivo(String ruta,String nombre, Carpeta carpeta, List<String> contenido) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.carpeta = carpeta;
        this.contenido = contenido;
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

    public Carpeta getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(Carpeta carpeta) {
        this.carpeta = carpeta;
    }

    public List<String> getContenido() {
        return contenido;
    }

    public void setContenido(List<String> contenido) {
        this.contenido = contenido;
    }
}
