import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ServicioCaminos {
	
	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
	}

	public List<List<Integer>> caminos() {
	    List<List<Integer>> caminos = new ArrayList<>();
	    Set<Integer> visitados = new HashSet<>();
	    List<Integer> camino = new ArrayList<>();
	    camino.add(origen);
	    caminosGrafo(origen, visitados, caminos, camino, 0);
	    return caminos;
	}

	private void caminosGrafo(int v, Set<Integer> visitados, List<List<Integer>> caminos, List<Integer> camino, int cantArcos) {
	    visitados.add(v);
	    if (destino == v && cantArcos <= lim) {
	        caminos.add(new ArrayList<>(camino));
	        return;
	    }
	    if (cantArcos > lim) {
	        return;
	    }else if(cantArcos < lim) {
	    	Iterator<Integer> it = grafo.obtenerAdyacentes(v);
		    while (it.hasNext()) {
		        int verticeDestino = it.next();
		        if (!visitados.contains(verticeDestino)) {
		        	visitados.add(verticeDestino);
		            camino.add(verticeDestino);
		            cantArcos++;
		            caminosGrafo(verticeDestino, visitados, caminos, camino, cantArcos);
		            camino.remove(camino.size() - 1);
		            cantArcos--;
		            visitados.remove(verticeDestino);
		        }
		    }
		    visitados.remove(v);
	    }
	    
	}

}
