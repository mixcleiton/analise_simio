package br.com.cleiton.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.cleiton.dto.out.StatsDTO;
import br.com.cleiton.repository.impl.SequenciaRepositoryImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class StatsServiceTest {

	@Mock
	private SequenciaRepositoryImpl repository;
	
	private StatsService service;
	
	@BeforeEach
	public void before() {
		this.service = new StatsService(this.repository);
	}
	
	@Test
	public void getStats_test() {
		StatsDTO dtoMock = this.getStatsComValores();
		Mockito.lenient().when(repository.getStats()).thenReturn(dtoMock);
		
		StatsDTO dto = this.service.getStats();
		
		assertEquals(dto.getCountHumanDna(), dto.getCountHumanDna());
		assertEquals(dto.getCountSimianDna(), dto.getCountSimianDna());
		assertEquals(dto.getRatio(), dto.getRatio());
	}
	
	private StatsDTO getStatsComValores() {
		return StatsDTO.builder()
				.countHumanDna(100L)
				.countSimianDna(40L)
				.ratio(new BigDecimal(40L / 100L))
				.build();
	}
}
