package br.com.sramos.locadora.endereco;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.sramos.locadora.endereco.Endereco;
import br.com.sramos.locadora.util.HibernateUtil;

public class EnderecoDAO {
	
	private Session sessao;
	
	private Transaction transacao;
	
	public void salvar(Endereco endereco){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.save(endereco);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel inserir o endereco. Erro: " + e.getMessage());
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar operação de inserção. Mensagem" + e2.getMessage());
			}
		}
	}
	
	public void atualizar(Endereco endereco){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.update(endereco);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel alterar o endereco. Erro: " + e.getMessage());
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar operação de atualização. Mensagem" + e2.getMessage());
			}
		}
	}
	
	public void excluir(Endereco endereco){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.delete(endereco);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel excluir o endereco. Erro: " + e.getMessage());
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar operação de exclusão. Mensagem" + e2.getMessage());
			}
		}
	}
	
	public Endereco buscarCategoria(Integer codigo){
		Endereco endereco = null;
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = this.sessao.createCriteria(Endereco.class);
			filtro.add(Restrictions.eq("endereco", codigo));
			endereco = (Endereco) filtro.uniqueResult();
			
			this.transacao.commit();
		} catch (Throwable e) {
			if(this.transacao.isActive()){
				this.transacao.rollback();
			}
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar operação de busca. Mensagem" + e2.getMessage());
			}
		}
		return endereco;
	}
	
	public List<Endereco> listar(){
		List<Endereco> enderecos = null;
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = this.sessao.createCriteria(Endereco.class);
			enderecos = filtro.list();
			
			this.transacao.commit();
		} catch (Throwable e) {
			if(this.transacao.isActive()){
				this.transacao.rollback();
			}
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar operação de listagem. Mensagem" + e2.getMessage());
			}
		}
		return enderecos;
	}

}
