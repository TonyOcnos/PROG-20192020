
package tareaut8.pasos;

import tareaut8.Agenda;
import tareaut8.Cliente;
import tareaut8.Persona;


/**
 * Paso 5. En este paso se implementa y usa el método "borrarCita"
 * donde se borrarán las citas ficticias de la agenda creada
 * con "buildSampleAgenda".
 * 
 * @author Salvador Romero
 */
public class TareaUT8Paso5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        System.out.println("==========================");
        System.out.println("   - Tarea 8 Paso 5 -");
        System.out.println("==========================");
        System.out.println("Salida esperada:");
        System.out.println("**********************************\n");
        System.out.println(salidaEsperada);
        System.out.println("\n\nSalida para tu implementación:");
        System.out.println("**********************************\n");
        Agenda a=Agenda.buildSampleAgenda();
        Cliente[] clientes={new Persona(8888, "ALEXA"),new Persona(8889, "ROBERT")};
        
        System.out.println("Borrando la cita ficticia de ALEXA y mostrandola:");
        System.out.println("-------------------------------------------------");
        System.out.println(a.borrarCitaCliente(clientes[0].getId()));
        System.out.println();
        
        System.out.println("Borrando la cita ficticia de ROBERT y mostrandola:");
        System.out.println("-------------------------------------------------");
        System.out.println(a.borrarCitaCliente(clientes[1]));
        System.out.println();
        
        System.out.println("Borrando la cita ficticia de ALEXA por segunda vez:");
        System.out.println("--------------------------------------------------");
        System.out.println(a.borrarCitaCliente(clientes[0].getId()));
        System.out.println();
        
        System.out.println("Borrando la cita ficticia de ROBERT por segunda vez:");
        System.out.println("---------------------------------------------------");
        System.out.println(a.borrarCitaCliente(clientes[1]));
        System.out.println();
        
        try {
            System.out.println("Intentando borrar la cita de un cliente \"null\": ");
            System.out.println("-------------------------------------------------");

            a.borrarCitaCliente(null);
        } 
        catch (IllegalArgumentException e)
        {
            System.out.println("Mensaje de la excepción: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("No se ha generado la excepción adecuada.");
        }
    }
     private static final String salidaEsperada = "Borrando la cita ficticia de ALEXA y mostrandola:\n" +
"-------------------------------------------------\n" +
"Cliente .............: {ID=8888;NOMBRE=ALEXA}\n" +
"Día de la cita ......: 19/02/2019\n" +
"Hora de la cita .....: 20:00\n" +
"Descripción .........: Arreglar frigorífico.\n" +
"\n" +
"\n" +
"Borrando la cita ficticia de ROBERT y mostrandola:\n" +
"-------------------------------------------------\n" +
"Cliente .............: {ID=8889;NOMBRE=ROBERT}\n" +
"Día de la cita ......: 19/02/2019\n" +
"Hora de la cita .....: 18:00\n" +
"Descripción .........: Arreglar caldera.\n" +
"\n" +
"\n" +
"Borrando la cita ficticia de ALEXA por segunda vez:\n" +
"--------------------------------------------------\n" +
"null\n" +
"\n" +
"Borrando la cita ficticia de ROBERT por segunda vez:\n" +
"---------------------------------------------------\n" +
"null\n" +
"\n" +
"Intentando borrar la cita de un cliente \"null\": \n" +
"-------------------------------------------------\n" +
"Mensaje de la excepción: El cliente no puede ser null al eliminar la cita.";
}
