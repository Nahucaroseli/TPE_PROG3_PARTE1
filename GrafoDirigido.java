import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GrafoDirigido<T> implements Grafo<T> {
	
	private Map<Integer,Set<Arco<T>>> mapa;
	private int cantVertices;
	private int cantArcos;
	
	
	public GrafoDirigido() {
		this.mapa = new HashMap<>();
		this.cantArcos = 0;
		this.cantVertices = 0;
	}
	
	/**
	* Complejidad: O(1), este metodo tiene complejidad O(1)
	* ya que solo agrega un vertice nuevo al mapa y crea una lista vacia q corresponde a los arcos
	* que salen del vertice nuevo. 
	*/
	@Override
	public void agregarVertice(int verticeId) {
		if(!contieneVertice(verticeId)) {
			mapa.put(verticeId, new HashSet<>());
			cantVertices++;
		}

	}

	
	/**
	* Complejidad: O(V+A), este metodo tiene complejidad O(V+A), donde V 
	* hace referencia a la cantidad de vertices y A hace referencia a la cantidad de arcos,
	* debido a que debe realizar dos recorridos sobre los arcos del grafo, el primer recorrido
	* es para borrar los arcos que tienen el vertice a eliminar como destino, y el segundo, es para 
	* borrar los arcos que tienen al vertice que se quiere borrar, como origen.
	*/
	@Override
	public void borrarVertice(int verticeId) {
		if (contieneVertice(verticeId)) {
			// En esta parte se borran todos los arcos que tienen a verticeId como destino
			for (Set<Arco<T>> arcosAdyacentes : mapa.values()) {
				Iterator<Arco<T>> it = arcosAdyacentes.iterator();
				while (it.hasNext()) {
					Arco<T> arco = it.next();
					if (arco.getVerticeDestino() == verticeId) {
						it.remove();
						cantArcos--;
					}
				}
			}

			// Aca se borran todos los arcos que tienen a verticeId como origen
			Set<Arco<T>> arcos = mapa.remove(verticeId);
			for (Arco<T> arco : arcos) {
				Set<Arco<T>> adyacentes = mapa.get(arco.getVerticeDestino());
				adyacentes.remove(new Arco<>(verticeId, arco.getVerticeDestino(), arco.getEtiqueta()));
				cantArcos--;
			}

			cantVertices--;
		}

	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if (contieneVertice(verticeId1) && contieneVertice(verticeId2)) {
			Set<Arco<T>> adyacentes = mapa.get(verticeId1);
			if (adyacentes.add(new Arco<>(verticeId1, verticeId2, etiqueta))) {
				cantArcos++;
			}
		}

	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		

	}

	@Override
	public boolean contieneVertice(int verticeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public int cantidadVertices() {
		// TODO Auto-generated method stub
		return this.cantVertices;
	}

	@Override
	public int cantidadArcos() {
		// TODO Auto-generated method stub
		return this.cantArcos;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		// TODO Auto-generated method stub
		return null;
	}


}
