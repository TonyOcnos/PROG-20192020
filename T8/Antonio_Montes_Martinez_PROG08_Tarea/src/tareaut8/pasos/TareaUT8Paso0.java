/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaut8.pasos;

import tareaut8.Agenda;

/**
 * Paso 0. Constructor.
 * En este paso se comprueba si la Agenda dispone del constructor.
 * 
 * @author Salvador Romero
 */
public class TareaUT8Paso0 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("==========================");
        System.out.println("    - Tarea 8 Paso 0 -");
        System.out.println("==========================");
        System.out.println("Salida esperada:");
        System.out.println("**********************************\n");
        System.out.println(salidaEsperada);
        System.out.println("\n\nSalida para tu implementación:");
        System.out.println("**********************************\n");
                        
        try {
            System.out.println("Intentando crear agenda con citas por día de más:");
            Agenda a=new Agenda(null, Agenda.MAX_CITAS_DIA_CUALQUIER_AGENDA+1,null, null);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("\t>"+e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println("ERROR, excepción no esperada:"+e.getMessage());
        }
        
        try {
            System.out.println("Intentando crear agenda con citas por día de menos:");
            Agenda a=new Agenda(null, Agenda.MIN_CITAS_DIA_CUALQUIER_AGENDA-1,null, null);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("\t>"+e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println("ERROR, excepción no esperada:"+e.getMessage());
        }
        
        try {
            System.out.println("Intentando crear agenda con 10 citas por día:");
            Agenda a=new Agenda(null, 10 ,null, null);
            System.out.println("\t>OK");
        }
        catch (Exception e)
        {
            System.err.println("ERROR, excepción no esperada:"+e.getMessage());
        }
    }
    
    private static final String salidaEsperada =
            "Intentando crear agenda con citas por día de más:\n" +
"	>No puede haber menos de 5, ni más de 40, citas al día. En tu caso se han indicado: 41.\n" +
"Intentando crear agenda con citas por día de menos:\n" +
"	>No puede haber menos de 5, ni más de 40, citas al día. En tu caso se han indicado: 4.\n" +
"Intentando crear agenda con 10 citas por día:\n" +
"	>OK";
}
