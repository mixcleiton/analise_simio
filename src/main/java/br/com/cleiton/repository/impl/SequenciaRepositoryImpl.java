package br.com.cleiton.repository.impl;

import javax.persistence.EntityManager;

import org.hibernate.query.NativeQuery;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import br.com.cleiton.dto.out.StatsDTO;
import br.com.cleiton.mapper.StatsMapper;

@Repository
public class SequenciaRepositoryImpl {

	private EntityManager entityManager;
	private StatsMapper mapper;
	
	public SequenciaRepositoryImpl(EntityManager entityManager,
			StatsMapper mapper) {
		this.entityManager = entityManager;
		this.mapper = mapper;
	}
	
	public StatsDTO getStats() {
		String query = this.criarConsulta();
		
		Object[] valores = (Object[]) entityManager
			.createNativeQuery(query)
			.unwrap(NativeQuery.class)
			.addScalar("countHumanDna", LongType.INSTANCE)
			.addScalar("countSimianDna", LongType.INSTANCE)
			.addScalar("ratio", BigDecimalType.INSTANCE)
			.getSingleResult();
		
		return mapper.converterToDTO(valores);
	}
	
	private String criarConsulta() {
		StringBuilder query = new StringBuilder();
		
		query.append(" SELECT COUNT(HUMANO.DNA) as countHumanDna, \n");
		query.append("		  COUNT(SIMIAN.DNA) as countSimianDna, \n");
		query.append("		  round((COUNT(HUMANO.DNA) * 1.0) / (COUNT(SIMIAN.DNA) * 1.0), 2) as ratio \n");
		query.append(" FROM SEQUENCIA SEQ \n");
		query.append(" LEFT JOIN ( \n");
		query.append("    SELECT DNA \n");
		query.append("    FROM SEQUENCIA \n");
		query.append("    WHERE  \n");
		query.append("    SIMIAN = TRUE \n");
		query.append(" ) as SIMIAN ON \n");
		query.append(" SIMIAN.DNA = SEQ.DNA \n");
		query.append(" LEFT JOIN ( \n");
		query.append("    SELECT DNA \n");
		query.append("    FROM SEQUENCIA \n");
		query.append("    WHERE  \n");
		query.append("    SIMIAN = FALSE \n");
		query.append(" ) as HUMANO ON \n");
		query.append(" HUMANO.DNA = SEQ.DNA \n");
		
		return query.toString();
	}
}
