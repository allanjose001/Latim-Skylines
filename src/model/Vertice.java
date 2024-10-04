package model;

public class Vertice implements Comparable<Vertice>{
	private int id;
	private String nomeCidade;
	private String nomeAeroporto;
	private double distancia; //esta distancia é usada no algoritmo de Dijkstra
	private Vertice anterior;
	
	public Vertice(int id, String nomeCidade, String nomeAeroporto) {

		this.id = id;
		this.nomeCidade = nomeCidade;
		this.nomeAeroporto = nomeAeroporto;
		this.distancia = 0.0;
		this.anterior = null;
	}
	
	public Vertice() {
		
	}
	
	//===================Metodos===================
	
	@Override
	public int compareTo(Vertice vertice) {
		return Double.compare(distancia, vertice.getDistancia());
	}

	@Override
	public String toString() {
		return "Vertice " + id + "\nCidade: " + nomeCidade + "\nAeroporto: " + nomeAeroporto + "\ndistancia: "
				+ distancia + "\nanterior: " + anterior;
	}
	
	//================getters e setters================

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getNomeAeroporto() {
		return nomeAeroporto;
	}

	public void setNomeAeroporto(String nomeAeroporto) {
		this.nomeAeroporto = nomeAeroporto;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public Vertice getAnterior() {
		return anterior;
	}

	public void setAnterior(Vertice anterior) {
		this.anterior = anterior;
	}
}