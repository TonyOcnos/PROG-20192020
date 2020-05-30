/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaut8.pasos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Collectors;
import tareaut8.Agenda;


/**
 * Paso 3. En este paso se implementa el método noLaborable y se prueba.
 * Para ello se espera que se haya implementado buildSampleAgenda.
 * 
 * @author Salvador Romero
 */
public class TareaUT8Paso3 {

   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] fechasAcomprobar = {"2019-09-02", "2019-09-04", "2019-10-31",
            "2019-11-01", "2019-11-02", "2019-11-03", "2019-11-04",
            "2019-11-05", "2019-12-04", "2019-12-05", "2019-12-06",
            "2019-12-07", "2019-12-09", "2019-12-10", "2019-12-20",
            "2019-12-21", "2019-12-28", "2020-01-07", "2020-01-08",
            "2020-01-15", "2020-04-03", "2020-04-04", "2020-04-09",
            "2020-04-12", "2020-04-13", "2020-04-25"};
        
        System.out.println("==========================");
        System.out.println("   - Tarea 8 Paso 3 -");
        System.out.println("==========================");
        System.out.println("Salida esperada:");
        System.out.println("**********************************\n");
        System.out.println(salidaEsperada);
        System.out.println("\n\nSalida para tu implementación:");
        System.out.println("**********************************\n");
        
        Agenda a=Agenda.buildSampleAgenda();
        
        for (int i=0;i<fechasAcomprobar.length;i++){
            LocalDate fechaAComprobar=LocalDate.parse(fechasAcomprobar[i]);
            String v=a.noLaborable(fechaAComprobar).stream().collect(Collectors.joining(" y "));
            if (v.length()>0)
                System.out.println(fechaAComprobar + " cae en " + v+".");
            else 
                System.out.println(fechaAComprobar + " no cae en no laborable.");
        }
    }
    
     private static final String salidaEsperada = "2019-09-02 no cae en no laborable.\n"
            + "2019-09-04 no cae en no laborable.\n"
            + "2019-10-31 no cae en no laborable.\n"
            + "2019-11-01 cae en PUENTENOV.\n"
            + "2019-11-02 cae en PUENTENOV.\n"
            + "2019-11-03 cae en MERECIDASVACACIONES y PUENTENOV.\n"
            + "2019-11-04 cae en MERECIDASVACACIONES y PUENTENOV.\n"
            + "2019-11-05 cae en MERECIDASVACACIONES.\n"
            + "2019-12-04 cae en MERECIDASVACACIONES.\n"
            + "2019-12-05 cae en PUENTEDIC y MERECIDASVACACIONES.\n"
            + "2019-12-06 cae en PUENTEDIC y MERECIDASVACACIONES.\n"
            + "2019-12-07 cae en PUENTEDIC y MERECIDASVACACIONES.\n"
            + "2019-12-09 cae en PUENTEDIC.\n"
            + "2019-12-10 no cae en no laborable.\n"
            + "2019-12-20 no cae en no laborable.\n"
            + "2019-12-21 cae en NAVIDADES.\n"
            + "2019-12-28 cae en NAVIDADES.\n"
            + "2020-01-07 cae en NAVIDADES.\n"
            + "2020-01-08 no cae en no laborable.\n"
            + "2020-01-15 no cae en no laborable.\n"
            + "2020-04-03 no cae en no laborable.\n"
            + "2020-04-04 cae en SEMANASANTA.\n"
            + "2020-04-09 cae en SEMANASANTA.\n"
            + "2020-04-12 cae en SEMANASANTA.\n"
            + "2020-04-13 no cae en no laborable.\n"
            + "2020-04-25 no cae en no laborable.";
}
