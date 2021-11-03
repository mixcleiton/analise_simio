package br.com.cleiton.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleiton.dto.in.SequenciaDTO;
import br.com.cleiton.dto.out.AnaliseDTO;
import br.com.cleiton.exception.SequenciaRuntimeException;
import br.com.cleiton.service.SimianService;

@RestController
@RequestMapping("simian")
public class SimianController {

	private static final String MENSAGEM_ERRO_INTERNO = "Erro interno da aplicação";
	private SimianService service;
	
	public SimianController(SimianService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Object> validarDNA(@RequestBody SequenciaDTO entrada) {
		
		try {
			Boolean isSimian = this.service.validarDNA(entrada);
			return new ResponseEntity<>(new AnaliseDTO(isSimian), HttpStatus.OK);
		} catch (SequenciaRuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(MENSAGEM_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
