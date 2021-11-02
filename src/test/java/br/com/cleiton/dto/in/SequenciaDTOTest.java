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
public class SequenciaDTOTest {

	@Test
	public void sequenca_to_string_test() {
		SequenciaDTO dto = new SequenciaDTO();
		dto.setDna(Arrays.asList("ABCD"));
		
		assertEquals("SequenciaDTO(dna=[ABCD])", dto.toString());
	}
	
}
