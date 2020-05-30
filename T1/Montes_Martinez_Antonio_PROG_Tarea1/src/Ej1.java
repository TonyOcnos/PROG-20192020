/*
 * Antonio Manuel Montes Martínez
 * IES AGUADULCE
 */
public class Ej1 {
    /*Creación del tipo de dato enumerado (ENUM) para la visulización del nombre de los jugadores*/
    public enum basketPlayers {JOHNSON, SCOTT, WORTHY, JABBAR, GREEN}
    /*Además vamos a visualizar el último jugador mediante una variable de tipo basketPlayer. Dicha variable, para poder
    ser usada en el entorno del main() deberá declararse como estática (al estilo global en JavaScript)*/
    static basketPlayers jugador5 = basketPlayers.GREEN;

    public static void main (String[] args){
        System.out.println("Jugador 1: " + basketPlayers.JOHNSON);
        System.out.println("Jugador 2: " + basketPlayers.SCOTT); 
        System.out.println("Jugador 3: " + basketPlayers.WORTHY); 
        System.out.println("Jugador 4: " + basketPlayers.JABBAR); 
        System.out.println("Jugador 5: " + jugador5); 
    }
}
