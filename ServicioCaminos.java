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
	    Set<Arco<?>> visitados = new HashSet<>();
	    List<Integer> camino = new ArrayList<>();
	    camino.add(origen);
	    caminosGrafo(origen, visitados, caminos, camino, 0);
	    return caminos;
	}

	private void caminosGrafo(int v, Set<Arco<?>> visitados, List<List<Integer>> caminos, List<Integer> camino, int cantArcos) {
		visitados.add(new Arco<>(camino.get(camino.size() - 1), v, null));
	    if (cantArcos > lim) {
	        return;
	    }
	    if (destino == v) {
	        caminos.add(new ArrayList<>(camino));
	        return;
	    }
	    
	    Iterator<Integer> it = grafo.obtenerAdyacentes(v);
	    while (it.hasNext()) {
	        int verticeDestino = it.next();
	        Arco<?> arco = new Arco<>(v, verticeDestino, null);
	        if (!existeArcoVisitado(arco, visitados)) {
	            visitados.add(arco);
	            camino.add(verticeDestino);
	            caminosGrafo(verticeDestino, visitados, caminos, camino, cantArcos + 1);
	            camino.remove(camino.size() - 1);
	            visitados.remove(arco);
	        }
	    }
	    visitados.remove(new Arco<>(camino.get(camino.size() - 1), v, null));
	}

	private boolean existeArcoVisitado(Arco<?> arco, Set<Arco<?>> visitados) {
	    for (Arco<?> visitado : visitados) {
	        if (visitado.getVerticeOrigen() == arco.getVerticeOrigen() && visitado.getVerticeDestino() == arco.getVerticeDestino()) {
	            return true;
	        }
	    }
	    return false;
	}

}
