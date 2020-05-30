package modelo;
import java.io.Serializable;

/**
 * Clase de tipo cartel, que además le hemos implementado la interface de Serializable para poder sacar un archivo con los objetos serializados
 * @author Antonio Montes Martínez - PROG T9 - 2020
 */
public class Cartel implements Serializable{
    
    static final long serialVersionUID = 42L;
    /**
     * Variable string para el nombre del cartel
     */
    private final String nombre;
    /**
     * Variable int para el año de la película
     */
    private final int anyo;
    /**
     * Variable string para la ruta del archivo
     */
    private final String ruta;
    /**
     * Constructor de tres parámetros
     * @param nombre Nombre de la película
     * @param anyo Año de la película
     * @param ruta Ruta de la imagen del cartel
     */
    public Cartel(String nombre, int anyo, String ruta){
        this.nombre = nombre;
        this.anyo = anyo;
        this.ruta = ruta;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public int getAnyo(){
        return this.anyo;
    }
    
    public String getRuta(){
        return this.ruta;
    }
}