
public class Registradora {

    public static void main(String[] args) {
      primeiroBug(); // correcao: na classe RelacaoPesoPreco o item da linha 22 estava como sanduba ao inves de sanduiche

      segundoBug(); // correcao: 1) cálculo de valor da torta na classe RelacaoPesoPreco se faz necessario o metodo double na divisao de
                       // inteiros. 2) há uma divergencia no uso da variavel tortas, recebendo tanto a quantidade de tortas como de fatias
                       // sendo criado entao a variavel fatiasTortas na classe ItensPorQuantidade.

       terceiroBug(); // Na classe QuantidadeMinimaItens os retornos de cafe e leite estavam invertidos, bem como cafe com acento na classe
                      // RelacaoPesoPreco. Criado classe para desconto do estoque.

      quartoBug(); // comparador de quantidade minima de sanduiches na classe QuantidadeMinimaItem alterado de == para <=

      quintoBug(); // na classe RelacaoPesoPreco se faz necessario o metodo double na divisao de inteiros

      //sextoBug();
    }

    private static double registrarItem(String item, int quantidade) {
        double precoItem = RelacaoPesoPreco.retornaPrecoProduto(item, quantidade);

        DescontaEstoque.descontarItem(item, quantidade);

        if (QuantidadeMinimaItem.precisaReposicao(item)) {
            if ("pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item)) {
                if (!DataProjeto.cozinhaEmFuncionamento()) {
                    System.out.println("Cozinha fechada!");
                }
                ReposicaoCozinha.reporItem(item);
            }

            if ("leite".equals(item) || "cafe".equals(item)) {
                ReposicaoFornecedor.reporItem(item);

            }

        }
        System.out.println("-------------------");
        DescontaEstoque.mostrarEstoque(item);

        return precoItem;
    }

    private static void primeiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "sanduiche";
        int quantidade = 4;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("---Primeiro Bug---"));
        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void segundoBug() {
        DataProjeto.criarDataComCozinhaEncerradaMasComDiaUtil();
        String item = "torta";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("---Segundo Bug---"));
        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void terceiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "cafe";
        int quantidade = 40;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("---Terceiro Bug---"));
        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void quartoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("---Quarto Bug---"));
        System.out.println(String.format("Valor total: %.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
    }

    private static void quintoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "pao";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("---Quinto Bug---"));
        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void sextoBug() {
        DataProjeto.criarDataComCozinhaEncerradaSemDiaUtil();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("---Sexto Bug---"));
        System.out.println(String.format("Valor total: %.2f", precoTotal2));
    }

}
