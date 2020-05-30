/* Bloque para importar la librería que nos permitirá solicitar una entrada de datos mediante teclado. */
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase para pasar números de OCTAL a BINARIO mediante el uso de StringBuilder y reemplazo de caracteres.
 * @author martiant
 */
public class ejercicio02 {
    public static void main (String[] args){
        
        /*Creación y declaración de variables: boolean para la comprobación del bucle WHILE y un boolean para comprobar la entrada de datos*/
        boolean comprob = false;
        boolean test;
        
        /*Creación y declaración de variables: la variable tipo Scanner para la entradad de datos y la String que contendrá el resultado*/
        Scanner teclado = new Scanner(System.in);
        String cadena = "";
        
        /*Bloque WHILE que nos permitirá el control de la cadena introducida, repitiéndose hasta que ésta no sea correcta*/
        while(!comprob){
            
            test = true;
            
            System.out.println("Introduzca cadena:");
            cadena = teclado.nextLine();
            
            /*Mediante replaceAll podemos quitar los espacios que existan en la cadena (se ha de usar doble \\ para escapar el caracter \*/
            cadena = cadena.replaceAll("\\s+","");
            
            /*Bucle FOR para recorrer la cadena caracter a caracter*/
            for(int i=0;i<cadena.length();i++){
                /*Mediante este SWITCH (que también podría usarse IF) comprobamos todos los caracteres introducidos, y de no ser uno de ellos correcto, TEST pasara a ser FALSE y deberá
                introducirse de nuevo la cadena*/
                switch (cadena.charAt(i)){
                    case '0':
                        break;
                    case '1':
                        break;
                    case '2':
                        break;
                    case '3':
                        break;
                    case '4':
                        break;
                    case '5':
                        break;
                    case '6':
                        break;
                    case '7':
                        break;
                    default:
                        test = false;
                }
            }
            
            /*De haber pasado el test todos los caracteres, pasamos a reemplazarlos todos pro su equivalente binario*/
            if (test) {
                cadena = cadena.replaceAll("0", "000");
                cadena = cadena.replaceAll("1", "001");
                cadena = cadena.replaceAll("2", "010");
                cadena = cadena.replaceAll("3", "011");
                cadena = cadena.replaceAll("4", "100");
                cadena = cadena.replaceAll("5", "101");
                cadena = cadena.replaceAll("6", "110");
                cadena = cadena.replaceAll("7", "111");
                /*Impresión por pantalla de manera formateada como string %s*/
                System.out.printf("* /%s/", cadena);
                System.out.println("");

                comprob = true;
            }
            /*Mensaje que se imprime en caso de que la cadena introducida no sea correcta*/
            else {
                System.out.println("FIN DE LA EJECUCIÓN: NO ES UN NÚMERO OCTAL VÁLIDO.");
            }
        }
    }
}

