/*En este bloque importaremos las librerías necesarias para: manejo de excepciones con formatos de fechas y tiempos, el uso de LocalDate y métodos para sumar en fechas (Period) y la introducción de datos*/
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase que nos permite la creación de una fecha mediante solicitudes a través de teclado y nos permite posicionar dicha fecha dentro de un array con más fechas
 * @author martiant
 */
public class ejercicio04 {
    public static void main (String[] args){
        
        /*Creación y declaración de variables que nos permitirán guardar los datos introducidos por teclado: año, día y mes*/
        int numMes = 0, anho = 0, mes = 0, dia = 0;
       
        /*Creación y declaración de variables de control para los bucles WHILE y para la detección en la comparación de fechas*/
        boolean flag = true, comprob = false;
        
        /*Creación de las variables LocalDate: la fecha inicial sobre la que iremos sumando meses y una variable de referencia para evitar fallos de VARIABLE QUIZÁS NO INICIADA*/
        LocalDate fechaInicial = LocalDate.of(2019, 9, 1);
        LocalDate fechaNueva = LocalDate.of(2019, 05, 05);
        
        /*Primer bloque WHILE para comprobar que el número mínimo de meses a mostrar es mayor a 5*/
        while (!comprob){
            System.out.println("Introduce el número de fechas (mayor o igual a 5): ");
            Scanner teclado = new Scanner(System.in);
            try {
                numMes = teclado.nextInt();
                /*Creación de excepción personalizada*/
                if (numMes<5){
                    throw new IllegalArgumentException("¡Por favor, introduzca un número mayor o igual a 5!");
                }
                comprob = true;
            }
            catch (NumberFormatException | InputMismatchException ex1) {
                System.out.println("Por favor, introduzca un formato de año válido");
            }
            catch (IllegalArgumentException ex3) {
                System.out.println(ex3.getMessage());
            }
        }
        
        /*Creación del array que contendrá el número de fechas introduciendo anteriormente*/
        LocalDate[] arrayFechas = new LocalDate[numMes];
        /*Asignación de fechaInicial a la primera posición de nuestro array*/
        arrayFechas[0] = fechaInicial;
        
        /*Asignación del resto de posiciones del array mediante un bucle y el método de objeto LocalDate.plus(Period.ofMonths(x). Sumaremos tantos meses como posiciones tengamos en el array*/
        for(int i=0;i<numMes;i++){
            arrayFechas[i] = fechaInicial.plus(Period.ofMonths(i));
        }
        
        comprob = false;
        
        /*Siguiente bloque para la comprobación del formato de año introducido*/
        while(!comprob){
            while (!comprob){
                System.out.println("Introduzca el año: ");
                Scanner teclado = new Scanner(System.in);
                try {
                    anho = teclado.nextInt();
                    comprob = true;
                }
                catch (NumberFormatException | InputMismatchException ex1) {
                    System.out.println("Por favor, introduzca un formato de año válido");
                }
            }

            comprob = false;

            while (!comprob){
                System.out.println("Introduzca el mes: ");
                Scanner teclado = new Scanner(System.in);
                try {
                    mes = teclado.nextInt();
                    comprob = true;
                }
                catch (NumberFormatException | InputMismatchException ex1) {
                    System.out.println("Por favor, introduzca un formato de mes válido");
                }
            }

            comprob = false;

            while (!comprob){
                System.out.println("Introduzca el día: ");
                Scanner teclado = new Scanner(System.in);
                try {
                    dia = teclado.nextInt();
                    comprob = true;
                }
                catch (NumberFormatException | InputMismatchException ex1) {
                    System.out.println("Por favor, introduzca un formato de día válido");
                }
            }
            
            /*Para evitar crear una exepción personalizada para las 3 entradas anteriores, es mejor capturar un excepción general en la asignación al LocalDate creado anteriormente*/
            try {
                fechaNueva = LocalDate.of(anho, mes, dia);
                comprob = true;
            }
            catch (DateTimeException ex2) {
                    comprob = false;
                    System.err.println("Por favor, esa fecha no es posible. Pruebe con otra válida");
            }
        }
        
        /*En caso de que la fecha creada es anterior a la primera posición del array, esta solapará dicha posición*/
        if (fechaNueva.isBefore(arrayFechas[0])){
            arrayFechas[0] = fechaNueva;
        }
        /*En caso de que es posterior a la última fecha de la posición del array, esta solapará dicha posición*/
        else if (fechaNueva.isAfter(arrayFechas[numMes-1])){
            arrayFechas[numMes-1] = fechaNueva;
        }
        /*Y si la fecha introducida se encuentra entre la primera y la última, deberemos de saber la inmediata posterior a ésta*/
        else {
            /*Para ello, se crea un bucle FOR para encontrar la primera fecha que es posterior a la creada*/
            for (int i=0;i<numMes;i++){
                /*En caso de encontrarse, asignaremos al boolean FLAG el valor FALSE para no volver a entrar en el IF y no incurrir en fechas posteriores*/
                if (fechaNueva.isBefore(arrayFechas[i]) && flag){
                    arrayFechas[i-1] = fechaNueva;
                    flag = false;
                }
            }
        }
        
        /*Para la impresión por pantalla, se ha empleado printf e imprimir así el valor con formato STRING*/
        for (int i=0;i<numMes;i++){
            System.out.printf("%s ", arrayFechas[i]);
        }
    }
}