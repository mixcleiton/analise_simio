package br.com.cleiton.dto.out;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AnaliseDTOTest {

	
	@Test
	public void analise_dto_get_set_test() {
		AnaliseDTO dto = new AnaliseDTO(null);
		
		dto.setSimian(Boolean.TRUE);
		
		assertEquals(dto.getSimian(), Boolean.TRUE);
		
	}
	
	@Test
	public void analise_dto_construtor_test() {
		AnaliseDTO dto = new AnaliseDTO(Boolean.FALSE);
		
		assertEquals(dto.getSimian(), Boolean.FALSE);
	}
	
	@Test
	public void analise_dto_to_string_test() {
		AnaliseDTO dto = new AnaliseDTO(Boolean.FALSE);
		
		assertEquals("AnaliseDTO(simian=false)", dto.toString());
	}
}
