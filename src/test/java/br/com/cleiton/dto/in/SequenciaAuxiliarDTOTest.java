package br.com.cleiton.dto.in;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class SequenciaAuxiliarDTOTest {

	@Test
	public void adicionar_valor_quantidade_test() {
		
		SequenciaAuxiliarDTO dto 
			= new SequenciaAuxiliarDTO(null, 0, null, null);
		
		dto.setDna("DCBD");
		dto.setIsSimian(Boolean.FALSE);
		dto.setMatriz(Arrays.asList("DCBD"));
		dto.setQuantidadeSequencia(0);
		
		dto.adicionarValorQuantidade(2);
		
		assertEquals(dto.getQuantidadeSequencia(), 2);
	}
	
}
