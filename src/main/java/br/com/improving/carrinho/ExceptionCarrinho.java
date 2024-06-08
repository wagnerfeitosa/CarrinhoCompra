package br.com.improving.carrinho;

public class ExceptionCarrinho extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ExceptionCarrinho(String msg) {
		super(msg);
	}

}
