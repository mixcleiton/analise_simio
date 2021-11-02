package br.com.cleiton.exception;

public class SequenciaExistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public SequenciaExistenteException(String mensagem) {
		super(mensagem);
	}
	
}
