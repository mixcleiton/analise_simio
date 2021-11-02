package br.com.cleiton.exception;

public class SequenciaRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SequenciaRuntimeException(String mensagem) {
		super(mensagem);
	}
	
}
