/*
 * Antonio Manuel Montes Martínez
 * IES AGUADULCE
 */
public class Ej6 {
    
    public static void main (String[] args){
        
        int x; 
        x = (int)(Math.random()*(5-(-7)) + -7);
        String res;
        /*Ya que se uso el operador condicional ?: en el ejercicio anterior, he decidido usar la sentencia condicional if*/
        if (x>=-7&&x<0){
            System.out.println(x + " está entre -7 y 0, 0 no incluido");
        }
        if (x>=0&&x<=4){
            System.out.println(x + " está entre 0 y 4, ambos incluidos");
        }
        if (x>4&&x<=5){
            System.out.println(x + " está entre 4 y 5, 4 no incluido");
        }
    }
}
