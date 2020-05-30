
package tareaut8;

/**
 * Implementación de un cliente correspondiente a un único individuo humano.
 * 
 * @author profe
 */
public class Persona implements Cliente {
    /**
     * Identificador del cliente.
     */
    private final int ID;
    /**
     * Nombre del cliente.
     */
    private final String NOMBRE;
    
    /**
     * Configura una instancia de Persona, dotandola de identificador (id) y
     * nombre.
     * @param id Identificador de la persona.
     * @param nombre Nombre de la persona.
     * @throws IllegalArgumentException Se lanzará esta excepción si el nombre
     * del cliente es null.
     */
    public Persona(int id, String nombre) throws IllegalArgumentException
    {
        if (nombre==null)
               throw new IllegalArgumentException("El nombre del cliente no puede ser null.");
        this.ID=id;
        this.NOMBRE=nombre;
    }

    //Documentación heredada de la interfaz Cliente.
    @Override
    public long getId() {
        return this.ID;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return NOMBRE;
    }

    /**
     * Obtiene un resumen textual de la información de esta instancia. 
     * El resumen tendrá el formato {ID=19;NOMBRE=pepe}.
     * @return Resumen textual de la persona.
     */
    @Override
    public String toString() {
        return String.format("{ID=%s;NOMBRE=%s}",this.ID,this.NOMBRE);
    }

    /**
     * Construye 4 personas de ejemplo, con los identificadores:
     * - ID=8888;NOMBRE=ALEXA
     * - ID=8889;NOMBRE=ROBERT
     * - ID=8884;NOMBRE=CHARLIE
     * - ID=8883;NOMBRE=ANNE
     * @return Array con 4 instancias de la clase Persona.
     */
    public static Persona[] buildSamplePersonas()
    {
        return new Persona[]{new Persona(8888, "ALEXA"),
            new Persona(8889, "ROBERT"),
            new Persona(8884, "CHARLIE"),
            new Persona(8883, "ANNE")};
    }
}
