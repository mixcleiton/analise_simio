package br.com.cleiton.service;

import org.springframework.stereotype.Service;

import br.com.cleiton.dto.out.StatsDTO;
import br.com.cleiton.repository.impl.SequenciaRepositoryImpl;

@Service
public class StatsService {

	private SequenciaRepositoryImpl repository;
	
	public StatsService(SequenciaRepositoryImpl repository) {
		this.repository = repository;
	}
	
	public StatsDTO getStats() {
		return this.repository.getStats();
	}
}
