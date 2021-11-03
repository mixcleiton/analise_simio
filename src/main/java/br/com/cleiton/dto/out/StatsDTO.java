package br.com.cleiton.dto.out;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("count_simian_dna")
	private Long countSimianDna;
	
	@JsonProperty("count_human_dna")
	private Long countHumanDna;
	
	@JsonProperty("ratio")
	private BigDecimal ratio;
	
}
