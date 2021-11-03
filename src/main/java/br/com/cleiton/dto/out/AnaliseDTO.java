package br.com.cleiton.dto.out;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AnaliseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("is_simian")
	private Boolean simian;
	
}
