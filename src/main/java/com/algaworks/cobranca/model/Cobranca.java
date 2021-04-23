package com.algaworks.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

// notation para dizer que essa classe é uma Entidade
// Entidades se tornam Tabelas no BD.
@Entity
public class Cobranca {

	// são mapeamentos para o banco de dados saber que é o ID.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

	// o dado será de tempo, porém apenas com dia, mes e ano.
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	// o dado será do tipo enumerado, apenas com Strings.
	@Enumerated(EnumType.STRING)
	private StatusCharge charge;

	// realiza formatação do dado.
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal value;
	private String description;

	// está gerando um ID com hash
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cobranca other = (Cobranca) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public StatusCharge getCharge() {
		return charge;
	}

	public void setCharge(StatusCharge charge) {
		this.charge = charge;
	}

}
