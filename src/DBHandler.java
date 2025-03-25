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

    public V remover(k key) {
        if (contem(key)){
            V objeto = todos.get(key);
            todos.remove(key);
            salvar();
            return objeto;
        }
        return null;
    }

    public V procurar(k id){
        return todos.get(id);
    }

    public Collection<V> getTodos() {
        return todos.values();
    }

    public boolean contem(k key) {
        return todos.containsKey(key);
    }
}
