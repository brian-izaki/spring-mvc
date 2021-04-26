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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@NotBlank(message = "Descrição é obrigatória")
	@Size(max = 60, message = "A descrição não pode ter mais de 60 caracteres")
	private String description;
	
	// o dado será de tempo, porém apenas com dia, mes e ano.
	@NotNull(message = "A data é obrigatória")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	// realiza formatação do dado.
	@NotNull(message = "Valor é obrigatório")
	@DecimalMin(value = "0.01", message = "Valor não pode ser menor que R$ 0,01")
	@DecimalMax(value = "9999999999.99", message = "Valor não pode ser maior que R$ 9.999.999.999,99")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal value;

	// o dado será do tipo enumerado, apenas com Strings.
	@Enumerated(EnumType.STRING)
	private StatusCharge charge;

	

	
	public boolean isPendente() {
		return StatusCharge.PENDENTE.equals(this.charge);
	}
	
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
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
	
}
