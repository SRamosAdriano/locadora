package br.com.sramos.locadora.categoria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.sramos.locadora.util.HibernateUtil;

public class CategoriaDAO {
	
	private Session sessao;
	
	private Transaction transacao;
	
	public void salvar(Categoria categoria){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.save(categoria);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel inserir a categoria. Erro: " + e.getMessage());
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
	
	public void atualizar(Categoria categoria){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.update(categoria);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel alterar a categoria. Erro: " + e.getMessage());
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
	
	public void excluir(Categoria categoria){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.delete(categoria);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel excluir a categoria. Erro: " + e.getMessage());
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
	
	public Categoria buscarCategoria(Integer codigo){
		Categoria categoria = null;
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = this.sessao.createCriteria(Categoria.class);
			filtro.add(Restrictions.eq("categoria", codigo));
			categoria = (Categoria) filtro.uniqueResult();
			
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
		return categoria;
	}
	
	public List<Categoria> listar(){
		List<Categoria> categorias = null;
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = this.sessao.createCriteria(Categoria.class);
			categorias = filtro.list();
			
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
		return categorias;
	}
}
