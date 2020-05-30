package tareaut8;

/**
 * Excepción que modela acciones que de ser completadas conducirían a 
 * que la agenda tuviera un estado inconsistente o ilegal. 
 *
 * @author profe
 */
public class IllegalAgendaStateException extends Exception
{

    private static final long serialVersionUID = 13939494988811L;

    /**
     * Motivo por el que ha sido lanzada esta excepción.
     */
    private final Motivo m;

    /**
     * Diferentes motivos por los que puede lanzarse esta excepción.
     */
    public enum Motivo {
        /**
         * El día indicado no esta disponible. Esto puede ocurrir porque es un
         * día no hábil, o porque es un día no laborable.
         */
        DIA_NO_DISPONIBLE,
        /**
         * El cliente ya tiene cita en la agenda. Solo puede existir una cita
         * por cliente, y ya hay una cita para un cliente con el ID 
         * proporcionado.
         */
        YA_EXISTE_CITA, 
        /**
         * El número de citas máximas por día ha sido superado.
         */
        CITAS_MAXIMAS_SUPERADAS
    };
    
    /**
     * Crea una nueva excepción con un mensaje y motivo concreto.
     * @param message Mensaje de la excepción.
     * @param m Motivo de la excepción.
     */
    public IllegalAgendaStateException (String message, Motivo m)
    {
        super(message);
        this.m=m;
    }

    /**
     * Permite obtener el motivo por el que se produjo la excepción.
     * @return Uno de los valores del enumerado Motivo.
     */
    public Motivo getMotivo() {
        return m;
    }
}