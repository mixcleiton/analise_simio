package br.com.cleiton.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.cleiton.dto.in.SequenciaAuxiliarDTO;
import br.com.cleiton.dto.in.SequenciaDTO;

@Component
public class SequenciaUtil {

	private static final int QUANTIDADE_SEQUENCIA_CONSIDERA_SIMIAN = 2;
	private static final int QUANTIDADE_CONSIDERA_SIMIAN = 4;
	private static final Logger LOGGER = LoggerFactory.getLogger(SequenciaUtil.class);
	
	public void percorrerSequencia(SequenciaDTO entrada, SequenciaAuxiliarDTO auxiliar) {
		List<String> matriz = entrada.getDna();
		
		for (int linha = 0; linha < matriz.size(); linha++) {
			
			percorrerHorizontal(auxiliar, linha);
			
			if (auxiliar.getQuantidadeSequencia() >= QUANTIDADE_SEQUENCIA_CONSIDERA_SIMIAN) {
				LOGGER.info("Valores encontrado no ponto da linha {}", linha);
				auxiliar.setIsSimian(Boolean.TRUE);
				break;
			}
			
			percorrerVerticalDiagonal(auxiliar, linha);
			
			if (auxiliar.getQuantidadeSequencia() >= QUANTIDADE_SEQUENCIA_CONSIDERA_SIMIAN) {
				LOGGER.info("Valores encontrado no ponto da linha {}", linha);
				auxiliar.setIsSimian(Boolean.TRUE);
				break;
			}
		}
		
		LOGGER.info("Sequencia mostra que o dna é simian: {}", auxiliar.getIsSimian());
	
	}
	
	private String carregarLetra(List<String> matriz, int linha, int coluna) {
		return matriz.get(linha).substring(coluna, coluna + 1);
	}

	private int percorrerVerticalDiagonal(SequenciaAuxiliarDTO auxiliar, int linha) {
		int quantidadeSequencia = 0;
		
		for (int coluna = 0; coluna < auxiliar.getMatriz().get(linha).length(); coluna++) {
			this.verificarColuna(auxiliar, linha, coluna);
			
			this.percorrerDiagonal(auxiliar, linha, coluna);
		}
		
		return quantidadeSequencia;
	}

	private void percorrerDiagonal(SequenciaAuxiliarDTO sequencia, int linha, int coluna) {
		String letra = this.carregarLetra(sequencia.getMatriz(), linha, coluna);
		
		this.percorrerDiagonalSubindo(sequencia, linha, letra, coluna);
		
		this.percorrerDiagonalDescendo(sequencia, linha, letra, coluna);
	}

	private void percorrerDiagonalSubindo(SequenciaAuxiliarDTO sequencia, int linha, String letra, int coluna) {
		int quantidadeLetrasIguais = 1;
		int colunaMovendo = coluna;
		
		for (int linhaSubindo = linha - 1; linhaSubindo >= 0; linhaSubindo--) {
			colunaMovendo += 1;
			
			if (colunaMovendo < sequencia.getMatriz().get(linha).length()) {
				String letraAuxiliar = this.carregarLetra(sequencia.getMatriz(), linhaSubindo, colunaMovendo);
				
				if (letra.equals(letraAuxiliar)) {
					quantidadeLetrasIguais += 1;
				} else {
					break;
				}
				
				quantidadeLetrasIguais = vereficarZerarValores(sequencia, quantidadeLetrasIguais);
				
			} else {
				break;
			}
		}
	}
	
	private void percorrerDiagonalDescendo(SequenciaAuxiliarDTO sequencia, int linha, String letra, int coluna) {
		int quantidadeLetrasIguais = 1;
		int colunaMovendo = coluna;
		
		for (int linhaDescendo = linha + 1; linhaDescendo < sequencia.getMatriz().size(); linhaDescendo++) {
			colunaMovendo += 1;
			
			if (colunaMovendo < sequencia.getMatriz().get(linha).length()) {

				String letraAuxiliar = this.carregarLetra(sequencia.getMatriz(), linhaDescendo, colunaMovendo);
				
				if (letra.equals(letraAuxiliar)) {
					quantidadeLetrasIguais += 1;
				} else {
					break;
				}
				
				quantidadeLetrasIguais = vereficarZerarValores(sequencia, quantidadeLetrasIguais);
				
			} else {
				break;
			}
		}
	}
	
	private void verificarColuna(SequenciaAuxiliarDTO sequencia, int linha, int coluna) {
		String letra = this.carregarLetra(sequencia.getMatriz(), linha, coluna);
		int quantidadeLetrasIguais = 1;
		
		for (int linhaAux = linha + 1; linhaAux < sequencia.getMatriz().size(); linhaAux++) {
			
			String letraAuxiliar = this.carregarLetra(sequencia.getMatriz(), linhaAux, coluna);
			
			if (!letra.equals(letraAuxiliar)) {
				break;
			} else {
				quantidadeLetrasIguais += 1;
			}
			
			quantidadeLetrasIguais = vereficarZerarValores(sequencia, quantidadeLetrasIguais);
		}
	}

	private int vereficarZerarValores(SequenciaAuxiliarDTO sequencia, int quantidadeLetrasIguais) {
		if (quantidadeLetrasIguais == QUANTIDADE_CONSIDERA_SIMIAN) {
			quantidadeLetrasIguais = 0;
			sequencia.adicionarValorQuantidade(1);
		}
		return quantidadeLetrasIguais;
	}

	private void percorrerHorizontal(SequenciaAuxiliarDTO sequencia, int linha) {
		String letra = "";
		int quantidadeLetraIguais = 1;
		
		for (int coluna = 0; coluna < sequencia.getMatriz().get(linha).length(); coluna++) {
			
			String letraAuxiliar = this.carregarLetra(sequencia.getMatriz(), linha, coluna);
			
			if (!letra.equals(letraAuxiliar)) {
				letra = letraAuxiliar;
				quantidadeLetraIguais = 1;
			} else {
				quantidadeLetraIguais += 1;
			}
			
			quantidadeLetraIguais = vereficarZerarValores(sequencia, quantidadeLetraIguais);
		}
	}
	
}
