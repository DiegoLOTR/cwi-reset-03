import java.util.Random;
/**
 * Esta classe foi criada com o intuito de descontar do estoque e printar no console o estoque remanescente
 */
public class DescontaEstoque {

    static void descontarItem(String item, int quantidade) {

        if ("pao".equals(item)) {
            ItensPorQuantidade.pao = ItensPorQuantidade.pao - (quantidade * 60); // cada pao possui 60 gramas
        }

        if ("torta".equals(item)) {
            ItensPorQuantidade.fatiasTorta = ItensPorQuantidade.fatiasTorta - quantidade; //venda de torta feita em fatias
        }

        if ("leite".equals(item)) {
            ItensPorQuantidade.leite = ItensPorQuantidade.leite - quantidade; // venda por unidade
        }

        if ("cafe".equals(item)) {
            ItensPorQuantidade.cafe = ItensPorQuantidade.cafe - quantidade; // venda por unidade
        }

        if ("sanduiche".equals(item)) {
            ItensPorQuantidade.sanduiche = ItensPorQuantidade.sanduiche - quantidade; // venda por unidade
        }


    }

    static int getEstoque(String item) {

        int estoqueAtual = 0;

        if ("pao".equals(item)) {
            estoqueAtual = ItensPorQuantidade.pao / 60;

            return estoqueAtual;
        }

        if ("torta".equals(item)) {
            estoqueAtual = ItensPorQuantidade.fatiasTorta;

            return estoqueAtual;
        }

        if ("leite".equals(item)) {
            estoqueAtual = ItensPorQuantidade.leite;

            return estoqueAtual;
        }

        if ("cafe".equals(item)) {
            estoqueAtual = ItensPorQuantidade.cafe;

            return estoqueAtual;
        }

        if ("sanduiche".equals(item)) {
            estoqueAtual = ItensPorQuantidade.sanduiche;

            return estoqueAtual;
        }

        return 0;
    }
}
