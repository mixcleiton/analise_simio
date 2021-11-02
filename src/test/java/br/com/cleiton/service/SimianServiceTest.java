package br.com.cleiton.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.cleiton.dto.in.SequenciaDTO;
import br.com.cleiton.entidade.Sequencia;
import br.com.cleiton.exception.SequenciaRuntimeException;
import br.com.cleiton.mapper.SequenciaMapper;
import br.com.cleiton.repository.SequenciaRepository;
import br.com.cleiton.util.SequenciaUtil;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class SimianServiceTest {

	@Mock
	private SequenciaRepository repository;
	
	private SequenciaUtil sequenciaUtil;
	private SequenciaMapper sequenciaMapper;
	private SimianService service;
	
	@BeforeEach
	public void before() {
		this.sequenciaUtil = new SequenciaUtil();
		this.sequenciaMapper = new SequenciaMapper();
		this.service = new SimianService(sequenciaUtil, repository, sequenciaMapper);
	}
	
	@Test
	public void matriz_obrigatoria_test() {
		RuntimeException exception = assertThrows(SequenciaRuntimeException.class,
				() -> this.service.validarDNA(new SequenciaDTO()));
		
		assertEquals("é obrigatório informar uma sequência de DNA", exception.getMessage());
	}
	
	@Test
	public void matriz_tamanho_test() {
		SequenciaDTO dto = new SequenciaDTO();
		List<String> matriz = Arrays.asList("TGCT", "ATGC", "TGATA", "TATC");
		dto.setDna(matriz);
		
		RuntimeException exception = assertThrows(SequenciaRuntimeException.class,
				() -> this.service.validarDNA(dto));
		
		assertEquals("Matriz utilizada não é NxN", exception.getMessage());
	}
	
	@Test
	public void letras_invalidas_test() {
		SequenciaDTO dto = new SequenciaDTO();
		List<String> matriz = Arrays.asList("TGCB", "ATGC", "TGAT", "TATC");
		dto.setDna(matriz);
		
		RuntimeException exception = assertThrows(SequenciaRuntimeException.class,
				() -> this.service.validarDNA(dto));
		
		assertEquals("Foram identificadas as seguinte(s) letra(s) inválidas na matriz: [B]", exception.getMessage());
	}
	
	@Test
	public void sequencia_humano_existente_banco_test() {
		SequenciaDTO dto = carregarSequenciaHumanoValida();
		
		Optional<Sequencia> entidade = Optional.of(this.criarSequenciaHumanoValida());
		Mockito.lenient().when(repository.findById(service.carregarDNAEmString(dto))).thenReturn(entidade);
		
		assertEquals(this.service.validarDNA(dto), Boolean.FALSE);
	}

	private SequenciaDTO carregarSequenciaHumanoValida() {
		SequenciaDTO dto = new SequenciaDTO();
		List<String> matriz = this.carregarDNAHumanoValido();
		dto.setDna(matriz);
		return dto;
	}
	
	private List<String> carregarDNAHumanoValido() {
		return Arrays.asList("TGCA", "ATGC", "TGAT", "TATC");
	}
	
	private Sequencia criarSequenciaHumanoValida() {
		SequenciaDTO dto = carregarSequenciaSimianValida();
		
		return Sequencia.builder()
				.dna(this.service.carregarDNAEmString(dto))
				.simian(Boolean.FALSE)
				.build();
	}
	
	@Test
	public void sequencia_simian_existente_banco_test() {
		SequenciaDTO dto = carregarSequenciaSimianValida();
		
		Optional<Sequencia> entidade = Optional.of(this.criarSequenciaSimianValida());
		Mockito.lenient().when(repository.findById(service.carregarDNAEmString(dto))).thenReturn(entidade);
		
		assertEquals(this.service.validarDNA(dto), Boolean.TRUE);
	}
	
	private SequenciaDTO carregarSequenciaSimianValida() {
		SequenciaDTO dto = new SequenciaDTO();
		List<String> matriz = this.carregarDNASimianValido();
		dto.setDna(matriz);
		return dto;
	}
	
	private List<String> carregarDNASimianValido() {
		return 
			Arrays.asList("AAAA", 
						  "ATGA", 
						  "TGAA", 
						  "TATA");
	}
	
	private Sequencia criarSequenciaSimianValida() {
		SequenciaDTO dto = carregarSequenciaSimianValida();
		
		return Sequencia.builder()
				.dna(this.service.carregarDNAEmString(dto))
				.simian(Boolean.TRUE)
				.build();
	}
	
	@Test
	public void sequencia_humano_nao_existente_banco_test() {
		SequenciaDTO dto = carregarSequenciaHumanoValida();
		
		Optional<Sequencia> entidade = Optional.empty();
		Mockito.lenient().when(repository.findById(service.carregarDNAEmString(dto))).thenReturn(entidade);
		
		assertEquals(this.service.validarDNA(dto), Boolean.FALSE);
	}
	
	@Test
	public void sequencia_simian_nao_existente_banco_test() {
		SequenciaDTO dto = carregarSequenciaHumanoValida();
		
		Optional<Sequencia> entidade = Optional.of(this.criarSequenciaSimianValida());
		Mockito.lenient().when(repository.findById(service.carregarDNAEmString(dto))).thenReturn(entidade);
		
		assertEquals(this.service.validarDNA(dto), Boolean.TRUE);
	}
}
