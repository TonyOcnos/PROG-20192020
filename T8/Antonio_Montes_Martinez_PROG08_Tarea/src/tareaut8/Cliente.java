package tareaut8;

/**
 * Interfaz con lo mínimo que debe implementar un cliente (getId).
 * Todo cliente debe tener como mínimo un ID.
 * @author profe
 */
public interface Cliente {
    /**
     * Obtiene el Id de cliente.
     * @return Id de cliente.
     */
    public long getId();
}
