package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Grafo {
	private final List<Nodo>nodos;
	private final List<Arista>aristas;
	private final int orden;//cantidad de nodos
	private final int tamaño;//cantidad de aristas
	private Nodo pInicial;
	private Nodo pFinal;
	
	public Grafo(String pathFile) throws FileNotFoundException{
		List<Nodo>nodos = new ArrayList<Nodo>();
		List<Arista>aristas= new ArrayList<Arista>();
		Scanner sc = new Scanner(new File(pathFile));
		sc.useLocale(Locale.US);
		orden = sc.nextInt();
		tamaño = sc.nextInt();
		pInicial = new Nodo("Nodo_"+sc.nextInt());
		pFinal = new Nodo("Nodo_"+sc.nextInt());
		for(int i=0;i<tamaño;i++){
			aristas.add(new Arista(sc.nextInt(),sc.nextInt(),sc.nextInt()));
		}
		for(int i=0;i<orden;i++){//generador de nodos
			nodos.add(new Nodo("Nodo_"+(i)));
		}
		this.nodos = new ArrayList <Nodo>(nodos);
		this.aristas = new ArrayList <Arista>(aristas);
		sc.close();
	}
	
	public List<Nodo> getNodos() {
		return nodos;
	}
	public List<Arista> getAristas() {
		return aristas;
	}
	public Nodo getpInicial() {
		return pInicial;
	}
	public Nodo getpFinal() {
		return pFinal;
	}
}
