import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ServicioDFS {
	
	private Grafo<?> grafo;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	
	
	public List<Integer> dfsForest() {
	    List<Integer> vertices = new ArrayList<>();
	    Set<Integer> visitados = new HashSet<>();
	    Iterator<Integer> it = grafo.obtenerVertices();
	    while (it.hasNext()) {
	        int vertice = it.next(); 
	        if (!visitados.contains(vertice)) {
	        	dfs(vertice,visitados,vertices);
	        }
	    }
	    
	    return vertices;
	}

	private void dfs(int verticeId, Set<Integer> visitados, List<Integer> vertices) {
	    visitados.add(verticeId);
	    vertices.add(verticeId);
	    Iterator<Integer> it = grafo.obtenerAdyacentes(verticeId);
	    while (it.hasNext()) {
	        int verticeDestino = it.next(); 
	        if (!visitados.contains(verticeDestino)) {
	        	dfs(verticeDestino,visitados,vertices);
	        }
	    }
	    
	}


}
