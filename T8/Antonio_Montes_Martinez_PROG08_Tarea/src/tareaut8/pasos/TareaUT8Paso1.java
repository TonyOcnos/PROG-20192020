/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaut8.pasos;

import tareaut8.Agenda;

/**
 * Paso 1. buildSimpleAgenda y el método toString.
 * En este paso se comprueba si la Agenda dispone del método buildSimpleAgenda.
 * Será necesario que se disponga del constructor.
 * 
 * @author Salvador Romero
 */
public class TareaUT8Paso1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("==========================");
        System.out.println("    - Tarea 8 Paso 1 -");
        System.out.println("==========================");
        System.out.println("Salida esperada:");
        System.out.println("**********************************\n");
        System.out.println(salidaEsperada);
        System.out.println("\n\nSalida para tu implementación:");
        System.out.println("**********************************\n");
        Agenda a=Agenda.buildSampleAgenda();
        System.out.println(a.toString());
    }
    
    private static final String salidaEsperada = "AGENDA: SAMPLE\n"
            + "Citas máximas por día: 10\n"
            + "Días no hábiles: [SATURDAY, SUNDAY]\n"
            + "Rango de fechas no laborables:\n"
            + "..............................\n"
            + "SEMANASANTA : 04/04/2020 - 12/04/2020\n"
                + "PUENTEDIC : 05/12/2019 - 09/12/2019\n"
            + "MERECIDASVACACIONES : 03/11/2019 - 07/12/2019\n"
            + "NAVIDADES : 21/12/2019 - 07/01/2020\n"
            + "PUENTENOV : 01/11/2019 - 04/11/2019";
}
