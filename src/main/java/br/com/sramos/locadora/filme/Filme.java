package br.com.sramos.locadora.filme;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sramos.locadora.categoria.Categoria;

@Entity
@Table(name="filme")
public class Filme implements Serializable {

	private static final long serialVersionUID = -4371075587146024678L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_filme")
	private Integer filme;
	
	@ManyToOne
	@JoinColumn(name="cod_categoria")
	private Categoria categoria;
	
	private String descricao;
	
	private Date ano;

	public Integer getFilme() {
		return filme;
	}

	public void setFilme(Integer filme) {
		this.filme = filme;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Filme [filme=" + filme + ", categoria=" + categoria
				+ ", descricao=" + descricao + ", ano=" + ano + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((filme == null) ? 0 : filme.hashCode());
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
		Filme other = (Filme) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (filme == null) {
			if (other.filme != null)
				return false;
		} else if (!filme.equals(other.filme))
			return false;
		return true;
	}
	
	

}
