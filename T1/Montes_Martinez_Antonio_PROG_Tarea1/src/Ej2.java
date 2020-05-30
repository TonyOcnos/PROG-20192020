/*
 * Antonio Manuel Montes Martínez
 * IES AGUADULCE
 */
public class Ej2 {
    
    public static void main(String[] args){
        /*Delcaración de varialbes*/
        short x;
        short y;
        x = 14;
        y = 8;
        
        System.out.println("El valor de x es " + x + ", y es " + y);
        System.out.println("La división entera (x/y) da: " + x/y);
        System.out.println("El resultado de x + y es " + x+y);
        System.out.println("El resto de la división entera (x%y) da: " + x%y);
        /*Dado que los símbolos + y - tienen la misma prioridad, por asociación de izquierda a derecha, hay que separar la resta entre paréntesis para
        darle una entidad por separado de la String*/
        System.out.println("El resultado de x - y es " + (x-y));
    }
}
