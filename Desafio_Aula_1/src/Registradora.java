
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

     sextoBug(); // criado codigo para gerenciamento do estoque e condicao para apenas descontar do estoque caso cozinha esteja fechada se ha a quantidade minima
    }

    private static double registrarItem(String item, int quantidade) {
        double precoItem = RelacaoPesoPreco.retornaPrecoProduto(item, quantidade);
        int estoqueAtual = DescontaEstoque.getEstoque(item);

        if ("pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item)) {
            if (DataProjeto.cozinhaEmFuncionamento()) {
                DescontaEstoque.descontarItem(item, quantidade);
            } else if (quantidade <= estoqueAtual){
                DescontaEstoque.descontarItem(item, quantidade);
            } else {
                System.out.println("-------------------");
                System.out.println("Cozinha fechada, não sendo possível comprar essa quantidade de produto!");
                //System.out.println("Estoque de: " + estoqueAtual + "unidades");
                precoItem = 0.0;
            }
        }

        if (QuantidadeMinimaItem.precisaReposicao(item)) {
            if ("pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item)) {
                if (DataProjeto.cozinhaEmFuncionamento()) {
                    ReposicaoCozinha.reporItem(item);
                }
            }

            if ("leite".equals(item) || "cafe".equals(item)) {
                    ReposicaoFornecedor.reporItem(item);
            }

        }

        System.out.println("-------------------");
        DescontaEstoque.mostrarEstoque(item);
        System.out.println("-------------------");

        return precoItem;
    }

    private static void primeiroBug() {
        System.out.println(String.format("---Primeiro Pedido---"));

        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "sanduiche";
        int quantidade = 4;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println(String.format("-------------------"));
    }

    private static void segundoBug() {
        System.out.println(String.format("---Segundo Pedido---"));

        DataProjeto.criarDataComCozinhaEncerradaMasComDiaUtil();
        String item = "torta";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println(String.format("-------------------"));
    }

    private static void terceiroBug() {
        System.out.println(String.format("---Terceiro Pedido---"));

        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "cafe";
        int quantidade = 40;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println(String.format("-------------------"));
    }

    private static void quartoBug() {
        System.out.println(String.format("---Quarto Pedido---"));

        DataProjeto.criarDataComCozinhaFuncionando();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
        System.out.println(String.format("-------------------"));
    }

    private static void quintoBug() {
        System.out.println(String.format("---Quinto Pedido---"));

        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "pao";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println(String.format("-------------------"));
    }

    private static void sextoBug() {
        System.out.println(String.format("---Sexto Pedido---"));

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

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
        System.out.println(String.format("-------------------"));
    }

}
