package com.algaworks.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.cobranca.model.Cobranca;
import com.algaworks.cobranca.model.StatusCharge;
import com.algaworks.cobranca.repository.Cobrancas;
import com.algaworks.cobranca.repository.filter.CobrancaFilter;

@Service
public class CadastroCobrancaService {
	@Autowired
	private Cobrancas cobrancas;
	
	public void salvar(Cobranca cobranca) {
		try {
			cobrancas.save(cobranca);			
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	public void excluir(Long code) {
		cobrancas.deleteById(code);
	}
	
	public String receber(Long code) {
		Cobranca cobranca = cobrancas.getOne(code);
		cobranca.setCharge(StatusCharge.RECEBIDO);
		cobrancas.save(cobranca);
		
		return StatusCharge.RECEBIDO.getDescricao();
	}
	
	public List<Cobranca> filtrar(CobrancaFilter cobrancaFilter) {
		String filter = cobrancaFilter.getDescription() == null ? "" : cobrancaFilter.getDescription();
		
		return cobrancas.findByDescriptionContainingOrderByCodeAsc(filter);
	}
	
}
