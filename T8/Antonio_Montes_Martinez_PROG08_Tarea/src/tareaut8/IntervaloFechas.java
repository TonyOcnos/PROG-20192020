package tareaut8;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase que modela un intervalo o rango de fechas.
 * En esta clase existen diversos métodos que permite comprobar si una fecha
 * está dentro del rango o si un intervalo se solapa con otro.
 * 
 * @author profe
 */
public class IntervaloFechas {

    /**
     * Comienzo del intervalo de fechas.
     */
    private final LocalDate start;
    /**
     * Fin del intervalo de fechas.
     */
    private final LocalDate end;

    /**
     * Configura una instancia de IntervaloFechas con la fecha de comienzo
     * y fecha de fin indicadas por parámetro.
     * @param start Fecha de comienzo (tiene que ser distinto de null).
     * @param end Fecha de fin (tiene que ser distinto de null, y no puede
     * ser anterior a start).
     * @throws IllegalArgumentException Se lanzará esta excepción si alguna
     * de las fechas es null o si la fecha de comienzo es posterior a la de
     * fin.
     */
    public IntervaloFechas (LocalDate start, LocalDate end) throws IllegalArgumentException
    {
        if (start==null || end==null) 
            throw new IllegalArgumentException("Alguna de las fechas pasadas como parámetro es null.");
        if (start.isAfter(end))
            throw new IllegalArgumentException("La fecha de comienzo es posterior a la fecha de fin.");            
        this.end=end;
        this.start=start;
    }
    
    /**
     * Permite crear un intervalo de fechas partiendo de los datos numéricos
     * de las fechas. 
     * Imagina que quieres crear un rango de fechas del 1/2/2019 al 3/4/2020,
     * en tal caso, usaríamos este método de la siguiente forma:
     * 
     * IntervaloFechas if=IntervaloFechas.of(2019,2,1,2020,4,3);
     * 
     * @param aYear Año de comienzo.
     * @param aMonth Mes de comienzo.
     * @param aDay Día de comienzo.
     * @param bYear Año de fin.
     * @param bMonth Mes de fin.
     * @param bDay Día de fin.
     * @return Instancia de IntervaloFechas con las fechas dadas.
     * @throws IllegalArgumentException Lanzará una excepción si:
     *  - Alguna de las fechas dadas no es válida por está fuera de rango.
     *  - Si la fecha de comienzo es posterior a la de fin.
     */
    public static IntervaloFechas of(int aYear,int aMonth, int aDay, int bYear, int bMonth, int bDay) throws IllegalArgumentException
    {   
        IntervaloFechas m;
        LocalDate start;
        LocalDate end;
        try {
            start=LocalDate.of(aYear,aMonth,aDay);
            end=LocalDate.of(bYear,bMonth,bDay);
        }
        catch (DateTimeException e)
        {
            throw new IllegalArgumentException("Intervalo no contiene fechas válidas");
        }
        
        m = new IntervaloFechas(start, end);
        
        return m;
    }
    
    /**
     * Obtiene la fecha de inicio del intervalo.
     * @return Fecha de inicio del intervalo.
     */
    public LocalDate getStart ()
    {
        return start;
    }

    /**
     * Obtiene la fecha de fin del intervalo.
     * @return Fecha de fin del intervalo.
     */
    public LocalDate getEnd()
    {
        return end;
    }
    
    /**
     * Permite comprobar si una fecha dada está dentro del intervalo.
     * Las fechas de inicio y fin del intervalo están incluidas.
     * @param fecha Fecha a comprobar.
     * @return true si la fecha está dentro del intervalo y false si no lo 
     * están.
     */
    public boolean estaDentroDelIntervalo(LocalDate fecha)
    {
        return !fecha.isBefore(start) && !fecha.isAfter(end);
    }
    
    /**
     * Método que permite obtener un intervalo en formato texto.
     * El texto producido contiene la fecha de inicio y la fecha de fin
     * siguiendo el siguiente modelo:
     * 31/12/2019 - 31/12/2020
     * @return Texto que contiene el intervalo en formato texto.
     */
    @Override
    public String toString()
    {
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%s - %s",dtf.format(start),dtf.format(end));
    }
}