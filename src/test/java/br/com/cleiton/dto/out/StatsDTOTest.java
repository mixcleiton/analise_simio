package br.com.cleiton.dto.out;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class StatsDTOTest {

	@Test
	public void stats_dto_get_set_test() {
		StatsDTO dto = new StatsDTO();
		dto.setCountHumanDna(1L);
		dto.setCountSimianDna(2L);
		dto.setRatio(new BigDecimal(3L));
		
		assertEquals(dto.getCountHumanDna(), 1L);
		assertEquals(dto.getCountSimianDna(), 2L);
		assertEquals(dto.getRatio(), new BigDecimal(3L));
		
	}
}
