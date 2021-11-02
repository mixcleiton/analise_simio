package br.com.cleiton.mapper;

import org.springframework.stereotype.Component;

import br.com.cleiton.dto.in.SequenciaAuxiliarDTO;
import br.com.cleiton.entidade.Sequencia;

@Component
public class SequenciaMapper {

	public Sequencia converterParaEntidade(SequenciaAuxiliarDTO auxiliar) {
		return Sequencia.builder()
				.dna(auxiliar.getDna())
				.simian(auxiliar.getIsSimian())
				.build();
	}
	
}
