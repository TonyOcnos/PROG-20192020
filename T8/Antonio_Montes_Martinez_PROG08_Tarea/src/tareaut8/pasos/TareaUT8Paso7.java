/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaut8.pasos;

import java.time.LocalDateTime;
import tareaut8.Agenda;
import tareaut8.Cita;
import tareaut8.Cliente;
import tareaut8.IllegalAgendaStateException;
import tareaut8.Persona;


/**
 * Paso 7. En este paso se implementa y prueba el método agregarCita.
 * Se prueba a agregar citas para días no hábiles, días no laborables,
 * citas "null", etc.
 * 
 * @author Salvador Romero
 */
public class TareaUT8Paso7 {

   
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
        Cliente cliente=new Persona(8810, "PATRICK");      
        
        try
        {
            System.out.printf("Intentando añadir una cita 'null'\n");
            System.out.println("-----------------------------------------------");
            Cita c=null;
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agrego la cita (y no debería haberse agregado)");
        } catch (IllegalArgumentException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
        }  catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }   
        
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-03-14T16:00:00");
            System.out.printf("Intentando añadir una cita en día no hábil (%s)\n",fecha.getDayOfWeek().name());
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agrego la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        }  catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-03-15T16:00:00");
            System.out.printf("Intentando añadir una cita en día no hábil (%s)\n",fecha.getDayOfWeek().name());
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agrego la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        }  catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();

        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2019-11-04T16:00:00");
            System.out.printf("Intentando añadir una cita en día no laborable (%s)\n",fecha.getDayOfWeek().name());
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agrego la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        }  catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
         
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2019-12-05T16:00:00");
            System.out.printf("Intentando añadir una cita en día no laborable (%s)\n",fecha.getDayOfWeek().name());
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agrego la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2019-12-27T16:00:00");
            System.out.printf("Intentando añadir una cita en día no laborable (%s)\n",fecha.getDayOfWeek().name());
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agrego la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-04-08T16:00:00");
            System.out.printf("Intentando añadir una cita en día no laborable (%s)\n",fecha.getDayOfWeek().name());
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agrego la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-04-08T16:00:00");
            System.out.printf("Intentando añadir una cita en día no laborable (%s)\n",fecha.getDayOfWeek().name());
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agrego la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-03-12T16:00:00");
            System.out.printf("Intentando añadir una cita en día no laborable (%s)\n",fecha.getDayOfWeek().name());
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡CORRECTO!! Se agrego la cita.");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    + "IllegalAgendaStateException cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-03-12T16:00:00");
            System.out.printf("Intentando añadir una cita en día no laborable (%s)\n",fecha.getDayOfWeek().name());
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agrego la cita, pero el cliente ya tenía cita.");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción: "+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();

        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-03-12T16:00:00");
            System.out.printf("Intentando añadir más de 10 citas una cita en día no laborable (%s)\n",fecha.getDayOfWeek().name());
            System.out.println("-----------------------------------------------");
            for (int i=0;i<12;i++)
            {
                final int j=i+9999;
                Cita c=new Cita(()->(long)j,fecha.plusMinutes(i),"Cortar cables.");
                a.agregarCita(c);
            }
            System.out.println("¡¡ERROR!! Se agregaron más de 10 citas en un mísmo día en la agenda de ejemplo.");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción: "+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
    }
    
     private static final String salidaEsperada = "Intentando añadir una cita 'null'\n" +
"-----------------------------------------------\n" +
"Texto excepción:La cita no puede ser null.\n" +
"Intentando añadir una cita en día no hábil (SATURDAY)\n" +
"-----------------------------------------------\n" +
"Texto excepción:La cita no se puede crear porque no es un día hábil: SATURDAY\n" +
"Motivo excepción: DIA_NO_DISPONIBLE\n" +
"\n" +
"Intentando añadir una cita en día no hábil (SUNDAY)\n" +
"-----------------------------------------------\n" +
"Texto excepción:La cita no se puede crear porque no es un día hábil: SUNDAY\n" +
"Motivo excepción: DIA_NO_DISPONIBLE\n" +
"\n" +
"Intentando añadir una cita en día no laborable (MONDAY)\n" +
"-----------------------------------------------\n" +
"Texto excepción:La cita no se puede crear porque coincide con MERECIDASVACACIONES PUENTENOV\n" +
"Motivo excepción: DIA_NO_DISPONIBLE\n" +
"\n" +
"Intentando añadir una cita en día no laborable (THURSDAY)\n" +
"-----------------------------------------------\n" +
"Texto excepción:La cita no se puede crear porque coincide con PUENTEDIC MERECIDASVACACIONES\n" +
"Motivo excepción: DIA_NO_DISPONIBLE\n" +
"\n" +
"Intentando añadir una cita en día no laborable (FRIDAY)\n" +
"-----------------------------------------------\n" +
"Texto excepción:La cita no se puede crear porque coincide con NAVIDADES\n" +
"Motivo excepción: DIA_NO_DISPONIBLE\n" +
"\n" +
"Intentando añadir una cita en día no laborable (WEDNESDAY)\n" +
"-----------------------------------------------\n" +
"Texto excepción:La cita no se puede crear porque coincide con SEMANASANTA\n" +
"Motivo excepción: DIA_NO_DISPONIBLE\n" +
"\n" +
"Intentando añadir una cita en día no laborable (WEDNESDAY)\n" +
"-----------------------------------------------\n" +
"Texto excepción:La cita no se puede crear porque coincide con SEMANASANTA\n" +
"Motivo excepción: DIA_NO_DISPONIBLE\n" +
"\n" +
"Intentando añadir una cita en día no laborable (THURSDAY)\n" +
"-----------------------------------------------\n" +
"¡¡CORRECTO!! Se agrego la cita.\n" +
"\n" +
"Intentando añadir una cita en día no laborable (THURSDAY)\n" +
"-----------------------------------------------\n" +
"Texto excepción:La cita no se puede crear porque dicho cliente ya tiene una cita\n" +
"Motivo excepción: YA_EXISTE_CITA\n" +
"\n" +
"Intentando añadir más de 10 citas una cita en día no laborable (THURSDAY)\n" +
"-----------------------------------------------\n" +
"Texto excepción: Numero de citas máximas por día superado.\n" +
"Motivo excepción: CITAS_MAXIMAS_SUPERADAS";
    
}
