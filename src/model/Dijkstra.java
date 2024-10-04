package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	private Grafo grafo;
	private Vertice inicial;
	private Vertice destino;
	private ArrayList<Vertice> caminhoMinimo;
	private List<Vertice> naoPercorridos;
	
	
	public Dijkstra(Grafo grafo, Vertice inicial, Vertice destino) {
		this.grafo = grafo;
		this.inicial = inicial;
		this.destino = destino;
		this.caminhoMinimo = new ArrayList<>();
		this.naoPercorridos = new ArrayList<>();
		inicializarGrafo(grafo, inicial);
	}
	
	public Dijkstra() {
		
	}
	
	//===================Metodos===================
	
	//define a distancia de todos os vertice do grafo como sendo infinito
	//exceto o Vertice inicial, já que ele será 0
	private void inicializarGrafo(Grafo grafo, Vertice inicial) {
		//isso é necessario senão o codigo quebra depois de fazer 5 ou 6 buscas
		//sem parar e iniciar novamente o codigo
		caminhoMinimo.clear();
		naoPercorridos.clear();
		
		caminhoMinimo.add(inicial);
		for(Vertice vertice : grafo.getVertices()) {
			if(!vertice.equals(inicial)) {
				vertice.setDistancia(Double.POSITIVE_INFINITY);
				
			} else {
				vertice.setDistancia(0);
			}
			this.naoPercorridos.add(vertice);
		}
		//ordena em ordem crescente, assim o vertice Inicial ficará no inicio da lista
		//e o restante ficará do mesmo jeito
		Collections.sort(naoPercorridos);
	}
	
	//procura o caminho com menor custo de passagem, para isso, aquela variavel Distancia 
	//declarada no vertice será a soma de todos os parametros sendo considerados, neste caso
	//ele assumira a soma dos valores da passagens
	public ArrayList<Vertice> procurarCaminhoPassagem(Grafo grafo, Vertice inicial, Vertice destino) {
		inicializarGrafo(grafo, inicial);
		
		//armazena os vertices que precisam ser processados priorizando o vertice
		//com a menor distancia conhecida
		PriorityQueue<Vertice> filaVertice = new PriorityQueue<>();
		filaVertice.add(inicial);
		
		while(!filaVertice.isEmpty()) {
			//retira o vertice com menor valor de distancia da lista para ser utilizado
			Vertice atual = filaVertice.poll();
			
			//getIrmaos retorna uma lista com todos as ARESTAS que estão conectadas a este vertice
			for(Aresta aresta : grafo.getIrmaos(atual)) {
				//irmao será o VERTICE do outro lado da aresta que os conecta
				Vertice irmao = aresta.adjacencia(atual);
				
				double distanciaTemp = atual.getDistancia() + aresta.getPrecoPassagem();
				
				//se a soma de distancia for menor do que a distancia armazenada anteriormente
				//neste irmão, então quer dizer que o caminho atual é o menor caminho
				if (distanciaTemp < irmao.getDistancia()) {
					//remove esse irmao lista de prioridade, é necessario pois a lista de prioridade
					//não se atualiza sozinha, então apenas removo o elemento, atualizo seus parametros 
					//e o re-adiciono devolta na lista
					filaVertice.remove(irmao);
					
					//como eu encontrei um caminho mais curto para chegar neste irmão, então eu preciso
					//atualizar a distancia dele
					irmao.setDistancia(distanciaTemp);
					//e atualizar o ponteiro que diz para que direção é o caminho mais curto
					irmao.setAnterior(atual);
					
					
					//re-adiciono este irmão a fila de pioridade com os parametros atualizados
					filaVertice.add(irmao);
				}
			}
		}
		ArrayList<Vertice> menorCaminho = getMenorCaminho(destino);
		
		//isto é feito para garantir que o programa não pare
		//após algumas buscas sem reiniciar o codigo
		limparEstruturas(grafo);
		
		return menorCaminho;
	}
	
	public ArrayList<Vertice> procurarCaminhoTempo(Grafo grafo, Vertice inicial, Vertice destino) {
		inicializarGrafo(grafo, inicial);
		
		PriorityQueue<Vertice> filaVertice = new PriorityQueue<>();
		filaVertice.add(inicial);
		
		while(!filaVertice.isEmpty()) {
			Vertice atual = filaVertice.poll();
			
			for(Aresta aresta : grafo.getIrmaos(atual)) {
				Vertice irmao = aresta.adjacencia(atual);
				
				double distanciaTemp = atual.getDistancia() + aresta.getTempoViagem();
				
				if (distanciaTemp < irmao.getDistancia()) {
					filaVertice.remove(irmao);
					
					irmao.setDistancia(distanciaTemp);
					irmao.setAnterior(atual);
					
					filaVertice.add(irmao);
					
				}
			}
		}
		ArrayList<Vertice> menorCaminho = getMenorCaminho(destino);

		limparEstruturas(grafo); 
		
		return menorCaminho;
	
	}
	
	private ArrayList<Vertice> getMenorCaminho(Vertice destino) {
		ArrayList<Vertice> listaVertice = new ArrayList<>();
		
		//percorre a lista com o menor caminho a partir do vertice de destino até o vertice inicial
		//e adiciona os vertices na listaVertice
		for(Vertice vertice = destino; vertice != null; vertice = vertice.getAnterior()) {
			listaVertice.add(vertice);
		}
		
		//como os vertice da lista estão de trás para frente, então inverte ela para que fique certo
		Collections.reverse(listaVertice);
		return listaVertice;
	}
	
	//essa disgraça é necessaria para limpar os ponteiros do grafos antes de fazer cada
	//busca, senão ele pode acabar entrando em um loop de ponteiros errados
	//que faz o eclipse sugar 4gb de RAM e dar erro de memoria :D
	public void limparEstruturas(Grafo grafo) {
	    caminhoMinimo.clear();
	    naoPercorridos.clear();
	    for (Vertice v : grafo.getVertices()) {
	        v.setAnterior(null);
	        v.setDistancia(Double.POSITIVE_INFINITY); 
	    }
	}
	
	public double calcularValorPassagens(ArrayList<Vertice> caminho, Grafo grafo) {
		double valorTotal = 0;
		
		for (int i = 0; i < caminho.size() -1; i++) {
			Vertice origem = caminho.get(i);
			Vertice prox = caminho.get(i+1);
			Aresta aresta = grafo.buscarAresta(origem, prox);
			if (aresta != null) {
				valorTotal += aresta.getPrecoPassagem();
			}
		}
		return valorTotal;
	}
	
	public double calcularTempoViagem(ArrayList<Vertice> caminho, Grafo grafo) {
		double valorTotal = 0;
		
		for (int i = 0; i < caminho.size() -1; i++) {
			Vertice origem = caminho.get(i);
			Vertice prox = caminho.get(i+1);
			Aresta aresta = grafo.buscarAresta(origem, prox);
			if (aresta != null) {
				valorTotal += aresta.getTempoViagem();
			}
		}
		return valorTotal;
	}
	
	//===================Getters e setters===================	

	public Grafo getGrafo() {
		return grafo;
	}

	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}

	public Vertice getInicial() {
		return inicial;
	}

	public void setInicial(Vertice inicial) {
		this.inicial = inicial;
	}

	public Vertice getDestino() {
		return destino;
	}

	public void setDestino(Vertice destino) {
		this.destino = destino;
	}

	public ArrayList<Vertice> getCaminhoMinimo() {
		return caminhoMinimo;
	}

	public void setCaminhoMinimo(ArrayList<Vertice> caminhoMinimo) {
		this.caminhoMinimo = caminhoMinimo;
	}

	public List<Vertice> getNaoPercorridos() {
		return naoPercorridos;
	}

	public void setNaoPercorridos(List<Vertice> naoPercorridos) {
		this.naoPercorridos = naoPercorridos;
	}
	
}