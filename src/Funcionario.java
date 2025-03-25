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

    public void excluirProduto(){
        System.out.println("Id do produto a excluir: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        Produto produto = produtoController.excluir(id);
        if (produto != null) {
            System.out.println("Produto excluido: " + produto);
        } else {
            System.out.println("Produto não encontrado");
        }
    }

    public void editarProduto(){
        System.out.println("Id do produto a editar: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        Produto produto = produtoController.procurar(id);
        if (produto == null) {
            System.out.println("Produto não encntrado!");
            return;
        }
        ProdutoBuilder pb = new ProdutoBuilder();
        pb.setId(id);
        System.out.printf("Tipo [%s]: ", produto.getTipo());
        String tipo = sc.nextLine();
        if (tipo == null || tipo.isEmpty()) {
            tipo = produto.getTipo();
        }
        pb.setTipo(tipo);
        System.out.printf("Nome [%s]: ", produto.getNome());
        String nome = sc.nextLine();
        if (nome == null || nome.isEmpty()) {
            nome = produto.getNome();
        }
        pb.setNome(nome);
        System.out.printf("Descrição [%s]: ", produto.getDescricao());
        String desc = sc.nextLine();
        if (desc == null || desc.isEmpty()) {
            desc = produto.getDescricao();
        }
        pb.setDescricao(desc);
        System.out.printf("Preco [%f]: ", produto.getPreco());
        double preco;
        try {
            preco = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            preco = produto.getPreco();
        }
        pb.setPreco(preco);
        System.out.printf("Quantidade [%d]: ", produto.getQuantidade());
        int quantidade;
        try {
            quantidade = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            quantidade = produto.getQuantidade();
        }
        pb.setQuantidade(quantidade);
        produto = pb.criar();
        System.out.println("Produto editado: " + produto);
        String res = "";
        do {
            System.out.println("Confirmar? (y/n)");
            res = sc.nextLine();
        } while (!res.equals("y") && !res.equals("n"));
        if (res.equals("y")){
            produtoController.editar(produto);
            System.out.println("Produto editado: " + produto);
        } else {
            System.out.println("Operação cancelada!");
        }
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
