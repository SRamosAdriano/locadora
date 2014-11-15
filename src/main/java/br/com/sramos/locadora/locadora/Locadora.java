package br.com.sramos.locadora.locadora;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import br.com.sramos.locadora.categoria.Categoria;
import br.com.sramos.locadora.categoria.CategoriaDAO;
import br.com.sramos.locadora.cliente.Cliente;
import br.com.sramos.locadora.cliente.ClienteDAO;
import br.com.sramos.locadora.endereco.Endereco;
import br.com.sramos.locadora.endereco.EnderecoDAO;
import br.com.sramos.locadora.filme.Filme;
import br.com.sramos.locadora.filme.FilmeDAO;
import br.com.sramos.locadora.locacao.Locacao;
import br.com.sramos.locadora.locacao.LocacaoDAO;
import br.com.sramos.locadora.midia.Midia;
import br.com.sramos.locadora.midia.MidiaDAO;
import br.com.sramos.locadora.util.HibernateUtil;

/*
 * Classe para teste do sistema locadora.
 */
public class Locadora {
	public static void main(String[] args) {
		HibernateUtil.getSessionFactory().openSession();
		Locadora locadora = new Locadora();
		locadora.cadastraCategorias();
		locadora.cadastraFilmes();
		locadora.cadastraMidias();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = new Endereco();
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();
		cliente.setCelular("(11) 1111-2222");
		cliente.setEmail("solaris@javapro.com.br");
		cliente.setNome("Fulano Solaris");
		cliente.setTelefone("(11) 3333-4444");
		cliente.setEndereco(endereco);
		endereco.setBairro("Centro");
		endereco.setCep("89000-000");
		endereco.setCidade("Santo André");
		endereco.setComplemento("Casa");
		endereco.setNumero(new Integer(1));
		endereco.setRua("Av. Principal");
		endereco.setUf("SP");
		endereco.setCliente(cliente);
		clienteDAO.salvar(cliente);
		enderecoDAO.salvar(endereco);

		LocacaoDAO locacaoDAO = new LocacaoDAO();
		Locacao locacao = new Locacao();
		locacao.setDataDevolucao(new Date(System.currentTimeMillis()));
		locacao.setDataEmprestimo(new Date(System.currentTimeMillis()));
		locacao.setObservacao("Devolução final de semana");
		locacao.setHoraEmprestimo(new Time(System.currentTimeMillis()));
		
		locacao.setCliente(cliente);
		
		MidiaDAO midiaDAO = new MidiaDAO();
		Midia midia = (Midia) midiaDAO.buscarCategoria(new Integer(1));
		locacao.setMidia(midia);
		locacaoDAO.salvar(locacao);
		System.out.println("Cadastros gerados com sucesso!");
		
	}
	
	public void cadastraCategorias(){
		//Criando as categorias dos filmes
		String categorias[] = {"Aventura", "Ação", "Comédia"};
		Categoria categoria = null;
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		for(int i = 0 ; i < 3 ; i++){
			categoria = new Categoria();
			categoria.setDescricao(categorias[i]);
			categoriaDAO.salvar(categoria);
		}
	}
	
	public void cadastraFilmes(){
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		String filmesDescricao[] = {"Senhor dos Anéis", "Transformers", "Ghostbusters"};
		//Aqui subtraimos o ano de lançamento correto do filme de 1990, para gravarmos o ano correto no banco.
		Date filmesAnoProducao[] = {new Date(2001-1900, 11, 19), new Date(2007-1900, 6, 20), new Date(1985-1900, 1, 1)};
		FilmeDAO filmeDAO = new FilmeDAO();
		Filme filme = null;
		for(int i = 0 ; i < 3 ; i++){
			filme = new Filme();
			filme.setDescricao(filmesDescricao[i]);
			filme.setAno(filmesAnoProducao[i]);
			filme.setCategoria(categoriaDAO.buscarCategoria(i+1));
			filmeDAO.salvar(filme);
		}
	}
	
	public void cadastraMidias(){
		Midia midia = null;
		Filme filme = null;
		MidiaDAO midiaDAO = new MidiaDAO();
		FilmeDAO filmeDAO = new FilmeDAO();
		List<Filme> resultado = filmeDAO.listar();
		for(int i = 0 ; i < 3 ; i++){
			midia = new Midia();
			filme = (Filme) resultado.get(i);
			midia.setFilme(filme);
			midia.setInutilizada("N");
			midiaDAO.salvar(midia);
		}
	}

}
