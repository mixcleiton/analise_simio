package br.com.cleiton.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.cleiton.dto.in.SequenciaAuxiliarDTO;
import br.com.cleiton.dto.in.SequenciaDTO;
import br.com.cleiton.entidade.Sequencia;
import br.com.cleiton.exception.SequenciaExistenteException;
import br.com.cleiton.exception.SequenciaRuntimeException;
import br.com.cleiton.mapper.SequenciaMapper;
import br.com.cleiton.repository.SequenciaRepository;
import br.com.cleiton.util.SequenciaUtil;

@Service
public class SimianService {

	private static final String MENSAGEM_LETRAS_INVALIDAS = "Foram identificadas as seguinte(s) letra(s) inválidas na matriz: ";
	private static final String MATRIZ_NAO_E_NXN = "Matriz utilizada não é NxN";
	private static final String OBRIGATORIO_SEQUENCIA = "é obrigatório informar uma sequência de DNA";
	private static final Logger LOGGER = LoggerFactory.getLogger(SimianService.class);
	private static final List<String> LETRAS_VALIDAS = Arrays.asList("A", "T", "C", "G");
	private SequenciaUtil sequenciaUtil;
	private SequenciaRepository repository;
	private SequenciaMapper mapper;
	
	public SimianService(SequenciaUtil sequenciaUtil,
			SequenciaRepository repository,
			SequenciaMapper mapper) {
		this.sequenciaUtil = sequenciaUtil;
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public Boolean validarDNA(SequenciaDTO entrada) {
		SequenciaAuxiliarDTO auxiliar = 
				new SequenciaAuxiliarDTO(entrada.getDna(), 0, Strings.EMPTY, Boolean.FALSE);
		
		this.validarEntrada(entrada);
		
		try {
			this.validarSequenciaExistente(entrada, auxiliar);
			
			sequenciaUtil.percorrerSequencia(entrada, auxiliar);
		
			this.salvarSequencia(auxiliar);
		} catch (SequenciaExistenteException e) {
			LOGGER.debug("sequência já existe e não precisa ser validada.");
		}
		
		return auxiliar.getIsSimian();
	}
	
	private void salvarSequencia(SequenciaAuxiliarDTO auxiliar) {
		
		this.repository.save(this.mapper.converterParaEntidade(auxiliar));
		
	}
	
	private void validarSequenciaExistente(SequenciaDTO entrada, SequenciaAuxiliarDTO auxiliar)
			throws SequenciaExistenteException {
		String sequencia = carregarDNAEmString(entrada);
		
		auxiliar.setDna(sequencia);
		
		Optional<Sequencia> entidade = this.repository.findById(sequencia);
		
		if (entidade.isPresent()) {
			auxiliar.setIsSimian(entidade.get().getSimian());
			throw new SequenciaExistenteException("Sequencia de DNA já foi validada");
		}
	}

	public String carregarDNAEmString(SequenciaDTO entrada) {
		String sequencia = "";
		
		for (String dna : entrada.getDna()) {
			sequencia = sequencia.concat(dna);
		}
		return sequencia;
	}
	
	private void validarEntrada(SequenciaDTO entrada) {
		
		LOGGER.info("Validando os dados de entrada");
		
		this.validarMatrizObrigatoria(entrada);
 	
		this.validarTamanhoMatriz(entrada);
		
		this.validarLetras(entrada);
		
		LOGGER.info("Dados validados com sucesso");
	}

	private void validarMatrizObrigatoria(SequenciaDTO entrada) {
		if (entrada == null || entrada.getDna() == null || entrada.getDna().isEmpty()) {
			throw new SequenciaRuntimeException(OBRIGATORIO_SEQUENCIA);
		}
	}

	private void validarTamanhoMatriz(SequenciaDTO entrada) {
		Boolean tamanhoErrado = entrada.getDna().stream().filter(dna -> {
			return dna.length() != entrada.getDna().size();
		}).count() > 0;
		
		if (Boolean.TRUE.equals(tamanhoErrado)) {
			throw new SequenciaRuntimeException(MATRIZ_NAO_E_NXN);
		}
	}

	private void validarLetras(SequenciaDTO entrada) {
		String auxiliar = "";
		
		for (String dna: entrada.getDna()) {
			List<Character> valores = dna.chars().
					mapToObj(e -> (char)e).collect(Collectors.toList());
			
			String valoresFiltrados = this.carregarLetrasNaoValidas(valores);
			
			auxiliar = this.carregarLetrasNaoValidadasParaMensagem(auxiliar, valoresFiltrados);
		}
		
		Boolean letraInvalida = auxiliar.length() > 0;		
		
		this.verificarLetrasInvalidas(auxiliar, letraInvalida);
	}

	private void verificarLetrasInvalidas(String auxiliar, Boolean letraInvalida) {
		if (Boolean.TRUE.equals(letraInvalida)) {
			throw new SequenciaRuntimeException(MENSAGEM_LETRAS_INVALIDAS.concat(auxiliar));
		}
	}

	private String carregarLetrasNaoValidadasParaMensagem(String auxiliar, String valoresFiltrados) {
		if (!"[]".equals(valoresFiltrados)) {
			auxiliar = auxiliar.concat(valoresFiltrados);
		}
		
		return auxiliar;
	}

	private String carregarLetrasNaoValidas(List<Character> valores) {
		return valores.stream().filter(letra -> 
			!LETRAS_VALIDAS.contains(letra.toString()))
				.collect(Collectors.toList()).toString();
	}
}
