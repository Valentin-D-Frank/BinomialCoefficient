package Coeficiente;

import javafx.util.Pair;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;

public class CoeficienteParalelo extends RecursiveTask<Integer> {
    private int N, K;
    Pair<Integer, Integer> key;
    static ConcurrentHashMap<Pair<Integer,Integer>, Pair<CoeficienteParalelo,CoeficienteParalelo>> cache;
    public CoeficienteParalelo(int N, int K) {
        this.N = N;
        this.K = K;
        if (cache == null) cache = new ConcurrentHashMap<>();
        this.key = new Pair<Integer, Integer>(N, K);
    }
    @Override
    protected Integer compute() {
        if (cache.containsKey(key)) {
            cache.get(key).getValue().fork();
            return cache.get(key).getKey().compute() + cache.get(key).getValue().join();
        }
        if (N == 0 || K == 0 || K == N) {
            return 1;
        }
        CoeficienteParalelo izquierda = new CoeficienteParalelo(N-1,K-1);
        CoeficienteParalelo derecha = new CoeficienteParalelo(N-1,K);

        cache.put(key, new Pair<>(derecha, izquierda));

        izquierda.fork();

        return derecha.compute() + izquierda.join();
    }
}
