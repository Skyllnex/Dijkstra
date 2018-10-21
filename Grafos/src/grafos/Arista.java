package grafos;

public class Arista {
	private final Nodo origen;//nodo de origen
	private final Nodo destino;//nodo destino
	private final int distancia; // distancia de la arista
	
	public Arista(int origen,int destino,int distancia){
		this.origen = new Nodo("Nodo_"+origen);
		this.destino = new Nodo("Nodo_"+destino);
		this.distancia = distancia;
	}

	public Nodo getOrigen() {
		return origen;
	}
	public Nodo getDestino() {
		return destino;
	}
	public int getDistancia() {
		return distancia;
	}

	@Override
	public String toString() {
		return origen +" - "+ destino + " - " + distancia;
	}
}
