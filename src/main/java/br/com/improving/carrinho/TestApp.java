package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.util.Scanner;

public class TestApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		CarrinhoComprasFactory carrinho = new CarrinhoComprasFactory();
		
		System.out.print("numero de cliente: ");
		int numero = sc.nextInt();
		for(int n=0;n<numero;n++) {
			sc.nextLine();
			System.out.print("Entre com nome cliente: ");
			String cliente = sc.nextLine();
			
			System.out.print("Digite quantidade de produtos: ");
			int quantidadeProdutos = sc.nextInt();
			
			System.out.println("Entre com dados produto: ");
			for(int i=0;i<quantidadeProdutos;i++) {
				System.out.println("Cod_produto: ");
				Long codigo = sc.nextLong();
				sc.nextLine();
				System.out.print("produto: ");
				String nomeProd = sc.nextLine();
				
				System.out.print("Valor: ");
				BigDecimal valor  = sc.nextBigDecimal();
				
				System.out.print("Quantidade: ");
				int quantidade = sc.nextInt();
				
				
				
				
				Produto produto = new Produto(codigo,nomeProd);
				
				carrinho.criar(cliente).adicionarItem(produto, valor, quantidade);;
			
		}
		
		}//imprimindo elementos inseridos no carrinho
		for(CarrinhoCompras value : carrinho.getCarrinhosCompras().values()) {
			System.out.println(value+" valor Total: "+value.getValorTotal());
		
		}
		
		
		//obtendo valor do ticket gerado
		System.out.println("Tickets gerados: "+carrinho.getValorTicketMedio());

			
			
		}
		

}
