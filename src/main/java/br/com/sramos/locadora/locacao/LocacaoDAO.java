package br.com.sramos.locadora.locacao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.sramos.locadora.util.HibernateUtil;

public class LocacaoDAO {

	private Session sessao;
	
	private Transaction transacao;
	
	public void salvar(Locacao locacao){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.save(locacao);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel inserir a locacao. Erro: " + e.getMessage());
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
	
	public void atualizar(Locacao locacao){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.update(locacao);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel alterar a locacao. Erro: " + e.getMessage());
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
	
	public void excluir(Locacao locacao){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.delete(locacao);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel excluir a locacao. Erro: " + e.getMessage());
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
	
	public Locacao buscarCategoria(Integer codigo){
		Locacao locacao = null;
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = this.sessao.createCriteria(Locacao.class);
			filtro.add(Restrictions.eq("locacao", codigo));
			locacao = (Locacao) filtro.uniqueResult();
			
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
		return locacao;
	}
	
	public List<Locacao> listar(){
		List<Locacao> locacoes = null;
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = this.sessao.createCriteria(Locacao.class);
			locacoes = filtro.list();
			
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
		return locacoes;
	}
	
}
