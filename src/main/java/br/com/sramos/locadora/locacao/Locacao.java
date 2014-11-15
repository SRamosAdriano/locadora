package br.com.sramos.locadora.locacao;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sramos.locadora.cliente.Cliente;
import br.com.sramos.locadora.midia.Midia;

@Entity
@Table(name="locacao")
public class Locacao implements Serializable{

	private static final long serialVersionUID = -419231595124099320L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_locacao")
	private Integer locacao;
	
	@ManyToOne
	@JoinColumn(name="cod_cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="cod_midia")
	private Midia midia;
	
	@Column(name="data_emprestimo", updatable=false)
	private Date dataEmprestimo;
	
	@Column(name="hora_emprestimo", updatable=false)
	private Time horaEmprestimo;
	
	@Column(name="data_devolucao")	
	private Date dataDevolucao;
	
	@Column(name="obs")
	private String observacao;

	public Integer getLocacao() {
		return locacao;
	}

	public void setLocacao(Integer locacao) {
		this.locacao = locacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Time getHoraEmprestimo() {
		return horaEmprestimo;
	}

	public void setHoraEmprestimo(Time horaEmprestimo) {
		this.horaEmprestimo = horaEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "Locacao [locacao=" + locacao + ", cliente=" + cliente
				+ ", midia=" + midia + ", dataEmprestimo=" + dataEmprestimo
				+ ", horaEmprestimo=" + horaEmprestimo + ", dataDevolucao="
				+ dataDevolucao + ", observacao=" + observacao + "]";
	}


}
