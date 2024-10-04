package model;

import java.util.Objects;

public class Aresta{
	private Vertice v1;
	private Vertice v2;
	private double tempoViagem;
	private double precoPassagem;
	
	public Aresta(Vertice v1, Vertice v2, double tempoViagem, double precoPassagem) {
		this.v1 = v1;
		this.v2 = v2;
		this.tempoViagem = tempoViagem;
		this.precoPassagem = precoPassagem;
	}

	public Aresta() {

	}
	
	//===================metodos===================
	
	public Vertice adjacencia(Vertice vertice) {
		if (vertice.equals(v1)) {
			return v2;
		} else {
			return v1;
		}
	}
	

	/*
	@Override
	public int hashCode() {
		return Objects.hash(precoPassagem, tempoViagem, v1, v2);
	}

	//v1 e v2 são bidirecionais então é utilizado um OU para que a ordem de v1 e v2
	//não importe
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aresta other = (Aresta) obj;
		return Double.doubleToLongBits(precoPassagem) == Double.doubleToLongBits(other.precoPassagem)
				&& Double.doubleToLongBits(tempoViagem) == Double.doubleToLongBits(other.tempoViagem)
				&& Objects.equals(v1, other.v1) && Objects.equals(v2, other.v2)
				|| Objects.equals(v1, other.v2) && Objects.equals(v2, other.v1);
	}
	 */
	
	//================getters e setters================
	
	public Vertice getV1() {
		return v1;
	}

	public void setV1(Vertice v1) {
		this.v1 = v1;
	}

	public Vertice getV2() {
		return v2;
	}

	public void setV2(Vertice v2) {
		this.v2 = v2;
	}

	public double getTempoViagem() {
		return tempoViagem;
	}

	public void setTempoViagem(double tempoViagem) {
		this.tempoViagem = tempoViagem;
	}

	public double getPrecoPassagem() {
		return precoPassagem;
	}

	public void setPrecoPassagem(double precoPassagem) {
		this.precoPassagem = precoPassagem;
	}

	@Override
	public String toString() {
		return "\nArestas: " + v1.getNomeCidade() + ", " + v2.getNomeCidade() + "\nTempo de Viagem:" + tempoViagem + "\nPreço da Passagem: " + precoPassagem + "\n";
	}

}
