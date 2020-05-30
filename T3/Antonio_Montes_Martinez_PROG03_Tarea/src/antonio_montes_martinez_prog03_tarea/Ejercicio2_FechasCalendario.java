package antonio_montes_martinez_prog03_tarea;
/* Bloque para importar las diferentes librerias que nos permitirán manejar ciertas excepciones, trabajar con la entrada de datos
mediante teclado, trabajar con tiempos mediante LocalDate, formateado de la fecha... */
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Clase para determinar mediante la clase LocalDate el calendario escolar.
 * @author Antonio Manuel Montes Martinez - T3 - PROG
 */
public class Ejercicio2_FechasCalendario {
    public static void main(String[] args){
        /* Inicialización de las variables dia, mes y anho. Hay que inicializarlas, porque si no el compilador de Java te lanza un error ya que no te deja
        instanciar un LocalDate.of si no están las variables inicializadas (aunque despúes dichos valores se pisen mediante Scanner) */
        int dia = 0, mes = 0, anho = 0;
        /* Se añade variable para el control de flujo que nos permitirá comprobar el formato del tiempo introducido. El bloque while se explica más adelante */
        Boolean comprob = false;
        /* Instanciamos una clase de DateTimeFormatter para dar el formato deseado a nuestra instanacia de la clase LocalDate más adelante */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        /* Aquí instanciamos las diferentes horas mediante la clase LocalDate */
        LocalDate inicioCurso = LocalDate.of(2019, 9, 10);
        LocalDate inicioSS = LocalDate.of(2020, 4, 6);
        LocalDate finalSS = LocalDate.of(2020, 4, 12);
        LocalDate finalCurso = LocalDate.of(2020, 6, 24);
        /* Al igual que en el ejercicio 1, se realizan comprobaciones de formato y valor de las diferentes variables a través de un bucle while */
        while(!comprob){
            while(!comprob){
                System.out.println("Por favor, introduzca el día:");
                Scanner teclado = new Scanner(System.in);
                try {
                    dia = teclado.nextInt();
                    if (dia<0 || dia >31){
                        throw new IllegalArgumentException("Por favor, introduzca un formato de día válido");
                    }
                    comprob = true;
                }
                catch (NumberFormatException | InputMismatchException ex1) {
                    System.out.println("Por favor, introduzca un formato de día válido");
                }
                catch (IllegalArgumentException ex3) {
                    System.out.println(ex3.getMessage());
                }
            }

            comprob = false;

            while(!comprob){
                System.out.println("Por favor, introduzca el mes:");
                Scanner teclado = new Scanner(System.in);
                try {
                    mes = teclado.nextInt();
                    if (mes<0 || mes>12){
                        throw new IllegalArgumentException("Por favor, introduzca un formato de mes válido");
                    }
                    comprob = true;
                }
                catch (NumberFormatException | InputMismatchException ex1) {
                    System.out.println("Por favor, introduzca un formato de mes válido");
                }
                catch (IllegalArgumentException ex3) {
                    System.out.println(ex3.getMessage());
                }
            }

            comprob = false;

            while(!comprob){
                System.out.println("Por favor, introduzca el año:");
                Scanner teclado = new Scanner(System.in);
                try {
                    anho = teclado.nextInt();
                    if (anho<2019 || anho>2020){
                        throw new IllegalArgumentException("Por favor, introduzca un formato de año válido");
                    }
                    comprob = true;
                }
                catch (NumberFormatException | InputMismatchException ex1) {
                    System.out.println("Por favor, introduzca un formato de año válido");
                }
                catch (DateTimeException ex2) {
                    System.err.println("Por favor, esa fecha no es posible. Pruebe con otra válida");
                }
                catch (IllegalArgumentException ex3) {
                    System.out.println(ex3.getMessage());
                }
            }
            
            comprob = false;
            /* Este último try y catch nos permite comprobar si la fecha introducida es válida más allá del formato, es decir,
            que no es, por ejemplo, 31 de Febrero, la cual no es una fecha posible*/
            try {
                LocalDate fecha = LocalDate.of(anho, mes, dia);
                System.out.println(formatter.format(fecha));
                
                if (fecha.isBefore(inicioCurso) || (fecha.isAfter(finalCurso))){
                    System.out.println("Resultado: Está fuera del periodo escolar");
                } else if (fecha.isAfter(inicioSS) && fecha.isBefore(finalSS)) {
                    System.out.println("Resultado: La fecha está dentro de Semana Santa de este curso escolar 2019-2020");
                } else {
                    System.out.println("Resultado: Está dentro del periodo escolar del curso 2019-2020");
                }
                
                comprob = true;
            }
            catch (DateTimeException ex2) {
                System.out.println("Fecha no posible. Por favor, introduzca otra fecha");
            }
        }
    }   
}