package br.com.cwi.reset.projeto1;

import java.util.Arrays;
import java.util.List;

public class Exercicios1 {

    public Integer somarLista(List<Integer> numeros) {
     Integer resultado = 0;

        for (Integer numero : numeros) {
            resultado += numero;
        }

        return resultado;
    }

    public Double calcularMedia(List<Integer> numeros) {
        double media = 0.0;

        media = ((double) somarLista(numeros) / numeros.size());

        return media;
    }

    public Integer obterMaiorNumero(List<Integer> numeros) {
        Integer maiorNumero = 0;

        for (Integer numero : numeros) {

            if (numero > maiorNumero ){
                maiorNumero = numero;
            }
        }

        return maiorNumero;
    }

    public String obterPalavraInvertida(String palavra) {
        String palavraInvertida = "";

        for (int i = palavra.length() -1 ; i >=0 ; i--) {
           palavraInvertida += palavra.charAt(i);
        }

        return palavraInvertida ;
    }

    public List<Integer> ordenarLista(List<Integer> numeros) {
        List<Integer>numerosOrdenados = numeros;
        Integer tempNumero = 0;

        for(int i=0;i<numerosOrdenados.size();i++){
            for(int j=i+1;j<numerosOrdenados.size();j++){

                if(numerosOrdenados.get(i) > numerosOrdenados.get(j)  ){
                    tempNumero = numerosOrdenados.get(j);
                    numerosOrdenados.set(j,numerosOrdenados.get(i));
                    numerosOrdenados.set(i,tempNumero);
                }
            }
        }

        return numerosOrdenados;
    }
}

