package br.com.sramos.locadora.filme;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.sramos.locadora.filme.Filme;
import br.com.sramos.locadora.util.HibernateUtil;

public class FilmeDAO {

private Session sessao;
	
	private Transaction transacao;
	
	public void salvar(Filme filme){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.save(filme);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("N�o foi possivel inserir o filme. Erro: " + e.getMessage());
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar opera��o de inser��o. Mensagem" + e2.getMessage());
			}
		}
	}
	
	public void atualizar(Filme filme){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.update(filme);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("N�o foi possivel alterar o filme. Erro: " + e.getMessage());
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar opera��o de atualiza��o. Mensagem" + e2.getMessage());
			}
		}
	}
	
	public void excluir(Filme filme){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.delete(filme);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("N�o foi possivel excluir o filme. Erro: " + e.getMessage());
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar opera��o de exclus�o. Mensagem" + e2.getMessage());
			}
		}
	}
	
	public Filme buscarCategoria(Integer codigo){
		Filme filme = null;
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = this.sessao.createCriteria(Filme.class);
			filtro.add(Restrictions.eq("filme", codigo));
			filme = (Filme) filtro.uniqueResult();
			
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
				System.out.println("Erro ao fechar opera��o de busca. Mensagem" + e2.getMessage());
			}
		}
		return filme;
	}
	
	public List<Filme> listar(){
		List<Filme> filmes = null;
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = this.sessao.createCriteria(Filme.class);
			filmes = filtro.list();
			
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
				System.out.println("Erro ao fechar opera��o de listagem. Mensagem" + e2.getMessage());
			}
		}
		return filmes;
	}
}
