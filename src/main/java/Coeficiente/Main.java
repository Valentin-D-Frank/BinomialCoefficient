package Coeficiente;

import com.google.common.base.Stopwatch;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // SECUENCIAL CON HASHMAP
        int resultado = 0;
        final int ITER = 101;
        long duracion = 0;

        Stopwatch reloj = Stopwatch.createUnstarted();

        duracion = 0;
        for (int i = 0; i < ITER; i++) {
            //System.out.println(duracion);
            reloj.reset();
            reloj.start();
            resultado = Coeficiente.choose(20, 4);
            reloj.stop();
            if (i > 0) duracion += reloj.elapsed(TimeUnit.NANOSECONDS);
            Coeficiente.cache.clear();
            //System.out.println("-----------------------");
        }
        duracion = duracion / (ITER - 1);
        System.out.println("El calculo del algoritmo secuencial es: " + resultado);
        System.out.println("El calculo tomo " + duracion + " micro");

        // PARALELO CON CONCURRENTHASHMAP
        ForkJoinPool pool = new ForkJoinPool();


        //CoeficienteDParalelo coefparalelo = new CoeficienteDParalelo(20, 4);
        duracion = 0;
        resultado = 0;
        for (int i = 0; i < ITER; i++) {
        //System.out.println(duracion);
        CoeficienteParalelo coefparalelo = new CoeficienteParalelo(20, 4);
        reloj.reset();
        reloj.start();
        resultado = pool.invoke(coefparalelo);
        reloj.stop();
        //System.out.println("-----------------------");
          if (i > 0) duracion += reloj.elapsed(TimeUnit.NANOSECONDS);
        CoeficienteParalelo.cache.clear();
        }
        duracion = duracion / (ITER - 1);
        //duracion = reloj.elapsed(TimeUnit.MICROSECONDS);
        System.out.println("El calculo del algoritmo paralelo es: " + resultado);
        System.out.println("El calculo paralelo tomo " + duracion + " micro");
    }
}
