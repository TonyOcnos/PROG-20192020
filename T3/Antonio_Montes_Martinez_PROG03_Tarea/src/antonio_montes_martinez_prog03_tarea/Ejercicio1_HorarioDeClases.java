package antonio_montes_martinez_prog03_tarea;
/* Bloque para importar las diferentes librerias que nos permitirán manejar ciertas excepciones, trabajar con la entrada de datos
mediante teclado, trabajar con tiempos mediante LocalTime... */
import java.util.*;
import java.time.LocalTime;

/**
 * Clase para determinar mediante la clase LocalTime el horario escolar.
 * @author Antonio Manuel Montes Martinez - T3 - PROG
 */
public class Ejercicio1_HorarioDeClases{
    public static void main(String[] args){
        /* Inicialización de las variables hora y min. Hay que inicializarlas, porque si no el compilador de Java te lanza un error ya que no te deja
        instanciar un LocalTime.of si no están las variables inicializadas (aunque despúes dichos valores se pisen mediante Scanner) */
        int hora = 0, min = 0;
        /* Se añade variable para el control de flujo que nos permitirá comprobar el formato del tiempo introducido. El bloque while se explica más adelante */
        Boolean comprob = false;
        /* Aquí instanciamos las diferentes horas mediante la clase LocalTime */
        LocalTime primeraHora = LocalTime.parse("08:50");
        LocalTime segundaHora = LocalTime.parse("09:00");
        LocalTime terceraHora = LocalTime.parse("10:00");
        LocalTime recreo = LocalTime.parse("11:00");
        LocalTime cuartaHora = LocalTime.parse("11:30");
        LocalTime quintaHora = LocalTime.parse("12:30");
        LocalTime sextaHora = LocalTime.parse("13:30");
        LocalTime finalHora = LocalTime.parse("14:30");
        /* Instanciamos un objeto de la clase Scanner llamado teclado, que leerá los datos de entrada al System */
        
        /* Se inicia el bloque while para comprobar el formato y manejo de excepciones. Entramos con la variable booleana comprob en false, para entrar
        al menos una vez */	
        while(!comprob){
            System.out.println("Introduzca la hora:");
            /* Probamos a leer mediante teclado usando la clase Scanner */
            Scanner teclado = new Scanner(System.in);
            try {
                /* Para asegurarnos que es un dígito y lanza las excepciones correspondientes, hemos usado nextInt */
                hora = teclado.nextInt();
                /* Para comprobar que es una hora válida, es decir, entre 0 y 23, creamos una excepción con IllegalArgumentException */
                if (hora<0 || hora >23){
                    throw new IllegalArgumentException("Por favor, introduzca un formato de tiempo válido");
                }
                /* Si el bloque try no falla y recoge mediante catch una excepción, comprob pasará a ser true y saldrá del bloque while */
                comprob = true;
            } 
            /* Bloque para las diferentes excepciones */
            catch (NumberFormatException | InputMismatchException ex1) {
                System.err.println("Por favor, introduzca un formato de tiempo válido");
            }
            catch (IllegalArgumentException ex3) {
                System.out.println(ex3.getMessage());
            }
        }
        /* Volvemos a poner la variable booleana comprob como false, para mediante el siguiente while realizar lo mismo con los minutos */
        comprob = false;
        
        while(!comprob){
            System.out.println("Introduzca los minutos:");
            Scanner teclado = new Scanner(System.in);
            try {
                min = teclado.nextInt();
                
                if (min<0 || min>59){
                    throw new IllegalArgumentException("Por favor, introduzca un formato de tiempo válido");
                }
                
                comprob = true;
            } 
            catch (NumberFormatException | InputMismatchException ex1) {
                System.err.println("Por favor, introduzca un formato de tiempo válido");
            }
            catch (IllegalArgumentException ex3) {
                System.out.println(ex3.getMessage());
            }
        }
        /* Una vez comprobado la hora y los minutos, instanciamos dicha hora total como objeto de la clase LocalTime */
        LocalTime time = LocalTime.of(hora, min);
        /* Se muestra, formateada, la hora con los minutos */
        System.out.printf("Hora actual %d:%02d\n", hora, min);
        /* Para realizar el conteo de horas que queda, hemos pasado las horas y minutos a minutos totales, inicializando una variable con la cuenta de
        minutos transcurridos desde que empezó el día, con los máximos indicados por la hora final de clases */
        int totalMin = (time.getHour()*60)+time.getMinute();
        int maxMin = (14*60)+30;
        int minRes = maxMin - totalMin;
        /* Mediante compareTo, podemos comprobar si un tiempo es mayor, menor o igual que otro de referencia. En este primer if, comprobamos si las clases
        no han empezado todavía */
        if(time.compareTo(primeraHora)<0){
            System.out.println("Resultado: Las clases no han empezado todavía.");
            System.out.println("Faltan: " + (7-time.getHour())+ " horas y " + (60-time.getMinute()) + " minutos para empezar.");
        /* Tanto para la primera hora como para el recreo y el resto de horas, se realizá lo mismo mediante compareTo */
        } else if (((time.compareTo(primeraHora)>0) || (time.compareTo(primeraHora) == 0)) && (time.compareTo(segundaHora)<0)){
            System.out.println("Resultado: Las clases han comenzado y estamos en 1ª hora.");
            /* Aquí hemos usado una función del objeto instanciado de clase LocalTime para coger las horas y los minutos con getHour y getMinute */
            System.out.println("Las clases empezaron hace: " + (time.getHour()-8)+ " horas y " + (time.getMinute()) + " minutos.");
            System.out.println("Las clases terminarán en: " + minRes/60 + " horas y " + minRes%60 + " minutos."); 
            
        } else if (((time.compareTo(segundaHora)>0) || (time.compareTo(segundaHora) == 0)) && (time.compareTo(terceraHora)<0)){
            System.out.println("Resultado: Las clases han comenzado y estamos en 2ª hora.");
            System.out.println("Las clases empezaron hace: " + (time.getHour()-8)+ " horas y " + (time.getMinute()) + " minutos.");
            System.out.println("Las clases terminarán en: " + minRes/60 + " horas y " + minRes%60 + " minutos.");
            
        } else if (((time.compareTo(terceraHora)>0) || (time.compareTo(terceraHora) == 0)) && (time.compareTo(recreo)<0)){
            System.out.println("Resultado: Las clases han comenzado y estamos en 3ª hora.");
            System.out.println("Las clases empezaron hace: " + (time.getHour()-8)+ " horas y " + (time.getMinute()) + " minutos.");
            System.out.println("Las clases terminarán en: " + minRes/60 + " horas y " + minRes%60 + " minutos.");
            
        } else if (((time.compareTo(recreo)>0) || (time.compareTo(recreo) == 0)) && (time.compareTo(cuartaHora)<0)){
            System.out.println("Resultado: Las clases han comenzado y estamos en el recreo.");
            System.out.println("Las clases empezaron hace: " + (time.getHour()-8)+ " horas y " + (time.getMinute()) + " minutos.");
            System.out.println("Las clases terminarán en: " + minRes/60 + " horas y " + minRes%60 + " minutos.");
            
        } else if (((time.compareTo(cuartaHora)>0) || (time.compareTo(cuartaHora) == 0)) && (time.compareTo(quintaHora)<0)){
            System.out.println("Resultado: Las clases han comenzado y estamos en 4ª hora.");
            System.out.println("Las clases empezaron hace: " + (time.getHour()-8)+ " horas y " + (time.getMinute()) + " minutos.");
            System.out.println("Las clases terminarán en: " + minRes/60 + " horas y " + minRes%60 + " minutos.");
            
        } else if (((time.compareTo(quintaHora)>0) || (time.compareTo(quintaHora) == 0)) && (time.compareTo(sextaHora)<0)){
            System.out.println("Resultado: Las clases han comenzado y estamos en 5ª hora.");
            System.out.println("Las clases empezaron hace: " + (time.getHour()-8)+ " horas y " + (time.getMinute()) + " minutos.");
            System.out.println("Las clases terminarán en: " + minRes/60 + " horas y " + minRes%60 + " minutos.");
            
        } else if (((time.compareTo(sextaHora)>0) || (time.compareTo(sextaHora) == 0)) && (time.compareTo(finalHora)<0)){
            System.out.println("Resultado: Las clases han comenzado y estamos en 6ª hora.");
            System.out.println("Las clases empezaron hace: " + (time.getHour()-8)+ " horas y " + (time.getMinute()) + " minutos.");
            System.out.println("Las clases terminarán en: " + minRes/60 + " horas y " + minRes%60 + " minutos.");
        } else{
            /* Este último else, evalua la última cláusula, que es cuando las clases ya han acabado */
            System.out.println("Las clases han acabado. Comenzarán mañana a las 8:00");
        }
    }
}