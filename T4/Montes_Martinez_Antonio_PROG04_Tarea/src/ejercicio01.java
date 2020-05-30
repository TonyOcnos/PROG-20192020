/* Bloque para importar la librería que nos permitirá solicitar una entrada de datos mediante teclado. */
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase para realizar la concatenación de una String mediante StringBuilder and Append.
 * @author martiant
 */
public class ejercicio01 {
    public static void main(String[] args){
        
        /*Creación y declaración de variables: boolean para la comprobación del bucle WHILE, el StringBuilder para la concatenación de texto y la variable tipo Scanner para la entradad de datos*/
        boolean comprob = false;
        StringBuilder cadenaFinal = new StringBuilder();
        Scanner teclado = new Scanner(System.in);
        
        /*Bloque WHILE que nos permitirá el control de la cadena introducida, repitiéndose hasta que ésta no sea correcta*/
        while(!comprob){
            System.out.println("Por favor, introduzca una frase de al menos 4 caracteres:");
            /*Solicitud de entrada de datos*/
            String frase = teclado.nextLine();
                /*Estructura de control para comprobar que la cadena introducida es mayora a 4 caracteres*/
            if(frase.length()<4){
                System.out.println("¡Cadena no válida!");
            }
            else {
                /*Estructura de control con doble bucle FOR para recorrer la cadena caracter a caracter*/
                for(int i=0;i<frase.length();i++){
                    for(int j=0;j<=i;j++){
                       cadenaFinal.append(frase.charAt(j)); 
                    }
                    /*Si no se ha alcanzado el final, concatenar mediante append() el separado*/
                    if (i<frase.length()-1){
                        cadenaFinal.append(" | ");
                    }
                }
                /*Cambio de estado del booleano que nos permite salir del bucle WHILE si la cadena introducida fue correcta*/
                comprob = true;
            }
        }
        /*Impresión por pantalla del resultado obtenido*/
        System.out.println(cadenaFinal);
    }
}