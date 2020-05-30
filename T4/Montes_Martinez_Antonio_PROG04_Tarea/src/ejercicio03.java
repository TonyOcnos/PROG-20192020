/*Bloque para importar las librerías necesarias que nos permitirán: manejar excepciones para los tipos de datos de este ejercicio, introducir datos y la utilización de expresiones regulares*/
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase para convertir a decimal números octales y hexadecimales, así como el paso a int de la cadena en caso de ser decimal
 * @author martiant
 */
public class ejercicio03 {
    public static void main (String[] args){
        
        /*Creación y declaración de la variable que nos permitirá contar el número de errores introducidos*/
        int errores = 0;
       
        /*Creación de las variables para el manejo de texto*/
        String cadena = "";
        StringBuilder resultado = new StringBuilder("[");
        
        /*Creación de la variable para la entrada de texto*/
        Scanner teclado = new Scanner(System.in);
        
        /*Creación de los patrones para saber qué tipo de dato hemos introducido*/
        Pattern octal = Pattern.compile("(^0)[0-7]+$");
        Pattern hexa = Pattern.compile("(^0[x])[0123456789abcdef]+$");
        Pattern decimal = Pattern.compile("^[1-9]+$");
        
        System.out.println("Introduce un número octal, hexadecimal o decimal (fin o end para terminar): ");
        cadena = teclado.nextLine();
        
        /*Para evitar problemas, lo mejor es o bien pasar todo a mayúsculas o bien a minúsculas*/
        cadena = cadena.toLowerCase();
        
        /*Comprobamos que el texto introducido no es FIN, el cual pone fin al programa*/
        while (!cadena.equals("fin")){
            /*Volvemos a poner el texto en minúscula en caso de que hayamos cometido un error y se nos vuelva a solicitar texto*/
            cadena = cadena.toLowerCase();
            /*Ahora creamos las variables para posteriormente realizar la comprobación de nuestras expresiones regulares*/
            Matcher m1 = octal.matcher(cadena);
            Matcher m2 = hexa.matcher(cadena);
            Matcher m3 = decimal.matcher(cadena);
            /*Bloques de control para los diferentes patrones, por lo tanto, para los diferentes tipos de datos*/
            if (m1.matches()){
                resultado.append("O(").append(cadena).append("->").append(Integer.parseInt(cadena,8)).append(")");
                System.out.println("Introduce el siguiente número octal, hexadecimal o decimal (fin o end para terminar): ");
                cadena = teclado.nextLine();
            }
            else if (m2.matches()) {
                /*Aquí deberemos de extraer la cabecera 0x o 0X del numero introducido*/
                resultado.append("H(").append(cadena).append("->").append(Integer.parseInt(cadena.substring(2, cadena.length()),16)).append(")");
                System.out.println("Introduce el siguiente número octal, hexadecimal o decimal (fin o end para terminar): ");
                cadena = teclado.nextLine();
            }
            else if (m3.matches()) {
                resultado.append("D(").append(cadena).append("->").append(Integer.valueOf(cadena)).append(")");
                System.out.println("Introduce el siguiente número octal, hexadecimal o decimal (fin o end para terminar): ");
                try  {
                    cadena = teclado.nextLine();
                }
                catch (InputMismatchException ex1) {
                    System.out.println("ERROR: número no válido.");
                    errores += 1;
                }
            }
           
            /*En caso de que ninguna expresión regular y su comprobación den TRUE, aquí lanzaremos un mensaje de error*/
            else {
                System.out.println("ERROR: número no válido.");
                errores += 1;
                System.out.println("Introduce el siguiente número octal, hexadecimal o decimal (fin o end para terminar): ");
                cadena = teclado.nextLine();
            }
        }
        
        /*Impresión del resultado final, en caso de los errores, de manera formateada como decimal*/
        resultado.append("]");
        System.out.println(resultado);
        System.out.printf("Errores: /%d/", errores);
        System.out.println("");
        
    }
}