package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {
	private List<Item> itens = new ArrayList<>();

	/**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
	
	
    public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
    	
    	Item novoItem;
    	int itemEncontrado = encontraItem(produto);
    	try {
    		if(!itens.isEmpty()) {
    			
    			if(itemEncontrado < 0) {
    				novoItem = new Item(produto,valorUnitario,quantidade);
    				itens.add(novoItem);
        				
        		}else {
        			Item item = getItens().get(itemEncontrado);
    				item.setQuantidade(item.getQuantidade() + quantidade);
    				if(item.getValorUnitario() != valorUnitario) {
    					item.setValorUnitario(valorUnitario);
       
        				}
        			}
        		}else {
        			novoItem = new Item(produto,valorUnitario,quantidade);
        			itens.add(novoItem);
        		}
    		
    	}catch(ExceptionCarrinho e) {
    		e.printStackTrace();
    	}
      	
    }
    //metodo para encontrar produto retorna o index
public int encontraItem(Produto produto) {
	int itemEncontrado = -1;
	for(int index=0; index<itens.size() & itemEncontrado<0; index++) {
		if(itens.get(index).getProduto().equals(produto)) {
			itemEncontrado = index;
		}
	}
	return itemEncontrado;
}

	/**
	 * Permite a remoção do item que representa este produto do carrinho de compras.
	 *
	 * @param produto
	 * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e
	 *         false caso o produto não exista no carrinho.
	 */
	public boolean removerItem(Produto produto) {
		int itemEncontrado = encontraItem(produto);
		if(itemEncontrado>0) {
			itens.remove(itemEncontrado);
			return true;
			
		}
		return false;

	}

	/**
	 * Permite a remoção do item de acordo com a posição. Essa posição deve ser determinada pela
	 * ordem de inclusão do produto na coleção, em que zero representa o primeiro item.
	 *
	 * @param posicaoItem
	 * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e
	 *         false caso o produto não exista no carrinho.
	 */
	public boolean removerItem(int posicaoItem) {
		itens.remove(posicaoItem);
		return true;

	}

	/**
	 * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais de todos
	 * os itens que compõem o carrinho.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorTotal() {
		BigDecimal valor = BigDecimal.ZERO;
		valor = getItens().stream()
				.map(Item::getValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return valor;
	}

	/**
	 * Retorna a lista de itens do carrinho de compras.
	 *
	 * @return itens
	 */
	public List<Item> getItens() {
		return itens;

	}

	@Override
	public String toString() {
		return "CarrinhoCompras [itens=" + itens + "]";
	}

}