/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaut8.pasos;

import java.time.LocalDate;
import tareaut8.Agenda;
import tareaut8.Cliente;
import tareaut8.Persona;


/**
 * Paso 6. En este paso se implementa y se usa el método obtenerCitasFecha.
 * 
 * @author Salvador Romero
 */
public class TareaUT8Paso6 {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        System.out.println("==========================");
        System.out.println("   - Tarea 8 Paso 6 -");
        System.out.println("==========================");
        System.out.println("Salida esperada:");
        System.out.println("**********************************\n");
        System.out.println(salidaEsperada);
        System.out.println("\n\nSalida para tu implementación:");
        System.out.println("**********************************\n");
        Agenda a=Agenda.buildSampleAgenda();
        
        System.out.println("Obteniendo lista de citas del día desordenadas:");
        System.out.println("---------------------------------------------------");
        System.out.println(a.obtenerCitasFecha(LocalDate.parse("2019-02-19"),false));
        System.out.println();
        
        System.out.println("Obteniendo lista de citas del día ordenadas:");
        System.out.println("---------------------------------------------------");
        System.out.println(a.obtenerCitasFecha(LocalDate.parse("2019-02-19"),true));
        System.out.println();
        
    }
    
    private static final String salidaEsperada = "Obteniendo lista de citas del día desordenadas:\n" +
"---------------------------------------------------\n" +
"[Cliente .............: {ID=8888;NOMBRE=ALEXA}\n" +
"Día de la cita ......: 19/02/2019\n" +
"Hora de la cita .....: 20:00\n" +
"Descripción .........: Arreglar frigorífico.\n" +
", Cliente .............: {ID=8889;NOMBRE=ROBERT}\n" +
"Día de la cita ......: 19/02/2019\n" +
"Hora de la cita .....: 18:00\n" +
"Descripción .........: Arreglar caldera.\n" +
", Cliente .............: {ID=8884;NOMBRE=CHARLIE}\n" +
"Día de la cita ......: 19/02/2019\n" +
"Hora de la cita .....: 17:00\n" +
"Descripción .........: Arreglar lavadora.\n" +
", Cliente .............: {ID=8883;NOMBRE=ANNE}\n" +
"Día de la cita ......: 19/02/2019\n" +
"Hora de la cita .....: 21:00\n" +
"Descripción .........: Arreglar secador.\n" +
"]\n" +
"\n" +
"Obteniendo lista de citas del día ordenadas:\n" +
"---------------------------------------------------\n" +
"[Cliente .............: {ID=8884;NOMBRE=CHARLIE}\n" +
"Día de la cita ......: 19/02/2019\n" +
"Hora de la cita .....: 17:00\n" +
"Descripción .........: Arreglar lavadora.\n" +
", Cliente .............: {ID=8889;NOMBRE=ROBERT}\n" +
"Día de la cita ......: 19/02/2019\n" +
"Hora de la cita .....: 18:00\n" +
"Descripción .........: Arreglar caldera.\n" +
", Cliente .............: {ID=8888;NOMBRE=ALEXA}\n" +
"Día de la cita ......: 19/02/2019\n" +
"Hora de la cita .....: 20:00\n" +
"Descripción .........: Arreglar frigorífico.\n" +
", Cliente .............: {ID=8883;NOMBRE=ANNE}\n" +
"Día de la cita ......: 19/02/2019\n" +
"Hora de la cita .....: 21:00\n" +
"Descripción .........: Arreglar secador.\n" +
"]";
}
