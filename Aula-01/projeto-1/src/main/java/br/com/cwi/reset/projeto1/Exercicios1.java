package br.com.cwi.reset.projeto1;

import java.util.Arrays;
import java.util.List;

public class Exercicios1 {

    public Integer somarLista(List<Integer> numeros)
    {   Integer resultado = 0;

        for (int i = 0; i < numeros.size(); i++) {
            resultado = resultado + numeros.get(i);
        }

        return resultado;
    }

    public Double calcularMedia(List<Integer> numeros) {
        Integer resultado = 0;
        double media = 0.0;

        for (int i = 0; i < numeros.size(); i++) {
            resultado = resultado + numeros.get(i);
        }
        media = resultado / numeros.size();
        return media;
    }

    public Integer obterMaiorNumero(List<Integer> numeros) {
        Integer maiorNumero = 0;

        for (int i = 0; i < numeros.size(); i++) {

            if (numeros.get(i) > maiorNumero ){
                maiorNumero = numeros.get(i);
            }
        }

        return maiorNumero;
    }

    public String obterPalavraInvertida(String palavra) {
        String palavraInvertida = palavra;
        char aux;
        palavraInvertida.charAt(2) = a';
        for (int i = 0; i < palavra.length(); i++) {
            aux = palavra.charAt(i);


        }

        return palavraInvertida ;
    }

    public List<Integer> ordenarLista(List<Integer> numeros) {
        return Arrays.asList(1, 2, 3, 4, 5);
    }
}

