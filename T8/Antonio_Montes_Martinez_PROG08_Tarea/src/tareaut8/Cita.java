package tareaut8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que implementa una cita.
 * La cita se caracteriza por tener: un cliente, una fecha y hora y una 
 * descripción.
 * 
 * @author profe
 */
public class Cita implements Comparable<Cita>
{
    /**
     * Cliente que tiene la cita (no puede ser null).
     */
    private Cliente c;
    /**
     * Fecha y hora de la cita (no puede ser null).
     */
    private LocalDateTime fechaYHora;
    /**
     * Descripción (si puede ser null). 
     */
    private String descripcion;
 
    /**
     * Configura una instancia de Cita con un cliente específico (no puede
     * ser null), a una fecha y hora específica (no puede ser null) y
     * con una descripción concreta (si puede ser null).
     * @param c Cliente que tiene la cita.
     * @param fechaYHora Fecha y hora de la cita.
     * @param descripcion Descripción opcional de la cita.
     * @throws IllegalArgumentException Se lanzará esta excepción si el cliente
     * es null o si fechaYHora es null.
     */
    public Cita(Cliente c, LocalDateTime fechaYHora, String descripcion ) throws IllegalArgumentException
    {
        if (c==null)
            throw new IllegalArgumentException ("La instancia de cliente no puede ser null.");        
        if (fechaYHora==null)
            throw new IllegalArgumentException ("La instancia de fechaYHora no puede ser null.");        

        this.c=c;
        this.fechaYHora=fechaYHora;        
        this.descripcion=descripcion==null?"":descripcion;
        
    }
    
    /**
     * Obtiene el día de la semana de la cita. DayOfWeek es un enumerado
     * que contiene el día de la semana en inglés: MONDAY, TUESDAY, WEDNESDAY,
     * etc.
     * @return Día de la semana de esta cita.
     */
    public DayOfWeek getDiaDeLaSemana()
    {
        return fechaYHora.getDayOfWeek();
    }

    /**
     * Obtiene la fecha de la cita (pero no la hora).
     * @return Fecha de la cita.
     */
    public LocalDate getFecha()
    {
        return fechaYHora.toLocalDate();
    }

    /**
     * Obtiene la hora de la cita (pero no la fecha).
     * @return Hora de la cita.
     */
    public LocalTime getTime()
    {
        return fechaYHora.toLocalTime();
    }
    
    /**
     * Obtiene el ciente que tiene la cita.
     * @return Instancia de cliente asociada a la cita.
     */
    public Cliente getCliente()
    {
        return c;
    }

    /**
     * Método que obtiene una versión de la cita en formato texto que incluye:
     * 
     * - Cliente que tiene la cita (se utiliza método toString de Cliente).
     * - Fecha de la cita.
     * - Hora de la cita.
     * - Descripción (si la hay).
     * 
     * @return Texto con información de la cita.
     */
    @Override
    public String toString() {
        String ln=System.lineSeparator();
        StringBuilder salida = new StringBuilder();
        
        DateTimeFormatter dia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm");
        
        salida.append("Cliente .............: ").append(c.toString()).append(ln);
        salida.append("Día de la cita ......: ").append(dia.format(fechaYHora)).append(ln);
        salida.append("Hora de la cita .....: ").append(hora.format(fechaYHora)).append(ln);
        
        if (descripcion != null && descripcion.length() > 0) {
            salida.append("Descripción .........: ").append(descripcion).append(ln);
        }
        return salida.toString();
    }

    @Override
    public int compareTo(Cita c) {
            return this.getTime().compareTo(c.getTime());
    }
}