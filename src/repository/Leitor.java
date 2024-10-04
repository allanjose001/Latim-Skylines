package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Aresta;
import model.Grafo;
import model.Vertice;

public class Leitor {
	private ArrayList<Vertice> vertices;
	private ArrayList<Aresta> arestas;
	
	public Leitor() {
		vertices = new ArrayList<>();
		arestas = new ArrayList<>();
	}
	
	//============================Metodos==================================
	
	private String localArquivo = "src/repository/grafos.txt";
	
	public Grafo lerArquivos() throws IOException {
		BufferedReader leitor = new BufferedReader(new FileReader(localArquivo));
		
		String linha;
		
		boolean lendoVertices = false;
		boolean lendoArestas = false;
	
		while ((linha = leitor.readLine()) != null) {
			linha = linha.trim();
			
			//se tem linha em branco apenas a ignora
			if (linha.isEmpty()) {
				continue;
			}
			
			//verifica se está na parte de Vertices ou de arestas
			if (linha.startsWith("Vertices:")) {
				lendoVertices = true;
				lendoArestas = false;
				continue;
			} else if (linha.startsWith("Arestas:")) {
				lendoVertices = false;
				lendoArestas = true;
				continue;
			}
			
			//se estiver lendo Vertice ou Aresta, define apropiadamente o metodo
			//correto para a leitura
			if (lendoVertices) {
				lerVertice(linha);
			} else if (lendoArestas) {
				lerArestas(linha);
			}
			
		}
		leitor.close();
	
		return new Grafo(arestas, vertices);
	}
	
	public void lerVertice(String linha) {
		String[] partes = linha.split(";"); //a leitura é separada por espaços
		int id = Integer.parseInt(partes[0]); //a primeira parte é o id
		String nomeCidade = partes[1]; //a segunda parte é cidade
		String nomeAeroporto = partes[2]; //a terceira é o aeroporto
		
		//após dividir devidamente as variaveis, insere elas como um Vertice na lista de vertices
		vertices.add(new Vertice(id, nomeCidade, nomeAeroporto));
	}
	
	//mesma logica da leitura de Vertice
	public void lerArestas(String linha) {
		String[] partes = linha.split(";");
		int idV1 = Integer.parseInt(partes[0]);//as vertices são conectadas via seu id
		int idV2 = Integer.parseInt(partes[1]);
		
		double tempoViagem = Double.parseDouble(partes[2]);
		double precoPassagem = Double.parseDouble(partes[3]);
		
		Vertice v1 = buscaVerticePorId(idV1);
		Vertice v2 = buscaVerticePorId(idV2);
		
		if (v1 != null && v2 != null) {
			arestas.add(new Aresta(v1, v2, tempoViagem, precoPassagem));
		}
	}
	
	public Vertice buscaVerticePorId(int id) {
		for (Vertice vertice : vertices) {
			if (vertice.getId() == id) {
				return vertice;
			}
		}
		return null;
	}
	
}
