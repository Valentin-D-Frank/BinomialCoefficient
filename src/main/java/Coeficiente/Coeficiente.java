package Coeficiente;

import javafx.util.Pair;

import java.util.HashMap;

public class Coeficiente {
    public static HashMap<Pair<Integer,Integer>, Integer> cache = new HashMap<>();

    public static int choose (int N,int K){

        Pair <Integer, Integer> key =  new Pair <Integer, Integer> (N, K);
        //if(CoeficienteD.cache.size() < 3) System.out.println(CoeficienteD.cache.size());
        if (cache.containsKey(key)){
            //System.out.println(key);
            return cache.get(key);
        }
        if(N==0||K==0||K==N){
            return 1;
        }
        int izquierda = choose(N-1,K);
        int derecha = choose(N-1,K-1);
        int resultado = izquierda + derecha;
        cache.put(key,resultado);

        //System.out.println(cache);
        return resultado;

    }
}
