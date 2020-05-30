/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author martiant
 */
public class Ej7 {
    
    public static void main (String[] args){
        
        double x, y;
        x = 3;
        y = 7;
        /*Aquí se hace uso de la concatenación de Strings y variables*/
        System.out.println("Primera variable: " + x + "\nSegunda variable: " + y);
        System.out.println("Primera operación: (" + x + " + 2) * 3 = " + (x+2)*3);
        System.out.println("Segunda operación: ((" + y + " / 7) * 4) + " + "(" + x + " * 2) = " + ((x*2)+(y/7)*4));
        System.out.println("Tercera operación: (" + x + " + 5) * (" + y + " / 14) = " + (x+5)*y/14);
    }
}
