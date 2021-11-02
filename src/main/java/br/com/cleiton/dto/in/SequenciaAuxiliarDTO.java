package br.com.cleiton.dto.in;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SequenciaAuxiliarDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<String> matriz;
	private int quantidadeSequencia;
	private String dna;
	private Boolean isSimian;

	public void adicionarValorQuantidade(int valor) {
		this.quantidadeSequencia += valor;
	}
	
}
