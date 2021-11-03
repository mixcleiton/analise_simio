package br.com.cleiton.dto.in;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SequenciaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> dna;
	
}
