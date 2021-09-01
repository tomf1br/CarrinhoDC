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

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert" })
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

}
