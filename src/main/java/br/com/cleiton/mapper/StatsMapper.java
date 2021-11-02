package br.com.cleiton.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.cleiton.dto.out.StatsDTO;
import br.com.cleiton.enums.StatsEnum;

@Component
public class StatsMapper {
	
	public StatsDTO converterToDTO(Object[] banco) {
		return StatsDTO
				.builder()
				.countHumanDna((Long) banco[StatsEnum.QUANTIDADE_HUMANO.ordinal()])
				.countSimianDna((Long) banco[StatsEnum.QUANTIDADE_SIMIANS.ordinal()])
				.ratio((BigDecimal) banco[StatsEnum.PROPORCAO.ordinal()])
				.build();
	}
	
}
