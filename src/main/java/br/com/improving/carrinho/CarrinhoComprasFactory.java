package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 * 
 * As instâncias de CarrinhoComprasFactory são independentes entre si, ou seja, quando um carrinho 
 * para um cliente é criado em uma instância, a outra pode criar um novo carrinho para o mesmo 
 * cliente. Isso é verdade para todos os métodos.
 */
public class CarrinhoComprasFactory {
	private Map<String,CarrinhoCompras> carrinhosCompras = new HashMap<String,CarrinhoCompras>();
   

	/**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param identificacaoCliente
     * @return CarrinhoCompras
     */
    public CarrinhoCompras criar(String identificacaoCliente) {
    	if(carrinhosCompras.containsKey(identificacaoCliente)) {
    		return carrinhosCompras.get(identificacaoCliente);
    	}else {
    		CarrinhoCompras novoCarrinhoCompras = new CarrinhoCompras();
    		carrinhosCompras.put(identificacaoCliente, novoCarrinhoCompras);
    		return novoCarrinhoCompras;
    	}
    	
    }

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTicketMedio() {
    	List<CarrinhoCompras> listaValor = new ArrayList<>(getCarrinhosCompras().values());
    	BigDecimal soma = BigDecimal.ZERO;
    	soma = listaValor.stream().map(CarrinhoCompras::getValorTotal)
    			.reduce(BigDecimal.ZERO, BigDecimal::add);
    	BigDecimal tamanhoList = new BigDecimal(listaValor.size());
    	BigDecimal media = soma.divide(tamanhoList);
    	
    	return media.setScale(2,RoundingMode.HALF_UP);
    	}	   

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param identificacaoCliente
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidar(String identificacaoCliente) {
    	try {
    		carrinhosCompras.remove(identificacaoCliente);
    		return true;
    		
    	}catch(ExceptionCarrinho e) {
    		e.printStackTrace();
    		return false;
    	}
    

    }

	public Map<String, CarrinhoCompras> getCarrinhosCompras() {
		return carrinhosCompras;
	}

	public void setCarrinhosCompras(Map<String, CarrinhoCompras> carrinhosCompras) {
		this.carrinhosCompras = carrinhosCompras;
	}

	@Override
	public String toString() {
		return "CarrinhoComprasFactory [carrinhosCompras=" + carrinhosCompras + "]";
	}
    
}
