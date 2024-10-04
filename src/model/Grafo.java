package model;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
	private ArrayList<Aresta> arestas;
	private ArrayList<Vertice> vertices;
	
	public Grafo(ArrayList<Aresta> arestas, ArrayList<Vertice> vertices) {
		this.arestas = arestas;
		this.vertices = vertices;
	}

	public Grafo() {

	}

	//===================Metodos===================
	
	//retorna uma lista com todas as arestas que conectam o vertice com outros vertices
	public List<Aresta> getIrmaos(Vertice vertice) {
		return arestas.stream().filter(aresta-> aresta.getV1().equals(vertice) 
				|| aresta.getV2().equals(vertice)).toList();
	}
	
	public Vertice buscaVertice(Grafo grafo, String nome) {
		for (Vertice vertice : grafo.getVertices()) {
			if (vertice.getNomeCidade().equalsIgnoreCase(nome)) {
				return vertice;
			}
		}
		
		return null;
	}
	
	//retorna a aresta que conecta 2 vertices
	public Aresta buscarAresta(Vertice v1, Vertice v2) {
		for (Aresta aresta: arestas) {
			if ((aresta.getV1().equals(v1) && aresta.getV2().equals(v2)) || 
				 aresta.getV1().equals(v2) && aresta.getV2().equals(v1)) {
				return aresta;
			}
		}
		return null;
	}
	
	//não é utilizado neste projeto, mas aqui está a implementação
	public void buscaEmLargura(String strInicial, String strDestino) {
		ArrayList<Vertice> marcados = new ArrayList<Vertice>();
		ArrayList<Vertice> fila = new ArrayList<Vertice>();
		
		Vertice inicial = null;
		Vertice destino = null;

		for (Vertice vertice : this.vertices) {
			if (vertice.getNomeCidade().equalsIgnoreCase(strInicial)) {
				inicial = vertice;
			}
			
			if (vertice.getNomeCidade().equalsIgnoreCase(strDestino)) {
				destino = vertice;
			}
		}
		
		if (inicial == null) {
			System.out.println("cidade inicial não encontrada");
			return;
		}
		if (destino == null) {
			System.out.println("cidade de destino não encontrada");
		}
		
		marcados.add(inicial);
		System.out.println("rota mais rapida para o destino: ");
		System.out.println(inicial.getNomeCidade());
		fila.add(inicial);
		
		while(fila.size() > 0) {
			//um vertice visitado significa que todas suas arestas foram vistas
			Vertice visitado = fila.remove(0);
			
			if (visitado.equals(destino)) {
				System.out.println("fim da busca");
				return;
			}
			
			for (Aresta aresta: this.arestas) {
				if (aresta.getV1().equals(visitado)) {
					Vertice adjacente = aresta.getV2();
					
					if (adjacente != null && !marcados.contains(adjacente)) {
						marcados.add(adjacente);
						System.out.println(adjacente.getNomeCidade());
						fila.add(adjacente);
					}
				}
			}
		}
		
		System.out.println("Destino não pode ser alcancado\n");
	}
	
	public void imprimir() {
		for (Vertice vertice : vertices) {
			System.out.println("===================");
			System.out.println(" " + vertice.getNomeCidade());
			System.out.println(" " + vertice.getNomeAeroporto());
			System.out.println();

		}
	}
	
	//================getters e setters============
	
	public ArrayList<Aresta> getArestas() {
		return arestas;
	}

	public void setArestas(ArrayList<Aresta> arestas) {
		this.arestas = arestas;
	}

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Vertice> vertices) {
		this.vertices = vertices;
	}

	@Override
	public String toString() {
		return "Grafo [arestas=" + arestas + ", vertices=" + vertices + "]";
	}
	
}
