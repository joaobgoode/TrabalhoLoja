import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public abstract class DBHandler<k, V> {
    HashMap<k, V> todos;

    protected String endereco;

    public DBHandler(String endereco) {
        this.endereco = endereco;
        todos = new HashMap<k, V>();
        carregar();
    }

    public abstract void carregar();
    public abstract void salvar();
    public abstract void criar(V objeto);
    public abstract V alterar(V objeto);
    public abstract V remover(k key);
    public abstract V procurar(k id);
    public abstract Collection<V> getTodos();
    public abstract boolean contem(k key);
}
