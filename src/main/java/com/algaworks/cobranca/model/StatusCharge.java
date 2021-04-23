package com.algaworks.cobranca.model;

public enum StatusCharge {
	// são as "chave-valor" do enum. 
	// CHAVE("valor")
	PENDENTE("Pendente"), 
	RECEBIDO("Recebido");

	private String descricao;

	// construtor desse enum
	// Ele vai pegar os valores de descricao e atribuir para a variavel privada criada.
	StatusCharge(String descricao) {
		this.descricao = descricao;
	}
	
	// método que permite ver o valor armazenado no enum
	public String getDescricao() {
		return descricao;
	}
}
