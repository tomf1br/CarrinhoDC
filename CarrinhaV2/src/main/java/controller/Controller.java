package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	JavaBeans javabeans = new JavaBeans();
	DAO dao = new DAO();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		dao.testarConexao();
		// encaminhamento das requisicoes
		String action = request.getServletPath(); // armazena a requisicao atual
		System.out.println("Requisição: " + action); // apoio ao entendimento e verificacao de erros
		if (action.equals("/main")) {
			produtos(request, response);
		} else if (action.equals("/insert")) {
			novoProduto(request, response);
		} else if (action.equals("/select")) {
			listarProduto(request, response);
		} else if (action.equals("/update")) {
			editarProduto(request, response);
		} else if (action.equals("/delete")) {
			removerProduto(request, response);
		}
	}

	protected void novoProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		javabeans.setProduto(request.getParameter("produto"));
		javabeans.setQuantidade(request.getParameter("quantidade"));
		javabeans.setValor(request.getParameter("valor"));

		// executar o metodo inserirContato (DAO) passando javabeans
		dao.inserirProduto(javabeans); // Passo 6 do slide 21

		// redirecionar para a pagina agenda.jsp (passo 10 do slide 21)
		response.sendRedirect("main");

	}

	protected void produtos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// executar o metodo listarContatos() DAO - Passo 2 - Slide 22
		// O objeto lista é um vetor que recebe o retorno do metodo listarContatos()
		// lista - Passo 6 do slide 22
		ArrayList<JavaBeans> lista = dao.listarProdutos();
		// teste de recebimento
		/*
		 * for (int i = 0; i < lista.size(); i++) {
		 * System.out.println(lista.get(i).getId());
		 * System.out.println(lista.get(i).getProduto());
		 * System.out.println(lista.get(i).getQuantidade());
		 * System.out.println(lista.get(i).getValor()); }
		 */

		// Despachar a lista de contatos(vetor) para o documento agenda.jsp - Passo 7 -
		// slide 22
		request.setAttribute("produto", lista);
		RequestDispatcher rd = request.getRequestDispatcher("carrinho.jsp");
		rd.forward(request, response);
	}

	protected void listarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Passo 1 do slide 23
		String id = request.getParameter("id");
		// teste de recebimento do parametro
		System.out.println(id);
		// Setar a variavel idcon (JavaBeans) - Passo 2 do slide 23
		javabeans.setId(id);
		// Executar o metodo seleconarContato (DAO) - Passo 3 do slide 23
		dao.selecionarProduto(javabeans);
		// teste de recebimento
		// System.out.println(javabeans.getIdcon());
		// System.out.println(javabeans.getNome());
		// System.out.println(javabeans.getFone());
		// System.out.println(javabeans.getEmail());

		// Passo 10 - slide 23 "Despachar" os dados das variaveis JavaBeans para
		// editar.jsp
		request.setAttribute("id", javabeans.getId());
		request.setAttribute("produto", javabeans.getProduto());
		request.setAttribute("quantidade", javabeans.getQuantidade());
		request.setAttribute("valor", javabeans.getValor());
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	// Passo 2 - Editar o contato
	protected void editarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Passos 13 e 14 do slide 23 (receber os dados do formulario e setar JavaBeans)
		javabeans.setId(request.getParameter("id"));
		javabeans.setProduto(request.getParameter("produto"));
		javabeans.setQuantidade(request.getParameter("quantidade"));
		javabeans.setValor(request.getParameter("valor"));
		// Passo 15 - slide 23 (executar o metodo alterarContato)
		dao.alterarProduto(javabeans);
		// Passo 19 -redirecionar para o agenda.jsp atualizando a lista
		response.sendRedirect("main");
	}

	// Remover Produtp
	protected void removerProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Passo 3 do slide 24 (setar o idcon em JavaBeans)
		javabeans.setId(request.getParameter("id"));
		// Passo 4 slide 24 - executar o metodo deletarContato passando Idcon
		dao.deletarProduto(javabeans);
		// passo 8 do slide 24
		response.sendRedirect("main");
	}

}
