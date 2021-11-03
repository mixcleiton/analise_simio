package br.com.cleiton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cleiton.entidade.Sequencia;

@Repository
public interface SequenciaRepository extends JpaRepository<Sequencia, String> {
	
}
