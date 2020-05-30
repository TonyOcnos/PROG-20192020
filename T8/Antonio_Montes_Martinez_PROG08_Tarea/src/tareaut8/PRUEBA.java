/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaut8;

import java.time.DayOfWeek;

/**
 *
 * @author martiant
 */
public class PRUEBA {
    
    public static void main(String args[]){
        
        DayOfWeek[] dayOfWeeks = DayOfWeek.values();
        for (int i = 0; i < dayOfWeeks.length; i++) {
            DayOfWeek dayOfWeek = dayOfWeeks[i];
            System.out.println("dayOfWeek[" + i + "] = " + dayOfWeek + "; value = " + dayOfWeek.getValue());
        }
        DayOfWeek dayOfWeek = null;
        dayOfWeek = DayOfWeek.valueOf("SATURDAY");
        System.out.println("dayOfWeek = " + dayOfWeek);
    }
}
