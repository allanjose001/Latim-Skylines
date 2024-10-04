package view;

import java.io.IOException;
import java.util.ArrayList;

import model.Aresta;
import model.Dijkstra;
import model.Grafo;
import model.Vertice;
import repository.Leitor;

public class Main {

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu();
		
		menu.menu();
		
		/*
		
		ArrayList<Aresta> arestas = new ArrayList<Aresta>();
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();
		
		Vertice v0 = new Vertice(0, "cidade0", "cidade0porto");
		Vertice v1 = new Vertice(1, "cidade1", "cidade1porto");
		Vertice v2 = new Vertice(2, "cidade2", "cidade2porto");
		Vertice v3 = new Vertice(3, "cidade3", "cidade3porto");
		Vertice v4 = new Vertice(4, "cidade4", "cidade4porto");
		Vertice v5 = new Vertice(5, "cidade5", "cidade5porto");
		Vertice v6 = new Vertice(6, "cidade6", "cidade6porto");
		Vertice v7 = new Vertice(7, "cidade7", "cidade7porto");
		
		vertices.add(v0);
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		vertices.add(v4);
		vertices.add(v5);
		vertices.add(v6);
		vertices.add(v7);

		Aresta a0 = new Aresta(v0, v1, 4.0, 150.0);
		Aresta a1 = new Aresta(v1, v2, 2.0, 300.0);
		Aresta a2 = new Aresta(v2, v3, 3.0, 200.0);
		Aresta a3 = new Aresta(v2, v4, 2.0, 200.0);
		Aresta a4 = new Aresta(v3, v0, 2.0, 650.0);
		Aresta a5 = new Aresta(v3, v4, 3.0, 200.0);
		Aresta a6 = new Aresta(v4, v0, 2.0, 150.0);

		arestas.add(a0);
		arestas.add(a1);
		arestas.add(a2);
		arestas.add(a3);
		arestas.add(a4);
		arestas.add(a5);
		arestas.add(a6);

		Grafo grafo = new Grafo(arestas, vertices);
		
		String strInicial = "cidade0";
		String strDestino = "cidade3";
		
		Vertice inicial = grafo.buscaVertice(grafo, strInicial);
		Vertice destino = grafo.buscaVertice(grafo, strDestino);
		
	
		Dijkstra dijkstra = new Dijkstra(grafo, inicial, destino);
		
		ArrayList<Vertice> caminho = dijkstra.procurarCaminhoTempo(grafo, inicial, destino);
		
		System.out.println("voce terá de pegar um voo nas seguintes cidades: ");
		for (Vertice vertice : caminho) {
			System.out.println(vertice.getNomeCidade());
		}
		
		double valorTotal = dijkstra.calcularValorPassagens(caminho, grafo);
		double tempoTotal = dijkstra.calcularTempoViagem(caminho, grafo);
		System.out.println("valor total da viagem: " + valorTotal);
		System.out.println("e ira levar um total de " + tempoTotal +" horas");
	
		}
	
		
		public static Vertice buscaVertice(Grafo grafo, String nome) {
			for (Vertice vertice : grafo.getVertices()) {
				if (vertice.getNomeCidade().equalsIgnoreCase(nome)) {
					return vertice;
				}
			}
			return null;
		
		*/
	}
		
}