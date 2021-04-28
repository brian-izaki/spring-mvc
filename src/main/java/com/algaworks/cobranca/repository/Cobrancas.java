package com.algaworks.cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.cobranca.model.Cobranca;
import com.algaworks.cobranca.repository.filter.CobrancaFilter;

// os tipos do JpaRepository é uma entidade e o tipo de dado que é do ID que tem nela
public interface Cobrancas extends JpaRepository<Cobranca, Long>{
	
	List<Cobranca> findByDescriptionContainingOrderByCodeAsc(String filter);
	
}
