package grafos;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[]args) throws FileNotFoundException{
		//Grafo grafo = new Grafo("C:/Users/usuario/Desktop/"+"Grafo.txt");
		
		Grafo grafo = new Grafo("C:/Users/Skyllnex/Desktop/"+"Grafo.txt");
		List<Nodo>nodos = new ArrayList <Nodo>(grafo.getNodos());
		List<Arista>aristas = new ArrayList <Arista>(grafo.getAristas());
		System.out.println("============Nodos============");
		for(Nodo nodo:nodos){
			System.out.println(nodo);
		}
		System.out.println("============Aristas============");
		for(Arista arista:aristas)System.out.println(arista);
		System.out.println("==========Camino de Dijkstra==========");
		AlgoritmoDijkstra dijkstra = new AlgoritmoDijkstra(grafo);
		dijkstra.resolver(grafo.getpInicial());
		LinkedList<Nodo>camino = dijkstra.getCamino(grafo.getpFinal());
		System.out.println("Nodo inicial: "+grafo.getpInicial()+" "+"Nodo Final: "+grafo.getpFinal());
		if(camino!=null){
			for(Nodo nod:camino){
				System.out.println(nod);
			}
			LinkedList<Integer>trace = dijkstra.getTrace(camino);
			System.out.println("=========Valores de los caminos utilizados=========");
			int aux=0;
			for(int track:trace){
				System.out.println(track);
				aux+=track;
			}
			System.out.println("Coste minimo de "+grafo.getpInicial()+" a "+grafo.getpFinal()+": "+aux);
		}else{System.out.println("No existe un camino posible");}
	}
}
