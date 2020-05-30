/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaut8.pasos;

import tareaut8.Agenda;
import tareaut8.Cliente;
import tareaut8.Persona;


/**
 * Paso 4. En este paso se implementa y usa el método "buscarCitaDeCliente"
 * donde se buscarán las citas ficticias de la agenda creada con "buildSampleAgenda".
 * 
 * @author Salvador Romero
 */
public class TareaUT8Paso4 {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        System.out.println("==========================");
        System.out.println("   - Tarea 8 Paso 4 -");
        System.out.println("==========================");
        System.out.println("Salida esperada:");
        System.out.println("**********************************\n");
        System.out.println(salidaEsperada);
        System.out.println("\n\nSalida para tu implementación:");
        System.out.println("**********************************\n");
        Agenda a=Agenda.buildSampleAgenda();
        Cliente[] clientes={new Persona(8888, "ALEXA"),new Persona(8889, "ROBERT")};
        
        System.out.println("Intentando buscar la cita de un cliente a través del ID:");
        System.out.println("--------------------------------------------------------");
        System.out.println(a.buscarCitaDeCliente(clientes[0].getId()));
        System.out.println();
        
        System.out.println("Intentando buscar la cita de un cliente a través de la instancia de cliente:");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println(a.buscarCitaDeCliente(clientes[1]));
        System.out.println();
        
        System.out.println("Intentando buscar la cita de un cliente sin cita usando instancia de cliente");
        System.out.println("----------------------------------------------------------------------------");
        
        System.out.println(a.buscarCitaDeCliente(new Persona(9999,"TEST")));
        System.out.println();
        
        System.out.println("Intentando buscar la cita de un cliente sin cita usando ID de cliente");
        System.out.println("---------------------------------------------------------------------");
        System.out.println(a.buscarCitaDeCliente(938484));
        System.out.println();

        try {
            System.out.println("Intentando buscar la cita de un cliente \"null\": ");
            System.out.println("-------------------------------------------------");
            a.buscarCitaDeCliente(null);
        } 
        catch (IllegalArgumentException e)
        {
            System.out.println("Mensaje de la excepción: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("¡¡ERROR!! No se ha generado la excepción adecuada.");
        }
    }
    
    private static final String salidaEsperada = "\n" +
"Intentando buscar la cita de un cliente a través del ID:\n" +
"--------------------------------------------------------\n" +
"Cliente .............: {ID=8888;NOMBRE=ALEXA}\n" +
"Día de la cita ......: 19/02/2019\n" +
"Hora de la cita .....: 20:00\n" +
"Descripción .........: Arreglar frigorífico.\n" +
"\n" +
"\n" +
"Intentando buscar la cita de un cliente a través de la instancia de cliente:\n" +
"----------------------------------------------------------------------------\n" +
"Cliente .............: {ID=8889;NOMBRE=ROBERT}\n" +
"Día de la cita ......: 19/02/2019\n" +
"Hora de la cita .....: 18:00\n" +
"Descripción .........: Arreglar caldera.\n" +
"\n" +
"\n" +
"Intentando buscar la cita de un cliente sin cita usando instancia de cliente\n" +
"----------------------------------------------------------------------------\n" +
"null\n" +
"\n" +
"Intentando buscar la cita de un cliente sin cita usando ID de cliente\n" +
"---------------------------------------------------------------------\n" +
"null\n" +
"\n" +
"Intentando buscar la cita de un cliente \"null\": \n" +
"-------------------------------------------------\n" +
"Mensaje de la excepción: El cliente a buscar no puede ser null.";
}
