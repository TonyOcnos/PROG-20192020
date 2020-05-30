package tareaut8.pasos;

import java.time.DayOfWeek;
import tareaut8.Agenda;

/**
 * Paso 2. Verifica el funcionamiento del método esDiaHabil.
 * Para ejecutar este paso el paso 1 tiene que funcionar.
 * @author Salvador Romero
 */
public class TareaUT8Paso2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("==========================");
        System.out.println("   - Tarea 8 Paso 2 -");
        System.out.println("==========================");
        System.out.println("Salida esperada:");
        System.out.println("**********************************\n");
        System.out.println(salidaEsperada);
        System.out.println("\n\nSalida para tu implementación:");
        System.out.println("**********************************\n");
        Agenda a=Agenda.buildSampleAgenda();
        for(DayOfWeek m:DayOfWeek.values())
        {
            System.out.println(String.format("¿Es %s hábil? \t%s",m,a.esDiaHabil(m)?"Si":"No"));
        }
    }
    
    private static final String salidaEsperada = "\n"
            + "¿Es MONDAY hábil? 	Si\n"
            + "¿Es TUESDAY hábil? 	Si\n"
            + "¿Es WEDNESDAY hábil? 	Si\n"
            + "¿Es THURSDAY hábil? 	Si\n"
            + "¿Es FRIDAY hábil? 	Si\n"
            + "¿Es SATURDAY hábil? 	No\n"
            + "¿Es SUNDAY hábil? 	No";
}
