package antonio_montes_martinez_prog03_tarea;
/* Bloque para importar las diferentes librerias para el manejo de variables de tiempo (fecha y hora) */
import java.time.*;

/**
 * Clase para realizar operaciones con la combinación de las dos clases anteriores: LocalDateTime.
 * @author Antonio Manuel Montes Martinez - T3 - PROG
 */
public class Ejercicio3_OperacionesLocalDateTime {
    public static void main(String[] args){
        /* Aquí instanciamos las diferentes horas mediante la clase LocalDate y LocalTime, para finalmente crear la combinación de ambas con LocalDateTime */
        LocalDate date1 = LocalDate.of(2020, 4, 3);
        LocalTime time1 = LocalTime.of(11, 30);
        LocalDateTime datePrimero = LocalDateTime.of(date1, time1);
        /* Aquí instanciamos las diferentes horas mediante la clase LocalDate y LocalTime, para finalmente crear la combinación de ambas con LocalDateTime */
        LocalDate date2 = LocalDate.of(2020, 11, 1);
        LocalTime time2 = LocalTime.of(8, 15);
        LocalDateTime dateSegundo = LocalDateTime.of(date2, time2);
        /* Mediante el método de clase isBefore(), comprobamos si la fecha primera es anterior a la segunda. Es un método que devuelve un valor boolean */
        if (datePrimero.isBefore(dateSegundo)){
            System.out.println("La fecha " + datePrimero + " es anterior a " + dateSegundo);
        }
        /* Por último, para sumar valores a las fechas, podemos concatenar la función plus() tantas veces como necesitemos */
        System.out.println("La fecha resultante de añadir 1 día y 4 horas a " + datePrimero + " es " + datePrimero.plus(Duration.ofDays(1)).plus(Duration.ofHours(4)));
    }
}
