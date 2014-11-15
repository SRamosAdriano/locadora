package br.com.sramos.locadora.cliente;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.sramos.locadora.util.HibernateUtil;

public class ClienteDAO {

	private Session sessao;
	
	private Transaction transacao;
	
	public void salvar(Cliente cliente){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.save(cliente);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel inserir o cliente. Erro: " + e.getMessage());
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
	
	public void atualizar(Cliente Cliente){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.update(Cliente);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel alterar o cliente. Erro: " + e.getMessage());
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
	
	public void excluir(Cliente cliente){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.delete(cliente);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel excluir o cliente. Erro: " + e.getMessage());
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
	
	public Cliente buscarCategoria(Integer codigo){
		Cliente cliente = null;
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = this.sessao.createCriteria(Cliente.class);
			filtro.add(Restrictions.eq("cliente", codigo));
			cliente = (Cliente) filtro.uniqueResult();
			
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
		return cliente;
	}
	
	public List<Cliente> listar(){
		List<Cliente> clientes = null;
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = this.sessao.createCriteria(Cliente.class);
			clientes = filtro.list();
			
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
		return clientes;
	}
}
