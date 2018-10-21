package grafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AlgoritmoDijkstra {
	//private final List<Nodo> nodos;
	private final List <Arista> aristas;
	private Set<Nodo>nodosTachados;
	private Set<Nodo>nodosNoTachados;
	private Map<Nodo,Nodo>predecesores;
	private Map<Nodo,Integer>distancia;

	public AlgoritmoDijkstra(Grafo grafo){
		//this.nodos = new ArrayList<Nodo>(grafo.getNodos());
		this.aristas = new ArrayList<Arista>(grafo.getAristas());
	}

	public void resolver(Nodo origen){
		nodosTachados = new HashSet<Nodo>();
		nodosNoTachados = new HashSet<Nodo>();
		distancia = new HashMap<Nodo,Integer>();
		predecesores = new HashMap<Nodo,Nodo>();
		distancia.put(origen, 0);
		nodosNoTachados.add(origen);
		while(nodosNoTachados.size()>0){
			Nodo nod = getMinimo(nodosNoTachados);
			nodosTachados.add(nod);
			nodosNoTachados.remove(nod);
			encontrarDistanciaMinima(nod);
		}
	}

	private void encontrarDistanciaMinima(Nodo nod){
		List<Nodo> nodosAdyacentes = getVecinos(nod);
		for(Nodo objetivo : nodosAdyacentes){
			if (getDistanciaMasCorta(objetivo) > getDistanciaMasCorta(nod)
					+ getDistancia(nod, objetivo)) {
				distancia.put(objetivo, getDistanciaMasCorta(nod)
						+ getDistancia(nod, objetivo));
				predecesores.put(objetivo, nod);
				nodosNoTachados.add(objetivo);
			}
		}
	}
	
	private int getDistancia(Nodo nod, Nodo objetivo) {
		for (Arista arist : aristas) {
			if (arist.getOrigen().equals(nod)
					&& arist.getDestino().equals(objetivo)) {
				return arist.getDistancia();
			}
		}
		throw new RuntimeException("No deberia pasar, si o si debe llegar al return");
	}
	
	private List<Nodo> getVecinos(Nodo nod) {
		List<Nodo> vecinos = new ArrayList<Nodo>();
		for (Arista arist : aristas) {
			if (arist.getOrigen().equals(nod)
					&& !estaTachado(arist.getDestino())) {
				vecinos.add(arist.getDestino());
			}
		}
		return vecinos;
	}
	
	private Nodo getMinimo(Set<Nodo> vertices) {//Checkeado
		Nodo minimo = null;
		for (Nodo vert : vertices) {
			if (minimo == null) {
				minimo = vert;
			} else {
				if (getDistanciaMasCorta(vert) < getDistanciaMasCorta(minimo)) {
					minimo = vert;
				}
			}
		}
		return minimo;
	}

	private boolean estaTachado(Nodo nod){//Checkeado
		return nodosTachados.contains(nod);
	}
	
	private int getDistanciaMasCorta(Nodo destino) {//Checkeado
		Integer d = distancia.get(destino);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}
	
	public LinkedList<Nodo> getCamino(Nodo objetivo) {//Checkeado
		LinkedList<Nodo> camino = new LinkedList<Nodo>();
		Nodo posActual = objetivo;
		// Checkeo si existe un camino
		if (predecesores.get(posActual) == null) {
			return null;
		}
		camino.add(posActual);
		while (predecesores.get(posActual) != null) {
			posActual = predecesores.get(posActual);
			camino.add(posActual);
		}
		// Pongo la lista en el orden correcto
		Collections.reverse(camino);
		return camino;
	}
	
	public LinkedList<Integer>getTrace(LinkedList<Nodo> camino){
		LinkedList<Integer>trace = new LinkedList <Integer>();
		for(int i=0;i<camino.size();i++){
			if(i==camino.size()-1)break;
			trace.add(getDistancia(camino.get(i),camino.get(i+1)));
		}
		return trace;
	}
}
