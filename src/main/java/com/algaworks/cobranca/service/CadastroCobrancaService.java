package com.algaworks.cobranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.cobranca.model.Cobranca;
import com.algaworks.cobranca.repository.Cobrancas;

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
	
}
