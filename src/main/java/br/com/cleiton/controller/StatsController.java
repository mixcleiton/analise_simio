package br.com.cleiton.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleiton.service.StatsService;

@RestController
@RequestMapping("stats")
public class StatsController {

	private static final String MENSAGEM_ERRO_INTERNO = "Erro interno da aplicação";
	private static final Logger LOGGER = LoggerFactory.getLogger(StatsController.class);
	
	private StatsService service;
	
	public StatsController(StatsService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Object> getStats() {
		
		try {
			return new ResponseEntity<>(this.service.getStats(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Erro ao tentar ver os stats", e);
			return new ResponseEntity<>(MENSAGEM_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
