import java.util.Scanner;

public class Gerente extends Funcionario{

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
}
