import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

public abstract class Funcionario {

    public ProdutoController produtoController;

    public Funcionario(){
        produtoController = new ProdutoController();
    }

    public Produto criarProduto(){
        System.out.println("Criando produto");
        ProdutoBuilder produtoBuilder = new ProdutoBuilder();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do produto: ");
        String nome = sc.nextLine();
        produtoBuilder.setNome(nome);
        System.out.println("Digite o tipo do produto:");
        String tipo = sc.nextLine();
        produtoBuilder.setTipo(tipo);
        System.out.println("Digite a descrição do produto:");
        String descricao = sc.nextLine();
        produtoBuilder.setDescricao(descricao);
        System.out.println("Digite o quantidade do produto:");
        produtoBuilder.setQuantidade(sc.nextInt());
        System.out.println("Digite o valor da produto:");
        double valor = sc.nextDouble();
        sc.nextLine();
        produtoBuilder.setPreco(valor);
        System.out.println("Digite o id do produto:");
        int id = sc.nextInt();
        produtoBuilder.setId(id);
        Produto produto = produtoBuilder.criar();
        System.out.println("Produto criado");
        produtoController.criarProduto(produto);
        return produto;
    }

    public void listarProdutos(){
        Collection<Produto> lista = produtoController.listar();
        for (Produto produto : lista) {
            System.out.println(produto);
        }
    }

    public void procurarProduto(){
        System.out.println("Id do produto a procurar: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        Produto produto = produtoController.procurar(id);
        System.out.println("Produto encontrado: " + produto);
    }

    public void registrarVenda(){
        System.out.println("Id do produto: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        Produto produto = produtoController.procurar(id);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }
        int qtd = produto.getQuantidade();
        System.out.printf("Quantidade [Em estoque: %d]: ", qtd);
        int qtdVendida = sc.nextInt();
        sc.nextLine();
        if (qtdVendida <= qtd) {
            produto.setQuantidade(qtd - qtdVendida);
            produtoController.editar(produto);
            System.out.println("Quantidade em estoque: " + produto.getQuantidade());
        } else {
            System.out.println("Quantidade vendida inválida!");
        }
    }

    public void registrarCompra(){
        System.out.println("Id do produto: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        Produto produto = produtoController.procurar(id);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }
        System.out.println("Quantidade: ");
        int qtdComprada = sc.nextInt();
        sc.nextLine();
        if (qtdComprada > 0) {
            produto.setQuantidade(produto.getQuantidade() + qtdComprada);
            produtoController.editar(produto);
            System.out.println("Quantidade em estoque: " + produto.getQuantidade());
        } else {
            System.out.println("Quantidade vendida inválida!");
        }
    }
}
