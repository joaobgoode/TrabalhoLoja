import java.util.ArrayList;
import java.util.Collection;

public class ProdutoController {

    public ProdutoDBHandler dbHandler;

    public ProdutoController(){
        dbHandler = ProdutoDBHandler.getInstance();
    }

    public void criarProduto(Produto produto) {
        dbHandler.criar(produto);
    }

    public Collection<Produto> listar(){
        return dbHandler.getTodos();
    }

    public Produto excluir(int id) {
        return dbHandler.remover(id);
    }

    public Produto procurar(int id) {
        return dbHandler.procurar(id);
    }

    public Produto editar(Produto produto) {
        return dbHandler.alterar(produto);
    }
}
