package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Dijkstra;
import model.Grafo;
import model.Vertice;
import repository.Leitor;

public class Menu {
	
	public void menu() throws IOException {
		Leitor leitor = new Leitor();
		Grafo grafo = new Grafo();
		Scanner scan = new Scanner(System.in);
		
		grafo = leitor.lerArquivos();
		String strInicial, strDestino = null;
		
		Vertice inicial = new Vertice();
		Vertice destino = new Vertice();

		int continuar = 0;
		
		while (continuar != -1) {
			imprimirMenu();
			String opcao = scan.nextLine().trim();
			switch(opcao) {
				case "1":
					grafo.imprimir();
					break;
					
				case "2":	
					System.out.println("\nDe que cidade deseja partir?");
					strInicial = scan.nextLine();
					
					System.out.println("\nPara qual Cidade deseja viajar?");
					strDestino = scan.nextLine();
					
					inicial = grafo.buscaVertice(grafo, strInicial);
					destino = grafo.buscaVertice(grafo, strDestino);
					
					
					if (inicial == null || destino == null) {
						System.out.println("\n\u001B[31m"+"Uma ou mais cidades não foram encontradas, voltando para o Menu.\n"+ "\u001b[0m");
					} else {
						Dijkstra dijkstra = new Dijkstra(grafo, inicial, destino);
						
						ArrayList<Vertice> caminho = dijkstra.procurarCaminhoPassagem(grafo, inicial, destino);
						
						System.out.println("\nVoce terá de pegar um voo para seguintes cidades: ");
						for (Vertice vertice : caminho) {
							System.out.println(vertice.getNomeCidade());
							
						}
						double valorTotal = dijkstra.calcularValorPassagens(caminho, grafo);
						double tempoTotal = dijkstra.calcularTempoViagem(caminho, grafo);
						System.out.println("\nA viagem custará um total de R$ " + valorTotal+ "");
						System.out.println("E o tempo total de viagem será de " + tempoTotal+ " horas\n");
					}
					
					break;
					
				case "3":
					System.out.println("\nDe que cidade deseja partir?");
					strInicial = scan.nextLine();
					
					System.out.println("\nPara qual Cidade deseja viajar?");
					strDestino = scan.nextLine();
					
					inicial = grafo.buscaVertice(grafo, strInicial);
					destino = grafo.buscaVertice(grafo, strDestino);
					
					
					if (inicial == null || destino == null) {
						System.out.println("\n\u001B[31m"+"Uma ou mais cidades não foram encontradas, voltando para o Menu.\n"+ "\u001b[0m");
					} else {
						Dijkstra dijkstra = new Dijkstra(grafo, inicial, destino);
						
						ArrayList<Vertice> caminho = dijkstra.procurarCaminhoTempo(grafo, inicial, destino);
						
						System.out.println("\nVoce terá de pegar um voo para seguintes cidades: ");
						for (Vertice vertice : caminho) {
							System.out.println(vertice.getNomeCidade());
							
						}
						double tempoTotal = dijkstra.calcularTempoViagem(caminho, grafo);
						double valorTotal = dijkstra.calcularValorPassagens(caminho, grafo);
						System.out.println("\nO tempo total de viagem será de " + tempoTotal + " horas");
						System.out.println("E custará um total de R$ "+ valorTotal + "\n");
					}
					
					break;
					
				case "0":
					System.out.println("saindo");
					continuar = -1;
					break;
					
				default: 
					System.out.println("\u001B[31m"+"\nOpção invalida\n"+"\u001b[0m");
					break;
			}
			
		}
		
		
	}
	
	public void imprimirMenu() {
		System.out.println("================== Menu ================");
		System.out.println(" 1 - Mostrar destinos para viajar");
		System.out.println(" 2 - Encontrar rota mais barata");
		System.out.println(" 3 - Encontrar rota mais rapida");
		System.out.println(" 0 - Sair");
		System.out.println("========================================");
	}
	
}
