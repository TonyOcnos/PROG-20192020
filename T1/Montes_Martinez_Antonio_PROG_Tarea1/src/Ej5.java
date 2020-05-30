/*
 * Antonio Manuel Montes Martínez
 * IES AGUADULCE
 */
public class Ej5 {
    
    public static void main (String[] args){
        
        char t = 'A';
        char z = 'B';
        int x = 4;
        int y = 2;
        String res;
        /*Aunque los operadores condicionales se requieren para el ejercicio siguiente, también se hace uso de ellos aquí*/
        res=(x>y)?"x es mayor que y":"x es menor o igual que y";
        System.out.println(res);
        res=((x>y)&&(z!=t))?"x es mayor que y, y z no es igual a t":"x es menor o igual que y, y z es igual a t";
        System.out.println(res);
        res=(('F'!=z)&&('F'!=t)&&('F'<100))?"'F' es distinto de z y x y menor de 100":"'F' es igual que z y x";
        System.out.println(res);
        res=(t<z)?"t es posterior alfabéticamente a z":"t no es posterior alfabéticamente a z";
        System.out.println(res);
    }
}
