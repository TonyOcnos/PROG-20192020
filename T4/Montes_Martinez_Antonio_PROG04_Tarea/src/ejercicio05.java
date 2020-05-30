/*Bloque para importar librerías que nos permitirán: introducir datos mediante teclado y trabajar con expresiones regulares*/
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase que nos permitirá la creación de arrays uni y multidimensionales y el recorrido de estos para un determinado formato por pantalla
 * @author martiant
 */
public class ejercicio05 {
    public static void main (String[] args){
        /*Creación y asignación de variables de introducción de datos mediante teclado, el booleano de control para el bucle WHILE y de la cadena con la que vamos a trabajar*/
        Scanner teclado = new Scanner(System.in);
        String cadena = "";
        boolean comprob = false;
        /*Creación y asignación de variable para la longitud mínima de las diferentes palabras que compondrán la cadena*/
        int longMin = 0;
        /*Creación del patrón para saber que es un texto de al menos una letra entre asteriscos*/
        Pattern regEx = Pattern.compile("^([*])([aA-zZ\\s]+)([*])$");
        
        while (!comprob) {
            /*Solicitud de la cadena. Esta debe de ser introducida entre asteríscos y como mínimo con una letra*/
            System.out.println("Introduzca un texto entre asteriscos: ");
            System.out.print("Texto: ");
            cadena = teclado.nextLine();
            Matcher matcher1 = regEx.matcher(cadena);
            /*Condicional para comprobar la expresión regular*/
            if (matcher1.matches()){
                /*Con este tipo de asignación en la creación de un array de String, podemos directamente asignar todas las palabras de una cadena separadas por un espacio*/
                String[] arrayPalabras = cadena.split("\\s+");
                /*En las siguientes dos líneas, mediante el método substring eliminaremos el asterisco del principio y el asterisco del final*/
                arrayPalabras[0] = arrayPalabras[0].substring(1, arrayPalabras[0].length());
                arrayPalabras[arrayPalabras.length-1] = arrayPalabras[arrayPalabras.length-1].substring(0, arrayPalabras[arrayPalabras.length-1].length()-1);
                /*Aquí, como referencia, asignamos la longitud de la primera palabra a nuestra variable*/
                longMin = arrayPalabras[0].length();
                
                /*Bucle FOR para comprobar qué palabra es la más corta partiendo desde la primera posición del array de String*/
                for (int i=0;i<arrayPalabras.length;i++){
                    if (longMin > arrayPalabras[i].length()){
                        longMin = arrayPalabras[i].length();
                    }
                }
                
                /*Creación y asignación de longitud de un array CHAR bidimensional, con una longitud de X=el número de palabras e Y=longMin+1*/
                char[][] arrayBi = new char[arrayPalabras.length][longMin+1];
                
                /*Bucle FOR para la asignación de las diferentes posiciones del segundo eje del array bidimensional*/
                for(int i=0;i<arrayPalabras.length;i++){
                    for(int j=0;j<longMin;j++){
                        /*Iremos asignando los caracteres por separado al segundo eje del array bidimensional*/
                        arrayBi[i][j] = arrayPalabras[i].charAt(j);
                    }
                }
                
                /*Bucle FOR para imprimir por columnas las diferentes posiciones de nuestro array bidimensional*/
                for(int i=0;i<longMin;i++){
                    for(int j=0;j<arrayPalabras.length;j++){
                        System.out.print(arrayBi[j][i]);
                    }
                    System.out.println("");
                }
                comprob = true;
            }
            /*En caso de haber introducido una cadena con formato inválido, obtendremos este mensaje*/
            else {
                System.out.println("- El texto debe estar entre asteriscos.");
                System.out.println("- El texto debe tener al menos un carácter.");
                System.out.println("ERROR. Cadena no válida.");
            }
        }
    }
}